package math;

import java.util.Arrays;

public class GridCountingPaths {
    private int ways[][];
    public int uniquePaths(int A, int B) {
        if (A == 1 || B == 1)
            return 1;
        ways = new int[A][B];
        ways[0][0] = 0;
        ways[0][1] = 1;
        ways[1][0] = 1;
        return ways(A-1, B-1);
    }

    private int ways(int x, int y) {
        if (x < 0 || y < 0)
            return 0;
        if (ways[x][y] != 0 || x == 0 && y == 0) {
            return ways[x][y];
        }
        return ways(x-1, y) + ways(x, y - 1);
    }

    public static void main(String[] args) {
        GridCountingPaths gridCountingPaths = new GridCountingPaths();
        System.out.println(gridCountingPaths.uniquePaths(2,2));
        System.out.println(gridCountingPaths.uniquePaths(2,3));
        System.out.println(gridCountingPaths.uniquePaths(100,1));
    }

}
