package com.adswizz.filter;

import com.adswizz.model.Context;

import java.util.function.Predicate;

/**
 * @author mihaiisaroiu
 */
public class DeliveryPredicate implements Predicate<Context> {


    /**
     * Evaluates this predicate on the given argument.
     *
     * @param  the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    public boolean test(Context context) {
        return context.getElectable().impressions < context.getElectable().objective;
    }
}
