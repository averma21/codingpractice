package others.leetc.contests.oneeighty.one;

import util.Verifier;

import java.util.HashSet;
import java.util.Set;

public class ValidPathInAGrid {

    int[][] grid;
    Set<Integer> visited;

    private boolean traverse(int prevI, int prevJ, int i, int j) {
        int linearPos = i * grid[0].length + j;
        if (visited.contains(linearPos)) {
            return false;
        }
        visited.add(linearPos);
        int nextI = i, nextJ = j;
        switch (grid[i][j]) {
            case 1:
                if (prevI == i) {
                    if (prevJ == j - 1) {
                        nextJ = j + 1;
                    } else if (prevJ == j + 1) {
                        nextJ = j - 1;
                    }
                }
                break;
            case 2:
                if (prevJ == j) {
                    if (prevI == i - 1) {
                        nextI = i + 1;
                    } else if (prevI == i + 1) {
                        nextI = i - 1;
                    }
                }
                break;
            case 3:
                if (prevI == i && prevJ == j - 1) {
                    nextI = i + 1;
                } else if (prevI == i + 1 && prevJ == j) {
                    nextJ = j - 1;
                }
                break;
            case 4:
                if (prevI == i + 1 && prevJ == j) {
                    nextJ = j + 1;
                } else if (prevI == i && prevJ == j + 1) {
                    nextI = i + 1;
                }
                break;
            case 5:
                if (prevI == i - 1 && prevJ == j) {
                    nextJ = j - 1;
                } else if (prevI == i && prevJ == j - 1) {
                    nextI = i - 1;
                }
                break;
            case 6:
                if (prevI == i && prevJ == j + 1) {
                    nextI = i - 1;
                } else if (prevI == i - 1 && prevJ == j) {
                    nextJ = j + 1;
                }
        }
        if (nextI != i || nextJ != j) {
            if (i == grid.length - 1 && j == grid[0].length - 1) {
                return true;
            }
        }
        if ((nextI != i || nextJ != j) && (0 <= nextI && nextI < grid.length && 0 <= nextJ && nextJ < grid[0].length)) {
            return traverse(i, j, nextI, nextJ);
        }
        return false;
    }

    public boolean hasValidPath(int[][] grid) {
        this.grid = grid;
        this.visited = new HashSet<>();
        if (grid.length == 0 || grid[0].length == 0) {
            return true;
        }
        switch (grid[0][0]) {
            case 1:
                return traverse(0, -1, 0, 0);
            case 2:
                return traverse(-1, 0, 0, 0);
            case 3:
                boolean tr1 = traverse(0, -1, 0, 0);
                if (!tr1) {
                    visited.clear();
                    return traverse(1, 0, 0, 0);
                }
                return true;
            case 4:
                tr1 = traverse(1, 0, 0, 0);
                if (!tr1) {
                    visited.clear();
                    return traverse(0, 1, 0, 0);
                }
                return true;
            case 5:
                tr1 = traverse(-1, 0, 0, 0);
                if (!tr1) {
                    visited.clear();
                    return traverse(0, -1, 0, 0);
                }
                return true;
            case 6:
                tr1 = traverse(0, 1, 0, 0);
                if (!tr1) {
                    visited.clear();
                    return traverse(-1, 0, 0, 0);
                }
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ValidPathInAGrid vpig = new ValidPathInAGrid();
        Verifier.verifyEquals(vpig.hasValidPath(new int[][]{{2,4,3},{6,5,2}}), true);
        Verifier.verifyEquals(vpig.hasValidPath(new int[][]{{1,2,1},{1,2,1}}), false);
        Verifier.verifyEquals(vpig.hasValidPath(new int[][]{{1,1,2}}), false);
        Verifier.verifyEquals(vpig.hasValidPath(new int[][]{{1,1,1,1,1,1,3}}), true);
        Verifier.verifyEquals(vpig.hasValidPath(new int[][]{{2},{2},{2},{2},{2},{2},{6}}), true);
    }

}
