package com.leetcode;

/*
https://leetcode.com/problems/open-the-lock/
Medium. Array, Hash Table, String, Breadth-First Search.

You have a lock in front of you with 4 circular wheels. Each wheel has 10
slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate
freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
Each move consists of turning one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning
if the lock displays any of these codes, the wheels of the lock will
stop turning and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the
lock, return the minimum total number of turns required to open the lock,
or -1 if it is impossible.

Example 1:
 Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 Output: 6
 Explanation:
  A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
  Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
  because the wheels of the lock become stuck after the display becomes the dead end "0102".

Example 2:
 Input: deadends = ["8888"], target = "0009"
 Output: 1
 Explanation:
  We can turn the last wheel in reverse to move from "0000" -> "0009".

Example 3:
 Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"],
 target = "8888"
 Output: -1
 Explanation:
  We can't reach the target without getting stuck.

Example 4:
 Input: deadends = ["0000"], target = "8888"
 Output: -1

Constraints:
 1 <= deadends.length <= 500
 deadends[i].length == 4
 target.length == 4
 target will not be in the list deadends.
 target and deadends[i] consist of digits only.
*/

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

class _752_OpenTheLock {

    public static int openLock(String[] deadends, String target) {
        if (deadends == null || deadends.length == 0 || deadends.length > 500
            || target == null || target.length() != 4) return -1;

        final Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        if (visited.contains("0000")) return -1;

        final Deque<String> queue = new ArrayDeque<>();
        queue.offer("0000");
        visited.add("0000");
        int step = 0;

        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                String curr = queue.poll();

                if (target.equals(curr)) {
                    return step;
                }

                for (int j = 0; j < 4; j++) {
                    final String up = plusOne(curr, j);
                    if (!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    final String down = minusOne(curr, j);
                    if (!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private static String plusOne(String s, int j) {
        final char[] ch = s.toCharArray();
        if (ch[j] == '9') ch[j] = '0';
        else ch[j]++;
        return String.valueOf(ch);
    }

    private static String minusOne(String s, int j) {
        final char[] ch = s.toCharArray();
        if (ch[j] == '0') ch[j] = '9';
        else ch[j]--;
        return String.valueOf(ch);
    }

    public static int openLockBiBfs(String[] deadends, String target) {
        if (deadends == null || deadends.length == 0 || deadends.length > 500
                || target == null || target.length() != 4) return -1;

        final Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        if (visited.contains("0000")) return -1;

        Set<String> q1 = new HashSet<>();
        q1.add("0000");
        Set<String> q2 = new HashSet<>();
        q2.add(target);
        int step = 0;

        while (!q1.isEmpty() && !q2.isEmpty()) {
            Set<String> set = new HashSet<>();

            // search from q1
            for (String curr : q1) {
                if (q2.contains(curr)) return step;
                if (visited.contains(curr)) continue;
                visited.add(curr);

                for (int j = 0; j < 4; j++) {
                    String up = plusOne(curr, j);
                    if (!visited.contains(up)) {
                        set.add(up);
                        //visited.add(up);
                    }
                    String down = minusOne(curr, j);
                    if (!visited.contains(down)) {
                        set.add(down);
                        //visited.add(down);
                    }
                }
            }
            step++;
            // swap q1 and q2, next round search from q2
            q1 = q2;
            q2 = set;
        }
        return -1;
    }
}
