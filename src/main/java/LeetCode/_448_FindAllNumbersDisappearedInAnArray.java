package LeetCode;

/*
https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
Easy. Array.

Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array),
some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime?
You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
*/

import java.util.ArrayList;
import java.util.List;

class _448_FindAllNumbersDisappearedInAnArray {

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        final ArrayList<Integer> res = new ArrayList<>();
        if (nums == null || nums.length <= 1) return res;

        // [0, n - 1] => [1, n]
        // if i of [1, n] appeared in array, then mark nums[i] as false
        for (int num : nums) {
            int val = Math.abs(num) - 1;
            if (nums[val] > 0) {
                nums[val] = -nums[val];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
