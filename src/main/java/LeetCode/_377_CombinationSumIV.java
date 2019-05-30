package LeetCode;

/*
https://leetcode.com/problems/combination-sum-iv/
Medium. Array, Dynamic Programming.

Given an integer array with all positive numbers and no duplicates,
find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.

Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?

Credits:
Special thanks to @pbrother for adding this problem and creating all test cases.
*/

import java.util.Arrays;

/**
 * Better call it a question of Permutation Sum
 *
 * @see _39_CombinationSum
 * @see _40_CombinationSumII
 * @see _216_CombinationSumIII
 */
class _377_CombinationSumIV {

    public static int combinationSum4DPTopDown(int[] nums, int target) {

        if (nums == null || nums.length == 0) return 0;

        final int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;

        combine(dp, nums, target);
        return dp[target];
    }

    private static void combine(final int[] dp, final int[] nums, int target) {
        if (dp[target] != -1) return;

        int res = 0;
        for (int num : nums) {
            if (target >= num) {
                combine(dp, nums, target - num);
                res += dp[target - num];
            }
        }
        dp[target] = res;
    }

    public static int combinationSum4DPBottomUp(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        final int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    /**
     * Not worth implementing
     */
    public static int combinationSum4DPBottomUpSort(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        final int[] dp = new int[target + 1];
        dp[0] = 1;

        Arrays.sort(nums);

        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num > i) break;
                dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }
}
