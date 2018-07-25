package com.vsoltys.demo.sandbox.structures;

public class LinkedList<T> {

    private Node<T> head;

    public void add(final T value) {
        final Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
            return;
        }

        Node<T> lastNode = this.head;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        lastNode.next = node;
    }

    public T remove(final T value) {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        Node<T> current = this.head;
        if (current.value.equals(value)) {
            head = head.next;
            return current.value;
        }

        T result = null;
        while (current.next != null) {
            if (current.next.value.equals(value)) {
                result = current.next.value;
                current.next = current.next.next;
                break;
            }
            current = current.next;
        }
        return result;
    }

    public void addAfter(final T after, final T value) {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        final Node<T> node = new Node<>(value);

        Node<T> current = this.head;
        if (current.value.equals(after)) {
            final Node<T> next = current.next;
            current.next = node;
            node.next = next;
            return;
        }

        while (current.next != null) {
            if (current.next.value.equals(after)) {
                final Node<T> next = current.next.next;
                current.next.next = node;
                node.next = next;
                break;
            }
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    private class Node<R> {
        private R value;
        private Node<R> next;

        private Node(final R value) {
            this.value = value;
        }
    }

    public static void main(final String... args) {
        final LinkedList<Object> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        list.addAfter("b", "after b");

        System.out.println(list.remove("b"));
        System.out.println(list.remove("d"));
        System.out.println(list.remove("c"));
    }
}


