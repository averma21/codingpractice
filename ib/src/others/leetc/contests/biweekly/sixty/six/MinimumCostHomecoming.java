package others.leetc.contests.biweekly.sixty.six;

import util.Verifier;

import java.util.HashSet;
import java.util.Set;

public class MinimumCostHomecoming {

    int[] rowCosts;
    int[] colCosts;
    int[] homePos;
    long minCost;
    int m, n;

    private void doDfs(int[] curPos, long curCost, Set<Integer> visited) {
        int x = curPos[0], y = curPos[1];
        System.out.println("Checking " + x + "," + y);
        if (x < 0 || x >= m || y < 0 || y >= n || curCost >= minCost) {
            System.out.println("Returning high cost");
            return;
        }
        if (x == homePos[0] && y == homePos[1]) {
            minCost = curCost;
            return;
        }
        int posID = x * n + y;
        System.out.println("Position " + posID);
        if (visited.contains(posID)) {
            System.out.println("Already visited position " + posID);
            return;
        }
        visited.add(posID);
        if (x > 0 && homePos[0] < x) {
            doDfs(new int[]{x - 1, y}, curCost + rowCosts[x - 1], visited);
        }
        if (x < m - 1 && homePos[0] > x) {
            doDfs(new int[]{x + 1, y}, curCost + rowCosts[x + 1], visited);
        }
        if (y > 0 && homePos[1] < y) {
            doDfs(new int[]{x, y - 1}, curCost + colCosts[y - 1], visited);
        }
        if (y < n - 1 && homePos[1] > y) {
            doDfs(new int[]{x, y + 1}, curCost + colCosts[y + 1], visited);
        }
        visited.remove(posID);
    }

    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        this.m = rowCosts.length;
        this.n = colCosts.length;
        int sx = startPos[0], sy = startPos[1];
        int dx = homePos[0], dy = homePos[1];
        int[][] dp = new int[Math.abs(dx-sx) + 1][Math.abs(dy-sy) + 1];
        if (sx <= dx && sy <= dy) {
            dp[dx-sx][dy-sy] = 0;
            for (int i = dx - 1; i >= sx; i--) {
                dp[i-sx][dy-sy] = dp[i+1-sx][dy-sy] + rowCosts[i+1];
            }
            for (int j = dy - 1; j >= sy; j--) {
                dp[dx-sx][j-sy] = dp[dx-sx][j+1-sy] + colCosts[j+1];
            }
            for (int i = dx - 1; i >= sx; i--) {
                for (int j = dy - 1; j >= sy; j--) {
                    dp[i-sx][j-sy] = Math.min(dp[i+1-sx][j-sy] + rowCosts[i+1], dp[i-sx][j+1-sy] + colCosts[j+1]);
                }
            }
            return dp[0][0];
        }
        if (sx <= dx && sy >= dy) {
            dp[dx-sx][sy-dy] = 0;
            for (int i = dx - 1; i >= sx; i--) {
                dp[i-sx][sy-dy] = dp[i+1-sx][sy-dy] + rowCosts[i+1];
            }
            for (int j = dy + 1; j <= sy; j++) {
                dp[dx-sx][j-dy] = dp[dx-sx][j-1-dy] + colCosts[j-1];
            }
            for (int i = dx - 1; i >= sx; i--) {
                for (int j = dy + 1; j <= sy; j++) {
                    dp[i-sx][j-dy] = Math.min(dp[i+1-sx][j-dy] + rowCosts[i+1], dp[i-sx][j-1-dy] + colCosts[j-1]);
                }
            }
            return dp[0][sy-dy];
        }
        if (sx >= dx && sy <= dy) {
            for (int i = dx + 1; i <= sx; i++) {
                dp[i][dy] = dp[i-1][dy] + rowCosts[i-1];
            }
            for (int j = dy - 1; j >= sy; j--) {
                dp[dx][j] = dp[dx][j+1] + colCosts[j+1];
            }
            for (int i = dx + 1; i <= sx; i++) {
                for (int j = dy - 1; j >= sy; j--) {
                    dp[i][j] = Math.min(dp[i-1][j] + rowCosts[i-1], dp[i][j+1] + colCosts[j+1]);
                }
            }
        }
        if (sx >= dx && sy >= dy) {
            for (int i = dx + 1; i <= sx; i++) {
                dp[i][dy] = dp[i-1][dy] + rowCosts[i-1];
            }
            for (int j = dy + 1; j <= sy; j++) {
                dp[dx][j] = dp[dx][j-1] + colCosts[j-1];
            }
            for (int i = dx + 1; i <= sx; i++) {
                for (int j = dy + 1; j <= sy; j++) {
                    dp[i][j] = Math.min(dp[i-1][j] + rowCosts[i-1], dp[i][j-1] + colCosts[j-1]);
                }
            }
        }
        return dp[sx][sy];
    }

//    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
//        this.colCosts = colCosts;
//        this.rowCosts = rowCosts;
//        this.homePos = homePos;
//        this.minCost = Integer.MAX_VALUE;
//        this.m = rowCosts.length;
//        this.n = colCosts.length;
//        doDfs(startPos, 0, new HashSet<>());
//        return (int)minCost;
//    }

    public static void main(String[] args) {
        MinimumCostHomecoming mch = new MinimumCostHomecoming();
        Verifier.verifyEquals(mch.minCost(new int[] {1,0}, new int[] {2,3}, new int[] {5,4,3}, new int[] {8,2,6,7}), 18);
        Verifier.verifyEquals(mch.minCost(new int[] {0,0}, new int[] {0,0}, new int[] {5}, new int[] {26}), 0);
        Verifier.verifyEquals(mch.minCost(new int[] {3,0}, new int[] {4,1}, new int[] {10,5,6,7,11}, new int[] {4,9,13,16,29,28,22,13}), 20);
        Verifier.verifyEquals(mch.minCost(new int[]{45, 70}, new int[]{32, 35},
                new int[]{4, 10, 5, 8, 0, 10, 5, 9, 10, 2, 7, 7, 7, 6, 1, 1, 5, 1, 5, 9, 7, 1, 0, 3, 1, 2, 6, 4, 6, 4, 2, 4, 1, 1, 5, 2, 3, 9, 3, 9, 8, 4, 1, 4, 6, 6728, 8650, 6586, 9762, 9034, 9246, 5033, 9632, 6907, 7237, 6422, 5603, 6062, 5033, 5109, 8118, 7210, 9672, 8268, 5157, 5854, 7723, 8101},
                new int[]{7, 8, 9, 8, 10, 3, 10, 4, 8, 10, 7, 5, 5, 9, 7, 5, 7, 10, 8, 6, 2, 2, 4, 10, 7, 3, 6, 2, 1, 8, 1, 6, 5, 5, 1, 9, 10, 6, 3, 2, 8, 6, 3, 0, 10, 4, 5, 4, 6, 2, 1, 6, 8, 9, 3, 0, 5, 6, 8, 3, 8, 6, 10, 4, 6, 4, 3, 3, 6, 3, 6893, 9916, 9592, 7854, 8030, 6396, 8904, 5191, 9514, 9417, 9701, 9840, 9194, 6515, 5643, 7804, 9768, 8898, 6735, 8548, 6859, 6024, 9551, 9520, 5537, 5135}),
                20);

    }

}
