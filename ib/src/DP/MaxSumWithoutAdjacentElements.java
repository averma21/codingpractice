package DP;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/maximum-sum-2-x-n-grid-no-two-elements-adjacent/
 *
 * Extended form of Maximum sum such that no two elements are adjacent. We traverse column by column and maintain maximum
 * sum considering two cases.
 * 1) An element of current column is included. In this case we take maximum of two elements in current column.
 * 2) An element of current column is excluded (or not included)
 */
public class MaxSumWithoutAdjacentElements {

    private static int adjacent(List<List<Integer>> A) {
        if (A.size() != 2)
            return 0;
        int n = A.get(0).size();
        int maxExclCur = 0;
        int maxIncCur = Math.max(A.get(0).get(0), A.get(1).get(0));
        for (int i = 1; i < n; i++) {
            int incMax = Math.max(A.get(0).get(i), A.get(1).get(i)) + maxExclCur;
            maxExclCur = Math.max(maxExclCur, maxIncCur);
            maxIncCur = incMax;
        }
        return Math.max(maxExclCur, maxIncCur);
    }

    public static void main(String[] args) {
        List<List<Integer>> A = new ArrayList<>();
        A.add(Creator.getList(74, 37, 82, 1));
        A.add(Creator.getList(66, 38, 16, 1));
        Verifier.verifyEquals(adjacent(A), 156);
    }
}
