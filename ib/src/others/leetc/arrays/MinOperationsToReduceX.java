package others.leetc.arrays;

import java.util.HashMap;
import java.util.Map;

public class MinOperationsToReduceX {

    Map<Integer, Integer> forwardSumMap;
    Map<Integer, Integer> backwardSumMap;

    public int minOperations(int[] nums, int x) {
        forwardSumMap = new HashMap<>();
        backwardSumMap = new HashMap<>();
        int n = nums.length;
        if (n ==1) {
            return nums[0] == x ? 1 : -1;
        }
        int sum = 0;
        int minSteps = Integer.MAX_VALUE;
        for (int i = 0; i < n && sum < x; i++) {
            sum+=nums[i];
            if (sum == x) { //can only happen once since numbers are positive
                minSteps = i+1;
            }
            forwardSumMap.put(sum, i);
        }
        sum = 0;
        for (int i = n-1; i >= 0 && sum < x; i--) {
            sum+=nums[i];
            Integer index = forwardSumMap.get(x - sum);
            if (sum == x) { //can only happen once since numbers are positive
                minSteps = Math.min(minSteps, n-i);
            } else if (sum <= x && index != null && index < i) {
                minSteps = Math.min(minSteps, n-i + index + 1);
            }
            backwardSumMap.put(sum, i);
        }
        sum = 0;
        for (int i = 0; i < n && sum < x; i++) {
            sum+=nums[i];
            Integer index = backwardSumMap.get(x - sum);
            if (sum <= x && index != null && index > i) {
                minSteps = Math.min(minSteps, n - index + i + 1);
            }
        }
        return minSteps != Integer.MAX_VALUE ? minSteps : -1;
    }

}
