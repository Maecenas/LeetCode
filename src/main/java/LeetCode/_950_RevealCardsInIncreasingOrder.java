package LeetCode;

/*
https://leetcode.com/problems/reveal-cards-in-increasing-order/
Medium. Array.

In a deck of cards, every card has a unique integer.
You can order the deck in any order you want.

Initially, all the cards start face down (unrevealed) in one deck.

Now, you do the following steps repeatedly, until all cards are revealed:

Take the top card of the deck, reveal it, and take it out of the deck.
If there are still cards in the deck, put the next top card of the deck at the bottom of the deck.
If there are still unrevealed cards, go back to step 1. Otherwise, stop.

Return an ordering of the deck that would reveal the cards in increasing order.

The first entry in the answer is considered to be the top of the deck.

Example 1:

Input: [17,13,11,2,3,5,7]
Output: [2,13,3,11,5,17,7]

Explanation:
We get the deck in the order [17,13,11,2,3,5,7] (this order doesn't matter), and reorder it.
After reordering, the deck starts as [2,13,3,11,5,17,7], where 2 is the top of the deck.
We reveal 2, and move 13 to the bottom. The deck is now [3,11,5,17,7,13].
We reveal 3, and move 11 to the bottom. The deck is now [5,17,7,13,11].
We reveal 5, and move 17 to the bottom. The deck is now [7,13,11,17].
We reveal 7, and move 13 to the bottom. The deck is now [11,17,13].
We reveal 11, and move 17 to the bottom. The deck is now [13,17].
We reveal 13, and move 17 to the bottom. The deck is now [17].
We reveal 17.
Since all the cards revealed are in increasing order, the answer is correct.

Note:

1 <= A.length <= 1000
1 <= A[i] <= 10^6
A[i] != A[j] for all i != j
*/

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

class _950_RevealCardsInIncreasingOrder {

    public static int[] deckRevealedIncreasing(int[] deck) {
        if (deck == null) return new int[0];
        else if (deck.length <= 1) return deck;

        Arrays.sort(deck);

        final int N = deck.length;
        final int[] res = new int[N];

        final Deque<Integer> index = new LinkedList<>();
        for (int i = 0; i < N; i++) index.add(i);

        for (int card : deck) {
            // assert !index.isEmpty();
            res[index.pollFirst()] = card;
            if (!index.isEmpty()) index.offer(index.pollFirst());
        }

        return res;
    }

    public static int[] deckRevealedIncreasing2(int[] deck) {
        if (deck == null) return new int[0];
        else if (deck.length <= 1) return deck;

        Arrays.sort(deck);

        final int N = deck.length;
        final int[] res = new int[N];
        res[0] = deck[0];

        for (int i = 1, p = 0; i < N; i++) {
            for (int j = 0; j < 2; j += (res[p] == 0 ? 1 : 0)) {
                if (p == N - 1) p = 0;
                else p++;
            }
            res[p] = deck[i];
        }
        return res;
    }
}
