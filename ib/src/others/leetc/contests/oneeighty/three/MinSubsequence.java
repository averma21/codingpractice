package others.leetc.contests.oneeighty.three;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinSubsequence {

    public static List<Integer> minSubsequence(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);
        for (int num : nums) {
            list.add(num);
        }
        Collections.sort(list, Collections.reverseOrder());
        int [] sum = new int [nums.length];
        int n = list.size();
        sum[n - 1] = list.get(n - 1);
        for(int i = n - 2; i >= 0; i--) {
            sum[i] = list.get(i) + sum[i+1];
        }
        int i = 1;
        for(; i < n; i++) {
            if (sum[0] - sum[i] > sum[i]) {
                break;
            }
        }
        return list.subList(0, i);
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(minSubsequence(new int[] {4,3,10,9,8}), Creator.getList(10,9));
    }

}
