package DP;

import java.util.List;

public class BestPriceToBuyAndSellStocksI {

    public int maxProfit(final List<Integer> A) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < A.size(); i++) {
            int ai = A.get(i);
            if (ai < minprice)
                minprice = ai;
            else if (ai - minprice > maxprofit)
                maxprofit = ai - minprice;
        }
        return maxprofit;
    }

}
