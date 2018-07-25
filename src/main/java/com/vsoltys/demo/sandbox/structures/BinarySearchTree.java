package com.vsoltys.demo.sandbox.structures;

public class BinarySearchTree<K extends Comparable<K>, V> {

    private Node root;

    public V get(final K key) {
        return get(root, key);
    }

    public void put(final K key, final V value) {
        root = put(root, key, value);
    }

    public void delete(final K key) {
        delete(root, key);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    //TODO: return Iterable<K> after implementing Iterable for Queue
    public Queue<K> keys() {
        final Queue<K> queue = new Queue<>();
        traverseInOrder(root, queue);
        return queue;
    }

    public V ceiling(final K key) {
        return ceiling(root, key);
    }

    public V floor(final K key) {
        return floor(root, key);
    }

    public Node min() {
        return min(root);
    }

    public Node max() {
        return max(root);
    }

    public int rank(final K key) {
        return rank(root, key);
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    private V get(final Node node, final K key) {
        if (node == null) {
            return null;
        }

        final V value;

        final int compared = node.key.compareTo(key);
        if (compared < 0) {
            value = get(node.left, key);
        } else if (compared > 0) {
            value = get(node.right, key);
        } else {
            value = node.value;
        }
        return value;
    }


    // rewrite node value on key match
    private Node put(final Node node, final K key, final V value) {
        if (node == null) {
            return new Node(key, value);
        }

        final int compared = node.key.compareTo(key);
        if (compared < 0) {
            node.left = put(node.left, key, value);
        } else if (compared > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    // Hibbard deletion (when node has two children):
    // find next smallest to to-be-deleted current node and "move" it from old position to current node position
    private Node delete(Node node, final K key) {
        if (node == null) {
            return null;
        }

        final int compared = node.key.compareTo(key);
        if (compared < 0) {
            node.left = delete(node.left, key);
        } else if (compared > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                //remember values of current node, it will be deleted
                Node tempNode = node;
                //find prev. min (in right part), it will replace current node
                node = min(node.right);
                //delete that prev. min node (just found) from old place
                node.right = deleteMin(node.right);
                //add current node's left subtree
                node.left = tempNode.left;
            }
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    private Node deleteMin(final Node node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            return node.right;
        }

        node.left = deleteMin(node.left);
        node.count = 1 + size(node.left) + size(node.right);

        return node;
    }

    private Node deleteMax(final Node node) {
        if (node == null) {
            return null;
        }

        if (node.right == null) {
            return node.left;
        }

        node.right = deleteMax(node.right);
        node.count = 1 + size(node.left) + size(node.right);

        return node;
    }

    // move right, but check if left.right is closer to the key
    private V ceiling(final Node node, final K key) {
        if (node == null) {
            return null;
        }

        int compared = node.key.compareTo(key);
        if (compared == 0) {
            return node.value;
        }

        if (compared < 0) {
            final V ceiling = ceiling(node.left, key);
            return ceiling != null ? ceiling : node.value;
        } else {
            return ceiling(node.right, key);
        }
    }

    // move left, but check if right.left is any closer to the key
    private V floor(final Node node, final K key) {
        if (node == null) {
            return null;
        }

        int compared = node.key.compareTo(key);
        if (compared == 0) {
            return node.value;
        }

        if (compared < 0) {
            return floor(node.left, key);
        } else {
            final V floor = floor(node.right, key);
            return floor != null ? floor : node.value;
        }
    }

    private Node min(final Node node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    private Node max(final Node node) {
        if (node == null) {
            return null;
        }

        if (node.right == null) {
            return node;
        } else {
            return max(node.right);
        }
    }

    // number of nodes < key
    private int rank(final Node node, final K key) {
        if (node == null) {
            return 0;
        }

        int compared = node.key.compareTo(key);
        if (compared < 0) {
            return rank(node.left, key);
        } else if (compared > 0) {
            return 1 + size(node.left) + rank(node.right, key);
        } else {
            return size(node.left);
        }
    }

    private int size(final Node node) {
        if (node == null) {
            return 0;
        }
        return node.count;
    }

    private void traverseInOrder(final Node node, final Queue<K> queue) {
        if (node == null) {
            return;
        }

        traverseInOrder(node.left, queue);
        queue.push(node.key);
        traverseInOrder(node.right, queue);
    }

    private class Node {
        private Node left, right;

        private K key;
        private V value;
        private int count = 1;

        private Node(final K key, final V value) {
            this.key = key;
            this.value = value;
        }
    }
}

