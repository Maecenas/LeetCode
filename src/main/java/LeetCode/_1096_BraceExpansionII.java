package LeetCode;

/*
https://leetcode.com/problems/brace-expansion-ii/
Hard. String.

Under a grammar given below, strings can represent a set of lowercase words.
Let's use R(expr) to denote the set of words the expression represents.

Grammar can best be understood through simple examples:

Single letters represent a singleton set containing that word.

R("a") = {"a"}
R("w") = {"w"}

When we take a comma delimited list of 2 or more expressions,
we take the union of possibilities.

R("{a,b,c}") = {"a","b","c"}
R("{{a,b},{b,c}}") = {"a","b","c"}
(notice the final set only contains each word at most once)

When we concatenate two expressions, we take the set of possible concatenations
between two words where the first word comes from the first expression
and the second word comes from the second expression.

R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
R("{a{b,c}}{{d,e},f{g,h}}") = R("{ab,ac}{dfg,dfh,efg,efh}") =
{"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}

Formally, the 3 rules for our grammar:

For every lowercase letter x, we have R(x) = {x}
For expressions e_1, e_2, ... , e_k with k >= 2, we have R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
For expressions e_1 and e_2, we have R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)},
where + denotes concatenation, and × denotes the cartesian product.

Given an expression representing a set of words under the given grammar,
return the sorted list of words that the expression represents.

Example 1:

Input: "{a,b}{c{d,e}}"
Output: ["acd","ace","bcd","bce"]

Example 2:

Input: "{{a,z},a{b,c},{ab,z}}"
Output: ["a","ab","ac","z"]
Explanation: Each distinct word is written only once in the final answer.

Constraints:

1 <= expression.length <= 50
expression[i] consists of '{', '}', ','or lowercase English letters.
The given expression represents a set of words based on the grammar given in the description.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class _1096_BraceExpansionII {

    public static List<String> braceExpansionII(String expression) {
        if (expression == null || expression.length() == 0) return new ArrayList<>();

        final char[] chars = expression.toCharArray();
        return braceExpansionII(chars, 0, chars.length);
    }

    private static List<String> braceExpansionII(final char[] chars, int start, int end) {
        char preSign = ',';
        // Save List<String>
        final Stack<List<String>> stack = new Stack<>();
        for (int i = start; i < end; i++) {
            char c = chars[i];
            // case 1: {...} recursive, stack.operate(resList) by preSign
            if (c == '{') {
                int j = i, p = 1;
                while (chars[j] != '}' || p != 0) {
                    j++;
                    if (chars[j] == '{') p++;
                    if (chars[j] == '}') p--;
                }
                List<String> sList = braceExpansionII(chars, i + 1, j);
                if (preSign == '*') {
                    stack.push(merge(stack.pop(), sList));
                } else {
                    stack.push(sList);
                }
                i = j;
                // default preSign is *
                preSign = '*';
            } else if (Character.isLetter(c)) {
                // case 2: letter operate by preSign
                List<String> sList = new ArrayList<>();
                sList.add(String.valueOf(c));
                if (preSign == '*') {
                    stack.push(merge(stack.pop(), sList));
                } else {
                    stack.push(sList);
                }
                // default preSign is *
                preSign = '*';
            }
            // case 3: if c is ',', preSign is plus (default preSign is *)
            if (c == ',' || i == end) {
                preSign = ',';
            }
        }
        // output stack to one dimension list
        final ArrayList<String> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            for (String l : stack.pop()) {
                if (!res.contains(l)) {
                    res.add(l);
                }
            }
        }
        // sort by lexicographical order
        Collections.sort(res);
        return res;
    }

    /**
     * Cartesian product of two sets
     */
    private static List<String> merge(List<String> list1, List<String> list2) {
        final ArrayList<String> res = new ArrayList<>();
        for (String s1 : list1) {
            for (String s2 : list2) {
                res.add(s1 + s2);
            }
        }
        return res;
    }
}
