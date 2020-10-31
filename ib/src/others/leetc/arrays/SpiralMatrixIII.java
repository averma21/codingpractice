package others.leetc.arrays;

import util.Verifier;

//https://leetcode.com/problems/spiral-matrix-iii/
public class SpiralMatrixIII {

    int[][] ans;
    int curPos;
    int R;
    int C;

    private boolean isRowValid(int r) {
        return 0 <= r && r < R;
    }

    private boolean isColumnValid(int c) {
        return 0 <= c && c < C;
    }

    private void printSquare(int r0, int c0, int sqSize) {
        for (int j = c0; j < c0 + sqSize - 1; j++) {
            if (isRowValid(r0) && isColumnValid(j))
                ans[curPos++] = new int[]{r0, j};
        }
        for (int i = r0; i < r0 + sqSize - 1; i++) {
            if (isRowValid(i) && isColumnValid(c0 + sqSize - 1))
                ans[curPos++] = new int[]{i, c0 + sqSize - 1};
        }
        for (int j = c0 + sqSize - 1; j >= c0; j--) {
            if (isRowValid(r0 + sqSize - 1) && isColumnValid(j))
                ans[curPos++] = new int[]{r0 + sqSize - 1, j};
        }
        for (int i = r0 + sqSize - 1; i >= r0; i--) {
            if (isRowValid(i) && isColumnValid(c0 - 1))
                ans[curPos++] = new int[]{i, c0 - 1};
        }
    }

    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        ans = new int[R * C][2];
        curPos = 0;
        this.R = R;
        this.C = C;
        int rs = r0, cs = c0, sqSize = 2;
        while (curPos < R * C) {
            printSquare(rs, cs, sqSize);
            sqSize += 2;
            rs--;
            cs--;
        }
        return ans;
    }

    public static void main(String[] args) {
        SpiralMatrixIII sp3 = new SpiralMatrixIII();
        Verifier.verifyArrayEquals(sp3.spiralMatrixIII(1, 4, 0, 0), new int[][]
                {{0, 0}, {0, 1}, {0, 2}, {0, 3}}
        );
        Verifier.verifyArrayEquals(sp3.spiralMatrixIII(5,6,1,4), new int[][]
                {{1,4},{1,5},{2,5},{2,4},{2,3},{1,3},{0,3},{0,4},{0,5},{3,5},{3,4},{3,3},{3,2},{2,2},{1,2},{0,2},
                        {4,5},{4,4},{4,3},{4,2},{4,1},{3,1},{2,1},{1,1},{0,1},{4,0},{3,0},{2,0},{1,0},{0,0}}
        );
    }

}
