package LeetCode;

/*
https://leetcode.com/problems/subarray-sums-divisible-by-k/
Medium. Array, Hash Table.

Given an array A of integers, return the number of (contiguous, non-empty)
subarrays that have a sum divisible by K.

Example 1:

Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

Note:

1 <= A.length <= 30000
-10000 <= A[i] <= 10000
2 <= K <= 10000
*/

/**
 * @see _560_SubarraySumEqualsK
 */
class _974_SubarraySumsDivisibleByK {

    public static int subarraysDivByK(int[] A, int K) {
        if (A == null || A.length == 0 || K < 2) return 0;

        final int[] count = new int[K];
        // keep the same calculation for x % K = 0
        // C_n^1 + C_n^2 = n + n * (n - 1) / 2
        // = C_(n + 1)^2 = n * (n + 1) / 2
        count[0] = 1;
        int sum = 0;
        for (int x : A) {
            sum += x;
            count[(sum % K + K) % K]++;
        }

        int res = 0;
        for (int v : count) {
            res += (v * (v - 1)) >> 1;
        }
        return res;
    }
}
