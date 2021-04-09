package arrays;

import util.Creator;
import util.Verifier;

import java.util.List;

public class MaxSumContiguousSubArray {

    int maxSum(List<Integer> A) {
        int maxSum = Integer.MIN_VALUE, sumTillNow = 0;
        for (int a : A) {
            sumTillNow += a;
            maxSum = Math.max(maxSum, sumTillNow);
            if (sumTillNow < 0) {
                sumTillNow = 0;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        MaxSumContiguousSubArray mcsa = new MaxSumContiguousSubArray();
        Verifier.verifyEquals(mcsa.maxSum(Creator.getList(1, 2, 3, 4, -10)), 10);
        Verifier.verifyEquals(mcsa.maxSum(Creator.getList(-1, -2, -3, -4, 0)), 0);
        Verifier.verifyEquals(mcsa.maxSum(Creator.getList(-1, -2, -3, -4)), -1);
        Verifier.verifyEquals(mcsa.maxSum(Creator.getList(3, 2, 1, -1, -2, -3, -4, 10)), 10);
        Verifier.verifyEquals(mcsa.maxSum(Creator.getList(3, 2, 1, -1, -2, -3, -4, 10, -3, 4)), 11);
    }

}
