package DP.round2;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Question statement is incomplete!
 * Basically we have to find if its possible to reach rightmost bottom cell from 0,0. If yes return length of longest path else return -1.
 * P.S. all paths from 0,0 to n-1,m-1 will have same length as we can travel only down or right so you can just return n+m-1 as well.
 */
public class IncreasingPathInMatrix {

    public int solve(List<List<Integer>> A) {

        if (A == null || A.size() == 0 || A.get(0).size() == 0) {
            return 0;
        }
        int m = A.size(), n = A.get(0).size();
        int [][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = 0;
            }
        }
        dp[0][0] = 1;
        for (int j = 1; j < n; j++) {
            if (A.get(0).get(j) > A.get(0).get(j-1)) {
                dp[0][j] = dp[0][j-1]+1;
            } else {
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            if (A.get(i).get(0) > A.get(i-1).get(0)) {
                dp[i][0] = dp[i-1][0] + 1;
            } else {
                break;
            }
        }
        int maxPath = Integer.MIN_VALUE;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int up = A.get(i-1).get(j);
                boolean upReachable = dp[i-1][j] != 0;
                int left = A.get(i).get(j-1);
                boolean leftReachable = dp[i][j-1] != 0;
                if (upReachable && A.get(i).get(j) > up) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + 1);
                }
                if (leftReachable && A.get(i).get(j) > left) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j-1] + 1);
                }
                maxPath = Math.max(maxPath, dp[i][j]);
            }
        }
        return dp[m-1][n-1] > 0 ? dp[m-1][n-1] : -1;
    }

    public static void main(String[] args) {
        IncreasingPathInMatrix ipm = new IncreasingPathInMatrix();
        Verifier.verifyEquals(ipm.solve(Creator.getList(
                Creator.getList(1,2),
                Creator.getList(3,4)
        )), 3);
        Verifier.verifyEquals(ipm.solve(Creator.getList(
                Creator.getList(1,2,3,4),
                Creator.getList(2,2,3,4),
                Creator.getList(3,2,3,4),
                Creator.getList(4,5,6,7)
        )), 7);
    }
}
