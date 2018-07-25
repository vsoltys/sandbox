package com.vsoltys.demo.sandbox.recursion.magicindex;

public class MagicIndex {

    // magic index: i, a[i] == i, -1 if not found -> a[5] == 5
    public static void main(String[] args) {

        // on sorted array with distinct values
        int[] b = {-5, -3, 1, 2, 3, 5, 11, 23, 24, 27, 33};
        System.out.println(magicIndex(b));
    }

    // O(n)
    private static int magicIndex(int[] a) {
        if (a == null || a.length == 0) return -1;

        for (int i = 0; i < a.length; i++) {
            if (a[i] == i) return i;
        }
        return -1;
    }
}
