package LeetCode;

/*
https://leetcode.com/problems/3sum-closest/
Medium. Array, Two pointers.

Given an array nums of n integers and an integer target, find three integers in nums
such that the sum is closest to target.
Return the sum of the three integers.
You may assume that each input would have exactly one solution.

Example:

Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/

import java.util.Arrays;

class _16_3SumClosest {

    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) throw new IllegalArgumentException();

        Arrays.sort(nums);

        int closest = nums[0] + nums[1] + nums[2], lo, hi, sum;
        // early return
        if (closest >= target) return closest;

        for (int i = 0; i < nums.length - 2; i++) {
            // only check first occurrence of duplicate keys
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            lo = i + 1;
            hi = nums.length - 1;
            while (lo < hi) {
                sum = nums[i] + nums[lo] + nums[hi];
                if (sum > target) {
                    hi--;
                } else if (sum < target) {
                    lo++;
                } else {
                    return target;
                }
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }
                // early return
                if (sum > target && lo == i + 1 && hi == i + 1) {
                    return closest;
                }
            }
        }
        return closest;
    }

    public static int threeSumClosest2(int[] nums, int target) {
        if (nums == null || nums.length < 3) throw new IllegalArgumentException();

        Arrays.sort(nums);

        int diffPos = Integer.MAX_VALUE, diffNeg = Integer.MAX_VALUE, diff;
        int lo, hi;

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            lo = i + 1;
            hi = nums.length - 1;
            while (lo < hi) {
                diff = nums[i] + nums[lo] + nums[hi] - target;
                if (diff > 0) {
                    diffPos = Math.min(diffPos, diff);
                    hi--;
                } else if (diff < 0) {
                    diffNeg = Math.max(diffNeg, -diff);
                    lo++;
                } else {
                    return target;
                }
                // early return
                if (diff > 0 && lo == i + 1 && hi == i + 1) {
                    return diffPos < diffNeg ? target + diffPos : target - diffNeg;
                }
            }
        }
        return diffPos < diffNeg ? target + diffPos : target - diffNeg;
    }
}
