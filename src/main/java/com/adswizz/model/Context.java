package com.adswizz.model;

/**
 * @author mihaiisaroiu
 */
public class Context {
    private Electable electable;
    private Request request;
    private int availInventory=1;

    public Context(Electable electable, Request request, int availInventory) {
        this.electable = electable;
        this.request = request;
        this.availInventory = availInventory;
    }

    public static Context of(Electable electable, Request request) {
        return new Context(electable, request, 1);
    }

    public Electable getElectable() {
      //  electable.deliveryRate = getUnderdeliveryRate(electable);
        return electable;

    }

    public Request getRequest() {
        return request;
    }

    public double getUnderdeliveryRate(Electable c) {
     //   System.out.println(availInventory);
        return 1.0*(c.objective - c.impressions) / availInventory;
    }

    @Override
    public String toString() {
        return "Context{" +
                "campaign=" + electable +
                ", request=" + request +
                '}';
    }
}
