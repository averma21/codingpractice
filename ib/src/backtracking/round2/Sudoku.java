package backtracking.round2;

import util.Creator;

import java.util.ArrayList;
import java.util.List;

import static backtracking.Sudoku.getChars;

public class Sudoku {

    List<List<Character>> A;
    private static final int N = 9;

    private boolean canFillRowI(char c, int i) {
        return A.get(i).stream().noneMatch(ele -> ele == c);
    }

    private boolean canFillColJ(char c, int j) {
        return A.stream().noneMatch(list -> list.get(j) == c);
    }

    private boolean checkSquare(char c, int row, int col) {
        int iStart = 3*(row/3);
        int jStart = 3*(col/3);
        for (int i = iStart; i <= iStart + 2; i++) {
            for (int j = jStart; j <= jStart + 2; j++) {
                if (A.get(i).get(j) == c) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean fill(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col > N) {
            return false;
        }
        char aij = A.get(row).get(col);
        if (aij != '.') {
            if (row == N-1 && col == N-1) {
                return true;
            }
            return col < N-1 ? fill(row, col + 1) : fill(row+1, 0);
        }
        for (char num = '1'; num <= '9'; num++) {
            if (canFillColJ(num, col) && canFillRowI(num, row) && checkSquare(num, row, col)) {
                A.get(row).set(col, num);
                if (row == N-1 && col == N-1) {
                    return true;
                }
                boolean canFillRemaining = col < N-1 ? fill(row, col + 1) : fill(row+1, 0);
                if (canFillRemaining) {
                    return true;
                }
            }
        }
        A.get(row).set(col, '.');
        return false;
    }

    public void solveSudoku(List<List<Character>> a) {
        if (a == null || a.size() != N) {
            return;
        }
        for (List<Character> l : a) {
            if (l.size() != N) {
                return;
            }
        }
        A = a;
        fill(0, 0);
    }

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        //[], [6..195...], [.98....6.], [8...6...3], [4..8.3..1], [7...2...6], [.6....28.], [...419..5], [....8..79]
        List<List<Character>> list = Creator.getList(getChars("53..7...."), getChars("6..195..."), getChars(".98....6."), getChars("8...6...3"),
                getChars("4..8.3..1"), getChars("7...2...6"), getChars(".6....28."), getChars("...419..5"),
                getChars("....8..79"));
        sudoku.solveSudoku(list);
        System.out.println(list);
    }

}
