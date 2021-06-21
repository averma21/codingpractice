package stack.round2;

import util.Creator;
import util.Verifier;

import java.util.List;
import java.util.Stack;

public class LargestRectangleHistogram {

    public int largestArea(List<Integer> A) {
        if (A == null || A.size() == 0) {
            return 0;
        }
        if (A.size() == 1) {
            return A.get(0);
        }
        int n = A.size();
        Stack<Integer> indexStack = new Stack<>();
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int ai = A.get(i);
            if (indexStack.isEmpty() || ai >= A.get(indexStack.peek())) {
                indexStack.push(i);
                continue;
            }
            while (!indexStack.isEmpty() && ai < A.get(indexStack.peek())) {
                int index = indexStack.pop();
                int height = A.get(index);
                int width = indexStack.isEmpty() ? i : i - indexStack.peek() - 1;
                maxArea = Math.max(maxArea, height*width);
            }
            indexStack.push(i);
        }
        while (!indexStack.isEmpty()) {
            int index = indexStack.pop();
            int height = A.get(index);
            int width = indexStack.isEmpty() ? A.size() : A.size() - indexStack.peek() - 1;
            maxArea = Math.max(maxArea, height*width);
        }
        return maxArea;
    }

    public int largestAreaEasy(List<Integer> A) {
        if (A == null || A.size() == 0) {
            return 0;
        }
        if (A.size() == 1) {
            return A.get(0);
        }
        int n = A.size();
        Stack<Integer> indexStack = new Stack<>();
        int maxArea = Integer.MIN_VALUE;
        int [] minRight = new int[A.size()];
        int [] minLeft = new int[A.size()];
        for (int i = 0; i < n; i++) {
            int ai = A.get(i);
            if (indexStack.isEmpty() || ai >= A.get(indexStack.peek())) {
                indexStack.push(i);
                continue;
            }
            while (!indexStack.isEmpty() && ai < A.get(indexStack.peek())) {
                int index = indexStack.pop();
                minRight[index] = i;
            }
            indexStack.push(i);
        }
        while (!indexStack.isEmpty()) {
            minRight[indexStack.pop()] = A.size();
        }
        for (int i = n-1; i >= 0; i--) {
            int ai = A.get(i);
            if (indexStack.isEmpty() || ai >= A.get(indexStack.peek())) {
                indexStack.push(i);
                continue;
            }
            while (!indexStack.isEmpty() && ai < A.get(indexStack.peek())) {
                int index = indexStack.pop();
                minLeft[index] = i;
            }
            indexStack.push(i);
        }
        while (!indexStack.isEmpty()) {
            minLeft[indexStack.pop()] = -1;
        }
        for (int i = 0; i < n; i++) {
            maxArea = Math.max(A.get(i) * (minRight[i] - minLeft[i] - 1), maxArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangleHistogram lrh = new LargestRectangleHistogram();
        Verifier.verifyEquals(lrh.largestArea(Creator.getList(2)), 2);
        Verifier.verifyEquals(lrh.largestArea(Creator.getList(1,2,3,4)), 6);
        Verifier.verifyEquals(lrh.largestArea(Creator.getList(4,3,2,1)), 6);
        Verifier.verifyEquals(lrh.largestArea(Creator.getList(2,1,2,4,3,3,2,6,6)), 14);
        Verifier.verifyEquals(lrh.largestArea(Creator.getList(2,1,5,6,2,3)), 10);
        Verifier.verifyEquals(lrh.largestArea(Creator.getList(47, 69, 67, 97, 86, 34, 98, 16, 65, 95, 66, 69, 18,
                1, 99, 56, 35, 9, 48, 72, 49, 47, 1, 72, 87, 52, 13, 23, 95, 55, 21, 92, 36, 88, 48, 39, 84, 16, 15, 65,
                7, 58, 2, 21, 54, 2, 71, 92, 96, 100, 28, 31, 24, 10, 94, 5, 81, 80, 43, 35, 67, 33, 39, 81, 69, 12, 66,
                87, 86, 11, 49, 94, 38, 44, 72, 44, 18, 97, 23, 11, 30, 72, 51, 61, 56, 41, 30, 71, 12, 44, 81, 43, 43,
                27)), 418);

        Verifier.verifyEquals(lrh.largestAreaEasy(Creator.getList(2)), 2);
        Verifier.verifyEquals(lrh.largestAreaEasy(Creator.getList(1,2,3,4)), 6);
        Verifier.verifyEquals(lrh.largestAreaEasy(Creator.getList(4,3,2,1)), 6);
        Verifier.verifyEquals(lrh.largestAreaEasy(Creator.getList(2,1,2,4,3,3,2,6,6)), 14);
        Verifier.verifyEquals(lrh.largestAreaEasy(Creator.getList(2,1,5,6,2,3)), 10);
        Verifier.verifyEquals(lrh.largestAreaEasy(Creator.getList(47, 69, 67, 97, 86, 34, 98, 16, 65, 95, 66, 69, 18,
                1, 99, 56, 35, 9, 48, 72, 49, 47, 1, 72, 87, 52, 13, 23, 95, 55, 21, 92, 36, 88, 48, 39, 84, 16, 15, 65,
                7, 58, 2, 21, 54, 2, 71, 92, 96, 100, 28, 31, 24, 10, 94, 5, 81, 80, 43, 35, 67, 33, 39, 81, 69, 12, 66,
                87, 86, 11, 49, 94, 38, 44, 72, 44, 18, 97, 23, 11, 30, 72, 51, 61, 56, 41, 30, 71, 12, 44, 81, 43, 43,
                27)), 418);
    }

}
