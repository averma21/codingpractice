package DP.round2;

import util.Verifier;

public class Knapsack01 {

    public int solve(int[] A, int[] B, int C) {
        if (A == null || B == null || A.length == 0 || B.length == 0) {
            return 0;
        }
        int m = A.length, n = C+1;
        int [][] dp = new int[m][n];
        for (int w = 1; w < n; w++) {
            dp[0][w] = w < B[0] ? 0 : A[0];
        }
        for (int i = 1; i < m; i++) {
            for (int w = 1; w < n; w++) {
                if (B[i] > w) {
                    dp[i][w] = dp[i-1][w];
                } else {
                    dp[i][w] = Math.max(A[i] + dp[i-1][w-B[i]], dp[i-1][w]);
                }
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        Knapsack01 k01 = new Knapsack01();
        Verifier.verifyEquals(k01.solve(new int[]{60,100,120}, new int[]{10,20,30}, 50), 220);
        Verifier.verifyEquals(k01.solve(new int[]{100,60,120}, new int[]{20,10,30}, 50), 220);
        Verifier.verifyEquals(k01.solve(new int[]{40,20,30,10}, new int[]{19,13,15,12}, 10), 0);
        Verifier.verifyEquals(k01.solve(new int[]{40,20,30,10}, new int[]{19,13,15,12}, 30), 50);
    }

}
