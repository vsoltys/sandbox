package com.vsoltys.demo.sandbox.structures;

public class Queue<T> {

    private Node<T> head, tail;

    public void push(final T value) {
        final Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
            tail = head;
            return;
        }

        tail.next = node;
        tail = tail.next;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        final Node<T> node = head;
        head = head.next;

        return node.value;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private class Node<V> {
        private V value;
        private Node<V> next;

        private Node(final V value) {
            this.value = value;
        }
    }

    public static void main(final String... args) {
        final Queue<String> queue = new Queue<>();

        queue.push("a");
        queue.push("b");
        queue.push("c");

        while (!queue.isEmpty()) {
            System.out.println(queue.pop());
        }
    }
}
