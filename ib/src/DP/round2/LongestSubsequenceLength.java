package DP.round2;

import util.Verifier;

import java.util.Stack;

public class LongestSubsequenceLength {


    public int longestSubsequenceLength(final int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int [] lisFromLeft = new int[n];
        for (int i = 0; i < n; i++) {
            int maxLengthIncludingThis = 1;
            int ai = A[i];
            for (int j = 0; j < i; j++) {
                if (A[j] < ai) {
                    maxLengthIncludingThis = Math.max(maxLengthIncludingThis, lisFromLeft[j] + 1);
                }
            }
            lisFromLeft[i] = maxLengthIncludingThis;
        }
        int [] lisFromRight = new int[n];
        for (int i = n-1; i >= 0; i--) {
            int maxLengthIncludingThis = 1;
            int ai = A[i];
            for (int j = n-1; j > i; j--) {
                if (A[j] < ai) {
                    maxLengthIncludingThis = Math.max(maxLengthIncludingThis, lisFromRight[j] + 1);
                }
            }
            lisFromRight[i] = maxLengthIncludingThis;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, lisFromLeft[i] + lisFromRight[i] - 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        LongestSubsequenceLength lsl = new LongestSubsequenceLength();
        Verifier.verifyEquals(lsl.longestSubsequenceLength(new int[] {1,2,1}), 3);
        Verifier.verifyEquals(lsl.longestSubsequenceLength(new int[] {1, 11, 2, 10, 4, 5, 2, 1}), 6);
        Verifier.verifyEquals(lsl.longestSubsequenceLength(new int[] {1, 3, 5, 6, 4, 8, 4, 3, 2, 1}), 9);
    }

}
