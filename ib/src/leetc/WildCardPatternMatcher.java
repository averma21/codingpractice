package leetc;

import util.Verifier;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 *
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 *
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 * Example 5:
 *
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 */
public class WildCardPatternMatcher {

    private static boolean isMatchStarMeansZeroOrMore(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatchStarMeansZeroOrMore(text, pattern.substring(2)) ||
                    (first_match && isMatchStarMeansZeroOrMore(text.substring(1), pattern)));
        } else {
            return first_match && isMatchStarMeansZeroOrMore(text.substring(1), pattern.substring(1));
        }
    }

    private static boolean isMatchStarMeansZeroOrMoreDP(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[i].length; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println("");
//        }
        return dp[0][0];
    }

    private static boolean isMatchStarMeansOneOrMore(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return first_match && (isMatchStarMeansOneOrMore(text.substring(1), pattern.substring(2)) ||
                    isMatchStarMeansOneOrMore(text.substring(1), pattern));
        } else {
            return first_match && isMatchStarMeansOneOrMore(text.substring(1), pattern.substring(1));
        }
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(isMatchStarMeansZeroOrMore("a", "a"), true);
        Verifier.verifyEquals(isMatchStarMeansZeroOrMore("a", "a*"), true);
        Verifier.verifyEquals(isMatchStarMeansZeroOrMore("ab", "a*b"), true);
        Verifier.verifyEquals(isMatchStarMeansZeroOrMore("aab", "a*b"), true);
        Verifier.verifyEquals(isMatchStarMeansZeroOrMore("bc", "a*c"), false);
        Verifier.verifyEquals(isMatchStarMeansZeroOrMore("ac", "a.*c"), true);
        Verifier.verifyEquals(isMatchStarMeansZeroOrMore("b", "c*b"), true);
        Verifier.verifyEquals(isMatchStarMeansZeroOrMore("ac", "ab*c"), true);

        Verifier.verifyEquals(isMatchStarMeansZeroOrMoreDP("a", "a"), true);
        Verifier.verifyEquals(isMatchStarMeansZeroOrMoreDP("a", "a*"), true);
        Verifier.verifyEquals(isMatchStarMeansZeroOrMoreDP("ab", "a*b"), true);
        Verifier.verifyEquals(isMatchStarMeansZeroOrMoreDP("aab", "a*b"), true);
        Verifier.verifyEquals(isMatchStarMeansZeroOrMoreDP("bc", "a*c"), false);
        Verifier.verifyEquals(isMatchStarMeansZeroOrMoreDP("ac", "a.*c"), true);
        Verifier.verifyEquals(isMatchStarMeansZeroOrMoreDP("b", "c*b"), true);
        Verifier.verifyEquals(isMatchStarMeansZeroOrMoreDP("ac", "ab*c"), true);


        Verifier.verifyEquals(isMatchStarMeansOneOrMore("a", "a"), true);
        Verifier.verifyEquals(isMatchStarMeansOneOrMore("a", "a*"), true);
        Verifier.verifyEquals(isMatchStarMeansOneOrMore("ab", "a*b"), true);
        Verifier.verifyEquals(isMatchStarMeansOneOrMore("aab", "a*b"), true);
        Verifier.verifyEquals(isMatchStarMeansOneOrMore("bc", "a*c"), false);
        Verifier.verifyEquals(isMatchStarMeansOneOrMore("ac", "a.*c"), false);
        Verifier.verifyEquals(isMatchStarMeansOneOrMore("b", "c*b"), false);
        Verifier.verifyEquals(isMatchStarMeansOneOrMore("ac", "ab*c"), false);
    }

}
