package others.leetc.thirtydaychallenge;

import util.Verifier;

public class BestTimeToBuyAndSellStocksII {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1)
            return 0;
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            int pi = prices[i];
            int prev = prices[i-1];
            if (pi >= prev) {
                sum += pi - prev;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(maxProfit(new int[] {7,1,5,3,6,4}), 7);
        Verifier.verifyEquals(maxProfit(new int[] {1,2,3,4,5}), 4);
        Verifier.verifyEquals(maxProfit(new int[] {7,6,4,3,1}), 0);
    }

}
