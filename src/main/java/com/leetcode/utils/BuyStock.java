package com.leetcode.utils;

/**
 * Define the general problem with 3D DP array
 *   dp[i][k][s] or dp[i][k][1]
 *     where 0 <= i < n, 1 <= k <= K, s={0, 1}
 *     where n is the total number of days
 *           K is number of maximum allowed transactions
 *           s == 0 is holding shares, s == 1 is not holding shares
 * <p>
 * And we could enumerate {@code 2 * n * K} states:
 *   for 0 <= i < n:
 *     for 1 <= k <= K:
 *       for s in {0, 1}:
 *         dp[i][k][s] = max(buy, sell, hold)
 * <p>
 * base case：
 *   dp[-1][...][0] = dp[...][0][0] = 0
 *   dp[-1][...][1] = dp[...][0][1] = -infinity
 * <p>
 * transition function:
 *   dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 *     holding     max(   do nothing,            sell          )
 *   dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 *    not holding  max(   do nothing,             buy            )
 *
 * @see com.leetcode._121_BestTimeToBuyAndSellStock
 * @see com.leetcode._122_BestTimeToBuyAndSellStockII
 * @see com.leetcode._123_BestTimeToBuyAndSellStockIII
 * @see com.leetcode._188_BestTimeToBuyAndSellStockIV
 * @see com.leetcode._309_BestTimeToBuyAndSellStockWithCooldown
 * @see com.leetcode._714_BestTimeToBuyAndSellStockWithTransactionFee
 */
public final class BuyStock {
}
