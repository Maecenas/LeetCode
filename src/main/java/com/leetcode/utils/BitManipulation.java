package com.leetcode.utils;

public final class BitManipulation {

    public static char toLowerCase(char ch) {
        if (!Character.isLetter(ch)) return ch;

        // bit mask of 0010 0000 (32)
        return (char) (ch | ' ');
    }

    public static char toUpperCase(char ch) {
        if (!Character.isLetter(ch)) return ch;

        // bit mask of 0101 1111 (95)
        return (char) (ch & '_');
    }

    public static char toConvertedCase(char ch) {
        if (!Character.isLetter(ch)) return ch;

        // bit mask of 0010 0000 (32)
        return (char) (ch ^ ' ');
    }

    public static int clearRightmostBitOfOne(int n) {
        return n & (n - 1);
    }
}
