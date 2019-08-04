package stack;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of integers A. There is a sliding window of size B which
 * is moving from the very left of the array to the very right.
 * You can only see the w numbers in the window. Each time the sliding window moves
 * rightwards by one position. You have to find the maximum for each window.
 * The following example will give you more clarity.
 *
 * The array A is [1 3 -1 -3 5 3 6 7], and B is 3.
 *
 * Window position	Max
 * ———————————-	————————-
 * [1 3 -1] -3 5 3 6 7	3
 * 1 [3 -1 -3] 5 3 6 7	3
 * 1 3 [-1 -3 5] 3 6 7	5
 * 1 3 -1 [-3 5 3] 6 7	5
 * 1 3 -1 -3 [5 3 6] 7	6
 * 1 3 -1 -3 5 [3 6 7]	7
 * Return an array C, where C[i] is the maximum value of from A[i] to A[i+B-1].
 *
 * Note: If B > length of the array, return 1 element with the max of the array.
 */
public class SlidingWindow {

    static List<Integer> getMax(List<Integer> A, int B) {

        // Create a Double Ended Queue, deque that will store indexes of array elements
        // The queue will store indexes of useful elements in every window and it will
        // maintain decreasing order of values from front to rear in deque, i.e.,
        // arr[deque.front[]] to arr[deque.rear()] are sorted in decreasing order 
        Deque<Integer> deque = new LinkedList<Integer>();
        List<Integer> ans = new ArrayList<>(B);
        int i;
        if (B < 0)
            return null;
        /* Process first B (or first window) elements of array */
        for (i = 0; i < B; ++i) {
            while (!deque.isEmpty() && A.get(i) >= A.get(deque.peekLast()))
                deque.removeLast(); // Remove from rear
            deque.addLast(i);
        }

        for (; i < A.size(); ++i) {
            ans.add(A.get(deque.peek()));

            // Remove the elements which are out of this window
            while ((!deque.isEmpty()) && deque.peek() <= i - B)
                deque.removeFirst();

            // Remove all elements smaller than the currently
            // being added element (remove useless elements)
            while ((!deque.isEmpty()) && A.get(i) >= A.get(deque.peekLast()))
                deque.removeLast();

            // Add current element at the rear of deque
            deque.addLast(i);
        }

        // Print the maximum element of last window
        ans.add(A.get(deque.peek()));
        return ans;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(getMax(Creator.getList(1, 3, -1, -3, 5, 3, 6, 7), 3), Creator.getList(3,3,5,5,6,7));
    }

}
