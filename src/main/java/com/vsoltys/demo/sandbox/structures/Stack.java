package com.vsoltys.demo.sandbox.structures;

public class Stack<T> {
    private Node<T> top;

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        final T value = top.value;
        top = top.next;

        return value;
    }

    public void push(T value) {
        final Node<T> node = new Node<>(value);

        if (top == null) {
            top = node;
        } else {
            node.next = top;
            top = node;
        }
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return top.value;
    }

    public boolean isEmpty() {
        return top == null;
    }

    private class Node<T> {
        private T value;
        private Node<T> next;

        private Node(final T value) {
            this.value = value;
        }
    }

    public static void main(final String... args) {
        final Stack<String> stack = new Stack<>();

        stack.push("c");
        stack.push("b");
        stack.push("a");

        System.out.println(stack.peek());

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
