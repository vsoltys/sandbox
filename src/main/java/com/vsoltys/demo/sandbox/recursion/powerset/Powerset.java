package com.vsoltys.demo.sandbox.recursion.powerset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Powerset {

    public static void main(final String... args) {
        final List<String> items = Arrays.asList("a1", "a2", "a3");

        final List<List<String>> powerSet = getPowerset(items);

        powerSet.forEach(System.out::print);
    }

    private static List<List<String>> getPowerset(final List<String> items) {
        return getPowerset(items, 0);
    }

    private static List<List<String>> getPowerset(final List<String> items, int index) {
        final List<List<String>> allSubsets;

        if (items.size() == index) {
            allSubsets = new ArrayList<>();
            allSubsets.add(new ArrayList<>());
        } else {
            allSubsets = getPowerset(items, index + 1);

            final List<List<String>> moreSubsets = new ArrayList<>();
            for (final List<String> subset : allSubsets) {
                final List<String> newSubset = new ArrayList<>(subset);
                newSubset.add(items.get(index));
                moreSubsets.add(newSubset);
            }
            allSubsets.addAll(moreSubsets);
        }
        return allSubsets;
    }
}
