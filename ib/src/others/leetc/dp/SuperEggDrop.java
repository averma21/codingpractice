package others.leetc.dp;

//https://leetcode.com/problems/super-egg-drop/
//https://leetcode.com/problems/super-egg-drop/discuss/158974/C%2B%2BJavaPython-2D-and-1D-DP-O(KlogN)

import util.Verifier;

/**
 * Explanation
 * Drop eggs is a very classical problem.
 * Some people may come up with idea O(KN^2)
 * where dp[K][N] = 1 + max(dp[K - 1][i - 1], dp[K][N - i]) for i in 1...N.
 * However this idea is very brute force, for the reason that you check all possiblity.
 *
 * So I consider this problem in a different way:
 * dp[M][K]means that, given K eggs and M moves,
 * what is the maximum number of floor that we can check.
 *
 * The dp equation is:
 * dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1,
 * which means we take 1 move to a floor,
 * if egg breaks, then we can check dp[m - 1][k - 1] floors.
 * if egg doesn't breaks, then we can check dp[m - 1][k] floors.
 *
 * dp[m][k] is the number of combinations and it increase exponentially up to N
 *
 *
 * Complexity
 * For time, O(NK) decalre the space, O(KlogN) running,
 * For space, O(NK).
 */
public class SuperEggDrop {

    public int superEggDrop(int K, int N) {
        int[][] dp = new int[N + 1][K + 1];
        int m = 0;
        while (dp[m][K] < N) {
            ++m;
            for (int k = 1; k <= K; ++k)
                dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1;
        }
        return m;
    }

    public static void main(String[] args) {
        SuperEggDrop sed = new SuperEggDrop();
        Verifier.verifyEquals(sed.superEggDrop(2, 100), 14);
    }

}
