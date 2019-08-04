package stack;

import util.Creator;
import util.Verifier;

import java.util.List;
import java.util.Stack;

/**
 * Solution - The largest area rectangle would include the top of at least one histogram bar. So, for each histogram bar,
 * we calculate the maximum area which could be obtained by including the top of this bar. Then we take the max of these
 * areas to get the answer.
 *
 * To calculate each bar max area : use a stack. Keep pushing till stack.top is less than next element in list. If encountered
 * a small element, keep on popping and calculate the area for all popped bars until stack is empty or top is less than
 * or equal to next element.
 * To calculate area of popped bar, height of rectangle would be equal to height of popped bar (since we include top of bar in rectangle)
 * The width will be i - left. Where left is right boundary(stack.peek + 1) of bar present in stack or the y axis (0) if stack is empty.
 *
 * After the process stack would contain those elements' indexes who don't have any smaller element on their right in the original list.
 * So for each of these elements, area needs to be calculated same way->
 * Pop element
 *  right boundary of rectangle for this element would be A.size() + 1
 *  left boundary would be right boundary(stack.peek + 1) of bar present in stack or the y axis (0) if stack is empty.
 *  height would be equal to popped element.
 *
 * NOTE: while calculating area of popped element, if stack's top still contains same height element as popped element, keep on popping
 * until top is less than this popped element, since for all those elements, area would be same as the popped element.
 */
public class LargestRectangleHistogram {

    private static int largestArea(List<Integer> A) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < A.size(); i++) {
            int ai = A.get(i);
            if (stack.isEmpty() || A.get(stack.peek()) <= ai) {
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty() && A.get(stack.peek()) > ai) {
                int poppedPos = stack.pop();
                Integer poppedEle = A.get(poppedPos);
                int height = poppedEle;
                while (!stack.isEmpty() && A.get(stack.peek()).equals(poppedEle))
                    stack.pop();
                int bottom = i - (!stack.isEmpty() ? stack.peek() + 1 : 0);
                maxArea = Math.max(maxArea, bottom * height);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int poppedPos = stack.pop();
            Integer poppedEle = A.get(poppedPos);
            int height = poppedEle;
            while (!stack.isEmpty() && A.get(stack.peek()).equals(poppedEle))
                stack.pop();
            int bottom = A.size() - (!stack.isEmpty() ? stack.peek() + 1 : 0);
            maxArea = Math.max(maxArea, bottom * height);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(largestArea(Creator.getList(2)), 2);
        Verifier.verifyEquals(largestArea(Creator.getList(1,2,3,4)), 6);
        Verifier.verifyEquals(largestArea(Creator.getList(4,3,2,1)), 6);
        Verifier.verifyEquals(largestArea(Creator.getList(2,1,2,4,3,3,2,6,6)), 14);
        Verifier.verifyEquals(largestArea(Creator.getList(2,1,5,6,2,3)), 10);
        Verifier.verifyEquals(largestArea(Creator.getList(47, 69, 67, 97, 86, 34, 98, 16, 65, 95, 66, 69, 18,
                1, 99, 56, 35, 9, 48, 72, 49, 47, 1, 72, 87, 52, 13, 23, 95, 55, 21, 92, 36, 88, 48, 39, 84, 16, 15, 65,
                7, 58, 2, 21, 54, 2, 71, 92, 96, 100, 28, 31, 24, 10, 94, 5, 81, 80, 43, 35, 67, 33, 39, 81, 69, 12, 66,
                87, 86, 11, 49, 94, 38, 44, 72, 44, 18, 97, 23, 11, 30, 72, 51, 61, 56, 41, 30, 71, 12, 44, 81, 43, 43,
                27)), 418);
    }

}
