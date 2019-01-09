package strings;

import util.Verifier;

public class PalindromeString {

    private boolean isAlphanumeric(char c) {
        return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9';
    }

    public int isPalindrome(String A) {
        if (A == null) {
            return 0;
        }
        if (A.length() <= 1) {
            return 1;
        }
        for(int i = 0, j = A.length() - 1; i <= j;) {
            char first = A.charAt(i);
            char second = A.charAt(j);
            if (!isAlphanumeric(first)) {
                i++;
                continue;
            }
            if (!isAlphanumeric(second)) {
                j--;
                continue;
            }
            if (Character.toLowerCase(first) != Character.toLowerCase(second)) {
                return 0;
            }
            i++;j--;
        }
        return 1;
    }

    public static void main(String[] args) {
        PalindromeString palindromeString = new PalindromeString();
        Verifier.verifyEquals(palindromeString.isPalindrome(null), 0);
        Verifier.verifyEquals(palindromeString.isPalindrome(""), 1);
        Verifier.verifyEquals(palindromeString.isPalindrome("a"), 1);
        Verifier.verifyEquals(palindromeString.isPalindrome("abA"), 1);
        Verifier.verifyEquals(palindromeString.isPalindrome("a;ba"), 1);
        Verifier.verifyEquals(palindromeString.isPalindrome("A man, a plan, a canal: Panama"), 1);
        Verifier.verifyEquals(palindromeString.isPalindrome("race a car"), 0);
    }

}
