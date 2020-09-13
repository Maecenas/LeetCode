package LeetCode;

/*
https://leetcode.com/problems/verifying-an-alien-dictionary/
Easy. Hash Table.

In an alien language, surprisingly they also use english lowercase letters,
but possibly in a different order.
The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the
alphabet, return true if and only if the given words are sorted
lexicographicaly in this alien language.

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language,
then the sequence is sorted.

Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language,
then words[0] > words[1], hence the sequence is unsorted.

Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is
shorter (in size.) According to lexicographical rules "apple" > "app",
because 'l' > '∅', where '∅' is defined as the blank character which is less
than any other character (More info).

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
*/

/**
 * @see _269_AlienDictionary
 */
class _953_VerifyingAnAlienDictionary {

    private static final int R = 26;  // lowercase letters
    private static final int[] map = new int[R];

    public static boolean isAlienSorted(String[] words, String order) {
        if (words == null || words.length <= 1) return true;
        if (order == null || order.length() != R) return false;

        for (int i = 0; i < R; i++) {
            map[order.charAt(i) - 'a'] = i;
        }

        String prev = words[0];
        for (int i = 1; i < words.length; i++) {
            String word2 = words[i];
            if (!isAlienSorted(prev, word2)) return false;
            prev = word2;
        }
        return true;
    }

    private static boolean isAlienSorted(String word1, String word2) {
        // find the first difference character
        for (int i = 0; i < Math.min(word1.length(), word2.length()); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                return map[word1.charAt(i) - 'a'] <= map[word2.charAt(i) - 'a'];
            }
        }
        return word1.length() <= word2.length();
    }
}
