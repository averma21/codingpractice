package DP.round2;

import util.Verifier;

public class DungeonPrincess {

    public int calculateMinimumHP(int[][] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int m = A.length, n = A[0].length;
        int [][] dp = new int[m][n];
        dp[m-1][n-1] = A[m-1][n-1] < 0 ? -1 * A[m-1][n-1] + 1 : 0;
        for (int j = n-2; j >= 0; j--) {
            dp[m-1][j] = dp[m-1][j+1] > 0 ? Math.max(dp[m-1][j+1] - A[m-1][j], 0) : (A[m-1][j] > 0 ? 0 : -A[m-1][j] + 1);
        }
        for (int i = m-2; i >= 0; i--) {
            dp[i][n-1] = dp[i+1][n-1] > 0 ? Math.max(dp[i+1][n-1] - A[i][n-1], 0) : (A[i][n-1] > 0 ? 0 : -A[i][n-1] + 1);
        }
        for (int i = m-2; i >= 0; i--) {
            for (int j = n-2; j >= 0; j--) {
                int right = dp[i][j+1] > 0 ? Math.max(dp[i][j+1] - A[i][j], 0) : (A[i][j] > 0 ? 0 : -A[i][j] + 1);
                int down = dp[i+1][j] > 0 ? Math.max(dp[i+1][j] - A[i][j], 0) : (A[i][j] > 0 ? 0 : -A[i][j] + 1);
                dp[i][j] = Math.min(right, down);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        DungeonPrincess dp = new DungeonPrincess();
        Verifier.verifyEquals(dp.calculateMinimumHP(new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        }), 7);
        Verifier.verifyEquals(dp.calculateMinimumHP(new int[][]{
                {-39, -65, -93, -51, -97, -46, -32, -89, -70, -56, -14, -95, 2, -3, -32, -7, 8, -10, -16, -92},
                {-95, -55, -99, -51, -7, -82, -93, -6, -8, -54, -76, -20, -80, -2, 9, -100, -81, -78, -58, -27},
                {-76, -44, -40, -47, -50, -82, -21, -98, -28, 0, -10, 2, -90, -6, -12, -91, -28, -98, 1, -49},
                {-18, -54, -95, -51, 8, -18, -33, -18, -44, 2, 3, -11, -81, -35, 7, -19, -82, -42, -21, -45},
                {-57, -63, -42, -70, -66, -65, -52, -81, -17, -23, -91, 1, -68, -52, -42, 1, -65, -43, -69, -18},
                {-57, -49, -35, -56, -20, -36, -42, -47, -70, -26, -53, -41, -9, -98, 2, -25, 8, -6, -99, -47},
                {-76, -64, -8, -18, -3, 9, -23, -6, -93, -43, -82, -82, -47, -30, -48, -2, -54, -6, -19, -47},
                {-4, -96, -28, 10, -95, -25, -29, -37, 4, -87, -58, -68, -7, -92, -34, -48, -21, -17, -55, -91},
                {-28, -8, 5, -96, -17, -56, -54, -79, -17, 8, -92, -20, -65, -96, -88, -87, 6, -68, -46, -1},
                {-25, -79, -27, -77, -88, 7, -70, 3, -10, -58, 10, 6, 5, -55, -94, -41, -26, -19, -39, -12},
                {-46, -92, 9, -90, -31, -86, -1, 4, -40, -41, -95, 1, -60, -69, -42, -67, -45, -65, -47, -91},
                {-32, -99, 4, -65, -10, -83, -67, -96, -69, -63, 4, -43, -48, -98, -16, -73, -21, 1, -81, -56},
                {0, -1, -86, -71, -75, -1, -95, -22, -12, -38, -39, 10, -98, -53, -84, -60, -42, -85, -21, -98},
                {-33, -6, -31, -66, -70, -27, -25, -99, -26, 8, -86, -68, -92, -63, -62, -95, -8, -65, -13, -31},
                {-7, -84, -17, -66, -84, -13, 2, -34, -22, -96, -81, -89, -61, -34, 10, -23, -96, 3, -2, -82},
                {-30, -48, 1, -40, -84, -7, -8, -90, -32, -5, 7, -53, -64, -25, -73, -82, -85, -40, 1, -35},
                {-80, -83, -91, -90, -73, 2, -18, -25, -76, -72, -6, -28, -49, -86, 3, -80, -63, 4, -85, 3},
                {6, -8, -52, 1, -57, -72, -73, -28, -88, -74, -25, -46, -93, -76, -10, -44, -92, -38, -70, -74},
                {-43, -24, -98, -36, -77, -81, -2, -90, 1, -42, 3, -82, -2, -32, -80, -32, -12, -60, -79, -32},
                {-91, -82, -65, 8, -12, -64, -42, -82, -66, -16, -97, -96, -79, -29, -79, -6, -6, -19, 3, 4}
        }), 983);
    }

}
