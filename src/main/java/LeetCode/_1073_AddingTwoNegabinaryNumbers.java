package LeetCode;

/*
https://leetcode.com/problems/adding-two-negabinary-numbers/
Medium.

Given two numbers arr1 and arr2 in base -2, return the result of adding them together.

Each number is given in array format: as an array of 0s and 1s,
from most significant bit to least significant bit.
For example, arr = [1, 1, 0, 1] represents the number (-2)^3 + (-2)^2 + (-2)^0 = -3.
A number arr in array format is also guaranteed to have no leading zeros:
either arr == [0] or arr[0] == 1.

Return the result of adding arr1 and arr2 in the same format:
as an array of 0s and 1s with no leading zeros.

Example 1:

Input: arr1 = [1,1,1,1,1], arr2 = [1,0,1]
Output: [1,0,0,0,0]
Explanation: arr1 represents 11, arr2 represents 5, the output represents 16.

Note:

1 <= arr1.length <= 1000
1 <= arr2.length <= 1000
arr1 and arr2 have no leading zeros
arr1[i] is 0 or 1
arr2[i] is 0 or 1
*/

import java.util.ArrayList;

class _1073_AddingTwoNegabinaryNumbers {

    public static int[] addNegabinary(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) return new int[0];
        else if (arr1.length == 0) return arr2;
        else if (arr2.length == 0) return arr1;

        final ArrayList<Integer> digits = new ArrayList<>();
        final int m = arr1.length - 1, n = arr2.length - 1;

        int carry = 0;
        for (int i = 0; ; i++) {
            int digit1 = i <= m ? arr1[m - i] : 0;
            int digit2 = i <= n ? arr2[n - i] : 0;
            carry += digit1 + digit2;

            // early return
            if (i >= m && i >= n && carry == 0) break;

            // carry divmod base (-2)
            digits.add(carry & 0x1);
            carry = -(carry >> 1);
        }

        // early return
        if (digits.size() == 0) return new int[]{0};
        else if (digits.size() == 1) return new int[]{digits.get(0)};

        // remove leading zeroes
        int size = digits.size() - 1;
        while (size >= 0) {
            if (digits.get(size) == 1) break;
            size--;
        }
        // no 1 in the result
        if (size == -1) return new int[]{0};

        final int[] res = new int[size + 1];
        for (int i = 0; i <= size; i++) {
            res[i] = digits.get(size - i);
        }
        return res;
    }
}
