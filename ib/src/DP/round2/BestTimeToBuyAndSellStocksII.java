package DP.round2;

import util.Verifier;

public class BestTimeToBuyAndSellStocksII {

    public int maxProfit(final int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int n = A.length;
        int start = -1;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] < A[i+1]) {
                start = i;
                break;
            }
        }
        if (start == -1)
            return 0;
        int maxProfit = 0;
        for (int i = start + 1; i < n; i++) {
            if (A[i] >  A[i-1]) {
                maxProfit += A[i] - A[i-1];
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStocksII btt = new BestTimeToBuyAndSellStocksII();
        Verifier.verifyEquals(btt.maxProfit(new int[]{1,2,3}), 2);
        Verifier.verifyEquals(btt.maxProfit(new int[]{5,2,10}), 8);
    }

}
