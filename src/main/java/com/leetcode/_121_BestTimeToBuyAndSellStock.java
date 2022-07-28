package com.leetcode;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
Easy. Array, Dynamic Programming.

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction
(i.e., buy one and sell one share of the stock),
design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.

Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
*/

/**
 * @see _122_BestTimeToBuyAndSellStockII
 * @see _123_BestTimeToBuyAndSellStockIII
 * @see _188_BestTimeToBuyAndSellStockIV
 * @see _309_BestTimeToBuyAndSellStockWithCooldown
 * @see _714_BestTimeToBuyAndSellStockWithTransactionFee
 */
class _121_BestTimeToBuyAndSellStock {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }

    /**
     * Dynamic Programming as LeetCode #53 Maximum Subarray
     */
    public static int maxProfitDP(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int maxCurr = 0, maxSoFar = 0;
        for (int i = 1; i < prices.length; i++) {
            // the return of +=, = assignment operation may be misleading
            // maxCur = Math.max(0, maxCur += prices[i] - prices[i - 1]);
            maxCurr = Math.max(0, maxCurr + (prices[i] - prices[i - 1]));
            maxSoFar = Math.max(maxSoFar, maxCurr);
        }
        return maxSoFar;
    }

    /**
     * dp[n][0] -> maxProfit at day n when not holding shares
     * dp[n][1] -> maxProfit at day n when holding shares
     * <p>
     * dp[n][0] = max(dp[n - 1][0], dp[n - 1][1] + prices[i])
     * dp[n][1] = max(dp[n - 1][1], -prices[i])
     */
    public static int maxProfitDpTemplate(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        final int n = prices.length;
        final int[][] dp = new int[2][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1] + prices[i]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], -prices[i]);
        }
        return dp[(n - 1) % 2][0];
    }
}
