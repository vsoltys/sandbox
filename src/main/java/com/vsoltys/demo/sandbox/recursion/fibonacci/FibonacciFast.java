package com.vsoltys.demo.sandbox.recursion.fibonacci;

public class FibonacciFast {
    public static void main(String[] args) {

        final int n = 40;
        final int result = fibonacci(n);

        System.out.println(result);
    }

    // O(n)
    private static int fibonacci(int n) {
        if (n < 2) return 1;

        // a & b are min (base) cases
        int a = 1;
        int b = 0;
        int c = a + b;

        // move 1 level up in binary tree
        for (int i = 2; i < n; i++) {
            b = a;
            a = c;
            c = a + b;
        }

        return c;
    }
}
