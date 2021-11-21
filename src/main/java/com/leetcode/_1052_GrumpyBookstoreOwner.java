package com.leetcode;

/*
https://leetcode.com/problems/grumpy-bookstore-owner/
Medium. Array, Sliding Window.

Today, the bookstore owner has a store open for customers.length minutes.
Every minute, some number of customers (customers[i]) enter the store,
and all those customers leave after the end of that minute.

On some minutes, the bookstore owner is grumpy. If the bookstore owner is
grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0. When the
bookstore owner is grumpy, the customers of that minute are not satisfied,
otherwise they are satisfied.

The bookstore owner knows a secret technique to keep themselves not grumpy
for X minutes straight, but can only use it once.

Return the maximum number of customers that can be satisfied throughout the day.

Example 1:

Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
Output: 16
Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.

Note:

1 <= X <= customers.length == grumpy.length <= 20000
0 <= customers[i] <= 1000
0 <= grumpy[i] <= 1
*/

class _1052_GrumpyBookstoreOwner {

    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        if (customers == null || customers.length == 0 || grumpy == null
                || grumpy.length != customers.length || X < 1 || X > customers.length) return 0;

        // use sliding window curr of size X
        // use satisfy to record customers satisfied without grumpy
        int satisfy = 0, max = 0;
        for (int i = 0, curr = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                satisfy += customers[i];
            } else {
                curr += customers[i];
            }
            if (i >= X && grumpy[i - X] == 1) {
                curr -= customers[i - X];
            }
            max = Math.max(max, curr);
        }
        return satisfy + max;
    }
}
