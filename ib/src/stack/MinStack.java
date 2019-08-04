package stack;

import util.Verifier;

import java.util.Stack;

public class MinStack {

    private Stack<Integer> stack = new Stack<>();
    private int min = Integer.MAX_VALUE;

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(x);
            min = x;
        } else if (x >= min) {
            stack.push(x);
        } else {
            stack.push(2*x - min);
            min = x;
        }
    }

    public void pop() {
        if (stack.isEmpty())
            return;
        if (stack.peek() > min) {
            stack.pop();
        } else {
            min = 2*min - stack.pop();
        }
    }

    public int top() {
        if (stack.isEmpty())
            return -1;
        if (stack.peek() > min) {
            return stack.peek();
        } else {
            return min;
        }
    }

    public int getMin() {
        if (stack.isEmpty())
            return -1;
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(3);
        Verifier.verifyEquals(minStack.getMin(), 3);
        minStack.push(-1);
        Verifier.verifyEquals(minStack.getMin(), -1);
        minStack.push(2);
        Verifier.verifyEquals(minStack.getMin(), -1);
        minStack.push(-3);
        Verifier.verifyEquals(minStack.getMin(), -3);
        minStack.push(2);
        Verifier.verifyEquals(minStack.top(), 2);
        Verifier.verifyEquals(minStack.getMin(), -3);
        minStack.pop();
        Verifier.verifyEquals(minStack.top(), -3);
        Verifier.verifyEquals(minStack.getMin(), -3);
        minStack.pop();
        Verifier.verifyEquals(minStack.top(), 2);
        Verifier.verifyEquals(minStack.getMin(), -1);
        minStack.pop();
        Verifier.verifyEquals(minStack.top(), -1);
        Verifier.verifyEquals(minStack.getMin(), -1);
        minStack.pop();
        Verifier.verifyEquals(minStack.top(), 3);
        Verifier.verifyEquals(minStack.getMin(), 3);
        minStack.pop();
        Verifier.verifyEquals(minStack.top(), -1); //empty
        Verifier.verifyEquals(minStack.getMin(), -1); //empty

    }

}
