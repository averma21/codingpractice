package DP.round2;

import util.Verifier;

public class EggDropProblem {

    public int solve(int A, int B) {
        int [][] dp = new int[A+1][B+1];
        for (int j = 1; j <= B; j++) {
            dp[1][j] = j;
        }
        for (int i = 1; i <= A; i++) {
            dp[i][1] = 1;
        }
        for (int i = 2; i <= A; i++) {
            for (int j = 2; j <= B; j++) {
                if (i > j) {
                    dp[i][j] = dp[i-1][j];
                    continue;
                }
                int min = Integer.MAX_VALUE;
                for (int k = 1; k <= j; k++) {
                    int attemptsIfFirstEggBreaks = 1 + dp[i-1][k-1];
                    int attemptsIfItDoesntBreak = 1 + dp[i][j-k];
                    min = Math.min(min, Math.max(attemptsIfFirstEggBreaks, attemptsIfItDoesntBreak)); // max b/c worst case complexity is to be considered
                }
                dp[i][j] = min;
            }
        }
        return dp[A][B];
    }

    public static void main(String[] args) {
        EggDropProblem edp = new EggDropProblem();
        Verifier.verifyEquals(edp.solve(3,14), 4);
    }

}
