package com.leetcode.utils;

public final class BinarySearch {

    public static int indexOf(int[] nums, int target) {
        // [lo, hi]
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else if (nums[mid] == target) {
                return mid;
            }
        }
        return -1;
    }

    public static int firstIndexOf(int[] nums, int target) {
        // [lo, hi]
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else if (nums[mid] == target) {
                hi = mid - 1;
            }
        }
        if (lo >= nums.length || nums[lo] != target) return -1;
        return lo;
    }

    private static int leftBoundClosedOpen(int[] nums, int target) {
        // [lo, hi)
        int lo = 0, hi = nums.length, mid;
        while (lo < hi) {
            mid = (lo + hi) >>> 1;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else if (nums[mid] > target) {
                hi = mid;
            } else if (nums[mid] == target) {
                hi = mid;
            }
        }
        if (lo >= nums.length || nums[lo] != target) return -1;
        return lo;
    }

    public static int lastIndexOf(int[] nums, int target) {
        // [lo, hi]
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else if (nums[mid] == target) {
                lo = mid + 1;
            }
        }
        if (hi < 0 || nums[hi] != target) return -1;
        return hi;
    }

    private static int rightBoundClosedOpen(int[] nums, int target) {
        // [lo, hi)
        int lo = 0, hi = nums.length, mid;
        while (lo < hi) {
            mid = (lo + hi) >>> 1;
            if (nums[mid] == target) {
                lo = mid + 1;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else if (nums[mid] > target) {
                hi = mid;
            }
        }
        if (lo == 0 || nums[lo - 1] != target) return -1;
        return lo - 1;
    }
}
