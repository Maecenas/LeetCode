package com.leetcode;

/*
https://leetcode.com/problems/random-pick-index/
Medium. Hash Table, Math, Reservoir Sampling, Randomized.

Given an integer array nums with possible duplicates,
randomly output the index of a given target number.
You can assume that the given target number must exist in the array.

Implement the Solution class:
 Solution(int[] nums) Initializes the object with the array nums.
 int pick(int target) Picks a random index i from nums
   where nums[i] == target.
  If there are multiple valid i's, then each index should have
   an equal probability of returning.

Example 1:
 Input
  ["Solution", "pick", "pick", "pick"]
  [[[1, 2, 3, 3, 3]], [3], [1], [3]]
 Output
  [null, 4, 0, 2]
 Explanation
  Solution solution = new Solution([1, 2, 3, 3, 3]);
  solution.pick(3); // It should return either index 2, 3, or 4 randomly.
                    // Each index should have equal probability of returning.
  solution.pick(1); // It should return 0.
                    // Since in the array only nums[0] is equal to 1.
  solution.pick(3); // It should return either index 2, 3, or 4 randomly.
                    // Each index should have equal probability of returning.

Constraints:
 1 <= nums.length <= 2 * 10^4
 -2^31 <= nums[i] <= 2^31 - 1
 target is an integer from nums.
 At most 10⁴ calls will be made to pick.

Your Solution object will be instantiated and called as such:
 Solution obj = new Solution(nums);
 int param_1 = obj.pick(target);
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class _398_RandomPickIndex {

    static class Solution {

        final int[] nums;
        final Random rand;

        public Solution(int[] nums) {
            this.nums = nums;
            this.rand = ThreadLocalRandom.current();
        }

        public int pick(int target) {
            int res = -1;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != target) continue;
                count++;
                if (rand.nextInt(count) == 0) {
                    res = i;
                }
            }
            return res;
        }
    }

    static class Solution2 {

        final Map<Integer, List<Integer>> map;
        final Random rand;

        public Solution2(int[] nums) {
            this.map = new HashMap<>();
            this.rand = ThreadLocalRandom.current();
            for (int i = 0; i < nums.length; i++) {
                this.map.computeIfAbsent(nums[i], ignored -> new ArrayList<>())
                        .add(i);
            }
        }

        public int pick(int target) {
            final List<Integer> candidates = map.get(target);
            return candidates.get(rand.nextInt(candidates.size()));
        }
    }
}
