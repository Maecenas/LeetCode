package LeetCode;

/*
https://leetcode.com/problems/set-mismatch/
Easy. Hash Table, Math.

The set S originally contains numbers from 1 to n. But unfortunately,
due to the data error, one of the numbers in the set got duplicated
to another number in the set, which results in repetition of one number
and loss of another number.

Given an array nums representing the data status of this set after the error.
Your task is to firstly find the number occurs twice and then find the number
that is missing. Return them in the form of an array.

Example 1:

Input: nums = [1,2,2,4]
Output: [2,3]

Note:

The given array size will in the range [2, 10000].
The given array's numbers won't have any order.
*/

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class _645_SetMismatch {

    /**
     * O(n), O(1), two traversals
     */
    public static int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length < 2) return new int[0];

        int dup = -1;
        for (int num : nums) {
            if (nums[Math.abs(num) - 1] < 0) {
                dup = Math.abs(num);
            } else {
                nums[Math.abs(num) - 1] *= -1;
            }
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                return new int[]{dup, i + 1};
            }
        }
        return new int[0];
    }

    /**
     * O(n), O(1)， Bit Xor
     */
    public static int[] findErrorNumsBitManipulation(int[] nums) {
        if (nums == null || nums.length < 2) return new int[0];

        // sum = missing ^ dup
        int dup = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]);
            sum ^= ((i + 1) ^ index);
            if (nums[index - 1] < 0) {
                dup = index;
            } else {
                nums[index - 1] *= -1;
            }
        }
        return new int[]{dup, sum ^ dup};
    }

    /**
     * O(n), O(1), Math
     */
    public static int[] findErrorNumsStream(int[] nums) {
        if (nums == null || nums.length < 2) return new int[0];

        Set<Integer> numSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int setSum = numSet.stream().reduce(0, Integer::sum);
        int actualSum = IntStream.of(nums).sum();
        int targetSum = IntStream.range(1, nums.length + 1).sum();
        return new int[]{actualSum - setSum, targetSum - setSum};
    }
}
