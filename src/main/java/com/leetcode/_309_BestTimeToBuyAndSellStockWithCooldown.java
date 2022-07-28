package com.leetcode;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
Medium. Array, Dynamic Programming.

You are given an array prices where prices[i] is the price of a given stock
on the i-th day.

Find the maximum profit you can achieve.
You may complete as many transactions as you like
(i.e., buy one and sell one share of the stock multiple times)
with the following restrictions:

After you sell your stock, you cannot buy stock on the next day
(i.e., cooldown one day).

Note: You may not engage in multiple transactions simultaneously
(i.e., you must sell the stock before you buy again).

Example 1:
 Input: prices = [1,2,3,0,2]
 Output: 3
 Explanation: transactions = [buy, sell, cooldown, buy, sell]

Example 2:
 Input: prices = [1]
 Output: 0

Constraints:
 1 <= prices.length <= 5000
 0 <= prices[i] <= 1000
*/

/**
 * @see _121_BestTimeToBuyAndSellStock
 * @see _122_BestTimeToBuyAndSellStockII
 * @see _123_BestTimeToBuyAndSellStockIII
 * @see _188_BestTimeToBuyAndSellStockIV
 * @see _714_BestTimeToBuyAndSellStockWithTransactionFee
 */
class _309_BestTimeToBuyAndSellStockWithCooldown {

    /**
     * dp[n][0] -> maxProfit at day n when not holding shares
     * dp[n][1] -> maxProfit at day n when holding shares
     * <p>
     * dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
     * dp[i][1] = max(dp[i - 1][1], dp[i - 2][0] - prices[i])
     */
    public static int maxProfitDpTemplate(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length > 5_000) return -1;

        final int n = prices.length;
        final int[][] dp = new int[3][2];
        // base case 1
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        if (n > 1) {
            dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
            dp[1][1] = Math.max(dp[0][1], -prices[1]);
        }
        for (int i = 2; i < n; i++) {
            dp[i % 3][0] = Math.max(dp[(i - 1) % 3][0], dp[(i - 1) % 3][1] + prices[i]);
            dp[i % 3][1] = Math.max(dp[(i - 1) % 3][1], dp[(i - 2) % 3][0] - prices[i]);
        }
        return dp[(n - 1) % 3][0];
    }
}
