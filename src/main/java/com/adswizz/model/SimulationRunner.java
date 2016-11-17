package com.adswizz.model;

import com.adswizz.filter.DeliveryLimitationPredicate;
import com.adswizz.filter.DeliveryPredicate;
import com.adswizz.filter.DurationPredicate;
import com.adswizz.filter.PacingPredicate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author mihaiisaroiu
 */
public class SimulationRunner {
    /**
     * Generate 100 campaigns
     * Generate 100000 requests
     * find best fill factor
     **/

    public static void main(String... args) throws FileNotFoundException {
        int minPrice = 1;
        int maxPrice = 10;
        int objective = 1000;

        long duration[] = new long[3];
        duration[0] = 10;
        duration[1] = 20;
        duration[2] = 15;
        /**
         *
         * ad durations.
         *
         * */


        List<Electable> electableList = IntStream.range(0, 100).mapToObj(i -> new Electable(ThreadLocalRandom.current().nextInt(minPrice, maxPrice + 1), objective, ThreadLocalRandom.current().nextInt(1, 10),
                1.0, duration[ThreadLocalRandom.current().nextInt(0, 3)])).collect(Collectors.toList());
        PrintStream out = new PrintStream(new FileOutputStream(new File("out.txt")));//System.out;
        Delivery d = new Delivery(Comparator.provide(100),
                new DurationPredicate(), new DeliveryPredicate(), new PacingPredicate(), new DeliveryLimitationPredicate());
        d.addCampaigns(electableList);
        int zr = 0;
        while (zr < 50) {
            Request req = new Request(30, (int) (ThreadLocalRandom.current().nextGaussian() * 1) + 5);
            List<Electable> theList = d.fill(req);
            long revenue = theList.stream().mapToLong(electable -> electable.price).sum();
            out.println(revenue);
            zr += revenue == 0 ? 1 : 0;
            out.flush();
        }
    }
}
