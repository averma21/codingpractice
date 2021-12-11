package others.leetc.contests.twohundred.sixty.six;

import util.Verifier;

import java.util.Arrays;

public class MinimizedMaximumOfProducts {

    int shops;
    int [] quantities;

    boolean isPossible(int maxValue) {
        int filledShops = 0;
        int dispatchedItems = 0;
        for (int i = 0; i < quantities.length; i++) {
            filledShops += Math.ceil((quantities[i]*1.0)/maxValue);
            dispatchedItems = i;
            if (filledShops >= shops) {
                break;
            }
        }
        return dispatchedItems == quantities.length - 1 && filledShops <= shops;
    }

    public int minimizedMaximum(int n, int[] quantities) {
        this.shops = n;
        this.quantities = quantities;
        int low = 0, high = Arrays.stream(quantities).max().getAsInt() + 1;
        int ans = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (low + high)/2;
            if (isPossible(mid)) {
                ans = Math.min(ans, mid);
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MinimizedMaximumOfProducts mmp = new MinimizedMaximumOfProducts();
        Verifier.verifyEquals(mmp.minimizedMaximum(6, new int[] {11,6}), 3);
        Verifier.verifyEquals(mmp.minimizedMaximum(7, new int[] {15,10,10}), 5);
        Verifier.verifyEquals(mmp.minimizedMaximum(1, new int[] {10000}), 10000);
    }

}
