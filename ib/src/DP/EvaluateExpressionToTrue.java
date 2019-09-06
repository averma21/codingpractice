package DP;

import util.Verifier;

import java.util.Arrays;

public class EvaluateExpressionToTrue {

    private int[][] trueMatrix;
    private int[][] falseMatrix;
    private final int mod = 1003;

    private int calc(String s, int start, int end, boolean reqdResult) {
        if (s == null || start > end || start < 0 || end >= s.length())
            return 0;
        if (start == end) {
            if (s.charAt(start) == 'T' && reqdResult)
                return 1;
            if (s.charAt(start) == 'F' && !reqdResult)
                return 1;
            return 0;
        }
        if (reqdResult && trueMatrix[start][end] != -1)
            return trueMatrix[start][end];
        if (!reqdResult && falseMatrix[start][end] != -1)
            return falseMatrix[start][end];
        long ways = 0;
        for (int i = start; i < end; i++){
            char c = s.charAt(i);
            switch (c) {
                case '&': if (reqdResult) {
                    ways += (long)calc(s, start, i-1, true) * calc(s, i+1, end, true);
                } else {
                    ways += (calc(s, start, i-1, false) * ((long)calc(s, i+1, end, true) + calc(s, i+1, end, false))%mod)%mod +
                            calc(s, start, i-1, true) * (long)calc(s, i+1, end, false);
                };
                break;
                case '|' : if (reqdResult) {
                    ways += (calc(s, start, i-1, true) * (calc(s, i+1, end, true) + (long)calc(s, i+1, end, false))%mod)%mod +
                            calc(s, start, i-1, false) * (long)calc(s, i+1, end, true);
                } else {
                    ways += calc(s, start, i-1, false) * (long)calc(s, i+1, end, false);
                };
                break;
                case '^': if (reqdResult) {
                    ways += (calc(s, start, i-1, false) * (long)calc(s, i+1, end, true))%mod +
                            (calc(s, start, i-1, true) * (long)calc(s, i+1, end, false))%mod;
                } else {
                    ways += ((long)calc(s, start, i-1, false) * calc(s, i+1, end, false))%mod +
                            ((long) calc(s, start, i-1, true) * calc(s, i+1, end, true))%mod;
                }
            }
            ways %= mod;
        }
        if (reqdResult)
            trueMatrix[start][end] = (int)ways;
        else
            falseMatrix[start][end] = (int)ways;
        return (int)ways;
    }

    private int evalTrueWays(String A) {
        trueMatrix = new int[A.length()][A.length()];
        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < A.length(); j++) {
                trueMatrix[i][j] = -1;
            }
        }
        falseMatrix = new int[A.length()][A.length()];
        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < A.length(); j++) {
                falseMatrix[i][j] = -1;
            }
        }
        return calc(A, 0, A.length()-1, true);
    }

    public static void main(String [] args) {
        EvaluateExpressionToTrue eett = new EvaluateExpressionToTrue();
        Verifier.verifyEquals(eett.evalTrueWays(""), 0);
        Verifier.verifyEquals(eett.evalTrueWays("T"), 1);
        Verifier.verifyEquals(eett.evalTrueWays("F"), 0);
        Verifier.verifyEquals(eett.evalTrueWays("T&T"), 1);
        Verifier.verifyEquals(eett.evalTrueWays("F&T"), 0);
        Verifier.verifyEquals(eett.evalTrueWays("F|T"), 1);
        Verifier.verifyEquals(eett.evalTrueWays("F|F"), 0);
        Verifier.verifyEquals(eett.evalTrueWays("T|T"), 1);
        Verifier.verifyEquals(eett.evalTrueWays("T|T&F^T"), 4);
        Verifier.verifyEquals(eett.evalTrueWays("T^T^F"), 0);
        Verifier.verifyEquals(eett.evalTrueWays("T^T^T^F|F&F^F|T^F^T"), 533);
    }

}
