package com.adswizz.model;

import com.adswizz.Config;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mihaiisaroiu
 */
public class Delivery {
    final List<Electable> electableList;
    //public int availInventory;
    Predicate<Context> composed;
    private Comparator<Electable> comparator;

    public Delivery(Comparator comparator, Predicate<Context>... predicates) {
        this.comparator = comparator;
        this.composed = context -> context != null;

        if (predicates.length > 0) {
            Stream.of(predicates).forEach(other -> composed = composed.and(other));
        }
        electableList = new LinkedList<>();
    }

    public void addCampaign(Electable c) {
        electableList.add(c);
    }

    public void addCampaigns(List<Electable> cs) {
        electableList.addAll(cs);
    }

    public void reset() {
        electableList.forEach(campaign -> {
            campaign.impressions = 0;
            campaign.req = 0;
        });
    }


    public List<Electable> fill(Request request) {
        List<Electable> selected = electableList.stream().
                peek(a -> {
                    if (Config.dumpLines) System.out.println("available:" + a);
                    a.req++;
                }).
                map(el -> Context.of(el, request)).
                filter(composed).
                peek(a -> {
                    if (Config.dumpLines) System.out.println(a);
                }).
                map(Context::getElectable).
                sorted(comparator).
                peek(a -> {
                    if (Config.dumpLines) System.out.println("sorted: " + a);
                }).
                collect(ArrayList::new,
                        (campaigns, campaign) -> {
                            if (campaigns.stream().mapToLong(c -> c.duration).sum() + campaign.duration <= request.size)
                                campaigns.add(campaign);
                        },
                        new BiConsumer<List<Electable>, List<Electable>>() {
                            @Override
                            public void accept(List<Electable> campaigns, List<Electable> campaigns2) {
                                Stream.concat(campaigns.stream(), campaigns2.stream()).collect(Collectors.toList());
                            }
                        }
                );
        selected.forEach(Electable::played);
        return selected;
    }
}
