package LeetCode;

/*
https://leetcode.com/problems/find-common-characters/
Easy. Array, Hash Table.

Given an array A of strings made only from lowercase letters, return a list of all
characters that show up in all strings within the list (including duplicates).
For example, if a character occurs 3 times in all strings but not 4 times,
you need to include that character three times in the final answer.

You may return the answer in any order.

Example 1:

Input: ["bella","label","roller"]
Output: ["e","l","l"]

Example 2:

Input: ["cool","lock","cook"]
Output: ["c","o"]

Note:

1 <= A.length <= 100
1 <= A[i].length <= 100
A[i][j] is a lowercase letter
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class _1002_FindCommonCharacters {

    private static final int R = 26;  // lowercase letters

    /**
     * Return an intersection of every counts of String from A
     */
    public static List<String> commonChars(String[] A) {
        if (A == null || A.length == 0) return new ArrayList<>();

        final int[] intersect = new int[R], count = new int[R];
        Arrays.fill(intersect, Integer.MAX_VALUE);

        for (String str : A) {
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            for (int i = 0; i < R; i++) {
                intersect[i] = Math.min(intersect[i], count[i]);
            }
            Arrays.fill(count, 0);
        }

        final ArrayList<String> res = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            while (intersect[c - 'a']-- > 0) {
                res.add(String.valueOf(c));
            }
        }
        return res;
    }
}
