package com.leetcode;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
Easy. Array, Greedy.

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit.
 You may complete as many transactions as you like
 (i.e., buy one and sell one share of the stock multiple times).

 Note: You may not engage in multiple transactions at the same time
 (i.e., you must sell the stock before you buy again).

Example 1:

Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.

Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.

Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
*/

/**
 * @see _121_BestTimeToBuyAndSellStock
 * @see _123_BestTimeToBuyAndSellStockIII
 * @see _188_BestTimeToBuyAndSellStockIV
 * @see _309_BestTimeToBuyAndSellStockWithCooldown
 * @see _714_BestTimeToBuyAndSellStockWithTransactionFee
 */
class _122_BestTimeToBuyAndSellStockII {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    /**
     * dp[n][0] -> maxProfit at day n when not holding shares
     * dp[n][1] -> maxProfit at day n when holding shares
     * <p>
     * dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
     * dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - prices[i])
     */
    public static int maxProfitDpTemplate(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        final int n = prices.length;
        final int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[(i) % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1] + prices[i]);
            dp[(i) % 2][1] = Math.max(dp[(i - 1) % 2][1], dp[(i - 1) % 2][0] - prices[i]);
        }
        return dp[(n - 1) % 2][0];
    }
}
