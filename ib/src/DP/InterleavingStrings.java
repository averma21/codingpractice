package DP;

import util.Verifier;

public class InterleavingStrings {

    public static int isInterleave(String A, String B, String C) {
        boolean [][] dp = new boolean[A.length() + 1][B.length() + 1];
        if (C.length() != A.length() + B.length()) {
            return 0;
        }
        for (int i = 0; i <= A.length(); i++) {
            for (int j = 0; j <= B.length(); j++) {

                // two empty strings have an empty string 
                // as interleaving 
                if (i==0 && j==0)
                    dp[i][j] = true;

                    // A is empty 
                else if (i==0 && B.charAt(j-1)==C.charAt(j-1))
                    dp[i][j] = dp[i][j-1];

                    // B is empty 
                else if (j==0 && A.charAt(i-1)==C.charAt(i-1))
                    dp[i][j] = dp[i-1][j];

                    // Current character of C matches with current character of A, 
                    // but doesn't match with current character of B 
                else if(i > 0 && j >0 ) {
                    if (A.charAt(i - 1) == C.charAt(i + j - 1) && B.charAt(j - 1) != C.charAt(i + j - 1))
                        dp[i][j] = dp[i - 1][j];

                        // Current character of C matches with current character of B,
                        // but doesn't match with current character of A
                    else if (A.charAt(i - 1) != C.charAt(i + j - 1) && B.charAt(j - 1) == C.charAt(i + j - 1))
                        dp[i][j] = dp[i][j - 1];

                        // Current character of C matches with that of both A and B
                    else if (A.charAt(i - 1) == C.charAt(i + j - 1) && B.charAt(j - 1) == C.charAt(i + j - 1))
                        dp[i][j] = (dp[i - 1][j] || dp[i][j - 1]);
                }
            }
        }
        return dp[A.length()][B.length()] ? 1 : 0;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(isInterleave("B", "e", "Be"), 1);
        Verifier.verifyEquals(isInterleave("XXY", "XXZ", "XXZXXXY"), 0);
        Verifier.verifyEquals(isInterleave("XY" ,"WZ" ,"WZXY"), 1);
        Verifier.verifyEquals(isInterleave("XY", "X", "XXY"), 1);
    }

}
