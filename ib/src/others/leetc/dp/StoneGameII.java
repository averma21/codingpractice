package others.leetc.dp;

import util.Verifier;

//https://leetcode.com/problems/stone-game-ii/
public class StoneGameII {

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int m = (int)Math.ceil(n/2.0);
        int [][]dp = new int[m+1][n];
        dp[m][n-1] = piles[n-1];
        for (int j = n-2; j >= 0; j--) {
            dp[m][j] = piles[j] + dp[m][j+1];
        }
        for (int i = 1; i <= m; i++) {
            dp[i][n-1] = piles[n-1];
        }
        for (int j = n-2; j >= 0; j--) {
            for (int i = m-1; i >= 1; i--) {
                int max = Integer.MIN_VALUE, chosen = 0;
                for (int x = 1; x <= 2*i && j + x - 1 < n; x++) {
                    chosen += piles[j+x-1];
                    max = Math.max(max, chosen + (j+x < n ? (dp[m][j+x] - dp[Math.min(Math.max(i,x), m)][j+x]) : 0));
                }
                dp[i][j]=max;
            }
        }
        return dp[1][0];
    }

    public static void main(String[] args) {
        StoneGameII sg2 = new StoneGameII();
        Verifier.verifyEquals(sg2.stoneGameII(new int[] {2,7,9,4,4}), 10);
        Verifier.verifyEquals(sg2.stoneGameII(new int[] {18,2,19}), 20);
        Verifier.verifyEquals(sg2.stoneGameII(new int[] {7,18,2,6,19,1,4,2,9,4,6,4,7}), 51);
    }
}
