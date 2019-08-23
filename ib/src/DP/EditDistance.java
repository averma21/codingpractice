package DP;

import util.Verifier;

public class EditDistance {

    public static int minDistance(String A, String B) {
        int m = A.length(), n = B.length();
        int dp[][] = new int[m+1][n+1];
        for (int i = 0; i <=m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (A.charAt(i-1) == B.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1+ Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(minDistance("", ""), 0);
        Verifier.verifyEquals(minDistance("a", ""), 1);
        Verifier.verifyEquals(minDistance("", "a"), 1);
        Verifier.verifyEquals(minDistance("a", "a"), 0);
        Verifier.verifyEquals(minDistance("ab", "a"), 1);
        Verifier.verifyEquals(minDistance("sunday", "saturday"), 3);
    }

}
