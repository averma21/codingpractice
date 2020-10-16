package others.leetc.contests.biweekly.thirtysix;

//https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums/
//https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums/discuss/876845/JavaC%2B%2BPython-Easy-and-Concise-with-Prove (See KartikX comment 4 oct 2020)

/**
 * Intuition behind this solution:
 *
 * For every cell, the value in it is constrained by the values of the rowSum and colSum. Moreover, the value is constrained by the minimum of these two. For eg. if the rowSum=3 and colSum=4 for a particular cell, then you can only take the values [0, 1, 2, 3] for that cell.
 * What if we greedily pick the largest value out of the possible values each time ?
 * Picking the largest value possible corresponds to picking the min(rowSum, colSum). Since total(rowSum) = total(colSum), initially we had the equation that 3 + r2 + r3 + .. + rN = 4 + c2 + c3 + .. + cM. Since we use 3 as the value for the current cell, our new equation becomes 0 + r2 + r3 + .. + rN = 1 + c2 + c3 + .. + cM, which follows from the equation earlier. So as @lee215 mentioned, we haven't broken anything, the total(rowSum) = total(colSum) condition still holds.
 * In this manner, for every cell we can continue to choose the minimum value, which would keep on decrementing a value in the total(rowSum) = total(colSum) equation above. Eventually, our equation will decompose into just a single value rn = cm. In which case we would just put the value at cell[n][m] = rn = cm.
 */
public class ValidMatrix {

    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int [][] ans = new int[rowSum.length][colSum.length];
        for (int i = 0; i < rowSum.length; i++) {
            for (int j = 0; j < colSum.length; j++) {
                ans[i][j] = Math.min(rowSum[i], colSum[j]);
                rowSum[i] -= ans[i][j];
                colSum[j] -= ans[i][j];
            }
        }
        return ans;
    }

}
