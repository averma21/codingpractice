package graphs;

import util.Verifier;

public class WaterFlow {

    public int solve(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }
        int m = A.length, n = A[0].length;
        short [][] colors = new short[m][n]; // 1 = BLUE, 2 = RED, 3 = RED+BLUE
        for (int i = 0; i < m; i++) {
            colors[i][0] = 1;
            colors[i][n-1] = 2;
        }
        for (int j = 0; j < n; j++) {
            colors[0][j] = 1;
            colors[m-1][j] = 2;
        }
        colors[0][n-1] = 3;
        colors[m-1][0] = 3;
        int count = 2; // for two common corners
        boolean changed = false;
        do {
            changed = false;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int cij = colors[i][j];
                    boolean canGoToBlue = cij == 1 || cij == 3;
                    boolean canGoToRed = cij == 2 || cij == 3;
                    if (i > 0 && A[i-1][j] <= A[i][j]) {
                        switch (colors[i-1][j]) {
                            case 1: canGoToBlue = true;break;
                            case 2: canGoToRed = true;break;
                            case 3: canGoToBlue = true; canGoToRed = true;break;
                        }
                    }
                    if (i < m-1 && A[i+1][j] <= A[i][j]) {
                        switch (colors[i+1][j]) {
                            case 1: canGoToBlue = true;break;
                            case 2: canGoToRed = true;break;
                            case 3: canGoToBlue = true; canGoToRed = true;break;
                        }
                    }
                    if (j > 0 && A[i][j-1] <= A[i][j]) {
                        switch (colors[i][j-1]) {
                            case 1: canGoToBlue = true;break;
                            case 2: canGoToRed = true;break;
                            case 3: canGoToBlue = true; canGoToRed = true;break;
                        }
                    }
                    if (j < n-1 && A[i][j+1] <= A[i][j]) {
                        switch (colors[i][j+1]) {
                            case 1: canGoToBlue = true;break;
                            case 2: canGoToRed = true;break;
                            case 3: canGoToBlue = true; canGoToRed = true;break;
                        }
                    }
                    colors[i][j] = (short) (canGoToBlue && canGoToRed ? 3 : (canGoToBlue ? 1 : (canGoToRed ? 2 : 0)));
                    changed = changed || cij != colors[i][j];
                    if (cij != 3 && colors[i][j] == 3) {
                        count++;
                    }
                }
            }
        } while (changed);
        return count;
    }

    public static void main(String[] args) {
        WaterFlow wf = new WaterFlow();
        Verifier.verifyEquals(wf.solve(new int[][] {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        }), 7);
        Verifier.verifyEquals(wf.solve(new int[][] {
                {2,2},
                {2,2}
        }), 4);
        Verifier.verifyEquals(wf.solve(new int[][] {
                {15, 1, 10},
                {5, 19, 19},
                {3, 5, 6},
                {6, 2, 8},
                {2, 12, 16},
                {3, 8, 17},
                {12, 5, 3},
                {14, 13, 3},
                {2, 17, 19},
                {16, 8, 7},
                {12, 19, 10},
                {13, 8, 20}
        }), 16);
    }

}
