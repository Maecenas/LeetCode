package com.leetcode.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class QuickSelect {

    private static void quickSelect(int[] nums, int k) {
        shuffle(nums);

        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int j = partition(nums, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = lo - 1;
            else /* if (j == k) */ return;
        }
    }

    private static int partition(int[] nums, int lo, int hi) {
        // use nums[lo] as pivot
        // iterate nums to find j, let nums[:j-1] <= nums[j] <= nums[j+1:]
        int i = lo, j = hi + 1;
        while (true) {
            while (i < hi && nums[++i] < nums[lo]) {
                if (i == hi) break;
            }
            while (j > lo && nums[lo] < nums[--j]) {
                if (j == lo) break;
            }
            if (i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, lo, j);
        return j;
    }

    private static void swap(final int[] nums, final int i, final int j) {
        final int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private static void shuffle(int[] nums) {
        final Random rand = ThreadLocalRandom.current();
        for (int i = 1; i < nums.length; i++) {
            int r = rand.nextInt(i + 1);
            swap(nums, i, r);
        }

    }
}
