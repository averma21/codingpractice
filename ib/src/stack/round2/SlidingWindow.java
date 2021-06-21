package stack.round2;

import util.Creator;
import util.Verifier;

import java.util.*;

public class SlidingWindow {

    int getMaxValueIndex(PriorityQueue<Integer> priorityQueue, int left, int right) {
        while (!priorityQueue.isEmpty() && priorityQueue.peek() < left) {
            priorityQueue.poll();
        }
        return priorityQueue.isEmpty() ? -1 : priorityQueue.peek();
    }

    List<Integer> getMax(List<Integer> A, int B) {
        if (A == null || A.size() <= 1) {
            return A;
        }
        PriorityQueue<Integer> indexPriorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(A.get(o2), A.get(o1)); //decreasing order
            }
        });
        List<Integer> ans = new ArrayList<>();
        int n = A.size();
        for (int i = 0; i < B && i < n ; i++) {
            indexPriorityQueue.add(i);
        }
        for (int i = B; i < n; i++) {
            ans.add(A.get(getMaxValueIndex(indexPriorityQueue, i-B, i-1)));
            indexPriorityQueue.add(i);
        }
        ans.add(A.get(getMaxValueIndex(indexPriorityQueue, n-B, n-1)));
        return ans;
    }

    List<Integer> getMaxDequeueBased(List<Integer> nums, int k) {
        // This is just a wrapper function to convert list to array and vice versa. Array based code is needed for leetcode question.
        int [] a = new int[nums.size()];
        int i = 0;
        for (int n : nums) {
            a[i++] = n;
        }
        List<Integer> ans = new ArrayList<>();
        for (int x : maxSlidingWindow(a, k)) {
            ans.add(x);
        }
        return ans;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            int ai = nums[i];
            while (!deque.isEmpty() && nums[deque.peekLast()] < ai) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        int n = nums.length;
        int [] ans = new int [n - k + 1];
        int ind = 0;
        for (int i = k; i < n; i++) {
            ans[ind++] = nums[deque.peekFirst()];
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.removeFirst();
            }
            int ai = nums[i];
            while (!deque.isEmpty() && nums[deque.peekLast()] < ai) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        ans[ind++] = nums[deque.peekFirst()];
        return ans;
    }

    public static void main(String[] args) {
        SlidingWindow sw = new SlidingWindow();
        Verifier.verifyEquals(sw.getMax(Creator.getList(1, 3, -1, -3, 5, 3, 6, 7), 3), Creator.getList(3,3,5,5,6,7));
        Verifier.verifyEquals(sw.getMax(Creator.getList(1, -1), 1), Creator.getList(1,-1));
        Verifier.verifyEquals(sw.getMaxDequeueBased(Creator.getList(1, 3, -1, -3, 5, 3, 6, 7), 3), Creator.getList(3,3,5,5,6,7));
        Verifier.verifyEquals(sw.getMaxDequeueBased(Creator.getList(1, -1), 1), Creator.getList(1,-1));
    }

}
