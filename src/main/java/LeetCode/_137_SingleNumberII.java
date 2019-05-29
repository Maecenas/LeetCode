package LeetCode;

/*
https://leetcode.com/problems/single-number-ii/
Medium. Bit Manipulation.

Given a non-empty array of integers, every element appears three times except for one,
which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity.
Could you implement it without using extra memory?

Example 1:

Input: [2,2,3,2]
Output: 3

Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99
*/

class _137_SingleNumberII {

    /**
     * Using two integer a, b as BitSet to encode 4 different status (0, 1, 2, 3)
     * and can be reduced to 3 states (0, 1, 2). ceil(log3) = 2
     * Drawing state moving table as follows:
     * <p>
     * current  meet    ouput
     * a b      c        a b
     * ----------------------
     * 0 0      1        0 1
     * 0 0      0        0 0
     * 0 1      1        1 0
     * 0 1      0        0 1
     * 1 0      1        0 0
     * 1 0      0        1 0
     * <p>
     * From the table can be concluded as in digital logic,
     * First, we pick out a group of result by (output_a = 1)
     * Then, for each move, if any of a, b and c is the same with output_a,
     * we wrote down ORIGINAL else NOT value and use AND to concatenate them.
     * Last, use OR to connect moves in a group
     * <p>
     * a = ( a & ~b & ~c) | (~a &  b & c)
     * b = (~a &  b & ~c) | (~a & ~b & c)
     * <p>
     * The number required is then encoded in b itself.
     */
    public static int singleNumber(int[] nums) {
        if (nums == null) return 0;
        else if (nums.length == 1) return nums[0];
        else if (nums.length < 4) return 0;

        int a = 0, b = 0, tmpA;
        for (int c : nums) {
            tmpA = (a & ~b & ~c) | (~a & b & c);
            b = (~a & b & ~c) | (~a & ~b & c);
            a = tmpA;
        }
        return b;
    }

    /**
     * Another ways of depicting the state moving procedure
     * Start by dealing with the least important bit needless of temp
     */
    public static int singleNumber2(int[] nums) {
        if (nums == null) return 0;
        else if (nums.length == 1) return nums[0];
        else if (nums.length < 4) return 0;

        int a = 0, b = 0;
        for (int c : nums) {
            b = (b ^ c) & ~a;
            a = (a ^ c) & ~b;
        }
        return b;
    }
}
