package com.vsoltys.demo.sandbox.recursion.magicindex;

public class MagicIndexFast {

    // magic index: i, a[i] == i, -1 if not found -> a[5] == 5
    public static void main(String[] args) {

        // on sorted array with distinct values
        int[] a = {-5, -3, -2, 1, 3, 5, 6, 6, 8, 9, 11, 23, 24, 27, 33};
        System.out.println(magicIndex(a));
    }

    // apply half-and-half, like binary search (on sorted array only)
    private static int magicIndex(int[] a) {
        return magicIndex(a, 0, a.length - 1);
    }

    // O(lg n)
    private static int magicIndex(int[] a, int start, int end) {
        if (start > end) return -1;

        int middle = (start + end) / 2;
        if (a[middle] == middle) {
            return middle;
        }

        if (a[middle] > middle) {
            return magicIndex(a, start, middle - 1);
        } else {
            return magicIndex(a, middle + 1, end);
        }
    }
}
