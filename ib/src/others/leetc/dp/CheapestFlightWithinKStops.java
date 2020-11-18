package others.leetc.dp;

import util.Verifier;

public class CheapestFlightWithinKStops {

    private boolean debug = false;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int [][] graph = new int [n][n];
        if (src == dst) {
            return 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = -1;
            }
        }
        for (int [] edge : flights) {
            graph[edge[0]][edge[1]] = edge[2];
        }
        int [][][] dp = new int [n][n][K + 1];
        for (int k = 0; k <= K; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        dp[i][j][k] = 0;
                    } else {
                        dp[i][j][k] = k == 0 && graph[i][j] != -1 ? graph[i][j] : Integer.MAX_VALUE;
                    }
                }
            }
        }
        for (int k = 1; k <= K; k++) {
            if (debug)
            System.out.println("Checking for k=" + k);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        dp[i][j][k] = 0;
                        continue;
                    }
                    for (int i1 = 0; i1 < n; i1++) {
                        if (i == i1 || j == i1) {
                            continue;
                        }
                        if (graph[i][i1] != -1 && dp[i1][j][k-1] != Integer.MAX_VALUE) {
                            if (debug)
                            System.out.printf("Checking from %d to %d via %d\n",i, j, i1);
                            dp[i][j][k] = Math.min(dp[i][j][k], graph[i][i1] + dp[i1][j][k-1]);
                            if (debug)
                            System.out.printf("Updated to %d\n", dp[i][j][k]);
                        }
                    }
                    dp[i][j][k] = Math.min(dp[i][j][k], dp[i][j][k-1]);
                }
            }
        }
        return dp[src][dst][K] == Integer.MAX_VALUE ? -1 : dp[src][dst][K];
    }

    public static void main(String[] args) {
        CheapestFlightWithinKStops cfw = new CheapestFlightWithinKStops();
        //cfw.debug = true;
        Verifier.verifyEquals(cfw.findCheapestPrice(3,
                new int [][]{{0,1,100},{1,2,100},{0,2,500}},
        0,
        2,
        1), 200);
        Verifier.verifyEquals(cfw.findCheapestPrice(3,
                new int [][]{{0,1,100},{1,2,100},{0,2,500}},
                0,
                2,
                0), 500);
        Verifier.verifyEquals(cfw.findCheapestPrice(4,
                new int [][]{{0,1,100},{1,2,100},{0,3,500}},
                0,
                2,
                1), 200);
        Verifier.verifyEquals(cfw.findCheapestPrice(4,
                new int [][]{{0,1,100},{1,2,100},{0,3,500}},
                0,
                2,
                0), -1);
        Verifier.verifyEquals(cfw.findCheapestPrice(4,
                new int [][]{{0,1,2},{1,2,1},{2,0,10}},
                1,
                2,
                1), 1);
    }

}
