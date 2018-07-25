package com.vsoltys.demo.sandbox.recursion.fibonacci;

public class Fibonacci {
    public static void main(String[] args) {

        final int n = 40;
        final int result = fibonacci(n);

        System.out.println(result);
    }

    // O(2^n)
    private static int fibonacci(int n) {
        if (n < 2) return n;

        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
