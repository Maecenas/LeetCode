package LeetCode;

/*
https://leetcode.com/problems/count-and-say/
Easy. String.

The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221

 1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:

Input: 1
Output: "1"

Example 2:

Input: 4
Output: "1211"
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class _38_CountAndSay {

    private static final List<String> MAP = new ArrayList<>(Arrays.asList(
            "1", "11", "21", "1211", "111221", "312211", "13112221", "1113213211", "31131211131221"));

    public static String countAndSay(int n) {
        if (n <= 0) return "";

        if (MAP.size() >= n) return MAP.get(n - 1);

        final String prev = countAndSay(n - 1);
        final StringBuilder curr = new StringBuilder();
        int count = 1;

        for (int i = 0; i < prev.length(); i++) {
            if (i < prev.length() - 1 && prev.charAt(i) == prev.charAt(i + 1)) {
                count++;
            } else {
                curr.append(count);
                curr.append(prev.charAt(i));
                count = 1;
            }
        }

        final String res = curr.toString();
        MAP.add(n - 1, res);
        return res;
    }
}
