package DP;

import java.util.List;

public class BestTimeToBuyAndSellStocksIII {

    public int maxProfitInternal(final List<Integer> A, int maxTransactions) {
        if (A == null || A.size() == 0)
            return 0;
        int [][] maxProfit = new int[maxTransactions + 1][A.size()];
        for (int i = 0; i <= maxTransactions; i++) {
            int maxDiff = Integer.MIN_VALUE;
            for (int j = 0; j < A.size(); j++) {
                if (j == 0 || i == 0) {
                    maxProfit[i][j] = 0;
                    if (i != 0)
                        maxDiff = maxProfit[i-1][0] - A.get(0);
                    continue;
                }
                maxProfit[i][j] = Math.max(maxProfit[i][j-1], A.get(j) + maxDiff);
                maxDiff = Math.max(maxDiff, maxProfit[i-1][j] - A.get(j));
            }
        }
        return maxProfit[maxTransactions][A.size() - 1];
    }

}
