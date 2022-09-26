package com.leetcode;

/*
https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
Hard. Array, Hash Table, Math, Design, Randomized.

RandomizedCollection is a data structure that contains a collection of
numbers, possibly duplicates (i.e., a multiset). It should support inserting and
removing specific elements and also removing a random element.

Implement the RandomizedCollection class:

 RandomizedCollection() Initializes the empty RandomizedCollection object.
 bool insert(int val) Inserts an item val into the multiset, even if the item
   is already present. Returns true if the item is not present, false otherwise.
 bool remove(int val) Removes an item val from the multiset if present.
   Returns true if the item is present, false otherwise. Note that if val has multiple
   occurrences in the multiset, we only remove one of them.
 int getRandom() Returns a random element from the current multiset of
   elements. The probability of each element being returned is linearly related to the
   number of same values the multiset contains.

You must implement the functions of the class such that each function works
on average O(1) time complexity.

Note: The test cases are generated such that getRandom will only be called
if there is at least one item in the RandomizedCollection.

Example 1:
 Input
  ["RandomizedCollection", "insert", "insert", "insert", "getRandom", "remove","getRandom"]
  [[], [1], [1], [2], [], [1], []]
 Output
  [null, true, false, true, 2, true, 1]
 Explanation
   RandomizedCollection randomizedCollection = new RandomizedCollection();
   randomizedCollection.insert(1);   // return true since the collection does not contain 1.
                                     // Inserts 1 into the collection.
   randomizedCollection.insert(1);   // return false since the collection contains 1.
                                     // Inserts another 1 into the collection.
                                     // Collection now contains [1,1].
   randomizedCollection.insert(2);   // return true since the collection does not contain 2.
                                     // Inserts 2 into the collection.
                                     // Collection now contains [1,1,2].
   randomizedCollection.getRandom(); // getRandom should:
                                     // - return 1 with probability 2/3, or
                                     // - return 2 with probability 1/3.
   randomizedCollection.remove(1);   // return true since the collection contains 1.
                                     // Removes 1 from the collection.
                                     // Collection now contains [1,2].
   randomizedCollection.getRandom(); // getRandom should return 1 or 2, both equally likely.

Constraints:
 -2^31 <= val <= 2^31 - 1
 At most 2 * 10^5 calls in total will be made to insert, remove, and getRandom.

There will be at least one element in the data structure when getRandom is called.

Your RandomizedCollection object will be instantiated and called as such:

RandomizedCollection obj = new RandomizedCollection();
 boolean param_1 = obj.insert(val);
 boolean param_2 = obj.remove(val);
 int param_3 = obj.getRandom();
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @see _380_InsertDeleteGetRandomO1
 */
class _381_InsertDeleteGetRandomO1DuplicatesAllowed {

    static class RandomizedCollection {

        final Map<Integer, Set<Integer>> map;  // (val, indices)
        final List<Integer> list;
        final Random rand;

        /** Initialize your data structure here. */
        public RandomizedCollection() {
            this.map = new HashMap<>();
            this.list = new ArrayList<>();
            this.rand = ThreadLocalRandom.current();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            boolean isNewInsert = !map.containsKey(val) || map.get(val).size() == 0;

            map.computeIfAbsent(val, k -> new HashSet<>())
                            .add(list.size());
            list.add(val);
            return isNewInsert;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            if (!map.containsKey(val) || map.get(val).size() == 0) return false;
            final int removeIdx = map.get(val).iterator().next();
            map.get(val).remove(removeIdx);
            final int lastIdx = list.size() - 1;
            final int lastVal = list.get(lastIdx);
            if (removeIdx < lastIdx) {
                map.get(lastVal).remove(lastIdx);
                map.get(lastVal).add(removeIdx);
                swap(list, removeIdx, lastIdx);
            }
            list.remove(lastIdx);
            return true;
        }

        private static void swap(List<Integer> list, int i, int j) {
            int tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }
    }
}
