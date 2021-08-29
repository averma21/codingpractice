package DP.round2;

import util.Verifier;

public class RegularExpression {

    public int isMatch(final String A, final String B) {
        int m = A.length(), n = B.length();
        // pattern across second dimension
        boolean [][] dp = new boolean[m+1][n+1];
        //empty pattern matches only empty string
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = false;
        }
        boolean onlyStarTillNow = true;
        //empty string will match only if no non star character has been encountered
        for (int j = 1; j <= n; j++) {
            onlyStarTillNow = onlyStarTillNow && B.charAt(j-1) == '*';
            dp[0][j] = onlyStarTillNow;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char si = A.charAt(i-1), pj = B.charAt(j-1);
                if (pj != '*') {
                    if (pj == '?') {
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] = si == pj && dp[i-1][j-1];
                    }
                } else {
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                }
            }
        }

        return dp[m][n] ? 1 : 0;
    }

    public static void main(String[] args) {
        RegularExpression re = new RegularExpression();
        Verifier.verifyEquals(re.isMatch("aa", "a"), 0);
        Verifier.verifyEquals(re.isMatch("aa", "aa"), 1);
        Verifier.verifyEquals(re.isMatch("aaa", "aa"), 0);
        Verifier.verifyEquals(re.isMatch("aa", "*"), 1);
        Verifier.verifyEquals(re.isMatch("aa", "a*"), 1);
        Verifier.verifyEquals(re.isMatch("ab", "?*"), 1);
        Verifier.verifyEquals(re.isMatch("aab", "c*a*b"), 0);
    }

}
