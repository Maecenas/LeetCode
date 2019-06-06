package LeetCode;

/*
https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
Hard. Array.

This question is the same as "Max Chunks to Make Sorted" except
the integers of the given array are not necessarily distinct,
the input array could be up to length 2000,
and the elements could be up to 10^8.

Given an array arr of integers (not necessarily distinct),
we split the array into some number of "chunks" (partitions),
and individually sort each chunk. After concatenating them,
the result equals the sorted array.

What is the most number of chunks we could have made?

Example 1:

Input: arr = [5,4,3,2,1]
Output: 1
Explanation:
Splitting into two or more chunks will not return the required result.
For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3], which isn't sorted.

Example 2:

Input: arr = [2,1,3,4,4]
Output: 4
Explanation:
We can split into two chunks, such as [2, 1], [3, 4, 4].
However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.

Note:

arr will have length in range [1, 2000].
arr[i] will be an integer in range [0, 10^8].
*/

/**
 * @see _769_MaxChunksToMakeSorted
 */
class _768_MaxChunksToMakeSortedII {

    /**
     * O(n), O(n)
     */
    public static int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        else if (arr.length == 1) return 1;

        final int len = arr.length;
        final int[] minOfRight = new int[len];

        // min[i] = min(arr[i:])
        minOfRight[len - 1] = arr[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            minOfRight[i] = Math.min(arr[i], minOfRight[i + 1]);
        }

        int res = 1, max = Integer.MIN_VALUE;
        for (int i = 0; i < len - 1; i++) {
            max = Math.max(max, arr[i]);
            if (max <= minOfRight[i + 1]) {
                res++;
            }
        }
        return res;
    }
}
