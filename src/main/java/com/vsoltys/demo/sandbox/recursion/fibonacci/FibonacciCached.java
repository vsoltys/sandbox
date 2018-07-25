package com.vsoltys.demo.sandbox.recursion.fibonacci;

public class FibonacciCached {
    public static void main(String[] args) {

        final int n = 40;
        final int result = fibonacci(n, new int[n + 1]);

        System.out.println(result);
    }

    // O(2^n)
    private static int fibonacci(int n, int mem[]) {
        if (n < 2) return n;

        if (mem[n] == 0) {
            mem[n] = fibonacci(n - 1, mem) + fibonacci(n - 2, mem);
        }
        return mem[n];
    }
}
