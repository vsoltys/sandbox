package com.vsoltys.demo.sandbox.recursion.magicindex;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MagicIndexFastNotDistinct {

    // magic index: i, a[i] == i, -1 if not found -> a[5] == 5
    public static void main(String[] args) {

        // on sorted array with not distinct values
        int[] b = {-5, -3, -2, 3, 3, 4, 5, 5, 8, 8, 11, 23, 24, 27, 33};
        System.out.println(magicIndex(b));
    }

    private static int magicIndex(int[] a) {
        return magicIndex(a, 0, a.length - 1);
    }

    // O(lg 2n)
    private static int magicIndex(int[] a, int start, int end) {
        if (start > end) return -1;

        int middle = (start + end) / 2;
        if (a[middle] == middle) {
            return middle;
        }

        final int leftIndex = magicIndex(a, start, min(a[middle], middle - 1));
        if (leftIndex >= 0) {
            return leftIndex;
        } else {
            return magicIndex(a, max(a[middle], middle + 1), end);
        }
    }
}
