package others.leetc.dp;

//https://leetcode.com/problems/regular-expression-matching/

import DP.round2.RegularExpressionII;
import util.Verifier;

/**
 * S="aab", P="c*a*b:
 * Matrix =
 *     E  a  a  b
 *  E  T  F  F  F
 *  c  F  F  F  F
 *  *  T  F  F  F
 *  a  F  T  F  F
 *  *  T  T  T  F
 *  b  F  F  F  T
 *
 *  dp[i][j] denotes if substrings p(0..i) and s(0..j) match. So dp[m][n] would be the answer<br/>
 *  First column and first row denote Empty string and empty pattern respectively. So, dp[0][0] is true.<br>
 *  Empty pattern doesn't match anything (except empty string) so first row starting from index 1 is false<br>
 *  Empty string can match a non-zero length pattern of type a* or .* (since * means 0 or more). So for filling first column,
 *  value would be F if current pattern char is non star(*). In case of (*) we need to check if there has been only one <b>net</b> non-star
 *  character till now. For example in the matrix above, see in the first column both the values against * are T since first c is taken down
 *  by first star and second c has been taken down by second star. Net non-star could be calculated by incrementing count when non star is found
 *  and decrementing it if star is found.
 *
 *  Now for filling the matrix dp[i][j], if we encounter a non star character in pattern, we check if it matches with current character in string and if
 *  there has been a match till previous index of string and previous index in pattern (i.e dp[i-1][j-1] == true).
 *
 *  If we encounter star, there are two cases - star takes down previous char of pattern, so no occurrence of previous char in string, which can
 *  be checked by dp[i-2][j]. Other case is one or more occurrence, this can be checked by comparing previous pattern char with current string character and comparing match of
 *  string till previous index (j-1) matching till current index of pattern (*) (dp[i][j-1])
 */
public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        int m = p.length(), n = s.length();
        boolean [][] dp = new boolean [m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[0][i] = false;
        }
        int charFound = 0;
        for (int i = 1; i <= m; i++) {
            if (p.charAt(i-1) != '*') {
                charFound++;
                dp[i][0] = false;
            } else {
                dp[i][0] = (charFound == 1);
                charFound--;
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pi = p.charAt(i-1);
                char sj = s.charAt(j-1);
                if (pi != '*') {
                    if (pi == '.') {
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] = (dp[i-1][j-1] && pi == sj);
                    }
                } else {
                    char pi2 = p.charAt(i-2);
                    dp[i][j] = dp[i-2][j] || ((pi2 == sj || pi2 == '.') && dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        RegularExpressionMatching re2 = new RegularExpressionMatching();
        Verifier.verifyEquals(re2.isMatch("aa", "a"), false);
        Verifier.verifyEquals(re2.isMatch("aa", "aa"), true);
        Verifier.verifyEquals(re2.isMatch("aaa", "aa"), false);
        Verifier.verifyEquals(re2.isMatch("aa", "a*"), true);
        Verifier.verifyEquals(re2.isMatch("aa", ".*"), true);
        Verifier.verifyEquals(re2.isMatch("ab", ".*"), true);
        Verifier.verifyEquals(re2.isMatch("aab", "c*a*b"), true);
        Verifier.verifyEquals(re2.isMatch("baaaaaabaaaabaaaaababababbaab", "..a*aa*a.aba*a*bab*"), false);
    }

}
