package DP.round2;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class SubmatricesWithSum0 {

    private int get0SumCount(int[] A) {
        int n = A.length, sum = 0;
        Map<Integer, Integer> sumCount = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sum += A[i];
            sumCount.compute(sum, (k, v) -> v == null ? 1 : v + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : sumCount.entrySet()) {
            if (entry.getValue() > 1 || entry.getKey() == 0) {
                int combinations = (entry.getValue() * (entry.getValue() - 1)) / 2;
                ans += combinations;
                if (entry.getKey() == 0) {
                    ans += entry.getValue();
                }
            }
        }
        return ans;
    }

    public int solve(int[][] A) {
        int m = A.length;
        if (m == 0) {
            return 0;
        }
        int n = A[0].length;
        if (n == 0) {
            return 0;
        }
        int count = 0;
        for (int j = 0; j < n; j++) {
            int[] colSumArr = new int[m];
            for (int k = j; k < n; k++) {
                for (int i = 0; i < m; i++) {
                    colSumArr[i] += A[i][k];
                }
                count += get0SumCount(colSumArr);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        SubmatricesWithSum0 sws = new SubmatricesWithSum0();
        Verifier.verifyEquals(sws.solve(new int[][]{
                {-8, 5, 7},
                {3, 7, -8},
                {5, -8, 9}
        }), 2);
    }

}
