package LeetCode;

/*
https://leetcode.com/problems/summary-ranges/
Medium. Array.

Given a sorted integer array without duplicates,
return the summary of its ranges.

Example 1:

Input:  [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.

Example 2:

Input:  [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
*/

import java.util.ArrayList;
import java.util.List;

/**
 * @see _163_MissingRanges
 */
class _228_SummaryRanges {

    public static List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        final List<String> res = new ArrayList<>();
        int start, end = 0;

        while (end < nums.length) {
            start = end;
            while (end < nums.length - 1 && nums[end + 1] == nums[end] + 1) {
                end++;
            }
            addRange(res, nums[start], nums[end]);
            end++;
        }
        return res;
    }

    private static void addRange(final List<String> res, int start, int end) {
        res.add(start == end
                ? String.valueOf(start)
                : start + "->" + end);
    }
}
