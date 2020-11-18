package others.leetc.dp;

import util.Verifier;

//https://leetcode.com/problems/unique-paths-ii/
public class UniquePathsII {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int [][] dp = new int[m][n];
        boolean obstacleFound = false;
        for (int j = n-1; j >= 0; j--) {
            if (!obstacleFound) {
                obstacleFound = obstacleGrid[m-1][j] == 1;
            }
            dp[m-1][j] = obstacleFound ? 0 : 1;
        }
        obstacleFound = false;
        for (int i = m-1; i >= 0; i--) {
            if (!obstacleFound) {
                obstacleFound = obstacleGrid[i][n-1] == 1;
            }
            dp[i][n-1] = obstacleFound ? 0 : 1;
        }
        for (int i = m-2; i >= 0; i--) {
            for (int j = n-2; j >=0; j--) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i+1][j] + dp[i][j+1];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {

        UniquePathsII up2 = new UniquePathsII();
        Verifier.verifyEquals(up2.uniquePathsWithObstacles(new int[][] {
                {0,0,0}, {0,1,0}, {0,0,0}
        }), 2);
        Verifier.verifyEquals(up2.uniquePathsWithObstacles(new int[][] {
                {0,1}, {0,0}
        }), 1);
        Verifier.verifyEquals(up2.uniquePathsWithObstacles(new int[][] {
                {0,1,0}, {0,1,0}, {1,0,0}
        }), 0);
        Verifier.verifyEquals(up2.uniquePathsWithObstacles(new int[][] {
                {1,0}, {0,0}
        }), 0);
        Verifier.verifyEquals(up2.uniquePathsWithObstacles(new int[][] {
                {0,0}, {0,1}
        }), 0);
        Verifier.verifyEquals(up2.uniquePathsWithObstacles(new int[][] {
                {0,0,0}, {0,0,0}, {0,0,0},{0,1,0}
        }), 6);
    }

}
