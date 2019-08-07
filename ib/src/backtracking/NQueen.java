package backtracking;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NQueen {

    private Set<Integer> restrictedCols;
    private Set<Integer> restrictedLeftDiags;
    private Set<Integer> restrictedRightDiags;
    private int size;

    private String getPosString(int pos) {
        if (pos < 0 || pos > size)
            return "";
        StringBuilder s = new StringBuilder(size);
        for (int i = 0; i < pos; i++)
            s.append(".");
        s.append("Q");
        for (int i = pos+1; i < size; i++)
            s.append(".");
        return s.toString();
    }

    private void getSolnInternal(List<List<String>> ansList, List<String> ans, int queenNo) {
        if (queenNo == size) {
            ansList.add(new ArrayList<>(ans));
            return;
        }
        for (int col = 0; col < size; col++) {
            int leftDiagonal = size - (queenNo - col) - 1;
            int rightDiagonal = queenNo + col;
            if (!restrictedCols.contains(col) && !restrictedLeftDiags.contains(leftDiagonal) &&
                    !restrictedRightDiags.contains(rightDiagonal)) {
                ans.add(getPosString(col));
                restrictedCols.add(col);
                restrictedLeftDiags.add(leftDiagonal);
                restrictedRightDiags.add(rightDiagonal);
                getSolnInternal(ansList, ans, queenNo+1);
                ans.remove(ans.size() - 1);
                restrictedCols.remove(col);
                restrictedLeftDiags.remove(leftDiagonal);
                restrictedRightDiags.remove(rightDiagonal);
            }
        }
    }

    List<List<String>> solveNQueens(int a) {
        if (a == 1) {
            ArrayList<String> s = new ArrayList<String>(){{add("Q");}};
            return new ArrayList<List<String>>(){{add(s);}};
        }
        if (a <= 3)
            return Collections.emptyList();
        this.size = a;
        restrictedCols = new HashSet<>(a);
        restrictedLeftDiags = new HashSet<>(2*a - 1);
        restrictedRightDiags = new HashSet<>(2*a - 1);
        List<List<String>> ans = new ArrayList<>();
        getSolnInternal(ans, new ArrayList<>(), 0);
        return ans;
    }

    public static void main(String[] args) {
        NQueen nQueen = new NQueen();
        System.out.println(nQueen.solveNQueens(1));
        System.out.println(nQueen.solveNQueens(4));
        System.out.println(nQueen.solveNQueens(5));
    }

}
