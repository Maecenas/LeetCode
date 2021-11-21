package com.leetcode;

/*
https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards/
Easy. Array, Math.

In a deck of cards, each card has an integer written on it.

Return true if and only if you can choose X >= 2 such that
it is possible to split the entire deck into 1 or more groups of cards, where:

Each group has exactly X cards.
All the cards in each group have the same integer.

Example 1:

Input: [1,2,3,4,4,3,2,1]
Output: true
Explanation: Possible partition [1,1],[2,2],[3,3],[4,4]

Example 2:

Input: [1,1,1,2,2,2,3,3]
Output: false
Explanation: No possible partition.

Example 3:

Input: [1]
Output: false
Explanation: No possible partition.

Example 4:

Input: [1,1]
Output: true
Explanation: Possible partition [1,1]

Example 5:

Input: [1,1,2,2,2,2]
Output: true
Explanation: Possible partition [1,1],[2,2],[2,2]

Note:

1 <= deck.length <= 10000
0 <= deck[i] < 10000
*/

class _914_XOfAKindInADeckOfCards {

    private static final int R = 10000;  // max range

    /**
     * (nloglogn), O(n)
     */
    public static boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length < 2) return false;

        final int[] count = new int[R];
        for (int i : deck) {
            count[i]++;
        }
        // X must divide the greatest common divisor of every element count
        int g = 0;
        for (int i : count) {
            g = gcd(g, i);
            // early return
            if (g == 1) return false;
        }
        // return g >= 2;
        return true;
    }

    private static int gcd(int x, int y) {
        return x == 0 ? y : gcd(y, x % y);
    }
}
