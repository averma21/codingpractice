package stack.round2;

import util.Verifier;

import java.util.Stack;

public class MinStack {

    Stack<Integer> stack;
    int curMin;

    public MinStack() {
        this.stack = new Stack<>();
        this.curMin =  Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (x >= curMin) {
            stack.push(x);
        } else {
            stack.push(2*x - curMin);
            curMin = x;
        }
    }

    public void pop() {
       if (stack.isEmpty()) {
           return;
       }
       int popped = stack.pop();
       if (popped < curMin) {
            curMin = 2*curMin - popped;
       }
    }

    public int top() {
       if (stack.isEmpty()) {
           return -1;
       }
       int peeked = stack.peek();
       return Math.max(peeked, curMin);
    }

    public int getMin() {
       return stack.isEmpty() ? -1: curMin;
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
        //19  p g p g p g p g p g
        minStack.push(10);
        minStack.push(9);
        Verifier.verifyEquals(minStack.getMin(), 9);
        minStack.push(8);
        Verifier.verifyEquals(minStack.getMin(), 8);
        minStack.push(7);
        Verifier.verifyEquals(minStack.getMin(), 7);
        minStack.push(6);
        Verifier.verifyEquals(minStack.getMin(), 6);
        minStack.pop();
        Verifier.verifyEquals(minStack.getMin(), 7);
        minStack.pop();
        Verifier.verifyEquals(minStack.getMin(), 8);
        minStack.pop();
        Verifier.verifyEquals(minStack.getMin(), 9);
        minStack.pop();
        Verifier.verifyEquals(minStack.getMin(), 10);
        minStack.pop();
        Verifier.verifyEquals(minStack.getMin(), -1);
    }

}
