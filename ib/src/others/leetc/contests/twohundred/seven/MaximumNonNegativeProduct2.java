package others.leetc.contests.twohundred.seven;

import util.Verifier;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MaximumNonNegativeProduct2 {

    Map<Integer, Map<Integer, Long>> maxCache;
    Map<Integer, Map<Integer, Long>> minCache;

    private void fillMap(int x, int y, long min, long max) {
        maxCache.putIfAbsent(x, new HashMap<>());
        maxCache.computeIfPresent(x, (r,m) -> {m.put(y, max); return m;});
        minCache.putIfAbsent(x, new HashMap<>());
        minCache.computeIfPresent(x, (r,m) -> {m.put(y, min); return m;});
    }

    private void traverse(int[][] grid, int x, int y) {
        if (x == grid.length - 1 && y == grid[0].length - 1) {
            fillMap(x,y, grid[x][y], grid[x][y]);
            return;
        }
        if (maxCache.containsKey(x) && maxCache.get(x).containsKey(y)) {
            return;
        }
        long x1 = -1, x2 = -1, y1 = -1, y2 = -1;
        boolean xSet = false, ySet = false;
        if (x < grid.length - 1) {
            traverse(grid, x+1, y);
            x1 = maxCache.get(x+1).get(y) * grid[x][y];
            x2 = minCache.get(x+1).get(y) * grid[x][y];
            xSet = true;
        }
        if (y < grid[0].length - 1) {
            traverse(grid, x, y+1);
            y1 = maxCache.get(x).get(y+1) * grid[x][y];
            y2 = minCache.get(x).get(y+1) * grid[x][y];
            ySet = true;
        }
        long min, max;
        if (xSet && ySet) {
            min = Math.min(x1, Math.min(x2, Math.min(y1, y2)));
            max = Math.max(x1, Math.max(x2, Math.max(y1, y2)));
        } else if (xSet) {
            min = Math.min(x1, x2);
            max = Math.max(x1, x2);
        } else {
            min = Math.min(y1, y2);
            max = Math.max(y1, y2);
        }
        fillMap(x, y, min, max);
    }


    public int maxProductPath(int[][] grid) {
        maxCache = new HashMap<>();
        minCache = new HashMap<>();
        traverse(grid, 0, 0);
        long maxProd = maxCache.get(0).get(0);
        return (maxProd >= 0 ? (int)(maxProd%(1.0E9 + 7)) : -1);
    }

    public static void main(String[] args) {
        MaximumNonNegativeProduct2 mnnp = new MaximumNonNegativeProduct2();
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
        Verifier.verifyEquals(mnnp.maxProductPath(new int[][] {
                {-1,2,-4,-3,1,2,1,4,0,4,2,1,-1,-3},
                {0,-2,3,-3,1,3,2,3,-4,2,4,-2,3,-2},
                {-1,4,0,-2,1,1,2,-2,1,-1,-4,0,3,1},
                {-2,3,3,0,-4,1,3,-1,-1,-3,-1,1,2,-3},
                {0,0,3,0,1,4,4,-2,4,1,-3,4,-4,-3},
                {-4,0,3,-2,0,2,4,-2,-2,-3,0,2,-4,3},
                {2,4,-3,-4,0,2,-2,-4,1,-1,4,0,4,-1},
                {0,-4,-2,0,3,1,2,-2,-4,-3,3,0,2,0},
                {2,-2,-2,-4,-1,-3,4,-2,-1,-3,-1,4,3,3},
                {2,3,0,3,0,-1,3,-1,-3,-1,-3,4,1,-1},
                {-2,-2,3,4,-2,1,-3,1,3,-1,-1,2,1,-1},
                {2,0,2,0,1,-3,-3,4,2,3,2,1,4,-3},
                {0,-4,-4,0,3,-2,4,3,-2,-4,-2,-2,-2,4}}), 917363797);
        Verifier.verifyEquals(mnnp.maxProductPath(new int[][]
                {
                        {-1,2,-4,-3,1},
                        {0,-2,3,-3,1},
                        {-1,4,0,-2,1},
                        {-2,3,3,0,-4}
                }), 576);

    }

}
