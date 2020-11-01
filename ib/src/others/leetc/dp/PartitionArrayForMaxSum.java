package others.leetc.dp;

import util.Verifier;

//https://leetcode.com/problems/partition-array-for-maximum-sum/
//https://leetcode.com/problems/partition-array-for-maximum-sum/discuss/908765/Java-solution-with-explanation-3ms-faster-than-98-of-submissions

/**
 * dp[i] would be the solution of the problem from index 0..i. Meaning, dp[i] would denote the max sum after every possible partitioning done
 * for elements from 0 to i.
 *
 * To proceed with the solution, for every array index i, we need to maintain the maximum element between :
 * <ul>
 *     <li>i, i-1</li>
 *     <li>i, i-1, i-2</li>
 *     <li>i, i-1, i-2, i-3</li>
 *     <li>...</li>
 *     <li>i, i-1, i-2,... i-k-1</li>
 * </ul>
 *
 * dp[i]= max(dp[i], dp[i- possible size limit i.e from 1 to k] + maxelement * size)
 * i.e. dp[i] is the max of the following -
 * <ol>
 *     <li>arr[i]</li>
 *     <li>dp[i-1] + arr[i] (using current element once)</li>
 *     <li>dp[i-2] + max(arr[i],arr[i-1])*2 (using max of window of size 2, two times)</li>
 *     <li>dp[i-3] + max(arr[i],arr[i-1], arr[i-2])*3 (using max of window of size 3, three times)</li>
 *     <li>...</li>
 *     <li>dp[i-k] + max(arr[i], arr[i-1],... arr[i-k-1])*k (using max of window of size k, k times)</li>
 * </ol>
 * So, for the following array -
 * 1, 15, 7, 9, 2
 * dp[0] = 1;
 * dp[1] = max(1+15, 0 + 15*2) = 30
 * dp[2] = max(30 + 7, 1 + 15*2, 0 + 15*3) = 45
 * dp[3] = max(45 + 9, 30 + 9*2, 1 + 15*3) = 54
 */

public class PartitionArrayForMaxSum {

    public int maxSumAfterPartitioning(int[] arr, int k) {
        /*
         * maxNums[i][j] denotes the maximum number in window of size i+1 on the left (and including) arr[j].
         * So, maxNums[0][i] will be equal to arr[i] (since for 0, window length is 1)
         * maxNums[1][5] will be max(arr[5-1], arr[5])
         * maxNums[2][4] will be max(arr[4-2], arr[4-1], arr[4]).
         *
         * maxNums[j] array could be calculated from maxNums[j-1] array by just checking previous array's value with the
         * new number to be considered in the extended window.
         */
        int [][] maxNums = new int[k][arr.length];
        for (int j = 0; j < arr.length; j++) {
            maxNums[0][j] = arr[j];
        }
        for (int i = 1; i < k; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (j-i >= 0) {
                    maxNums[i][j] = Math.max(maxNums[i-1][j], arr[j-i]);
                } else {
                    maxNums[i][j] = maxNums[i-1][j];
                }
            }
        }
        int [] dp = new int[arr.length];
        dp[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp[i] = arr[i];
            for (int j = 1; j <= k && i - j >= -1; j++) {
                dp[i] = Math.max(dp[i], (i-j >= 0 ? dp[i-j] : 0) + maxNums[j-1][i]*j);
            }
        }
        return dp[arr.length - 1];
    }

    public static void main(String[] args) {
        PartitionArrayForMaxSum pafm = new PartitionArrayForMaxSum();
        Verifier.verifyEquals(pafm.maxSumAfterPartitioning(new int[] {1,15,7,9,2,5,10}, 3), 84);
        Verifier.verifyEquals(pafm.maxSumAfterPartitioning(new int[] {1,4,1,5,7,3,6,1,9,9,3}, 4), 83);
        Verifier.verifyEquals(pafm.maxSumAfterPartitioning(new int[] {1}, 1), 1);
    }

}
