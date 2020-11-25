package others.leetc.heapsandmaps;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
public class SmallestRangeCoveringKLists {

    PriorityQueue<int[]> queue;

    public int[] smallestRange(List<List<Integer>> nums) {
        queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int k = nums.size();
        int shortestListSize = Integer.MAX_VALUE;
        int maxValueInHeap = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            List<Integer> list = nums.get(i);
            queue.add(new int[] {list.get(0), i, 0});
            maxValueInHeap = Math.max(list.get(0), maxValueInHeap);
            shortestListSize = Math.min(list.size(), shortestListSize);
        }
        int minRangeSize = Integer.MAX_VALUE;
        int [] ans = new int[2];
        while (true) {
            int [] minEle = queue.remove();
            int min = minEle[0];
            int rangeSize = maxValueInHeap - min + 1;
            if (minRangeSize > rangeSize) {
                minRangeSize = rangeSize;
                ans[0] = min;
                ans[1] = maxValueInHeap;
            }
            List<Integer> nextList = nums.get(minEle[1]);
            int nextIndex = minEle[2]+1;
            if (nextIndex == nextList.size()) {
                break;
            }
            int toAdd = nextList.get(nextIndex);
            queue.add(new int [] {toAdd, minEle[1], nextIndex});
            maxValueInHeap = Math.max(toAdd, maxValueInHeap);
        }
        return ans;
    }

}
