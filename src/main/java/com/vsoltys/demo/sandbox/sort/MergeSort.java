package com.vsoltys.demo.sandbox.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(final String... args) {
        final String[] items = {"s", "o", "r", "t", "e", "x", "a", "m", "p", "l", "e"};
        System.out.println(Arrays.asList(items));

        sort(items);
        System.out.println(Arrays.asList(items));
    }

    public static void sort(final Comparable[] a) {

        Comparable[] aux = new Comparable[a.length];
        for (int k = 0; k < a.length; k++) {
            aux[k] = a[k];
        }

        sort(aux, a, 0, a.length - 1);
    }

    private static void sort(final Comparable[] a, final Comparable[] aux, final int lo, final int hi) {
        if (hi <= lo) return;

        //TODO: add cut-off for small arrays, i.e. < 7 items long (use insertion sort for that) - mergesort is too heavy on small arrays
//        if (hi - lo < 7) {
//            insertionSort(a, lo, hi);
//            return;
//        }

        final int mid = lo + (hi - lo) / 2;

        sort(aux, a, lo, mid);
        sort(aux, a, mid + 1, hi);

        // if biggest left is <= smallest right element -> no need to merge, already sorted
        if (aux[mid].compareTo(aux[mid + 1]) < 1) {
            return;
        }
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(final Comparable[] a, final Comparable[] aux, final int lo, final int mid, final int hi) {
        //assert isSorted(a, lo, mid);
        //assert isSorted(a, mid + 1, hi);

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {

            if (i > mid) {
                aux[k] = a[j++];
            } else if (j > hi) {
                aux[k] = a[i++];
            } else if (a[i].compareTo(a[j]) < 0) {
                aux[k] = a[i++];
            } else {
                aux[k] = a[j++];
            }
        }

        //assert isSorted(a, lo, hi);
    }
}
