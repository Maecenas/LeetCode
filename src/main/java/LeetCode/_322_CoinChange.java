package LeetCode;

/*
https://leetcode.com/problems/coin-change/
Medium. Dynamic Programming.

You are given coins of different denominations and a total amount of money
amount. Write a function to compute the fewest number of coins that you need
to make up that amount. If that amount of money cannot be made up
by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:

Input: coins = [2], amount = 3
Output: -1

Note:
You may assume that you have an infinite number of each kind of coin.
*/

import java.util.Arrays;

class _322_CoinChange {

    /**
     * O(m * n), O(m)
     */
    public static int coinChangeDPTopDown(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount <= 0) return 0;

        // f(n) = min(f(n - c_i)) + 1
        // f(-1) = -1, f(0) = 0
        return coinChange(coins, amount, new int[amount]);
    }

    private static int coinChange(int[] coins, int amount, int[] count) {
        if (amount < 0) return -1;
        else if (amount == 0) return 0;

        if (count[amount - 1] != 0) return count[amount - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, amount - coin, count);
            if (res >= 0 && res < min) {
                min = res + 1;
            }
        }
        count[amount - 1] = (min != Integer.MAX_VALUE) ? min : -1;
        return count[amount - 1];
    }

    /**
     * O(m * n), O(m)
     */
    public static int coinChangeDPBottomUp(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount <= 0) return 0;

        final int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin < i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] <= amount ? dp[amount] : -1;
    }
}
