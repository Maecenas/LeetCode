package LeetCode;

/*
https://leetcode.com/problems/moving-stones-until-consecutive-ii/
Medium. Array, Sliding Window.

On an infinite number line, the position of the i-th stone is given by stones[i].
Call a stone an endpoint stone if it has the smallest or largest position.

Each turn, you pick up an endpoint stone and move it to an unoccupied position
so that it is no longer an endpoint stone.

In particular, if the stones are at say, stones = [1,2,5], you cannot move the
endpoint stone at position 5, since moving it to any position (such as 0, or 3)
will still keep that stone as an endpoint stone.

The game ends when you cannot make any more moves,
ie. the stones are in consecutive positions.

When the game ends, what is the minimum and maximum number of moves
that you could have made? Return the answer as an length 2 array:
answer = [minimum_moves, maximum_moves]

Example 1:

Input: [7,4,9]
Output: [1,2]
Explanation:
We can move 4 -> 8 for one move to finish the game.
Or, we can move 9 -> 5, 4 -> 6 for two moves to finish the game.

Example 2:

Input: [6,5,4,3,10]
Output: [2,3]
We can move 3 -> 8 then 10 -> 7 to finish the game.
Or, we can move 3 -> 7, 4 -> 8, 5 -> 9 to finish the game.
Notice we cannot move 10 -> 2 to finish the game, because that would be an illegal move.

Example 3:

Input: [100,101,104,102,103]
Output: [0,0]

Note:

3 <= stones.length <= 10^4
1 <= stones[i] <= 10^9
stones[i] have distinct values.
*/

import java.util.Arrays;

class _1040_MovingStonesUntilConsecutiveII {

    /**
     * O(nlogn), O(1)
     */
    public static int[] numMovesStonesII(int[] stones) {
        if (stones == null || stones.length < 3) return new int[0];

        Arrays.sort(stones);

        final int len = stones.length;
        // find consecutive n positions
        // early return
        if (stones[len - 1] - stones[0] + 1 == len) return new int[]{0, 0};

        int min = len;
        for (int i = 0, j = 1; j < len; j++) {
            if (min == 1) break;
            while (stones[j] - stones[i] >= len) {
                i++;
            }
            //if (j - i + 1 == len - 1 && stones[j] - stones[i] + 1 == len - 1) {
            if (j - i + 1 == len - 1 && stones[j] - stones[i] == len - 2) {
                // special case if [stones[i], stones[j]] is consecutive
                // (n - 1) positions, would require two moves: (if i == 0)
                // stones[i] -> stones[j] + 2, stones[n - 1] -> stones[j] + 1
                min = Math.min(min, 2);
            } else {
                // final result is [stones[i], stones[j]], that have gap of
                // len - (j - i + 1)  and also needs at least much moves
                min = Math.min(min, len - j + i - 1);
            }
        }

        // move all stones to leftmost or rightmost, take the rightmost for example
        // each time move leftmost to the nearest right slot, decrement the gap by 1
        // number of gaps to close is A[n - 1] - A[1] - (n - 1) + 1
        final int max = Math.max(stones[len - 1] - stones[1], stones[len - 2] - stones[0])
                - len + 2;
        return new int[]{min, max};
    }
}
