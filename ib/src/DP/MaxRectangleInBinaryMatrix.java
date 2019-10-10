package DP;

import util.Verifier;

import java.util.Stack;

public class MaxRectangleInBinaryMatrix {

    private static int maxAreaRectHistogram(int [] heights) {
        if (heights == null || heights.length == 0)
            return 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int area = Integer.MIN_VALUE;
        for (int i = 1; i < heights.length; i++) {
            int top = stack.isEmpty() ? -1 : heights[stack.peek()];
            if (top == -1 || heights[i] > top) {
                stack.push(i);
                continue;
            }
            int rightBoundary = i;
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int popped = stack.pop();
                while (!stack.isEmpty() && heights[stack.peek()] == heights[popped]) {
                    stack.pop();
                }
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
                area = Math.max(area, (rightBoundary-leftBoundary-1) * heights[popped]);
            }
            stack.push(i);
        }
        int rightBoundary = stack.isEmpty() ? -1 : stack.peek() + 1;
        while (!stack.isEmpty()) {
            int popped = stack.pop();
            while (!stack.isEmpty() && heights[stack.peek()] == heights[popped]) {
                stack.pop();
            }
            int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
            area = Math.max(area, (rightBoundary-leftBoundary-1) * heights[popped]);
        }
        return area;
    }

    private static int maxAreaRect(int A[][]) {
        if (A == null || A[0] == null || A[0].length == 0)
            return 0;
        int area = maxAreaRectHistogram(A[0]);
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (A[i][j] == 0)
                    continue;
                A[i][j] = A[i-1][j] + 1;
            }
            area = Math.max(area, maxAreaRectHistogram(A[i]));
        }
        return area;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(maxAreaRect(new int[][]{{1,1,1},{0,1,1},{1,0,0}}), 4);
        Verifier.verifyEquals(maxAreaRect(new int[][]{{0,1,1,0},{1,1,1,1},{1,1,1,1},{1,1,0,0}}), 8);
        Verifier.verifyEquals(maxAreaRect(new int[][]{{0,0},{0,0}}), 0);
        Verifier.verifyEquals(maxAreaRect(new int[][]{{0,0}}), 0);
        Verifier.verifyEquals(maxAreaRect(new int[][]{{0}}), 0);
        Verifier.verifyEquals(maxAreaRect(new int[][]{{0,0},{1,0}}), 1);
        Verifier.verifyEquals(maxAreaRect(new int[][]{{0,0},{1,1}}), 2);
    }

}
