package com.leetcode;

/*
https://leetcode.com/problems/friend-circles/
Medium. Depth-first Search, Union Find.

There are N students in a class. Some of them are friends, while some are not.
Their friendship is transitive in nature.
For example, if A is a direct friend of B, and B is a direct friend of C,
then A is an indirect friend of C. And we defined a friend circle
is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in
the class. If M[i][j] = 1, then the ith and jth students are direct friends
with each other, otherwise not. And you have to output the total number of
friend circles among all the students.

Example 1:

Input:
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a frie
nd circle.
The 2nd student himself is in a friend circle. So return 2.

Example 2:

Input:
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd stude
nts are direct friends,
so the 0th and 2nd students are indirect friends. All of them are in the same
friend circle, so return 1.

Constraints:

1 <= N <= 200
M[i][i] == 1
M[i][j] == M[j][i]
*/

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class _547_FriendCircles {

    /**
     * Union Find
     * O(n^3), O(n)
     */
    public static int findCircleNum(int[][] M) {
        if (M == null || M.length == 0 || M.length > 200
                || M[0].length == 0 || M[0].length > 200) return 0;

        final int[] parent = new int[M.length];
        Arrays.fill(parent, -1);
        for (int i = 0; i < M.length; i++) {
            for (int j = i; j < M.length; j++) {
                if (M[i][j] == 1 && i != j) {
                    union(parent, i, j);
                }
            }
        }
        int res = 0;
        for (int val : parent) {
            if (val == -1) {
                res++;
            }
        }
        return res;
    }

    private static int find(int[] parent, int i) {
        if (parent[i] == -1) return i;
        return find(parent, parent[i]);
    }

    private static void union(int[] parent, int x, int y) {
        int xp = find(parent, x);
        int yp = find(parent, y);
        if (xp != yp) {
            parent[xp] = yp;
        }
    }

    /**
     * O(n^2), O(n)
     */
    public static int findCircleNumDFS(int[][] M) {
        if (M == null || M.length == 0 || M.length > 200
                || M[0].length == 0 || M[0].length > 200) return 0;

        final boolean[] visited = new boolean[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    private static void dfs(int[][] M, boolean[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(M, visited, j);
            }
        }
    }

    /**
     * O(n^2), O(n)
     */
    public static int findCircleNumBFS(int[][] M) {
        if (M == null || M.length == 0 || M.length > 200
                || M[0].length == 0 || M[0].length > 200) return 0;

        boolean[] visited = new boolean[M.length];
        int count = 0;
        final Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int s = queue.pop();
                    visited[s] = true;
                    for (int j = 0; j < M.length; j++) {
                        if (M[s][j] == 1 && !visited[j])
                            queue.add(j);
                    }
                }
                count++;
            }
        }
        return count;
    }
}
