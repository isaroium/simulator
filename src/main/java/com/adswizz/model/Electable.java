package com.adswizz.model;

public class Electable {
    public int price = 0;
    public int impressions = 0;
    public int objective = 10;
    public long duration = 1;
    public double deliveryRate = 1.0;
    public int limitationRestriction;
    public double allocation;
    public int req = 0;
    public double pacing;


    public Electable(int price, int objective, int limitationRestriction, double allocation, long duration) {
        this(price, objective, limitationRestriction, allocation, duration, 1.0);

    }

    public Electable(int price, int objective, int limitationRestriction, double allocation, long duration, double pacing) {
        this.price = price;
        this.objective = objective;
        this.limitationRestriction = limitationRestriction > 10 ? 10 : limitationRestriction < 0 ? 1 : limitationRestriction;
        this.duration = duration;
        this.allocation = allocation;
        this.pacing = pacing;
    }


    public Electable(int price, int objective, int limitationRestriction, double allocation) {
        this(price, objective, limitationRestriction, allocation, 1);
    }

    public Double getAllocation() {
        return 1.0 * (objective - impressions) / req;
    }


    public void played() {
        impressions++;
    }


    @Override
    public String toString() {
        return "Campaign{" +
                "deliveryRate=" + deliveryRate +
                ", price=" + price +
                ", impressions=" + impressions +
                ", objective=" + objective +
                ", duration=" + duration +
                ", limitationRestriction=" + limitationRestriction +
                ", allocation=" + getAllocation() +

                '}';
    }
}
