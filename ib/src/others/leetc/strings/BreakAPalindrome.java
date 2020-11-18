package others.leetc.strings;

import util.Verifier;

//https://leetcode.com/problems/break-a-palindrome/

/**
 * String of length 1 is always palindrome, return "" in that case.
 * Two cases - either result would be lexicographically lower, or higher
 * <ol>
 *     <li>
 *         Check from beginning, if any char is not 'a', replace it with 'a' => lexicographically lower(lowest) string.
 *         If non-a char is at middle of odd length palindrome, changing it won't make string non-palindrome, so ignore it.
 *     </li>
 *     <li>
 *         If no non-'a' char is found in the string, replace the last char with 'b' => lowest lexicographically higher string
 *     </li>
 * </ol>
 *
 */
public class BreakAPalindrome {

    public String breakPalindrome(String palindrome) {
        if (palindrome.length() == 1) {
            return "";
        }
        boolean isOdd = palindrome.length() % 2 == 1;
        StringBuilder sb = new StringBuilder();
        int mid = palindrome.length() / 2;
        int toCopyFrom = -1;
        for (int i = 0; i < palindrome.length(); i++) {
            char c = palindrome.charAt(i);
            char toAdd = c;
            if (c != 'a') {
                if (isOdd && i == mid) {
                    sb.append(toAdd);
                    continue;
                }
                toAdd = 'a';
                sb.append(toAdd);
                toCopyFrom = i + 1;
                break;
            }
            sb.append(toAdd);
        }
        if (toCopyFrom != -1) {
            if (toCopyFrom < palindrome.length()) {
                sb.append(palindrome.substring(toCopyFrom));
            }
        } else {
            sb.deleteCharAt(palindrome.length() - 1);
            sb.append('b');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BreakAPalindrome brk = new BreakAPalindrome();
        Verifier.verifyEquals(brk.breakPalindrome("abccba"), "aaccba");
        Verifier.verifyEquals(brk.breakPalindrome("aa"), "ab");
        Verifier.verifyEquals(brk.breakPalindrome("a"), "");
        Verifier.verifyEquals(brk.breakPalindrome("z"), "");
        Verifier.verifyEquals(brk.breakPalindrome("aza"), "azb");
        Verifier.verifyEquals(brk.breakPalindrome("aabaa"), "aabab");
        Verifier.verifyEquals(brk.breakPalindrome("aaaaa"), "aaaab");
        Verifier.verifyEquals(brk.breakPalindrome("aaaa"), "aaab");
        Verifier.verifyEquals(brk.breakPalindrome("abcba"), "aacba");
        Verifier.verifyEquals(brk.breakPalindrome("abba"), "aaba");
        Verifier.verifyEquals(brk.breakPalindrome("zzzz"), "azzz");
    }
}
