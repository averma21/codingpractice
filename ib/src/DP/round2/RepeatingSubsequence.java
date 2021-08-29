package DP.round2;

public class RepeatingSubsequence {

    public int anytwo(String A) {
        int n = A.length();
        int [][] dp = new int[n+1][n+1];
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 0;
        }
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                char ai = A.charAt(i), aj = A.charAt(j);
                if (ai == aj && i != j) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n][n] > 0 ? 1 : 0;
    }

}
