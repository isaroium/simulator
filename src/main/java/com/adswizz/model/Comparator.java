package com.adswizz.model;

/**
 * @author mihaiisaroiu
 */
public class Comparator implements java.util.Comparator<Electable> {
    int n;

    public static final Comparator provide(int pricePerSecondPriority) {
        Comparator toRet = new Comparator();
        toRet.n = pricePerSecondPriority < 0 ? 0 : pricePerSecondPriority > 100 ? 100 : pricePerSecondPriority;
        return toRet;
    }


    public int compare(Electable o1, Electable o2) {
        return Double.compare(yieldFunction(o2), yieldFunction(o1));
    }

    private double yieldFunction(Electable o) {
        return (n * ((1.0 * o.price) / o.duration) + (100 - n) * o.getAllocation());
    }
}
