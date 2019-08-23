package DP;

import util.Verifier;

public class DistinctSubsequences {

    public static int count(String A, String B) {
        int [][] dp = new int[A.length() + 1][B.length() + 1];
        for (int i = 0; i <= A.length(); i++) {
            for (int j = 0; j <= B.length(); j++) {
                if (i==0 ) {
                    dp[i][j] = j == 0 ? 1 : 0;
                } else if (j == 0) {
                    dp[i][j] = 1;
                } else if (A.charAt(i-1) == B.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[A.length()][B.length()];
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(count("r", "r"), 1);
        Verifier.verifyEquals(count("rr", "r"), 2);
        Verifier.verifyEquals(count("rab", "rab"), 1);
        Verifier.verifyEquals(count("rabb", "rab"), 2);
        Verifier.verifyEquals(count("rabb", "rabb"), 1);
        Verifier.verifyEquals(count("rabb", "rabi"), 0);
        Verifier.verifyEquals(count("rabbbit", "rabbit"), 3);
    }

}
