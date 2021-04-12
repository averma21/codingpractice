package math.round2;

import util.Verifier;

public class GridUniquePaths {

    public int paths(int A, int B) {
        if (A < 1 || B < 1) {
            return 0;
        }
        if (A == 1 || B == 1) {
            return 1;
        }
        int [][]dp = new int [A][B];
        for (int i = 0; i < B; i++) {
            dp[A-1][i] = 1;
        }
        for (int j = 0; j < A; j++) {
            dp[j][B-1] = 1;
        }
        for (int i = A-2; i >= 0; i--) {
            for (int j = B-2; j >=0; j--) {
                dp[i][j] = dp[i+1][j] + dp[i][j+1];
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        GridUniquePaths gup = new GridUniquePaths();
        Verifier.verifyEquals(gup.paths(2,2), 2);
        Verifier.verifyEquals(gup.paths(2,3), 3);
        Verifier.verifyEquals(gup.paths(100,1), 1);
    }

}
