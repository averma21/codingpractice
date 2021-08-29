package DP.round2;

import util.Verifier;

public class InterleavingStrings {

    public int isInterleave(String A, String B, String C) {
        if (C.length() != A.length() + B.length()) {
            return 0;
        }
        int m = A.length(), n = B.length();
        boolean [][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = A.charAt(i-1) == C.charAt(i-1) && dp[i-1][0];
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = B.charAt(j-1) == C.charAt(j-1) && dp[0][j-1];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char ai = A.charAt(i-1), bj = B.charAt(j-1), cij = C.charAt(i+j-1);
                if (ai == cij && bj == cij){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                } else if (ai == cij) {
                    dp[i][j] = dp[i-1][j];
                } else if (bj == cij) {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[m][n] ? 1 : 0;
    }

    public static void main(String[] args) {
        InterleavingStrings is = new InterleavingStrings();
        Verifier.verifyEquals(is.isInterleave("B", "e", "Be"), 1);
        Verifier.verifyEquals(is.isInterleave("XXY", "XXZ", "XXZXXXY"), 0);
        Verifier.verifyEquals(is.isInterleave("XY" ,"WZ" ,"WZXY"), 1);
        Verifier.verifyEquals(is.isInterleave("XY", "X", "XXY"), 1);
        Verifier.verifyEquals(is.isInterleave("aabcc", "dbbca", "aadbbcbcac"), 1);
        Verifier.verifyEquals(is.isInterleave("aabcc", "dbbca", "aadbbbaccc"), 0);
    }

}
