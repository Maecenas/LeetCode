package LeetCode;

/*
https://leetcode.com/problems/min-stack/
Easy. Stack, Design.

Design a stack that supports push, pop, top,
 and retrieving the minimum element in constant time.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 getMin() -- Retrieve the minimum element in the stack.

Example 1:
 Input
  ["MinStack","push","push","push","getMin","pop","top","getMin"]
  [[],[-2],[0],[-3],[],[],[],[]]
 Output
  [null,null,null,null,-3,null,0,-2]
 Explanation
  MinStack minStack = new MinStack();
  minStack.push(-2);
  minStack.push(0);
  minStack.push(-3);
  minStack.getMin(); // return -3
  minStack.pop();
  minStack.top();    // return 0
  minStack.getMin(); // return -2

Constraints:
 Methods pop, top and getMin operations will always be called on non-empty stacks.

Your MinStack object will be instantiated and called as such:

 MinStack obj = new MinStack();
 obj.push(x);
 obj.pop();
 int param_3 = obj.top();
 int param_4 = obj.getMin();
*/

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @see _239_SlidingWindowMaximum
 * @see _716_MaxStack
 */
class _155_MinStack {

    static class MinStack {

        final Stack<Integer> stack, mins;

        /** initialize your data structure here. */
        public MinStack() {
            this.stack = new Stack<>();
            this.mins = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (mins.isEmpty()) mins.push(x);
            else mins.push(Math.min(x, mins.peek()));
        }

        public void pop() {
            mins.pop();
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return mins.peek();
        }
    }

    static class MinStack2 {

        final Deque<Integer> stack, mins;

        /** initialize your data structure here. */
        public MinStack2() {
                this.stack = new ArrayDeque<>();
                this.mins = new ArrayDeque<>();
        }

        public void push(int x) {
            stack.addLast(x);
            if (mins.isEmpty()) mins.addLast(x);
            else mins.addLast(Math.min(x, getMin()));
        }

        public void pop() {
            stack.removeLast();
            mins.removeLast();
        }

        public int top() {
            return stack.getLast();
        }

        public int getMin() {
            return mins.getLast();
        }
    }
}
