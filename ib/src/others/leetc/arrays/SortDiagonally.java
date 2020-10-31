package others.leetc.arrays;

import java.util.PriorityQueue;

//https://leetcode.com/problems/sort-the-matrix-diagonally/
public class SortDiagonally {

    PriorityQueue<Integer> priorityQueue;

    void traverseSortAndCopy(int [][] orig, int [][] dest, int startI, int startJ) {
        priorityQueue.clear();
        int m = orig.length, n = orig[0].length;
        for (int i = startI, j = startJ; i < m && j < n; i++, j++) {
            priorityQueue.add(orig[i][j]);
        }
        for (int i = startI, j = startJ; i < m && j < n; i++, j++) {
            dest[i][j] = priorityQueue.remove();
        }
    }

    public int[][] diagonalSort(int[][] mat) {
        priorityQueue = new PriorityQueue<>();
        int m = mat.length, n = mat[0].length;
        int [][] ans = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            traverseSortAndCopy(mat, ans, i, 0);
        }
        for (int j = 1; j < n; j++) {
            traverseSortAndCopy(mat, ans, 0, j);
        }
        return ans;
    }

}
