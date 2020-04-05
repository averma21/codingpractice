package others.leetc.contests.oneeighty.one;

import util.Verifier;

import java.util.LinkedList;
import java.util.List;

public class CreateTargetArray {

    public static int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(i);
        }
        for (int i = 0; i < nums.length; i++) {
            list.add(index[i], nums[i]);
        }
        int [] ans = new int[nums.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(createTargetArray(new int[]{0,1,2,3,4}, new int[]{0,1,2,2,1}), new int[]{0,4,1,3,2});
        Verifier.verifyEquals(createTargetArray(new int[]{1,2,3,4,0}, new int[]{0,1,2,3,0}), new int[]{0,1,2,3,4});
        Verifier.verifyEquals(createTargetArray(new int[]{1}, new int[]{0}), new int[]{1});
    }

}
