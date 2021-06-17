package strings;

import util.Verifier;

import static util.PatternSearcher.kmpFailureFunc;

public class MinCharPalindromeBegin {

    public static int solve(String A) {
        StringBuilder sb = new StringBuilder(A);
        String text = sb.reverse().toString();
        String pattern = A;
        int [] ans = kmpFailureFunc(pattern);
        int i = 0, j = 0, n = text.length(), m = pattern.length();
        int maxMatchLen = 0, unmatchedPrefixLength = 0;
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
                i++;
                if (j == m) {
                    return 0;
                }
            } else if (j == 0) {
                i++;
            } else {
                if (j >= maxMatchLen) {
                    maxMatchLen = j;
                }
                j = ans[j - 1] + 1;
            }
        }
        if (j > 1) {
            unmatchedPrefixLength = i - j;
        } else {
            unmatchedPrefixLength = A.length() - 1;
        }
        return unmatchedPrefixLength;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(solve("a"), 0);
        Verifier.verifyEquals(solve("ab"), 1);
        Verifier.verifyEquals(solve("abaa"), 1);
        Verifier.verifyEquals(solve("abcd"), 3);
        Verifier.verifyEquals(solve("abba"), 0);
        Verifier.verifyEquals(solve("hqghumeaylnlfdxfi"), 16);
        Verifier.verifyEquals(solve("eylfpbnpljvrvipyamyehwqnq"), 24);
    }
}
