package DP;

import backtracking.PalindromePartitioning;
import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class PalindromePartitioningII {

    private boolean isPalindrome(String s) {
        if ("".equals(s))
            return true;
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }

    private Map<Integer, Integer> cuts;

    private int minCuts(String A, int index) {
        if (index == A.length() - 1) {
            return 0;
        }
        if (cuts.containsKey(index))
            return cuts.get(index);
        String prefix = "";
        int min = Integer.MAX_VALUE;
        for (int i = index; i < A.length(); i++) {
            prefix += A.charAt(i);
            if (isPalindrome(prefix)) {
                min = i == A.length() - 1 ? 0 : Math.min(min, 1 + minCuts(A,i+1));
            }
        }
        cuts.put(index, min);
        return min;
    }

    private int minCuts(String A) {
        if (A == null || A.length() == 0)
            return 0;
        cuts = new HashMap<>();
        return minCuts(A, 0);
    }

    public static void main(String[] args) {
        PalindromePartitioningII pp = new PalindromePartitioningII();
        Verifier.verifyEquals(pp.minCuts(null), 0);
        Verifier.verifyEquals(pp.minCuts(""), 0);
        Verifier.verifyEquals(pp.minCuts("a"), 0);
        Verifier.verifyEquals(pp.minCuts("aa"), 0);
        Verifier.verifyEquals(pp.minCuts("aba"), 0);
        Verifier.verifyEquals(pp.minCuts("aab"), 1);
        Verifier.verifyEquals(pp.minCuts("ab"), 1);
        Verifier.verifyEquals(pp.minCuts("abc"), 2);
        Verifier.verifyEquals(pp.minCuts("abba"), 0);
        Verifier.verifyEquals(pp.minCuts("aabb"), 1);
        Verifier.verifyEquals(pp.minCuts("aabbccc"), 2);
    }

}
