package DP.round2;

public class EditDistance {

    public static int minDistance(String A, String B) {
        int [][] dp = new int[A.length() + 1][B.length() + 1];
        for (int j = 0; j <= B.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i <= A.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i-1) == B.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[A.length()][B.length()];
    }
}
