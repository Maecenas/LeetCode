package com.leetcode;

/*
https://leetcode.com/problems/letter-tile-possibilities/
Medium. Backtracking.

You have a set of tiles, where each tile has one letter tiles[i] printed on it.
Return the number of possible non-empty sequences of letters you can make.

Example 1:

Input: "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".

Example 2:

Input: "AAABBC"
Output: 188

Note:

1 <= tiles.length <= 7
tiles consists of uppercase English letters.
*/

class _1079_LetterTilePossibilities {

    private static final int R = 26;  // uppercase letters

    public static int numTilePossibilities(String tiles) {
        if (tiles == null || tiles.length() == 0) return 0;

        final int[] count = new int[R];
        for (char c : tiles.toCharArray()) {
            count[c - 'A']++;
        }
        return backtrack(count);
    }

    private static int backtrack(int[] nums) {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            if (nums[i] == 0) continue;
            sum++;
            nums[i]--;
            sum += backtrack(nums);
            nums[i]++;
        }
        return sum;
    }
}
