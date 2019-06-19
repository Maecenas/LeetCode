package LeetCode;

/*
https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
Medium. Array, Binary Search.

A conveyor belt has packages that must be shipped from one port to another within D days.

The i-th package on the conveyor belt has a weight of weights[i].
Each day, we load the ship with packages on the conveyor belt
(in the order given by weights). We may not load more weight than
the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all
the packages on the conveyor belt being shipped within D days.

Example 1:

Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
Output: 15
Explanation:
A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of
capacity 14 and splitting the packages into parts like
(2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.

Example 2:

Input: weights = [3,2,2,4,1,4], D = 3
Output: 6
Explanation:
A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4

Example 3:

Input: weights = [1,2,3,1,1], D = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1

Note:

1 <= D <= weights.length <= 50000
1 <= weights[i] <= 500
*/

class _1011_CapacityToShipPackagesWithinDDays {

    /**
     * O(log(sum(nums) - max(nums))), O(1)
     * Binary Search the capacity to ship within D days
     */
    public static int shipWithinDays(int[] weights, int D) {
        if (weights == null || weights.length == 0 || D <= 0 || D > weights.length) return 0;

        int lo = 0, hi = 0;
        // lo = max(weights), hi = sum(weights)
        for (int w : weights) {
            lo = Math.max(lo, w);
            hi += w;
        }

        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            // extract into methods is faster
            if (shipWithinDays(weights, D, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    public static int shipWithinDays2(int[] weights, int D) {
        if (weights == null || weights.length == 0 || D <= 0 || D > weights.length) return 0;

        int lo = 1, hi = (weights.length * 500) / D;
        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (shipWithinDays(weights, D, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return hi;
    }

    private static boolean shipWithinDays(int[] weights, int D, int capacity) {
        int sum = 0;
        for (int w : weights) {
            if (w > capacity) return false;
            if (sum + w > capacity) {
                D--;
                if (D <= 0) return false;
                sum = w;
            } else {
                sum += w;
            }
        }
        return D > 0;
    }
}
