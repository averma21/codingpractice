package others.leetc.contests.oneseventy.oneseventyeight;

//https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid

import util.Verifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/**
 * Given a m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
 * 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
 * 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
 * 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
 * 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
 * Notice that there could be some invalid signs on the cells of the grid which points outside the grid.
 *
 * You will initially start at the upper left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path doesn't have to be the shortest.
 *
 * You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
 *
 * Return the minimum cost to make the grid have at least one valid path.
 */
public class MinimumCostOneValidPath {

    int hash(int row, int col, int rowSize) {
        return row*rowSize + col;
    }

    private static class Cell {
        private int x;
        private int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return x == cell.x &&
                    y == cell.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private Queue<Cell> queue;
    boolean found;
    private Set<Integer> visited;
    private static Cell delimiter = new Cell(-1, -1);
    private int colCount;
    private int rowCount;
    private int [][] grid;

    private Cell getNextCell(int row, int col, int direction) {
        int nextX = row, nextY = col;
        switch (direction) {
            case 1:
                nextX = row;
                nextY = col < colCount - 1 ? col + 1 : col;
                break;
            case 2:
                nextX = row;
                nextY = col > 0 ? col - 1 : col;
                break;
            case 3:
                nextX = row < rowCount - 1 ? row + 1 : row;
                nextY = col;
                break;
            case 4:
                nextX = row > 0 ? row - 1 : row;
                nextY = col;
                break;
        }
        return new Cell(nextX, nextY);
    }

    void visitReachableNodes(int row, int col, int direction) {
        Cell next;
        do {
            next = getNextCell(row, col, direction);
            int hash = hash(next.x, next.y, grid[0].length);
            if (next.x  == row && next.y == col || visited.contains(hash)) {
                break;
            }
            if (next.x == grid.length - 1 && next.y == grid[0].length - 1) {
                found = true;
                break;
            }
            queue.add(next);
            visited.add(hash);
            row = next.x;
            col = next.y;
            direction = grid[row][col];
        } while (true);
    }

    private List<Integer> getOtherDirections(int row, int col) {
        switch (grid[row][col]) {
            case 1: return Arrays.asList(2,3,4);
            case 2: return Arrays.asList(1,3,4);
            case 3: return Arrays.asList(1,2,4);
            case 4: return Arrays.asList(1,2,3);
        }
        return Collections.emptyList();
    }

    public int minCost(int[][] grid) {
        if (grid == null || grid.length == 0 || grid.length == 1 && (grid[0].length == 0 || grid[0].length == 1)) {
            return 0;
        }
        queue = new LinkedList<>();
        visited = new HashSet<>();
        queue.add(new Cell(0,0));
        this.grid = grid;
        rowCount = grid.length;
        colCount = grid[0].length;
        found = false;
        visitReachableNodes(0, 0, grid[0][0]);
        queue.add(delimiter);
        if (found)
            return 0;
        int cost = 1;
        while (!found && !queue.isEmpty()) {
            Cell next = queue.poll();
            if (next == null)
                break;
            if (next == delimiter) {
                cost++;
                queue.add(delimiter);
                continue;
            }
            for (int direction : getOtherDirections(next.x, next.y)) {
                visitReachableNodes(next.x, next.y, direction);
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        MinimumCostOneValidPath mcov = new MinimumCostOneValidPath();
        Verifier.verifyEquals(mcov.minCost(new int[][]{{1,1,1,1},{2,2,2,2},{1,1,1,1},{2,2,2,2}}), 3);
        Verifier.verifyEquals(mcov.minCost(new int[][]{{1,1,3},{3,2,2},{1,1,4}}), 0);
        Verifier.verifyEquals(mcov.minCost(new int[][]{{1,2},{4,3}}), 1);
        Verifier.verifyEquals(mcov.minCost(new int[][]{{2,2,2},{2,2,2}}), 3);
        Verifier.verifyEquals(mcov.minCost(new int[][]{{4}}), 0);

    }

}
