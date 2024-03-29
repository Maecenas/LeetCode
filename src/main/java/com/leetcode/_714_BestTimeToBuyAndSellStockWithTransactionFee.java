package com.leetcode;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
Medium. Array, Dynamic Programming, Greedy.

Your are given an array of integers prices, for which the i-th element
is the price of a given stock on day i; and a non-negative integer fee
representing a transaction fee.
You may complete as many transactions as you like, but you need to pay
the transaction fee for each transaction. You may not buy more than 1
share of a stock at a time (ie. you must sell the stock share
before you buy again.)

Return the maximum profit you can make.

Example 1:

Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
Buying at prices[0] = 1
Selling at prices[3] = 8
Buying at prices[4] = 4
Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

Note:
0 < prices.length <= 50000.
0 < prices[i] < 50000.
0 <= fee < 50000.
*/

/**
 * @see _121_BestTimeToBuyAndSellStock
 * @see _122_BestTimeToBuyAndSellStockII
 * @see _123_BestTimeToBuyAndSellStockIII
 * @see _188_BestTimeToBuyAndSellStockIV
 * @see _309_BestTimeToBuyAndSellStockWithCooldown
 */
class _714_BestTimeToBuyAndSellStockWithTransactionFee {

    public static int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0 || fee < 0) return 0;

        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }

    /**
     * dp[n][0] -> maxProfit at day n when not holding shares
     * dp[n][1] -> maxProfit at day n when holding shares
     * <p>
     * dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])
     * dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee)
     */
    public static int maxProfitDpTemplate(int[] prices, int fee) {
        if (prices == null || prices.length == 0 || prices.length > 50_000 || fee < 0) return 0;

        final int n = prices.length;
        final int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0] - fee;
        for (int i = 1; i < n; i++) {
            dp[(i) % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1] + prices[i]);
            dp[(i) % 2][1] = Math.max(dp[(i - 1) % 2][1], dp[(i - 1) % 2][0] - prices[i] - fee);
        }
        return dp[(n - 1) % 2][0];
    }
}
