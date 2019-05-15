package LeetCode;

/*
https://leetcode.com/problems/third-maximum-number/
Easy. Array.

Given a non-empty array of integers, return the third maximum number in this array.
If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:

Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.

Example 2:

Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.

Example 3:

Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.
*/

import java.util.PriorityQueue;

class _414_ThirdMaximumNumber {

    public static int thirdMax(int[] nums) {
        if (nums == null) return Integer.MIN_VALUE;
        else if (nums.length == 1) return nums[0];
        else if (nums.length == 2) return Math.max(nums[0], nums[1]);
        else if (nums.length == 3) return Math.min(nums[0], Math.min(nums[1], nums[2]));

        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            if (pq.size() < 3 && !pq.contains(num)) {
                pq.add(num);
            } else if (num > pq.peek() && !pq.contains(num)) {
                pq.poll();
                pq.add(num);
            }
        }
        if (pq.size() < 3) {
            while (pq.size() != 1) pq.poll();
        }
        return pq.peek();
    }

    public static int thirdMax2(int[] nums) {
        if (nums == null) return Integer.MIN_VALUE;
        else if (nums.length == 1) return nums[0];
        else if (nums.length == 2) return Math.max(nums[0], nums[1]);
        else if (nums.length == 3) return Math.min(nums[0], Math.min(nums[1], nums[2]));

        Integer max1 = null, max2 = null, max3 = null;
        for (Integer n : nums) {
            if (n.equals(max1) || n.equals(max2) || n.equals(max3)) continue;
            if (max1 == null || n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (max2 == null || n > max2) {
                max3 = max2;
                max2 = n;
            } else if (max3 == null || n > max3) {
                max3 = n;
            }
        }
        assert max1 != null;
        return max3 == null ? max1 : max3;
    }
}
