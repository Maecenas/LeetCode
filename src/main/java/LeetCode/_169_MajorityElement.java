package LeetCode;

/*
https://leetcode.com/problems/majority-element/
Easy. Array, Divide and Conquer, Bit Manipulation.

Given an array of size n, find the majority element.
The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3

Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2
*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @see _229_MajorityElementII
 */
class _169_MajorityElement {

    /**
     * O(n), O(n)
     */
    public static int majorityElementHashMap(int[] nums) {
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        else if (nums.length == 1) return nums[0];

        final Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            // this block can be refactored as:
            // map.put(num, map.getOrDefault(num, 0) + 1);
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }
        assert majorityEntry != null;
        return majorityEntry.getKey();
    }

    /**
     * O(nlogn), O(1) or O(n)
     */
    public static int majorityElementSorting(int[] nums) {
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        else if (nums.length == 1) return nums[0];

        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * O(∞), O(1)
     */
    public static int majorityElementRandomization(int[] nums) {
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        else if (nums.length == 1) return nums[0];

        Random rand = new Random();
        int majorityCount = nums.length / 2;

        while (true) {
            int candidate = nums[rand.nextInt(nums.length)];
            if (countOccurences(nums, candidate) > majorityCount) {
                return candidate;
            }
        }
    }

    private static int countOccurences(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num == target) {
                count++;
            }
        }
        return count;
    }

    /**
     * O(nlogn), O(logn)
     */
    public static int majorityElementDivideAndConquer(int[] nums) {
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        else if (nums.length == 1) return nums[0];

        return majorityElementRec(nums, 0, nums.length - 1);
    }

    private static int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = lo + ((hi - lo) >> 1);
        final int left = majorityElementRec(nums, lo, mid);
        final int right = majorityElementRec(nums, mid + 1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    private static int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    /**
     * O(n), O(1)
     */
    public static int majorityElementBitManipulation(int[] nums) {
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        else if (nums.length == 1) return nums[0];

        // final int[] bit = new int[32];
        // for (int num : nums) {
        //     for (int i = 0; i < 32; i++) {
        //         // could be optimized to read bit from right to left
        //         // left most (i + 1) bit of num is 1
        //         if ((num >> (31 - i) & 0x1) == 0x1) {
        //             bit[i]++;
        //         }
        //     }
        // }
        //
        // // sum up majority bits to get majority element
        // int res = 0;
        // for (int i = 0; i < 32; i++) {
        //     if (bit[i] > nums.length / 2) {
        //         res += (1 << (31 - i));
        //     }
        // }
        // return res;

        int majority = 0;
        for (int i = 0, mask = 1; i < 32; i++, mask <<= 1) {
            int bits = 0;
            for (int num : nums) {
                if ((num & mask) != 0) {
                    bits++;
                }
            }
            if (bits > (nums.length >> 1)) {
                majority |= mask;
            }
        }
        return majority;
    }

    /**
     * O(n), O(1)
     */
    public static int majorityElementBoyerMooreVoting(int[] nums) {
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        else if (nums.length == 1) return nums[0];

        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
