package com.leetcode;

/*
https://leetcode.com/problems/1-bit-and-2-bit-characters/
Easy. Array.

We have two special characters. The first character can be represented by one bit 0.
The second character can be represented by two bits (10 or 11).

Now given a string represented by several bits. Return whether the last character
must be a one-bit character or not. The given string will always end with a zero.

Example 1:

Input:
bits = [1, 0, 0]
Output: True
Explanation:
The only way to decode it is two-bit character and one-bit character.
So the last character is one-bit character.

Example 2:

Input:
bits = [1, 1, 1, 0]
Output: False
Explanation:
The only way to decode it is two-bit character and two-bit character.
So the last character is NOT one-bit character.

Note:
1 <= len(bits) <= 1000.
bits[i] is always 0 or 1.
*/

class _717_OneBitAndTwoBitCharacters {

    public static boolean isOneBitCharacter(int[] bits) {
        if (bits == null || bits.length == 0) return false;
        else if (bits.length == 1 || bits[bits.length - 2] == 0) return true;

        int i = 0;
        while (i < bits.length - 1) {
            i += bits[i] + 1;
        }
        return (i == bits.length - 1);
    }

    /**
     * Return true if and only if there are an even number of ones present
     */
    public static boolean isOneBitCharacter2(int[] bits) {
        if (bits == null || bits.length == 0) return false;
        else if (bits.length == 1 || bits[bits.length - 2] == 0) return true;

        int i = bits.length - 2;
        while (i >= 0 && bits[i] == 1) {
            i--;
        }
        return ((bits.length - i) & 0x1) == 0x0;
    }
}
