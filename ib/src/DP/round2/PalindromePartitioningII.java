package DP.round2;

import util.Verifier;

public class PalindromePartitioningII {

    public int minCut(String A) {
        if (A == null || A.length() == 0) {
            return 0;
        }
        boolean [][] isPalindrome = new boolean[A.length() + 1][A.length()];
        for (int j = 0; j < A.length(); j++) {
            isPalindrome[0][j] = true;
            isPalindrome[1][j] = true;
        }
        for (int lengthMinus1 = 1; lengthMinus1 < A.length(); lengthMinus1++) {
            for (int j = 0; j < A.length(); j++) {
                int i = Math.min(j + lengthMinus1, A.length() - 1);
                if (j == A.length() - 1) {
                    isPalindrome[lengthMinus1+1][j] = false;
                } else {
                    isPalindrome[lengthMinus1+1][j] = A.charAt(i) == A.charAt(j) &&isPalindrome[lengthMinus1-1][j+1];
                }
            }
        }
        int [] minCuts = new int[A.length()];
        for (int i = 0; i < A.length(); i++) {
            if (isPalindrome[i+1][0]) {
                minCuts[i] = 0;
            } else {
                int cuts = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {
                    if (isPalindrome[i-j][j+1] && 1 + minCuts[j] < cuts) {
                        cuts = 1 + minCuts[j];
                    }
                }
                minCuts[i] = cuts;
            }
        }
        return minCuts[A.length()-1];
    }

    public static void main(String[] args) {
        PalindromePartitioningII pp = new PalindromePartitioningII();
        Verifier.verifyEquals(pp.minCut(null), 0);
        Verifier.verifyEquals(pp.minCut(""), 0);
        Verifier.verifyEquals(pp.minCut("a"), 0);
        Verifier.verifyEquals(pp.minCut("aa"), 0);
        Verifier.verifyEquals(pp.minCut("aba"), 0);
        Verifier.verifyEquals(pp.minCut("aab"), 1);
        Verifier.verifyEquals(pp.minCut("ab"), 1);
        Verifier.verifyEquals(pp.minCut("abc"), 2);
        Verifier.verifyEquals(pp.minCut("abba"), 0);
        Verifier.verifyEquals(pp.minCut("aabb"), 1);
        Verifier.verifyEquals(pp.minCut("aabbccc"), 2);
    }

}
