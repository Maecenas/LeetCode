package com.leetcode;

/*
https://leetcode.com/problems/filling-bookcase-shelves/
Medium. Dynamic Programming.

We have a sequence of books: the i-th book has thickness books[i][0]
and height books[i][1].

We want to place these books in order onto bookcase shelves that have
total width shelf_width.

We choose some of the books to place on this shelf (such that the sum of
their thickness is <= shelf_width), then build another level of shelf of
the bookcase so that the total height of the bookcase has increased by
the maximum height of the books we just put down. We repeat this process
until there are no more books to place.

Note again that at each step of the above process, the order of the books
we place is the same order as the given sequence of books. For example,
if we have an ordered list of 5 books, we might place the first and second
book onto the first shelf, the third book on the second shelf, and the fourth
and fifth book on the last shelf.

Return the minimum possible height that the total bookshelf can be after
placing shelves in this manner.

Example 1:

Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
Output: 6
Explanation:
The sum of the heights of the 3 shelves are 1 + 3 + 2 = 6.
Notice that book number 2 does not have to be on the first shelf.

Constraints:

1 <= books.length <= 1000
1 <= books[i][0] <= shelf_width <= 1000
1 <= books[i][1] <= 1000

Hint 1:
Use dynamic programming: dp(i) will be the answer to the problem for books[i:].
*/

class _1105_FillingBookcaseShelves {

    public static int minHeightShelves(int[][] books, int maxWidth) {
        if (books == null || books.length == 0 || books[0].length != 2) return 0;

        final int n = books.length;
        // dp[i + 1] is the result of books[:i]
        final int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int width = books[i][0], height = books[i][1];
            // place book i on a new shelf
            dp[i + 1] = dp[i] + height;
            // or place books[j, i] (closed interval) in a shelf
            // plus the result of books[:j) as dp[(j - 1) + 1], or dp[j]
            for (int j = i - 1; j >= 0 && width + books[j][0] <= maxWidth; j--) {
                width += books[j][0];
                height = Math.max(height, books[j][1]);
                dp[i + 1] = Math.min(dp[i + 1], dp[j] + height);
            }
        }
        return dp[n];
    }
}
