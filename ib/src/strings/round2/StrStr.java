package strings.round2;

import util.Verifier;

import static strings.round2.MinCharPalindrome.getFailureArray;

public class StrStr {

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int strStr(final String A, final String B) {
        // B is the pattern/substring to be searched in A
        if (B == null || A == null || B.length() == 0 || A.length() == 0) {
            return -1;
        }
        int [] arr = getFailureArray(B);
        int i = 0, j = 0;
        while (i < A.length() && j < B.length()) {
            if (A.charAt(i) == B.charAt(j)) {
                j++;
                i++;
            } else {
                if (j == 0) {
                    i++;
                } else {
                    j = arr[j-1];
                }
            }
        }
        if (j == B.length()) {
            return i - j;
        }
        return -1;
    }

    public static void main(String[] args) {
        StrStr strStr = new StrStr();
        Verifier.verifyEquals(strStr.strStr("abcdaeb", "cdae"), 2);
        Verifier.verifyEquals(strStr.strStr("abcfabcfeefg", "abcfe"), 4);
        Verifier.verifyEquals(strStr.strStr("b", "baba"), -1);
    }

}
