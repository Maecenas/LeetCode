package com.leetcode;

/*
https://leetcode.com/problems/max-stack/
Easy. Design.

Design a max stack data structure that supports the stack operations
and supports finding the stack's maximum element.

Implement the MaxStack class:

 MaxStack() Initializes the stack object.
 void push(int x) Pushes element x onto the stack.
 int pop() Removes the element on top of the stack and returns it.
 int top() Gets the element on the top of the stack without removing it.
 int peekMax() Retrieves the maximum element in the stack without removing it.
 int popMax() Retrieves the maximum element in the stack and removes it.
   If there is more than one maximum element, only remove the top-most one.

Example 1:
 Input
  ["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
  [[], [5], [1], [5], [], [], [], [], [], []]
 Output
  [null, null, null, null, 5, 5, 1, 5, 1, 5]
 Explanation
  MaxStack stk = new MaxStack();
  stk.push(5);   // [5] the top of the stack and the maximum number is 5.
  stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
  stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
  stk.top();     // return 5, [5, 1, 5] the stack did not change.
  stk.popMax();  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
  stk.top();     // return 1, [5, 1] the stack did not change.
  stk.peekMax(); // return 5, [5, 1] the stack did not change.
  stk.pop();     // return 1, [5] the top of the stack and the max element is now 5.
  stk.top();     // return 5, [5] the stack did not change.

Constraints:
 -10^7 <= x <= 10^7
 At most 10^4 calls will be made to push, pop, top, peekMax, and popMax.
 There will be at least one element in the stack when pop, top, peekMax,
  or popMax is called.

Follow up: Could you come up with a solution that supports
 O(1) for each top call and O(logn) for each other call?

Your MaxStack object will be instantiated and called as such:
 MaxStack obj = new MaxStack();
 obj.push(x);
 int param_2 = obj.pop();
 int param_3 = obj.top();
 int param_4 = obj.peekMax();
 int param_5 = obj.popMax();
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @see _155_MinStack
 * @see _239_SlidingWindowMaximum
 */
class _716_MaxStack {

    static class MaxStack {

        final Stack<Integer> stack, maxs;

        /** initialize your data structure here. */
        public MaxStack() {
            this.stack = new Stack<>();
            this.maxs = new Stack<>();
        }

        /** O(1) */
        public void push(int x) {
            stack.push(x);
            if (maxs.isEmpty()) maxs.push(x);
            else maxs.push(Math.max(x, maxs.peek()));
        }

        /** O(1) */
        public int pop() {
            maxs.pop();
            return stack.pop();
        }

        /** O(1) */
        public int top() {
            return stack.peek();
        }

        /** O(1) */
        public int peekMax() {
            return maxs.peek();
        }

        /** O(n) */
        public int popMax() {
            int max = peekMax();
            List<Integer> list = new ArrayList<>();
            while (top() != max) {
                list.add(pop());
            }
            pop();
            for (int i = list.size() - 1; i >= 0; i--) {
                push(list.get(i));
            }
            return max;
        }
    }

    static class MaxStack2 {

        private static class Node {
            final int val;
            Node prev, next, recent;

            Node(int val) {
                this.val = val;
            }
        }

        final PriorityQueue<Integer> maxHeap;
        final Map<Integer, Node> seen;

        Node tail;

        public MaxStack2() {
            this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            this.seen = new HashMap<>();
        }

        /** O(nlogn) */
        public void push(int x) {
            maxHeap.add(x);

            Node node = new Node(x);
            if (seen.containsKey(x)) {
                node.recent = seen.get(x);
            }
            seen.put(x, node);

            // edge case
            if (tail != null) {
                tail.next = node;
                node.prev = tail;
            }
            tail = node;
        }

        /** O(nlogn) */
        public int pop() {
            int val = tail.val;
            maxHeap.remove(val);

            if (tail.recent == null) {
                seen.remove(tail.val);
            } else {
                seen.put(tail.val, tail.recent);
            }
            //tail.recent = null;

            // edge case
            if (tail.prev != null) {
                tail = tail.prev;
                //tail.next.prev = null;
                tail.next = null;
            } else {
                tail = null;
            }
            return val;
        }

        /** O(1) */
        public int top() {
            return tail.val;
        }

        /** O(nlogn) */
        public int peekMax() {
            if (maxHeap.isEmpty()) return Integer.MIN_VALUE;
            return maxHeap.peek();
        }

        /** O(nlogn) */
        public int popMax() {
            if (maxHeap.isEmpty()) return Integer.MIN_VALUE;
            int max = maxHeap.peek();

            Node del = seen.get(max);
            // early return
            if (del == tail) return pop();

            if (del.recent == null) {
                seen.remove(max);
            } else {
                seen.put(max, del.recent);
            }
            if (del.prev != null) del.prev.next = del.next;
            if (del.next != null) del.next.prev = del.prev;
            //del.recent = del.prev = del.next = null;

            maxHeap.poll();
            return max;
        }
    }

    static class MaxStack3 {

        /** Added LinkedList helper methods */
        private static class Node {
            final int val;
            Node prev, next, recent;

            Node(int val) {
                this.val = val;
            }

            Node appendTo(Node tail) {
                if (tail != null) tail.next = this;
                this.prev = tail;
                return this;
            }

            /** Returns previous node */
            Node remove() {
                Node prev = this.prev;
                if (this.prev != null) this.prev.next = this.next;
                if (this.next != null) this.next.prev = this.prev;
                return prev;
            }

            void addRecent(final Map<Integer, Node> seen) {
                Node recent = this.recent;
                if (seen.containsKey(this.val)) {
                    this.recent = seen.get(this.val);
                }
                seen.put(this.val, this);
            }

            void removeRecent(final Map<Integer, Node> seen) {
                if (this.recent == null) {
                    seen.remove(this.val);
                } else {
                    seen.put(this.val, this.recent);
                }
            }

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder();
                sb.append(val);

                Node curr = this.prev;
                while (curr != null) {
                    sb.append(" >- ").append(curr.val);
                    curr = curr.prev;
                }
                return sb.reverse().toString();
            }
        }

        final PriorityQueue<Integer> maxHeap;
        final Map<Integer, Node> seen;

        Node tail;

        public MaxStack3() {
            this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            this.seen = new HashMap<>();
        }

        /** O(nlogn) */
        public void push(int x) {
            maxHeap.add(x);

            Node node = new Node(x);
            node.addRecent(seen);

            tail = node.appendTo(tail);
        }

        /** O(nlogn) */
        public int pop() {
            int val = tail.val;
            maxHeap.remove(val);

            tail.removeRecent(seen);
            tail = tail.remove();
            return val;
        }

        /** O(1) */
        public int top() {
            return tail.val;
        }

        /** O(nlogn) */
        public int peekMax() {
            if (maxHeap.isEmpty()) return Integer.MIN_VALUE;
            return maxHeap.peek();
        }

        /** O(nlogn) */
        public int popMax() {
            if (maxHeap.isEmpty()) return Integer.MIN_VALUE;
            int max = maxHeap.peek();

            Node del = seen.get(max);
            // early return
            if (del == tail) return pop();

            del.removeRecent(seen);
            del.remove();

            maxHeap.poll();
            return max;
        }
    }
}
