package DP;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.List;

/**
 * DP of cut positions filled diagonally. dp[i][j] indicates min cost of cutting rod starting at i and ending at j.
 * So, dp array size would be n*n where n = cuts_array_size + 2 and dp[0][n-1] would be the minimum cost. dp array would
 * be filled diagonally in this case.
 *
 * for all i >= j dp[i][j] = 0
 * also if i == j-1 dp[i][j] = 0, since rod of length 1 can't be cut
 */
public class RodCutting {

    ArrayList<Integer> result;
    int[] cuts;
    int[][] parent;

    List<Integer> cutRod(int A, List<Integer> B) {
        int n = B.size() + 2;
        cuts = new int[n];
        cuts[0] = 0;
        for (int i = 0; i < B.size(); i++) {
            cuts[i + 1] = B.get(i);
        }
        cuts[n - 1] = A;

        long[][] dp = new long[n][n];
        parent = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int s = 0; s < n - len; s++) {
                int e = s + len;
                for (int k = s + 1; k < e; k++) {
                    System.out.printf("s=%d,e=%d,k=%d\n",s,e,k);
                    long sum = cuts[e] - cuts[s] + dp[s][k] + dp[k][e];
                    if (dp[s][e] == 0 || sum < dp[s][e]) {
                        dp[s][e] = sum;
                        parent[s][e] = k;
                    }
                    print("DP=", dp);
                }
            }
        }

        result = new ArrayList<>();
        backTrack(0, n - 1);

        return result;
    }

    private void backTrack(int s, int e) {
        if (s + 1 >= e) {
            return;
        }

        result.add(cuts[parent[s][e]]);
        backTrack(s, parent[s][e]);
        backTrack(parent[s][e], e);
    }

    void print(String s, long [][]a) {
        System.out.println(s);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        RodCutting rc = new RodCutting();
        Verifier.verifyEquals(rc.cutRod(6, Creator.getList(1,2,5)), Creator.getList(2,1,5));
    }

}
