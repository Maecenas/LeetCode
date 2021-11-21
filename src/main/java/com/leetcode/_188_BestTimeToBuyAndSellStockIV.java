package com.leetcode;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
Hard. Dynamic Programming.

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time
(ie, you must sell the stock before you buy again).

Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
*/

/**
 * @see _121_BestTimeToBuyAndSellStock
 * @see _122_BestTimeToBuyAndSellStockII
 * @see _123_BestTimeToBuyAndSellStockIII
 * @see _309_BestTimeToBuyAndSellStockWithCooldown
 * @see _714_BestTimeToBuyAndSellStockWithTransactionFee
 */
class _188_BestTimeToBuyAndSellStockIV {

    /**
     * dp[i, j] represents the max profit up until prices[j] using at most i transactions.
     * dp[i, j] = max(dp[i, j - 1], prices[j] - prices[j] + dp[i - 1, j]) { j in range of [0, j - 1] }
     *          = max(dp[i, j - 1], prices[j] + max(dp[i - 1, j] - prices[j]))
     * dp[0, j] = 0; 0 transactions makes 0 profit
     * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
     */
    public static int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1 || k < 1) return 0;

        final int len = prices.length;

        //if k >= n / 2, then you can make maximum number of transactions.
        if (k >= (len >> 1)) {
            int maxPro = 0;
            for (int i = 1; i < len; i++) {
                if (prices[i] > prices[i - 1])
                    maxPro += prices[i] - prices[i - 1];
            }
            return maxPro;
        }

        int[][] dp = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int localMax = dp[i - 1][0] - prices[0];
            for (int j = 1; j < len; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + localMax);
                localMax = Math.max(localMax, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][len - 1];
    }
}
