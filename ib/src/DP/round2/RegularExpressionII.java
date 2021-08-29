package DP.round2;

import util.Verifier;

public class RegularExpressionII {

    public int isMatch(final String A, final String B) {
        int m = A.length(), n = B.length();
        boolean [][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = false;
        }
        int charFound = 0;
        for (int j = 1; j <= n; j++) {
            if (B.charAt(j-1) != '*') {
                charFound++;
                dp[0][j] = false;
            } else {
                dp[0][j] = (charFound == 1);
                charFound--;
            }
        }
        for (int i = 1; i <= m ; i++) {
            char ai = A.charAt(i-1);
            for (int j = 1; j <= n; j++) {
                char bj = B.charAt(j-1);
                if (bj != '*') {
                    if (bj == '.') {
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] = dp[i-1][j-1] && ai == bj;
                    }
                } else {
                    char bj2 = B.charAt(j-2);
                    dp[i][j] = dp[i][j-2] || ((bj2 ==  '.' || ai == bj2) && dp[i-1][j]);
                }
            }
        }
        return dp[m][n] ? 1 : 0;
    }


    public static void main(String[] args) {
        RegularExpressionII re2 = new RegularExpressionII();
        Verifier.verifyEquals(re2.isMatch("aa", "a"), 0);
        Verifier.verifyEquals(re2.isMatch("aa", "aa"), 1);
        Verifier.verifyEquals(re2.isMatch("aaa", "aa"), 0);
        Verifier.verifyEquals(re2.isMatch("aa", "a*"), 1);
        Verifier.verifyEquals(re2.isMatch("aa", ".*"), 1);
        Verifier.verifyEquals(re2.isMatch("ab", ".*"), 1);
        Verifier.verifyEquals(re2.isMatch("aab", "c*a*b"), 1);
        Verifier.verifyEquals(re2.isMatch("baaaaaabaaaabaaaaababababbaab", "..a*aa*a.aba*a*bab*"), 0);
    }

}
