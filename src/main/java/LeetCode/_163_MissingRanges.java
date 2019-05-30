package LeetCode;

/*
https://leetcode.com/problems/missing-ranges/
Medium. Array.

Given a sorted integer array nums, where the range of elements are
in the inclusive range [lower, upper], return its missing ranges.

Example:

Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]
*/

import java.util.ArrayList;
import java.util.List;

/**
 * @see _228_SummaryRanges
 */
class _163_MissingRanges {

    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        final ArrayList<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            addRange(res, lower, upper);
            return res;
        }

        if (lower < nums[0]) {
            addRange(res, lower, nums[0] - 1);
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                addRange(res, nums[i - 1] + 1, nums[i] - 1);
            }
        }
        if (upper > nums[nums.length - 1]) {
            addRange(res, nums[nums.length - 1] + 1, upper);
        }
        return res;
    }

    private static void addRange(final List<String> res, int lower, int upper) {
        res.add(lower == upper
                ? String.valueOf(lower)
                : lower + "->" + upper);
    }
}
