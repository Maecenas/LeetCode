package LeetCode;

/*
https://leetcode.com/problems/statistics-from-a-large-sample/
Medium.

We sampled integers between 0 and 255, and stored the results in an array count:
count[k] is the number of integers we sampled equal to k.

Return the minimum, maximum, mean, median, and mode of the sample respectively,
as an array of floating point numbers. The mode is guaranteed to be unique.

(Recall that the median of a sample is:

The middle element, if the elements of the sample were sorted and the number of elements is odd;
The average of the middle two elements, if the elements of the sample were sorted and the number of elements is even.)

Example 1:

Input: count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
Output: [1.00000,3.00000,2.37500,2.50000,3.00000]
Example 2:

Input: count = [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
Output: [1.00000,4.00000,2.18182,2.00000,1.00000]

Constraints:

count.length == 256
1 <= sum(count) <= 10^9
The mode of the sample that count represents is unique.
Answers within 10^-5 of the true value will be accepted as correct.
*/

class _1093_StatisticsFromALargeSample {

    private static final int R = 256;  // range of elements

    public static double[] sampleStats(int[] count) {
        if (count == null || count.length != R) return new double[0];

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, n = 0, mode = 0;
        long sum = 0;
        for (int i = 0; i < count.length; i++) {
            int cnt = count[i];
            if (min == Integer.MAX_VALUE && cnt != 0) min = i;
            if (max == Integer.MIN_VALUE && count[count.length - 1 - i] != 0) max = count.length - 1 - i;
            n += cnt;
            sum += i * cnt;
            if (cnt > count[mode]) mode = i;
        }

        // O(logn) search for median
        for (int i = 1; i < R; i++) {
            count[i] += count[i - 1];
        }

        //// O(n) search for median
        //final int target = (n + 1) >> 1;
        //int medianCnt = 0;
        //double median = 0.0;
        //for (int i = 0; i < count.length; i++) {
        //    int num = count[i];
        //    medianCnt += num;
        //    if (medianCnt >= target) {
        //        if (medianCnt == target && ((n & 0x1) == 0x0)) {
        //            median = i;
        //            do {
        //                i++;
        //            } while (count[i] == 0);
        //            median = (median + i) / 2.0;
        //            break;
        //        } else {
        //            median = i;
        //            break;
        //        }
        //    }
        //}

        double median = binarySearch(count, (n + 1) >> 1);
        if ((n & 0x1) == 0x0) {
            median = (median + binarySearch(count, (n >> 1) + 1)) / 2.0;
        }

        //// restore modified input
        //for (int i = count.length - 1; i > 0; i--) {
        //    count[i] -= count[i - 1];
        //}

        return new double[]{min, max, ((double) sum) / n, median, mode};
    }

    private static int binarySearch(int[] count, int target) {
        int lo = 0, hi = count.length - 1;
        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (count[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
