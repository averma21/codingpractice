package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatternSearcher {

    public static int [] kmpFailureFunc(String pattern) {
        int[] ans = new int[pattern.length()];
        ans[0] = -1;
        for (int i = 1; i <= pattern.length() - 1; i++) {
            int j = ans[i - 1];
            while (j != - 1 && pattern.charAt(j + 1) != pattern.charAt(i)) {
                j = ans[j];
            }
            if (pattern.charAt(j + 1) == pattern.charAt(i)) {
                ans[i] = j + 1;
            } else {
                ans[i] = -1;
            }
        }
        return ans;
    }

    static List<Integer> kmpMatch(String text, String pattern) {
        int [] ans = kmpFailureFunc(pattern);
        List<Integer> matches = new ArrayList<>();
        int i = 0, j = 0, n = text.length(), m = pattern.length();
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
                if (j == m) {
                    matches.add(i - m + 1);
                    j = 0;
                }
                i++;
            } else if (j == 0){
                i++;
            } else {
                j = ans[j - 1] + 1;
            }
        }
        return matches;
    }

    public static void main(String[] args) {
        // KMP failure function
        Verifier.verifyEquals(kmpFailureFunc("abc"), new int[]{-1, -1, -1});
        Verifier.verifyEquals(kmpFailureFunc("aba"), new int[]{-1,-1,0});
        Verifier.verifyEquals(kmpFailureFunc("abab"), new int[]{-1,-1,0,1});
        Verifier.verifyEquals(kmpFailureFunc("abcab"), new int[]{-1,-1,-1,0,1});
        Verifier.verifyEquals(kmpFailureFunc("a"), new int[]{-1});
        Verifier.verifyEquals(kmpFailureFunc("aa"), new int[]{-1,0});
        Verifier.verifyEquals(kmpFailureFunc("aaa"), new int[]{-1,0,1});

        // KMP matcher
        Verifier.verifyEquals(kmpMatch("abc", "b"), Arrays.asList(new Integer[]{1}));
        Verifier.verifyEquals(kmpMatch("abcabc", "ab"), Arrays.asList(new Integer[]{0,3}));
        Verifier.verifyEquals(kmpMatch("abcabc", "abc"), Arrays.asList(new Integer[]{0,3}));
        Verifier.verifyEquals(kmpMatch("abc", "x").size(), 0);
        Verifier.verifyEquals(kmpMatch("aaba", "aba").size(), 1);
    }

}
