package LeetCode;

/*
https://leetcode.com/problems/distribute-candies-to-people/
Easy. Math.

We distribute some number of candies, to a row of n = num_people
people in the following way:

We then give 1 candy to the first person, 2 candies to the second
person, and so on until we give n candies to the last person.

Then, we go back to the start of the row, giving n + 1 candies
to the first person, n + 2 candies to the second person, and so on
until we give 2 * n candies to the last person.

This process repeats (with us giving one more candy each time, and moving to
the start of the row after we reach the end) until we run out of candies.
The last person will receive all of our remaining candies
(not necessarily one more than the previous gift).

Return an array (of length num_people and sum candies) that
represents the final distribution of candies.

Example 1:

Input: candies = 7, num_people = 4
Output: [1,2,3,1]
Explanation:
On the first turn, ans[0] += 1, and the array is [1,0,0,0].
On the second turn, ans[1] += 2, and the array is [1,2,0,0].
On the third turn, ans[2] += 3, and the array is [1,2,3,0].
On the fourth turn, ans[3] += 1 (because there is only one candy left), and the final array is [1,2,3,1].

Example 2:

Input: candies = 10, num_people = 3
Output: [5,2,3]
Explanation:
On the first turn, ans[0] += 1, and the array is [1,0,0].
On the second turn, ans[1] += 2, and the array is [1,2,0].
On the third turn, ans[2] += 3, and the array is [1,2,3].
On the fourth turn, ans[0] += 4, and the final array is [5,2,3].

Constraints:

1 <= candies <= 10^9
1 <= num_people <= 1000
*/

class _1103_DistributeCandiesToPeople {

    /**
     * O(sqrt(candies)), O(logn)
     */
    public static int[] distributeCandies(int candies, int n) {
        if (candies < 1 || n < 1) return new int[0];

        final int[] res = new int[n];
        for (int i = 0; candies > 0; i++) {
            res[i % n] += Math.min(candies, i + 1);
            candies -= i + 1;
        }
        return res;
    }

    /**
     * O(n), O(1)
     * Math
     */
    public static int[] distributeCandies2(int candies, int n) {
        if (candies < 1 || n < 1) return new int[0];

        int x = (int) (Math.sqrt(candies * 2 + 0.25) - 0.5);
        final int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int m = x / n + (x % n > i ? 1 : 0);
            res[i] = m * (i + 1) + m * (m - 1) / 2 * n;
        }
        res[x % n] += candies - x * (x + 1) / 2;
        return res;
    }
}
