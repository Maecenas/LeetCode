package com.leetcode;

/*
https://leetcode.com/problems/insert-delete-getrandom-o1/
Medium. Array, Hash Table, Design.

Implement the RandomizedSet class:

 bool insert(int val) - Inserts an item val into the set if not present.
   Returns true if the item was not present, false otherwise.
 bool remove(int val) - Removes an item val from the set if present.
   Returns true if the item was present, false otherwise.
 int getRandom() - Returns a random element from the current set of elements
   (it's guaranteed that at least one element exists when this method is called).
   Each element must have the same probability of being returned.

 Follow up: Could you implement the functions of the class with each function
   works in average O(1) time?

Example 1:

 Input
  ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
  [[], [1], [2], [2], [], [1], [2], []]
 Output
  [null, true, false, true, 2, true, false, 2]
 Explanation
  RandomizedSet randomizedSet = new RandomizedSet();
  randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
  randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
  randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
  randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
  randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
  randomizedSet.insert(2); // 2 was already in the set, so return false.
  randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.

Constraints:
 -2^31 <= val <= 2^31 - 1
 At most 105 calls will be made to insert, remove, and getRandom.
 There will be at least one element in the data structure
  when getRandom is called.

Your RandomizedSet object will be instantiated and called as such:
 RandomizedSet obj = new RandomizedSet();
 boolean param_1 = obj.insert(val);
 boolean param_2 = obj.remove(val);
 int param_3 = obj.getRandom();
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @see _381_InsertDeleteGetRandomO1DuplicatesAllowed
 */
class _380_InsertDeleteGetRandomO1 {

    static class RandomizedSet {

        final Map<Integer, Integer> map;  // (val, index)
        final List<Integer> list;
        final Random rand;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            this.map = new HashMap<>();
            this.list = new ArrayList<>();
            this.rand = ThreadLocalRandom.current();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (map.containsKey(val)) return false;

            map.put(val, list.size());
            list.add(val);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;

            int index = map.remove(val), last = list.size() - 1;
            if (index < last) {
                map.put(list.get(last), index);
                swap(list, index, last);
            }

            list.remove(last);
            return true;
        }

        private static void swap(List<Integer> list, int i, int j) {
            int tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }
    }

    static class RandomizedSet2 {

        final LinkedHashSet<Integer> set;
        final ThreadLocalRandom rand;

        public RandomizedSet2() {
            this.set = new LinkedHashSet<>();
            this.rand = ThreadLocalRandom.current();
        }

        public boolean insert(int val) {
            return set.add(val);
        }

        public boolean remove(int val) {
            return set.remove(val);
        }

        /**
         * O(n)
         */
        public int getRandom() {
            int n = rand.nextInt(set.size());
            for (int val : set) {
                if (n == 0) return val;
                n--;
            }
            return -1;
        }
    }
}
