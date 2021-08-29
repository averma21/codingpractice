package DP.round2;

import util.Verifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MaxRectangleInBinaryMatrix {

    private int maximumAreaHistogram(int [] A) {
        int maxArea = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < A.length; i++) {
            if (A[i] >= A[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
                    int rightExclusive = i;
                    int popped = stack.pop();
                    int leftInclusive = stack.isEmpty() ? 0 : stack.peek() + 1;
                    int area = A[popped] * (rightExclusive - leftInclusive);
                    maxArea = Math.max(maxArea, area);
                }
                stack.push(i);
            }
        }
        int rightInclusive = stack.peek();
        while (!stack.isEmpty()) {
            int popped = stack.pop();
            int leftInclusive = stack.isEmpty() ? 0 : (stack.peek() + 1);
            int area = A[popped] * (rightInclusive - leftInclusive + 1);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    private int maximalRectangleN2(int[][] A) {
        if (A == null) {
            return 0;
        }
        int m = A.length, n = A[0].length;
        int [] histo = new int[n];
        for (int i = 0; i < n; i++) {
            histo[i] = A[0][i];
        }
        int maxArea = maximumAreaHistogram(histo);
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0) {
                    histo[j] = 0;
                } else {
                    histo[j] = 1 + histo[j];
                }
            }
            maxArea = Math.max(maxArea, maximumAreaHistogram(histo));
        }
        return maxArea;
    }

    /**
     * This is O(N^3) approach. For every coordinate in the matrix, we calculate max height of 1s starting from that coordinate.
     * Now, we traverse the given matrix again, and for every non-zero coordinate we calculate the area of maximum rectangle whose
     * right bottom ends at that coordinate. To caculate the area, we traverse left from that point, and as soon as height of 1s
     * changes for a point, we calculate the area and keep the largest area found till now for this point. We break this traversal
     * if we reach a 0 value coordinate.
     *
     * O(N^2) is available in {@link #maximalRectangleN2(int[][])}
     */
    public int maximalRectangle(int[][] A) {
        if (A == null) {
            return 0;
        }
        int m = A.length, n = A[0].length;
        int [][] max1Heights = new int[m][n];
        for (int i = 0; i < n; i++) {
            max1Heights[0][i] = A[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max1Heights[i][j] = A[i][j] == 0 ? 0 :  max1Heights[i-1][j] + 1;
           }
        }

        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0) {
                    continue;
                }
                int maxAreaIJ = max1Heights[i][j];
                int rectangleHeightTillNow = max1Heights[i][j];
                int k = j-1;
                for (; k >= 0; k--) {
                    if (max1Heights[i][k] == 0) {
                        break;
                    }
                    if (max1Heights[i][k] < rectangleHeightTillNow) {
                        maxAreaIJ = Math.max(maxAreaIJ, rectangleHeightTillNow*(j-k));
                        rectangleHeightTillNow = max1Heights[i][k];
                    }
                }
                if (k < 0 || max1Heights[i][k] < rectangleHeightTillNow) {
                    maxAreaIJ = Math.max(maxAreaIJ, rectangleHeightTillNow*(j-k));
                }
                maxArea = Math.max(maxArea, maxAreaIJ);
            }
        }
        return Math.max(maxArea, 0);
    }

    private static class TestCase{
        int [][] matrix;
        int maxArea;

        public TestCase(int[][] matrix, int maxArea) {
            this.matrix = matrix;
            this.maxArea = maxArea;
        }
    }

    private static List<TestCase> getTestCases() {
        List<TestCase> cases = new ArrayList<>();
        cases.add(new TestCase(new int[][]{{1,1,1},{0,1,1},{1,0,0}}, 4));
        cases.add(new TestCase(new int[][]{{0,1,1,0},{1,1,1,1},{1,1,1,1},{1,1,0,0}}, 8));
        cases.add(new TestCase(new int[][]{{0,0},{0,0}}, 0));
        cases.add(new TestCase(new int[][]{{0,0}}, 0));
        cases.add(new TestCase(new int[][]{{0}}, 0));
        cases.add(new TestCase(new int[][]{{0,0},{1,0}}, 1));
        cases.add(new TestCase(new int[][]{{0,0},{1,1}}, 2));
        cases.add(new TestCase(new int[][]{
                {0, 0, 1, 0, 0, 0, 1, 0, 1},
                {0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 1, 0, 1},
                {0, 1, 0, 0, 0, 1, 1, 0, 1},
                {0, 1, 0, 0, 0, 0, 1, 1, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 0, 1, 0, 1}}, 9));
        cases.add(new TestCase(new int[][]{
                {1, 0, 0, 1, 0, 0, 1, 1},
                {0, 1, 0, 1, 1, 1, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 1},
        }, 3));
        return cases;
    }

    public static void main(String[] args) {
        MaxRectangleInBinaryMatrix mibm = new MaxRectangleInBinaryMatrix();
        for (TestCase testCase : getTestCases()) {
            Verifier.verifyEquals(mibm.maximalRectangle(testCase.matrix), testCase.maxArea);
        }
        for (TestCase testCase : getTestCases()) {
            Verifier.verifyEquals(mibm.maximalRectangleN2(testCase.matrix), testCase.maxArea);
        }
    }

}
