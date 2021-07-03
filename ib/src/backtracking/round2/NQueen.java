package backtracking.round2;

import java.util.*;

public class NQueen {

    List<List<String>> ans;
    private Set<Integer> restrictedCols;
    private Set<Integer> restrictedLeftDiags;
    private Set<Integer> restrictedRightDiags;

    private StringBuilder getRowString(int a) {
        StringBuilder positionString = new StringBuilder();
        for (int j = 0; j < a; j++) {
            positionString.append(".");
        }
        return positionString;
    }

    void solveInternal(int a, int rowNum, Stack<String> curAns) {
        if (rowNum == a) {
            ans.add(new ArrayList<>(curAns));
            return;
        }
        for (int i = 0; i < a; i++) {
            int leftDiagonal = a - (rowNum - i) - 1;
            int rightDiagonal = rowNum + i;
            if (restrictedCols.contains(i) || restrictedLeftDiags.contains(leftDiagonal) ||
                    restrictedRightDiags.contains(rightDiagonal)) {
                continue;
            }
            curAns.push(getRowString(a).replace(i,i+1, "Q").toString());
            restrictedCols.add(i);
            restrictedLeftDiags.add(leftDiagonal);
            restrictedRightDiags.add(rightDiagonal);
            solveInternal(a, rowNum+1, curAns);
            curAns.pop();
            restrictedCols.remove(i);
            restrictedLeftDiags.remove(leftDiagonal);
            restrictedRightDiags.remove(rightDiagonal);
        }
    }

    List<List<String>> solveNQueens(int a) {
        if (a <= 0) {
            return new ArrayList<>();
        }
        ans = new ArrayList<>();
        restrictedCols = new HashSet<>();
        restrictedRightDiags = new HashSet<>();
        restrictedLeftDiags = new HashSet<>();
        solveInternal(a, 0, new Stack<>());
        return ans;
    }

    public static void main(String[] args) {
        NQueen nQueen = new NQueen();
        System.out.println(nQueen.solveNQueens(1));
        System.out.println(nQueen.solveNQueens(4));
        System.out.println(nQueen.solveNQueens(5));
    }

}
