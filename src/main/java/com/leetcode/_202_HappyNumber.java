package com.leetcode;

/*
https://leetcode.com/problems/happy-number/
Easy. Hash Table, Math.

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process:
Starting with any positive integer,
replace the number by the sum of the squares of its digits,
and repeat the process until the number equals 1 (where it will stay),
or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy numbers.

Example:

Input: 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
*/

import java.util.HashSet;

class _202_HappyNumber {

    // caching sets
    private static final HashSet<Integer> TRUE = new HashSet<>();
    private static final HashSet<Integer> FALSE = new HashSet<>();
    private static final HashSet<Integer> set = new HashSet<>();

    public static boolean isHappy(int n) {
        if (n <= 0) return false;

        if (TRUE.contains(n)) return true;
        else if (FALSE.contains(n)) return false;

        while (set.add(n)) {
            if (n == 1) {
                TRUE.addAll(set);
                set.clear();
                return true;
            }
            n = getSquaresOfDigits(n);
        }
        FALSE.addAll(set);
        set.clear();
        return false;
    }

    /**
     * The same method used to detect cycle in linked list
     */
    public static boolean isHappyFloydCycleDetection(int n) {
        if (n <= 0) return false;

        int slow = n, fast = n;
        do {
            slow = getSquaresOfDigits(slow);
            fast = getSquaresOfDigits(fast);
            fast = getSquaresOfDigits(fast);
        } while (slow != fast);

        return slow == 1;
    }

    private static int getSquaresOfDigits(int n) {
        int sum = 0, remain;
        while (n != 0) {
            remain = n % 10;
            sum += remain * remain;
            n /= 10;
        }
        return sum;
    }
}
