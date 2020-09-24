package LeetCode;

/*
https://leetcode.com/problems/high-five/
Easy. Array, Hash Table, Sort.

Given a list of scores of different students, return the average score
of each student's top five scores in the order of each student's id.

Each entry items[i] has items[i][0] the student's id, and items[i][1]
the student's score. The average score is calculated using integer division.

Example 1:

Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
Output: [[1,87],[2,88]]
Explanation:
The average of the student with id = 1 is 87.
The average of the student with id = 2 is 88.6.
But with integer division their average converts to 88.

Note:

1 <= items.length <= 1000
items[i].length == 2
The IDs of the students is between 1 to 1000
The score of the students is between 1 to 100
For each student, there are at least 5 scores
*/

import java.util.Map;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;

class _1086_HighFive {

    public static int[][] highFive(int[][] items) {
        if (items == null || items.length == 0 || items.length > 1_000 || items[0].length != 2) return new int[0][];

        final SortedMap<Integer, PriorityQueue<Integer>> map = new TreeMap<>();

        for (int[] item : items) {
            int id = item[0];
            int score = item[1];

            map.putIfAbsent(id, new PriorityQueue<>(6));
            final PriorityQueue<Integer> pq = map.get(id);
            pq.offer(score);
            if (pq.size() > 5) {
                pq.poll();
            }
            map.put(id, pq);
        }

        int index = 0;
        final int[][] res = new int[map.size()][2];

        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            int id = entry.getKey();
            PriorityQueue<Integer> pq = entry.getValue();

            int sum = 0;
            int size = pq.size();
            while (!pq.isEmpty()) {
                sum += pq.poll();
            }
            res[index][0] = id;
            res[index][1] = sum / size;
            index++;
        }

        return res;
    }
}
