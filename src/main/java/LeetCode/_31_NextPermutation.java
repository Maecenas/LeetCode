package LeetCode;

/*
https://leetcode.com/problems/next-permutation/
Medium. Array, Math.

Implement next permutation, which rearranges numbers into
the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it
as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and
its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

/**
 * @see _46_Permutations
 * @see _47_PermutationsII
 */
class _31_NextPermutation {

    /**
     * O(n), O(1)
     * See also: <a>https://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order</a>
     */
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= nums[i + 1]) continue;
            // Find the largest index i such that nums[i] < nums[i + 1]
            // If no such index exists, just reverse nums and done

            // Find the largest index j > i such that nums[j] > nums[i]
            int j = nums.length - 1;
            while (j > i + 1) {
                if (nums[j] > nums[i]) break;
                j--;
            }
            // swap nums[i] and nums[j]
            swap(nums, i, j);
            // Reverse the sub-array nums[i + 1:]
            reverse(nums, i + 1);
            return;
        }
        reverse(nums, 0);
    }

    private static void reverse(int[] nums, int start) {
        for (int end = nums.length - 1; start < end; start++, end--) {
            swap(nums, start, end);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
