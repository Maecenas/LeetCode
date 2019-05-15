package LeetCode;

/*
https://leetcode.com/problems/k-diff-pairs-in-an-array/
Easy. Array, Two Pointers.

Given an array of integers and an integer k, you need to find the number of
unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j),
where i and j are both numbers in the array and their absolute difference is k.

Example 1:

Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.

Example 2:

Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).

Example 3:

Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).

Note:

The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].
*/

import java.util.Arrays;
import java.util.HashMap;

class _532_KdiffPairsInAnArray {

    public static int findPairs(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k < 0) return 0;

        final HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i : nums) {
            if (map.containsKey(i)) {
                if (k == 0 && map.get(i) == 1) {
                    count++;
                }
                map.put(i, map.get(i) + 1);
            } else {
                if (map.containsKey(i - k)) count++;
                if (map.containsKey(i + k)) count++;
                map.put(i, 1);
            }
        }
        return count;
    }

    public static int findPairs2(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k < 0) return 0;

        Arrays.sort(nums);
        int count = 0, start = 0, end = 1;

        while (end < nums.length) {
            int i = nums[start];
            int j = nums[end];
            // If distance is less than k, increase the right index
            if (j - i < k) {
                end++;
            }
            // If larger than k, increase the left index
            else if (j - i > k) {
                start++;
            }
            // find pair (i, j), move start and end to next different number
            else {
                count++;
                while (start < nums.length && nums[start] == i) start++;
                while (end < nums.length && nums[end] == j) end++;
            }
            //left and right should not be the same number
            if (end == start) {
                end++;
            }
        }
        return count;
    }
}
