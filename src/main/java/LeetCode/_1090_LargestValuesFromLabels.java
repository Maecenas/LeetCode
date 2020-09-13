package LeetCode;

/*
https://leetcode.com/problems/largest-values-from-labels/
Medium.

We have a set of items: the i-th item has value values[i] and label labels[i].

Then, we choose a subset S of these items, such that:

|S| <= num_wanted
For every label L, the number of items in S with label L is <= use_limit.
Return the largest possible sum of the subset S.

Example 1:

Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
Output: 9
Explanation: The subset chosen is the first, third, and fifth item.
Example 2:

Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
Output: 12
Explanation: The subset chosen is the first, second, and third item.
Example 3:

Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
Output: 16
Explanation: The subset chosen is the first and fourth item.
Example 4:

Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
Output: 24
Explanation: The subset chosen is the first, second, and fourth item.

Note:

1 <= values.length == labels.length <= 20000
0 <= values[i], labels[i] <= 20000
1 <= num_wanted, use_limit <= values.length
*/

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

class _1090_LargestValuesFromLabels {

    public static int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        if (values == null || labels == null || values.length == 0 || values.length != labels.length
                || num_wanted == 0 || num_wanted > values.length
                || use_limit == 0 || use_limit > values.length) return 0;

        final int len = values.length;

        TreeMap<Integer, Deque<Integer>> map = new TreeMap<>();
        final HashMap<Integer, Integer> count = new HashMap<>();

        for (int i = 0; i < len; i++) {
            map.putIfAbsent(values[i], new ArrayDeque<>());
            map.get(values[i]).offer(labels[i]);
        }

        int res = 0, num = 0;
        while (num < num_wanted) {
            if (map.isEmpty()) return res;
            Map.Entry<Integer, Deque<Integer>> entry = map.lastEntry();
            int key = entry.getKey();
            if (entry.getValue().isEmpty()) {
                map.remove(key);
                continue;
            }
            int value = entry.getValue().poll();
            int countVal = count.getOrDefault(value, 0);

            if (countVal < use_limit) {
                count.put(value, countVal + 1);
                res += key;
                num++;
            }
        }

        return res;
    }

    public static int largestValsFromLabels2(int[] values, int[] labels, int num_wanted, int use_limit) {
        if (values == null || labels == null || values.length == 0 || values.length != labels.length
                || num_wanted == 0 || num_wanted > values.length
                || use_limit == 0 || use_limit > values.length) return 0;

        final PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> values[b] - values[a]);
        for (int i = 0; i < labels.length; i++) {
            pq.add(i);
        }

        final HashMap<Integer, Integer> count = new HashMap<>();

        int res = 0, num = 0;
        while (!pq.isEmpty()) {
            int index = pq.poll();
            int label = labels[index];
            if (num < num_wanted && count.getOrDefault(label, 0) < use_limit) {
                res += values[index];
                count.put(label, count.getOrDefault(label, 0) + 1);
                num++;
            }
        }
        return res;
    }
}
