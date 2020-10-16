package others.leetc.contests.twohundred.nine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/
public class SpecialArray {

    public int specialArray(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int max = -1;
        for (int num : nums) {
            countMap.putIfAbsent(num, 0);
            countMap.computeIfPresent(num, (k,v) -> v+1);
            max = Math.max(max, num);
        }

        int count = 0;
        for (int i = Math.max(nums.length, max); i >= 0; i--) {
            if (countMap.containsKey(i)) {
                count+=countMap.get(i);
            }
            if (count == i) {
                return count;
            }
        }
        return -1;
    }

    // faster (depends on length of array)
    public int specialArrayBySort(int[] nums) {
        Arrays.sort(nums);
        int numsSize = nums.length;
        int l = 1;
        int r = numsSize + 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[numsSize - mid] >= mid) {
                if (numsSize - mid == 0 || nums[numsSize - mid - 1] < mid) {
                    return mid;
                } else {
                    l = mid + 1;
                }
            } else {
                r = mid;
            }
        }
        return -1;
    }

}
