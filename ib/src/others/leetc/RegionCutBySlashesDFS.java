package others.leetc;

import util.Verifier;

//https://leetcode.com/problems/regions-cut-by-slashes/

/**
 * Idea is to split each cell into two parts(even in case of ' '). The part before slash would be 0 and part ofter that
 * will be 1. These two parts are considered left,right to each other.<br>
 *
 * Now, start traversing the matrix, with first part of first square. Consider slashes as boundaries and <b>flow like water</b>.
 * For example if you're in first part of a cell containing '/', then you can go in all of the following directions:
 * <ol>
 *     <li>left (to right part (part 1) of previous cell)</li>
 *     <li>top (to the cell above)
     *     <ol>
     *         <li>part 1 of the cell above if it contains '/'</li>
     *         <li>part 0 of the cell above if it contains '\' or ' '</li>
     *     </ol>
 *     </li>
 *     <li>Can't go right or bottom</li>
 * </ol>
 *
 */
public class RegionCutBySlashesDFS {

    boolean [][][] visited;
    int regionCount = 0;
    boolean currentRegionSet = false;
    String [] grid;
    int m;

    private void doDfs(int i, int j, int a) {
        if (i < 0 || j < 0 || i >= m || j >=m || visited[i][j][a]) {
            return;
        }
        visited[i][j][a] = true;
        if (!currentRegionSet) {
            currentRegionSet = true;
            regionCount++;
        }
        char c = grid[i].charAt(j);
        if (c == '/') {
            if (a == 0) {
                // can go left or up
                doDfs(i, j-1, 1); //go left
                if (i>0) {
                    doDfs(i-1, j, grid[i-1].charAt(j) == '\\' ? 0 : 1); //go to appropriate part of the cell above
                }
            } else {
                // can go right or down
                doDfs(i, j+1,0); //go right
                if (i < m -1) {
                    doDfs(i+1, j, grid[i+1].charAt(j) == '\\' ? 1 : 0); // go to appropriate part of the cell below
                }
            }
        } else if (c == '\\') {
            if (a == 0) {
                // Can go left or down
                doDfs(i, j-1, 1); //go left
                if (i < m -1) {
                    doDfs(i+1, j, grid[i+1].charAt(j) == '\\' ? 1 : 0); // go to appropriate part of the cell below
                }
            } else {
                // can go right or up
                doDfs(i, j+1,0); // go right
                if (i>0) {
                    doDfs(i-1, j, grid[i-1].charAt(j) == '\\' ? 0 : 1); //go to appropriate part of the cell above
                }
            }
        } else {
            // can go left,right, up, or down.
            if (a == 0) {
                doDfs(i, j-1, 1); //go left
                doDfs(i, j, 1); //go right
            } else {
                doDfs(i, j, 0); //go left
                doDfs(i, j+1, 0); // go right
            }
            if (i>0) {
                doDfs(i-1, j, grid[i-1].charAt(j) == '\\' ? 0 : 1); //go to appropriate part of the cell above
            }
            if (i < m -1) {
                doDfs(i+1, j, grid[i+1].charAt(j) == '\\' ? 1 : 0); // go down to appropriate part of the cell below
            }
        }
    }

    public int regionsBySlashes(String[] grid) {
        m = grid.length;
        visited = new boolean[m][m][2];
        regionCount = 0;
        this.grid = grid;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j][0]) {
                    currentRegionSet = false;
                    doDfs(i,j,0);
                }
                if (!visited[i][j][1]) {
                    currentRegionSet = false;
                    doDfs(i,j,1);
                }
            }
        }
        return regionCount;
    }

    public static void main(String[] args) {
        RegionCutBySlashesDFS rcbs = new RegionCutBySlashesDFS();
        Verifier.verifyEquals(rcbs.regionsBySlashes(new String[] {" /","/ "}), 2);
        Verifier.verifyEquals(rcbs.regionsBySlashes(new String[] {" /",
                "  "}), 1);
        Verifier.verifyEquals(rcbs.regionsBySlashes(new String[] {"\\/",
                "/\\"}), 4);
        Verifier.verifyEquals(rcbs.regionsBySlashes(new String[] {"/\\",
                "\\/"}), 5);
        Verifier.verifyEquals(rcbs.regionsBySlashes(new String[] {"//",
                "/ "}), 3);
        Verifier.verifyEquals(rcbs.regionsBySlashes(new String[] {" /\\"," \\/","\\  "}), 4);
    }

}
