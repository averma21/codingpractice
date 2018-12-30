package binarysearch;

import util.Verifier;

import java.util.ArrayList;
import java.util.List;

public class MatrixSearch {

    private static int negativeResult = -1;

    private int searchRow(ArrayList<ArrayList<Integer>> a, int b) {
        int rows = a.size();
        if (rows == 0) {
            return negativeResult;
        }
        int begin = 0, end = rows - 1;
        int columns = a.get(0).size();
        if (columns == 0) {
            return negativeResult;
        }
        while (begin <= end) {
            int mid = (begin + end)/2;
            List<Integer> row = a.get(mid);
            if (row.get(0) <= b && row.get(columns - 1) >= b) {
                return mid;
            } else if (b > row.get(columns - 1)) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return negativeResult;
    }

    private int searchColumn(List<Integer> a, int b) {
        if (a == null || a.size() == 0) {
            return negativeResult;
        }
        int begin = 0, end = a.size() - 1;
        while (begin <= end) {
            int mid = (begin + end)/2;
            int num = a.get(mid);
            if (num == b) {
                return mid;
            } else if (num < b) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return negativeResult;
    }

    public int searchMatrix(ArrayList<ArrayList<Integer>> a, int b) {
        if (a == null) {
            return 0;
        }
        int rowNum = searchRow(a, b);
        if (rowNum == negativeResult) {
            return 0;
        }
        int colNum = searchColumn(a.get(rowNum), b);
        if (colNum >= 0)
            return 1;
        return 0;
    }

    private ArrayList<ArrayList<Integer>> constructMatrix(int row, int col, int ... nums) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>(row);
        int c = 0;
        for (int i = 0; i < row; i++) {
            ArrayList<Integer> list = new ArrayList<Integer>(col);
            for (int j = 0; j < col; j++) {
                list.add(nums[c++]);
            }
            matrix.add(list);
        }
        return matrix;
    }

    public static void main(String[] args) {
        MatrixSearch ms = new MatrixSearch();
        Verifier.verifyEquals(ms.searchMatrix(ms.constructMatrix(3, 4, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), 10), 1);
        Verifier.verifyEquals(ms.searchMatrix(ms.constructMatrix(3, 4, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), 12), 1);
        Verifier.verifyEquals(ms.searchMatrix(ms.constructMatrix(3, 4, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), 1), 1);
        Verifier.verifyEquals(ms.searchMatrix(ms.constructMatrix(4, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), 12), 1);
        Verifier.verifyEquals(ms.searchMatrix(ms.constructMatrix(2, 2, 1, 2, 2, 3), 3), 1);
        Verifier.verifyEquals(ms.searchMatrix(ms.constructMatrix(2, 2, 1, 2, 2, 3), 2), 1);
        Verifier.verifyEquals(ms.searchMatrix(ms.constructMatrix(2, 2, 1, 2, 2, 3), 1), 1);
        Verifier.verifyEquals(ms.searchMatrix(ms.constructMatrix(3, 4, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), 0), 0);
        Verifier.verifyEquals(ms.searchMatrix(ms.constructMatrix(3, 4, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), 13), 0);
        Verifier.verifyEquals(ms.searchMatrix(ms.constructMatrix(4, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), 13), 0);
        Verifier.verifyEquals(ms.searchMatrix(ms.constructMatrix(2, 2, 1, 2, 2, 3), 4), 0);
    }
}
