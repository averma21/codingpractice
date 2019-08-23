package DP;

import util.Creator;
import util.Verifier;

import java.util.List;

public class LIS {

    public static int lis(final List<Integer> A) {
        int[] dp = new int[A.size()];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < A.size(); i++) {
            int maxTillNow = 1;
            for (int j = 0; j < i; j++) {
                int ai = A.get(i), aj = A.get(j);
                if (ai > aj && dp[j] + 1 > maxTillNow) {
                    maxTillNow = dp[j] + 1;
                }
            }
            dp[i] = maxTillNow;
            if (max < maxTillNow)
                max = maxTillNow;
        }
        return max;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(lis(Creator.getList(1, 2, 1, 5)), 3);
        Verifier.verifyEquals(lis(Creator.getList(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15)), 6);
    }

}
