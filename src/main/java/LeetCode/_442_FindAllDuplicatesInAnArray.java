package LeetCode;

/*
https://leetcode.com/problems/find-all-duplicates-in-an-array/
Medium. Array.

Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array),
some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
*/

import java.util.ArrayList;
import java.util.List;

/**
 * @see _448_FindAllNumbersDisappearedInAnArray
 */
class _442_FindAllDuplicatesInAnArray {

    public static List<Integer> findDuplicates(int[] nums) {
        if (nums == null || nums.length <= 1) return new ArrayList<>();

        final ArrayList<Integer> res = new ArrayList<>();

        // [0, n - 1] => [1, n]
        for (int num : nums) {
            int val = Math.abs(num) - 1;
            if (nums[val] < 0) {
                res.add(Math.abs(num));
            } else {
                nums[val] = -nums[val];
            }
        }
        return res;
    }

}
