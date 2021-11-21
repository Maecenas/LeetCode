package com.leetcode;

/*
https://leetcode.com/problems/duplicate-zeros/
Easy.

Given a fixed length array arr of integers, duplicate each occurrence of zero,
shifting the remaining elements to the right.

Note that elements beyond the length of the original array are not written.

Do the above modifications to the input array in place,
do not return anything from your function.

Example 1:

Input: [1,0,2,3,0,4,5,0]
Output: null
Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
Example 2:

Input: [1,2,3]
Output: null
Explanation: After calling your function, the input array is modified to: [1,2,3]

Note:

1 <= arr.length <= 10000
0 <= arr[i] <= 9
*/

class _1089_DuplicateZeros {

    public static void duplicateZeros(int[] arr) {
        if (arr == null || arr.length == 0) return;

        final int len = arr.length;

        int i = 0, count = 0;
        while (count < len) {
            count += (arr[i] == 0 ? 2 : 1);
            i++;
        }

        // early return
        if (i == len) return;
        // remove trailing 0
        if (count > len) {
            arr[len - 1] = 0;
            i--;
            count -= 2;
        }

        while (i > 0) {
            i--;
            if (arr[i] == 0) {
                arr[count - 1] = arr[i];
                arr[count - 2] = arr[i];
                count -= 2;
            } else {
                arr[count - 1] = arr[i];
                count--;
            }
        }
    }

    public static void duplicateZeros2(int[] arr) {
        if (arr == null || arr.length == 0) return;

        int i = 0, shift = 0;
        for (; shift + i < arr.length; i++) {
            shift += (arr[i] == 0 ? 1 : 0);
        }
        for (i--; shift > 0; i--) {
            if (i + shift < arr.length) {
                arr[i + shift] = arr[i];
            }
            if (arr[i] == 0) {
                shift--;
                arr[i + shift] = arr[i];
            }
        }
    }
}
