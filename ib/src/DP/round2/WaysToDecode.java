package DP.round2;

import util.Verifier;

public class WaysToDecode {

    public static int numDecodings(String A) {
        final int mod = (int)1e9 + 7;
        int [] ways = new int[A.length()];
        if (A.charAt(0) == '0') {
            return 0;
        }
        ways[0] = 1;
        char prev = A.charAt(0);
        for (int i = 1; i < A.length(); i++) {
            char cur = A.charAt(i);
            int waysConsideringCurCharAsSingle = cur == '0' ? 0 : ways[i-1];
            int twoChars = Integer.parseInt("" + prev + cur);
            int waysConsideringCurCharWithPrevChar = twoChars <= 26 ? (i >= 2 ? ways[i-2] : 1) : 0;
            if (twoChars < 10) {
                waysConsideringCurCharWithPrevChar = 0;
            }
            ways[i] = waysConsideringCurCharAsSingle + waysConsideringCurCharWithPrevChar;
            ways[i] %= mod;
            if (ways[i] == 0) {
                return 0;
            }
            prev = cur;
        }
        return ways[A.length() - 1];
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(numDecodings("1"), 1);
        Verifier.verifyEquals(numDecodings("12"), 2);
        Verifier.verifyEquals(numDecodings("123"), 3);
        Verifier.verifyEquals(numDecodings("1234"), 3);
        Verifier.verifyEquals(numDecodings("1230"), 0);
        Verifier.verifyEquals(numDecodings("1200"), 0);
        Verifier.verifyEquals(numDecodings("1220"), 2);
        Verifier.verifyEquals(numDecodings("0222"), 0);
        Verifier.verifyEquals(numDecodings("2611055971756562"), 4);
    }

}
