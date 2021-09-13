package DP.round2;

import util.Verifier;

public class ChainOfPairs {

    public static int solve(int[][] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int [] dp = new int[n];
        dp[n-1] = 1;
        int ans = Integer.MIN_VALUE;
        for (int i = n-2; i >= 0; i--) {
            int max = 0;
            for (int j = i + 1; j < n; j++) {
                if (A[i][1] < A[j][0]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = 1 + max;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(solve(new int[][] {
            {5, 24},
            {39, 60},
            {15, 28},
            {27, 40},
            {50, 90}
        }), 3);
        Verifier.verifyEquals(solve(new int[][] {
                {10,20},
                {1,2}
        }), 1);
        Verifier.verifyEquals(solve(new int[][] {
                {98, 894},
                {397, 942},
                {70, 519},
                {258, 456},
                {286, 449},
                {516, 626},
                {370, 873},
                {214, 224},
                {74, 629},
                {265, 886},
                {708, 815},
                {394, 770},
                {56, 252}
        }), 3);
    }

}
