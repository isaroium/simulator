package com.adswizz.filter;

import com.adswizz.model.Context;

import java.util.function.Predicate;

/**
 * @author mihaiisaroiu
 */
public class DurationPredicate implements Predicate<Context> {
    /**
     * Evaluates this predicate on the given argument.
     *
     * @param context the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    public boolean test(Context context) {

        return context.getElectable().duration < context.getRequest().size;
    }
}
