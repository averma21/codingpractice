package strings;

import util.Verifier;

public class LongestPalindrome {

    private static int palindromeFromCenter(String A, float centerPos) {
        int left = (int)Math.floor(centerPos);
        int right = (int)Math.ceil(centerPos);
        int length = 0;
        while (left >= 0 && right <= A.length() - 1 && A.charAt(left) == A.charAt(right)) {
            length = right - left + 1;
            left--;
            right++;
        }
        return length;
    }


    public static String longestPalindrome(String A) {
        if (A == null || A.isEmpty()) {
            return A;
        }
        float centerPos = 0;
        int maxLength = 0;
        float maxCenterPos = 0;
        while (centerPos <= A.length() - 1) {
            int length = palindromeFromCenter(A, centerPos);
            if (maxLength < length) {
                maxLength = length;
                maxCenterPos = centerPos;
            }
            centerPos += 0.5;
        }
        int left = (int)Math.floor(maxCenterPos);
        int right = (int)Math.ceil(maxCenterPos);
        while (right - left + 1 < maxLength) {
            left--;
            right++;
        }
        return A.substring(left, right+1);
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(longestPalindrome("a"), "a");
        Verifier.verifyEquals(longestPalindrome("aa"), "aa");
        Verifier.verifyEquals(longestPalindrome("ab"), "a");
        Verifier.verifyEquals(longestPalindrome("aaa"), "aaa");
        Verifier.verifyEquals(longestPalindrome("aba"), "aba");
        Verifier.verifyEquals(longestPalindrome("aaaabaaa"), "aaabaaa");
        Verifier.verifyEquals(longestPalindrome("abbcccbbbcaaccbababcbcabca"), "bbcccbb");
        Verifier.verifyEquals(longestPalindrome("caaccaccaac"), "caaccaccaac");
        Verifier.verifyEquals(longestPalindrome("cabcbcbbbcbbbcca"), "cbbbcbbbc");
    }

}
