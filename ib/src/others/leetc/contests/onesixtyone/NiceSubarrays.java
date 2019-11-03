package others.leetc.contests.onesixtyone;

import util.Verifier;

public class NiceSubarrays {

    public int numberOfSubarrays(int[] nums, int k) {
        int windowCount = 0, kCount = 0;
        int curWinStart = -1, prevWinStart = -1;
        for (int i = 0; i < nums.length;i++) {
            if (nums[i] % 2 == 1) {
                kCount++;
                if (curWinStart == -1) {
                    curWinStart = i;
                }
                if (kCount == k) {
                    kCount--;
                    int leftCount = curWinStart - prevWinStart;
                    int curI = i;
                    i++;
                    while (i < nums.length && nums[i] % 2 == 0)
                        i++;
                    int rightCount = i - curI;
                    windowCount += leftCount* rightCount;
                    prevWinStart = curWinStart;
                    curWinStart++;
                    while (curWinStart < nums.length && nums[curWinStart] % 2 == 0) {
                        curWinStart++;
                    }
                    i--;
                }
            }
        }
        return windowCount;
    }

    public static void main(String[] args) {
        NiceSubarrays ns = new NiceSubarrays();
//        Verifier.verifyEquals(ns.numberOfSubarrays(new int[]{1,1,2,1,1}, 3), 2);
//        Verifier.verifyEquals(ns.numberOfSubarrays(new int[]{2,4,6}, 1), 0);
//        Verifier.verifyEquals(ns.numberOfSubarrays(new int[]{2,2,2,1,2,2,1,2,2,2}, 2), 16);
//        Verifier.verifyEquals(ns.numberOfSubarrays(new int[]{12,14,1,4,3,2,11,15}, 3), 5);
        Verifier.verifyEquals(ns.numberOfSubarrays(new int[]{1,1,1}, 1), 3);
        Verifier.verifyEquals(ns.numberOfSubarrays(new int[]{1,1,1}, 2), 2);
    }

}
