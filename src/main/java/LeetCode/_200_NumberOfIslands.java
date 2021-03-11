package LeetCode;

/*
https://leetcode.com/problems/number-of-islands/
Medium. Depth-first Search, Breadth-first Search, Union Find.


Given an m x n 2d grid map of '1's (land) and '0's (water), return the number
of islands.

An island is surrounded by water and is formed by connecting adjacent lands
horizontally or vertically. You may assume all four edges of the grid are all
surrounded by water.

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
*/

import java.util.ArrayDeque;
import java.util.Deque;

class _200_NumberOfIslands {

    private static void dfs(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') return;

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public static int numIslandsDFS(char[][] grid) {
        if (grid == null || grid.length == 0 || grid.length > 300
                || grid[0].length == 0 || grid[0].length > 300) return 0;

        final int R = grid.length, C = grid[0].length;
        int res = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == '1') {
                    res++;
                    dfs(grid, r, c);
                }
            }
        }

        return res;
    }

    public static int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0 || grid.length > 300
                || grid[0].length == 0 || grid[0].length > 300) return 0;

        final int R = grid.length, C = grid[0].length;
        final Deque<Integer> neighbors = new ArrayDeque<>();
        int res = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == '1') {
                    res++;
                    // visited
                    grid[r][c] = '0';

                    neighbors.add(r * C + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.poll();
                        int row = id / C;
                        int col = id % C;
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            neighbors.add((row - 1) * C + col);
                            grid[row - 1][col] = '0';
                        }
                        if (row + 1 < R && grid[row + 1][col] == '1') {
                            neighbors.add((row + 1) * C + col);
                            grid[row + 1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            neighbors.add(row * C + col - 1);
                            grid[row][col - 1] = '0';
                        }
                        if (col + 1 < C && grid[row][col + 1] == '1') {
                            neighbors.add(row * C + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * See also: weighted quick-union with path compression
     * <a>https://algs4.cs.princeton.edu/15uf/WeightedQuickUnionPathCompressionUF.java.html</a>
     */
    static class UnionFind {
        int[] parent;
        int[] size;
        int count;

        public UnionFind(char[][] grid) {
            final int n = grid.length * grid[0].length;
            parent = new int[n];
            size = new int[n];
            count = 0;

            int id = 0;
            for (char[] row : grid) {
                for (char ch : row) {
                    if (ch == '1') {
                        parent[id] = id;
                        size[id] = 1;
                        count++;
                    }
                    id++;
                }
            }
        }

        public int count() {
            return count;
        }

        public int find(int p) {
            // without path compression
            //while (p != parent[p]) {
            //    p = parent[p];
            //}
            //return parent[p];

            // path compression iteratively
            //int root = p;
            //while (root != parent[root]) {
            //    root = parent[root];
            //}
            //while (p != root) {
            //    int newP = parent[p];
            //    parent[p] = root;
            //    p = newP;
            //}
            //return root;

            // path compression recursively
            if (parent[p] != p) parent[p] = find(parent[p]);
            return parent[p];
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            // make smaller root point to larger one
            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            count--;
        }
    }

    public static int numIslandsUF(char[][] grid) {
        if (grid == null || grid.length == 0 || grid.length > 300
                || grid[0].length == 0 || grid[0].length > 300) return 0;

        final int R = grid.length, C = grid[0].length;
        final UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == '0') continue;
                grid[r][c] = '0';
                if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                    uf.union(r * C + c, (r - 1) * C + c);
                }
                if (r + 1 < R && grid[r + 1][c] == '1') {
                    uf.union(r * C + c, (r + 1) * C + c);
                }
                if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                    uf.union(r * C + c, r * C + c - 1);
                }
                if (c + 1 < C && grid[r][c + 1] == '1') {
                    uf.union(r * C + c, r * C + c + 1);
                }
            }
        }

        return uf.count();
    }
}
