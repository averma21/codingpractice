package others.leetc.dp;

//https://leetcode.com/problems/count-square-submatrices-with-all-ones/

/**
 * One approach would be to count the number of possible squares having a point as lower right endpoint. If the point is 0, no such squares are there.
 * If it is 1, we use the dp approach to find the length largest possible square there. The number of squares having this point as lower right end would
 * be equal to the size of largest square. We add all these numbers and the sum is the answer.
 */
public class CountSquareSubMatrices {

    int [][] sqSizes;

    public int countSquares(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        sqSizes = new int[m][n];
        int count = 0;
        for (int j=0; j < n; j++) {
            int c = matrix[0][j];
            sqSizes[0][j] = c;
            count+=c;
        }
        for (int i = 1; i < m; i++) {
            int c = matrix[i][0];
            sqSizes[i][0] = c;
            count+=c;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    sqSizes[i][j] = 0;
                    continue;
                }
                int c=Math.min(sqSizes[i-1][j], Math.min(sqSizes[i-1][j-1], sqSizes[i][j-1])) + 1;
                count+=c;
                sqSizes[i][j]=c;
            }
        }
        return count;
    }

}
