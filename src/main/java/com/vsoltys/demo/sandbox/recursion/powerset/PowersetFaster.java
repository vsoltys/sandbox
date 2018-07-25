package com.vsoltys.demo.sandbox.recursion.powerset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowersetFaster {

    public static void main(final String... args) {
        final List<String> items = Arrays.asList("a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9");
        
        final List<List<String>> powerSet = getPowerset(items);
        powerSet.forEach(System.out::println);
    }

    // with combinatorics, will save a bit of space on avoiding recursive calls stack
    // each item in set could be either in (1) or out (0) of subset. we can describe this with bit string or binary digit
    private static List<List<String>> getPowerset(final List<String> items) {
        final List<List<String>> combinations = new ArrayList<>();

        int max = 1 << items.size(); // 2^n
        for (int combination = 0; combination < max; combination++) {
            combinations.add(transform(combination, items));
        }
        return combinations;
    }

    // consider int overflow boundaries 2^32-1
    // using bit array (combination) copy values to result list
    private static List<String> transform(int combination, final List<String> items) {
        final List<String> result = new ArrayList<>();

        for (String item : items) {
            if ((combination & 1) == 1) {
                result.add(item);
            }
            combination >>= 1;
        }
        return result;
    }
}
