package LeetCode;

/*
https://leetcode.com/problems/sum-of-subarray-minimums/
Medium. Array, Stack.

Given an array of integers A, find the sum of min(B),
where B ranges over every (contiguous) subarray of A.

Since the answer may be large, return the answer modulo 10^9 + 7.

Example 1:

Input: [3,1,2,4]
Output: 17
Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.

Note:

1 <= A.length <= 30000
1 <= A[i] <= 30000
*/

class _907_SumOfSubarrayMinimums {

    /**
     * If the modular divisor M is 10^9 + 7
     * It serves mainly three purposes:
     * 1. Fits in the range of int in languages like C/C++.
     *    One doesn't need to deal with overflows when doing addition/subtraction modulo M.
     * 2. M is a prime number.
     *    You can perform division modulo M just by calculating the modular inverse of the divisor.
     * 3. The square of M fits in 63 bits (size of long long),
     *    so you can perform multiplication modulo M without overflows.
     */
    private static final int MOD = (int) (1e9 + 7);

    public static int sumSubarrayMins(int[] A) {
        if (A == null || A.length == 0) return 0;

        final int len = A.length;
        // left[i] = length of subarray to the continuous larger element to the left
        // right[i] = length of subarray to the continuous larger element to the right
        final int[] left = new int[len], right = new int[len];
        // monotone helper stack to find the PLE and NLE
        // using another pointer would also work than a stack
        //final Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            int count = 1;
            //while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
            //    count += left[stack.pop()];
            //}
            //stack.push(i);
            int j = i - 1;
            while (j >= 0 && A[j] >= A[i]) {
                count += left[j];
                j -= left[j];
            }
            left[i] = count;
        }

        // stack.clear();
        //while (!stack.isEmpty()) stack.pop();

        for (int i = len - 1; i >= 0; i--) {
            int count = 1;
            //while (!stack.isEmpty() && A[stack.peek()] >= A[i]) {
            //    count += right[stack.pop()];
            //}
            //stack.push(i);
            int k = i + 1;
            while (k < A.length && A[k] > A[i]) {
                count += right[k];
                k += right[k];
            }
            right[i] = count;
        }

        //int res = 0;
        long res = 0;
        for (int i = 0; i < len; i++) {
            // res = (res + A[i] * left[i] * right[i]) % MOD;
            // res = (res + (A[i] * left[i] * right[i]) % MOD) % MOD;
            res = res + A[i] * left[i] * right[i];
        }
        return (int) (res % MOD);
    }
}
