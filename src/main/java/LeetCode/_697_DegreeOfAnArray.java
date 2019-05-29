package LeetCode;

/*
https://leetcode.com/problems/degree-of-an-array/
Easy. Array.

Given a non-empty array of non-negative integers nums, the degree of this
array is defined as the maximum frequency of any one of its elements.
Your task is to find the smallest possible length of a (contiguous)
subarray of nums, that has the same degree as nums.

Example 1:

Input: [1, 2, 2, 3, 1]
Output: 2
Explanation:
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.

Example 2:

Input: [1,2,2,3,1,4,2]
Output: 6

Note:
nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.
*/

import java.util.HashMap;

class _697_DegreeOfAnArray {

    public static int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        else if (nums.length == 1) return 1;

        //final HashMap<Integer, Integer> counter = new HashMap<>(), first = new HashMap<>();
        //int res = 0, degree = 0;
        //for (int i = 0; i < nums.length; i++) {
        //    //first.putIfAbsent(nums[i], i);
        //    if (!first.containsKey(nums[i])) {
        //        first.put(nums[i], i);
        //    }
        //    counter.put(nums[i], counter.getOrDefault(nums[i], 0) + 1);
        //    if (counter.get(nums[i]) > degree) {
        //        degree = counter.get(nums[i]);
        //        res = i - first.get(nums[i]) + 1;
        //    } else if (counter.get(nums[i]) == degree) {
        //        res = Math.min(res, i - first.get(nums[i]) + 1);
        //    }
        //}

        // the first 16 bits of entry are used to store first appearing index
        // the last 16 bits are used to count num
        final HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0, degree = 0;

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i << 16);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }

            int count = map.get(nums[i]) & 0xFFFF;
            if (count > degree) {
                degree = count;
                res = i - (map.get(nums[i]) >> 16) + 1;
            } else if (count == degree) {
                res = Math.min(res, i - (map.get(nums[i]) >> 16) + 1);
            }
        }
        return res;
    }
}
