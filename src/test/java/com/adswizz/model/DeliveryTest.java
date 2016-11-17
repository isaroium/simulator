package com.adswizz.model;

import com.adswizz.filter.DeliveryPredicate;
import com.adswizz.filter.DurationPredicate;
import com.adswizz.filter.PacingPredicate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author mihaiisaroiu
 */
public class DeliveryTest {

    @Test
    public void testBasicFunctionality() {
        Delivery d = new Delivery(Comparator.provide(100));
        d.addCampaign(new Electable(4, 10, 1, 1, 10));
        d.addCampaign(new Electable(5, 10, 2, 1, 10));
        d.addCampaign(new Electable(6, 10, 3, 1, 10));
        d.addCampaign(new Electable(7, 10, 4, 1, 10));
        d.addCampaign(new Electable(8, 10, 5, 1, 20));

        assertEquals(18, d.fill(Request.of(30, 10)).stream().mapToInt(c -> c.price).sum());
    }

    @Test
    public void testBasicFunctionality2() {
        Delivery d = new Delivery(Comparator.provide(100), new DurationPredicate(), new DeliveryPredicate(),new PacingPredicate());
        d.addCampaign(new Electable(4, 10, 1, 1, 10));
        d.addCampaign(new Electable(5, 10, 2, 1, 10));
        d.addCampaign(new Electable(6, 10, 3, 1, 10));
        d.addCampaign(new Electable(7, 10, 4, 1, 10));
        d.addCampaign(new Electable(8, 10, 5, 1, 20));

        assertEquals(13, d.fill(Request.of(20, 10)).stream().mapToInt(c -> c.price).sum());
    }

    @Test
    public void testBasicFunctionality3() {
        Delivery d = new Delivery(Comparator.provide(50));
        d.addCampaign(new Electable(4, 10, 1, 1, 10));
        d.addCampaign(new Electable(5, 10, 2, 1, 10));
        d.addCampaign(new Electable(6, 10, 3, 1, 10));
        d.addCampaign(new Electable(7, 10, 4, 1, 10));
        d.addCampaign(new Electable(8, 10, 5, 1, 20));

    }


}