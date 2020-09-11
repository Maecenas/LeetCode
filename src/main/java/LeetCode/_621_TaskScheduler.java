package LeetCode;

/*
https://leetcode.com/problems/task-scheduler/
Medium. Array, Greedy, Queue.

Given a char array representing tasks CPU need to do. It contains
capital letters A to Z where different letters represent different tasks.
Tasks could be done without original order. Each task could be done in one
interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means
between two same tasks, there must be at least n intervals
that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU
will take to finish all the given tasks.

Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.

Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

class _621_TaskScheduler {

    private static final int R = 26;  // uppercase letters

    /**
     * O(n), O(1)
     * Using PriorityQueue
     */
    public static int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;

        final int[] map = new int[R];
        for (char task : tasks) {
            map[task - 'A']++;
        }

        final PriorityQueue<Integer> pq = new PriorityQueue<>(R, Collections.reverseOrder());

        for (int time : map) {
            if (time > 0) {
                pq.offer(time);
            }
        }

        final ArrayList<Integer> rest = new ArrayList<>();
        int res = 0;

        while (!pq.isEmpty()) {
            for (int i = 0; i <= n; i++) {
                if (!pq.isEmpty()) {
                    if (pq.peek() > 1) {
                        rest.add(pq.poll() - 1);
                    } else {
                        pq.poll();
                    }
                }
                res++;
                if (pq.isEmpty() && rest.size() == 0) break;
            }

            for (int task : rest) {
                pq.offer(task);
            }
            rest.clear();
        }
        return res;
    }

    /**
     * O(n), O(1)
     * Using Sorting as naive PriorityQueue
     */
    public static int leastInterval2(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;

        final int[] map = new int[R];
        for (char task : tasks) {
            map[task - 'A']++;
        }

        Arrays.sort(map);
        int res = 0;

        while (map[R - 1] > 0) {
            // consume tasks during epoch
            for (int i = 0; i <= n; i++) {
                if (i < R && map[R - 1 - i] > 0) {
                    map[R - 1 - i]--;
                }
                res++;
                if (map[R - 1] == 0) break;
            }
            Arrays.sort(map);
        }
        return res;
    }

    /**
     * O(n), O(1)
     * Way much faster
     */
    public static int leastInterval3(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;

        final int[] map = new int[R];
        for (char task : tasks) {
            map[task - 'A']++;
        }

        Arrays.sort(map);

        // must be max - 1
        final int max = map[R - 1] - 1;
        int slots = max * n;
        for (int i = R - 2; i >= 0 && map[i] > 0; i--) {
            slots -= Math.min(map[i], max);
            if (slots <= 0) break;
        }
        return slots > 0 ? slots + tasks.length : tasks.length;
    }

    /**
     * O(n), O(1)
     * Math, Fastest
     */
    public static int leastInterval4(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;

        final int[] map = new int[R];
        for (char task : tasks) {
            map[task - 'A']++;
        }

        // max frequency
        int f_max = 0;
        for (int f : map) {
            f_max = Math.max(f_max, f);
        }

        // count the most frequent tasks
        int n_max = 0;
        for (int f : map) {
            if (f == f_max) {
                n_max++;
            }
        }

        return Math.max(tasks.length, (f_max - 1) * (n + 1) + n_max);
    }
}
