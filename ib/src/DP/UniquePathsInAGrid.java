package DP;

import util.Verifier;

import java.util.List;

public class UniquePathsInAGrid {

    public static int uniquePathsWithObstacles(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0)
            return 0;
        int [][] ways = new int[A.length][A[0].length];
        int j, i;
        for (j = 0; j < A[0].length && A[0][j] != 1; j++) {
            ways[0][j] = 1;
        }
        while (j < A[0].length) {
            ways[0][j] = 0;
            j++;
        }
        for (i = 0; i < A.length && A[i][0] != 1; i++) {
            ways[i][0] = 1;
        }
        while (i < A.length) {
            ways[i][0] = 0;
            i++;
        }
        for (i = 1; i < ways.length; i++) {
            for (j = 1; j < ways[i].length;j++) {
                if (A[i][j] == 1) {
                    ways[i][j] = 0;
                    continue;
                }
                ways[i][j] = ways[i-1][j] + ways[i][j-1];
            }
        }
        return ways[A.length-1][A[0].length-1];
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(uniquePathsWithObstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}}), 2);
    }

}
