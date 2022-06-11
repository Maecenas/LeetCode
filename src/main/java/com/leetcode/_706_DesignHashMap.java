package com.leetcode;

/*
https://leetcode.com/problems/design-hashmap/
Easy. Hash Table, Design.

Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

put(key, value) : Insert a (key, value) pair into the HashMap.
If the value already exists in the HashMap, update the value.
get(key): Returns the value to which the specified key is mapped,
or -1 if this map contains no mapping for the key.
remove(key) : Remove the mapping for the value key if this map contains
the mapping for the key.

Example:

MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);
hashMap.put(2, 2);
hashMap.get(1);       // returns 1
hashMap.get(3);       // returns -1 (not found)
hashMap.put(2, 1);    // update the existing value
hashMap.get(2);       // returns 1
hashMap.remove(2);    // remove the mapping for 2
hashMap.get(2);       // returns -1 (not found)

Note:

All keys and values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashMap library.

Your MyHashMap object will be instantiated and called as such:

MyHashMap obj = new MyHashMap();
obj.put(key,value);
int param_2 = obj.get(key);
obj.remove(key);
*/

/**
 * @see _705_DesignHashSet
 */
class _706_DesignHashMap {

    static class MyHashMap {

        private static class Node {
            final int key;
            int val;
            Node next;

            Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        private static final int R = 10000;  // number of buckets
        private final Node[] nodes;

        /**
         * Initialize your data structure here.
         */
        public MyHashMap() {
            nodes = new Node[R];
        }

        /**
         * value will always be non-negative.
         */
        public void put(int key, int value) {
            int i = hash(key);
            if (nodes[i] == null) {
                nodes[i] = new Node(-1, -1);
            }
            Node prev = find(nodes[i], key);
            if (prev.next == null) {
                prev.next = new Node(key, value);
            } else {
                prev.next.val = value;
            }
        }

        /**
         * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
         */
        public int get(int key) {
            int i = hash(key);
            if (nodes[i] == null) {
                return -1;
            }
            Node node = find(nodes[i], key);
            return node.next == null ? -1 : node.next.val;
        }

        /**
         * Removes the mapping of the specified value key if this map contains a mapping for the key
         */
        public void remove(int key) {
            int i = hash(key);
            if (nodes[i] == null) return;
            Node prev = find(nodes[i], key);
            if (prev.next == null) return;
            prev.next = prev.next.next;
        }

        private int hash(int key) {
            return Integer.hashCode(key) % R;
        }

        Node find(Node bucket, int key) {
            Node node = bucket, prev = null;
            while (node != null && node.key != key) {
                prev = node;
                node = node.next;
            }
            return prev;
        }
    }
}
