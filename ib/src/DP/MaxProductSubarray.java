package DP;

import util.Creator;
import util.Verifier;

import java.util.List;

public class MaxProductSubarray {

    private static int max(List<Integer> A) {
        if (A == null)
            return 0;
        if (A.size() == 1)
            return A.get(0);
        int maxProduct = Integer.MIN_VALUE;
        int [] minProductArray = new int[A.size()];
        int [] maxProductArray = new int[A.size()];
        minProductArray[0] = A.get(0);
        maxProductArray[0] = A.get(0);
        for (int i = 1; i < A.size(); i++) {
            int ai = A.get(i);
            if (ai >= 0) {
                minProductArray[i] = minProductArray[i-1] * ai;
                maxProductArray[i] = maxProductArray[i-1] > 0 ? maxProductArray[i-1] * ai : ai;
            } else {
                minProductArray[i] = maxProductArray[i-1] > 0 ? maxProductArray[i-1] * ai : ai;
                maxProductArray[i] = minProductArray[i-1] <= 0 ? minProductArray[i-1] * ai : ai;
            }
            maxProduct = Math.max(maxProduct, maxProductArray[i]);
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(max(Creator.getList(1)), 1);
        Verifier.verifyEquals(max(Creator.getList(-1)), -1);
        Verifier.verifyEquals(max(Creator.getList(1,2)), 2);
        Verifier.verifyEquals(max(Creator.getList(-1,-2,-3)), 6);
        Verifier.verifyEquals(max(Creator.getList(1,2,3)), 6);
        Verifier.verifyEquals(max(Creator.getList(1,-2,3)), 3);
        Verifier.verifyEquals(max(Creator.getList(2,3,-2,4)), 6);
        Verifier.verifyEquals(max(Creator.getList(2,3,-2,4,-5,-6,7,-8,-9,-10)), 2*3*2*4*5*6*7*8*9*10);
        Verifier.verifyEquals(max(Creator.getList(2,3,-2,4,-5,-6,0,-8,-9,-10)), 240);
        Verifier.verifyEquals(max(Creator.getList(2,3,-2,-4,-5,6,7)), 4*5*6*7);
        Verifier.verifyEquals(max(Creator.getList(2,3,-2,100)), 100);
        Verifier.verifyEquals(max(Creator.getList(2,3,0,100)), 100);
        Verifier.verifyEquals(max(Creator.getList(-4,0,-5,0)), 0);
        Verifier.verifyEquals(max(Creator.getList(0, 2, 0, -3, 3, 3, 0)), 9);
        Verifier.verifyEquals(max(Creator.getList( 0, -3, -2, 0, 1, 0, 0, 0, 0, 0, -2, 0, 0, 0, 3, 3, 0, 0, 0, 0)), 9);
        Verifier.verifyEquals(max(Creator.getList( 0, 0, 0, -3, -2, 0, 1, 0, 0, 0, 0, 0, -2, 0, 0, 0, 3, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, -3, 0, 0, 0, 0, -1, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, -2, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, -3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 0, 0, 0, 0, 0, 0, -1, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), 9);
    }
}
