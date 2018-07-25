package com.vsoltys.demo.sandbox.recursion.hanoi;

import java.util.Stack;

public class Towers {

    public static void main(final String[] args) {


    }

    private class Tower {
        private Stack<String> disks;
        private int index;

        private Tower(final Stack<String> disks) {
            this.disks = disks;
        }
    }
}
