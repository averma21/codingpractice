package backtracking;

import util.Creator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Sudoku {

    //private boolean solved = false;

    private boolean canFitEnclosingSquare(List<List<Character>> A, int row, int col, char c) {
        int row1, row2, row3, col1, col2, col3;
        if (row % 3 == 0) {
            row1 = row;
            row2 = row+1;
            row3 = row+2;
        } else if (row % 3 == 1) {
            row1 = row-1;
            row2 = row;
            row3 = row+1;
        } else {
            row1 = row-1;
            row2 = row-2;
            row3 = row;
        }
        if (col % 3 == 0) {
            col1 = col;
            col2 = col+1;
            col3 = col+2;
        } else if (col % 3 == 1) {
            col1 = col-1;
            col2 = col;
            col3 = col+1;
        } else {
            col1 = col-2;
            col2 = col-1;
            col3 = col;
        }
        List<Integer> rows = Arrays.asList(row1,row2,row3);
        List<Integer>  cols = Arrays.asList(col1,col2,col3);
        for (int rowx : rows) {
            for (int colx: cols) {
                if (A.get(rowx).get(colx).equals(c))
                    return false;
            }
        }
        return true;
    }

    boolean solve(List<List<Character>> A, int rowNo, int colNo) {
        int size = A.size();
        if (rowNo == size && colNo == 0) {
            return true;
        }
        List<Character> row = A.get(rowNo);
        if (row.get(colNo) == '.') {
            for (char c = '1'; c <= '9'; c++) {
                if (row.contains(c))
                    continue;
                boolean sameFound = false;
                for (List<Character> rowz : A) {
                    if (rowz.get(colNo) == c) {
                        sameFound = true;
                        break;
                    }
                }
                if (sameFound)
                    continue;
                if (canFitEnclosingSquare(A, rowNo, colNo, c)) {
                    row.set(colNo, c);
                    boolean solved = solve(A, colNo == size - 1 ? rowNo+1 : rowNo, (colNo + 1)%size);
                    if (solved) {
                        return true;
                    }
                    row.set(colNo, '.');
                }
            }
            return false;
        } else {
            return solve(A, colNo == size - 1 ? rowNo+1 : rowNo, (colNo + 1)%size);
        }
    }

    void solve(List<List<Character>> A) {
        if (A == null || A.size() == 0)
            return;
        solve(A, 0, 0);
    }

    public static List<Character> getChars(String s) {
        List<Character> cL = new ArrayList<>();
        for (int i = 0; i < s.length(); i++)
            cL.add(s.charAt(i));
        return cL;
    }

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        //[], [6..195...], [.98....6.], [8...6...3], [4..8.3..1], [7...2...6], [.6....28.], [...419..5], [....8..79]
        List<List<Character>> list = Creator.getList(getChars("53..7...."), getChars("6..195..."), getChars(".98....6."), getChars("8...6...3"),
                getChars("4..8.3..1"), getChars("7...2...6"), getChars(".6....28."), getChars("...419..5"),
                getChars("....8..79"));
        sudoku.solve(list);
        System.out.println(list);
    }

}
