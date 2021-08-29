package DP.round2;

import util.Verifier;

public class DistinctSubsequences {

    public int numDistinct(String A, String B) {
        int m = A.length(), n = B.length();
        int [][] dp = new int[m+1][n+1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= i && j <= n; j++) {
                char ai = A.charAt(i-1), bj = B.charAt(j-1);
                if (ai != bj) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        DistinctSubsequences ds = new DistinctSubsequences();
        Verifier.verifyEquals(ds.numDistinct("r", "r"), 1);
        Verifier.verifyEquals(ds.numDistinct("rr", "r"), 2);
        Verifier.verifyEquals(ds.numDistinct("rab", "rab"), 1);
        Verifier.verifyEquals(ds.numDistinct("rabb", "rab"), 2);
        Verifier.verifyEquals(ds.numDistinct("rabb", "rabb"), 1);
        Verifier.verifyEquals(ds.numDistinct("rabb", "rabi"), 0);
        Verifier.verifyEquals(ds.numDistinct("rabbbit", "rabbit"), 3);
    }
    
}
