package others.leetc.contests.twohundred.seven;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MaximumNonNegativeProduct {

    long maxProd = Long.MIN_VALUE;
    Map<Integer, Map<Integer, Long>> cache;

    private void traverse(int[][] grid, int x, int y, Stack<Integer> path) {
        if (x == grid.length - 1 && y == grid[0].length - 1) {
            long prod = 1;
            for (int val : path) {
                prod *= val;
            }
            maxProd = Math.max(maxProd, prod);
            return;
        }
        if (x < grid.length - 1) {
            path.push(grid[x+1][y]);
            traverse(grid, x+1, y, path);
            path.pop();
        }
        if (y < grid[0].length - 1) {
            path.push(grid[x][y+1]);
            traverse(grid, x, y+1, path);
            path.pop();
        }
    }


    public int maxProductPath(int[][] grid) {
        maxProd = Long.MIN_VALUE;
        cache = new HashMap<>();
        Stack<Integer> path = new Stack<>();
        path.push(grid[0][0]);
        traverse(grid, 0, 0, path);
        return (maxProd >= 0 ? (int)(maxProd%(1.0E9 + 7)) : -1);
    }

    public static void main(String[] args) {
        MaximumNonNegativeProduct mnnp = new MaximumNonNegativeProduct();
        Verifier.verifyEquals(mnnp.maxProductPath(new int[][] {{-1,-2,-3},
                {-2,-3,-3},
                {-3,-3,-2}}), -1);
        Verifier.verifyEquals(mnnp.maxProductPath(new int[][] {{1,-2,1},
                {1,-2,1},
                {3,-4,1}}), 8);
        Verifier.verifyEquals(mnnp.maxProductPath(new int[][] {{1, 3},
                {0,-4}}), 0);
        Verifier.verifyEquals(mnnp.maxProductPath(new int[][] {{ 1, 4,4,0},
                {-2, 0,0,1},
                { 1,-1,1,1}}), 2);
//        Verifier.verifyEquals(mnnp.maxProductPath(new int[][] ), -1);

    }

}
