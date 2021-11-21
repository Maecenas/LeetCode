package com.leetcode;

/*
https://leetcode.com/problems/friends-of-appropriate-ages/
Medium. Array.

Some people will make friend requests. The list of their ages is given
and ages[i] is the age of the ith person.

Person A will NOT friend request person B (B != A)
if any of the following conditions are true:

age[B] <= 0.5 * age[A] + 7
age[B] > age[A]
age[B] > 100 && age[A] < 100

Otherwise, A will friend request B.

Note that if A requests B, B does not necessarily request A.
Also, people will not friend request themselves.

How many total friend requests are made?

Example 1:

Input: [16,16]
Output: 2
Explanation: 2 people friend request each other.

Example 2:

Input: [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.

Example 3:

Input: [20,30,100,110,120]
Output:
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.

Notes:

1 <= ages.length <= 20000.
1 <= ages[i] <= 120.
*/

class _825_FriendsOfAppropriateAges {

    private static final int R = 121;  // ages[i] + 1

    public static int numFriendRequests(int[] ages) {
        if (ages == null || ages.length == 0) return 0;

        // Arrays.sort(ages);

        final int[] count = new int[R], sum = new int[R];
        for (int age : ages) {
            count[age]++;
        }
        for (int i = 1; i < R; i++) {
            sum[i] = count[i] + sum[i - 1];
        }

        // B is in range (0.5 * A + 7, A]
        // (B > 100 && A < 100) is redundant
        // solving the equation (0.5A + 7 < A) gives valid A start from 15
        int res = 0;
        for (int i = 15; i < R; i++) {
            if (count[i] == 0) continue;
            // (0.5 * A + 7, A] exclude A
            int num = sum[i] - sum[(i >> 1) + 7] - 1;
            res += num * count[i];
        }

        return res;
    }
}
