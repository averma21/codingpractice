package others.leetc.arrays;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/circular-array-loop/
public class CircularArrayLoop {

    Set<Integer> visited;
    Set<Integer> completed;
    int [] nums;

    boolean checkForwardCycle(int pos, int lastPos) {
        if (visited.contains(pos)) {
            if (lastPos == pos)
                return false;
            return true;
        }
        if (nums[pos] < 0) {
            return false;
        }
        visited.add(pos);
        return checkForwardCycle((pos + nums[pos])%nums.length, pos);
    }

    boolean checkBackwardCycle(int pos, int lastPos) {
        if (visited.contains(pos)) {
            if (lastPos == pos)
                return false;
            return true;
        }
        if (nums[pos] > 0) {
            return false;
        }
        visited.add(pos);
        return checkBackwardCycle((pos + (nums.length - (-1*nums[pos])%nums.length))%nums.length, pos);
    }

    public boolean circularArrayLoop(int[] nums) {
        visited = new HashSet<>();
        completed = new HashSet<>();
        this.nums = nums;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && !completed.contains(nums[i])) {
                if (checkForwardCycle(i, -1)) {
                    return true;
                }
                completed.addAll(visited);
                visited.clear();
            } else if  (nums[i] < 0 && !completed.contains(nums[i])) {
                if (checkBackwardCycle(i, -1)) {
                    return true;
                }
                completed.addAll(visited);
                visited.clear();
            }
        }
        return false;
    }

}
