package DP;

import util.Verifier;

import java.util.Arrays;

/**
 * Approach 1: Dynamic Programming
 * Intuition
 *
 * We have to put the words into a row, where each word may overlap the previous word. This is because no word is contained in any word.
 *
 * Also, it is sufficient to try to maximize the total overlap of the words.
 *
 * Say we have put some words down in our row, ending with word A[i]. Now say we put down word A[j] as the next word, where word j hasn't been put down yet. The overlap increases by overlap(A[i], A[j]).
 *
 * We can use dynamic programming to leverage this recursion. Let dp(mask, i) be the total overlap after putting some words down (represented by a bitmask mask), for which A[i] was the last word put down. Then, the key recursion is dp(mask ^ (1<<j), j) = max(overlap(A[i], A[j]) + dp(mask, i)), where the jth bit is not set in mask, and i ranges over all bits set in mask.
 *
 * Of course, this only tells us what the maximum overlap is for each set of words. We also need to remember each choice along the way (ie. the specific i that made dp(mask ^ (1<<j), j) achieve a minimum) so that we can reconstruct the answer.
 *
 * Algorithm
 *
 * Our algorithm has 3 main components:
 *
 * Precompute overlap(A[i], A[j]) for all possible i, j.
 * Calculate dp[mask][i], keeping track of the "parent" i for each j as described above.
 * Reconstruct the answer using parent information.
 * Please see the implementation for more details about each section.
 *
 * https://leetcode.com/problems/find-the-shortest-superstring/solution/
 * https://www.interviewbit.com/problems/shortest-common-superstring/
 */
public class ShortestCommonSuperstring {

    public static int solve(String[] A) {
        int N = A.length;

        // Populate overlaps
        int[][] overlaps = new int[N][N];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j) if (i != j) {
                int m = Math.min(A[i].length(), A[j].length());
                for (int k = m; k >= 0; --k)
                    if (A[i].endsWith(A[j].substring(0, k))) {
                        overlaps[i][j] = k;
                        break;
                    }
            }

        // dp[mask][i] = most overlap with mask, ending with ith element
        int[][] dp = new int[1<<N][N];
        int[][] parent = new int[1<<N][N];
        for (int mask = 0; mask < (1<<N); ++mask) {
            Arrays.fill(parent[mask], -1);

            for (int bit = 0; bit < N; ++bit) if (((mask >> bit) & 1) > 0) {
                // Let's try to find dp[mask][bit].  Previously, we had
                // a collection of items represented by pmask.
                int pmask = mask ^ (1 << bit);
                if (pmask == 0) continue;
                for (int i = 0; i < N; ++i) if (((pmask >> i) & 1) > 0) {
                    // For each bit i in pmask, calculate the value
                    // if we ended with word i, then added word 'bit'.
                    int val = dp[pmask][i] + overlaps[i][bit];
                    if (val > dp[mask][bit]) {
                        dp[mask][bit] = val;
                        parent[mask][bit] = i;
                    }
                }
            }
        }

        // # Answer will have length sum(len(A[i]) for i) - max(dp[-1])
        // Reconstruct answer, first as a sequence 'perm' representing
        // the indices of each word from left to right.

        int[] perm = new int[N];
        boolean[] seen = new boolean[N];
        int t = 0;
        int mask = (1 << N) - 1;

        // p: the last element of perm (last word written left to right)
        int p = 0;
        for (int j = 0; j < N; ++j)
            if (dp[(1<<N) - 1][j] > dp[(1<<N) - 1][p])
                p = j;

        // Follow parents down backwards path that retains maximum overlap
        while (p != -1) {
            perm[t++] = p;
            seen[p] = true;
            int p2 = parent[mask][p];
            mask ^= 1 << p;
            p = p2;
        }

        // Reverse perm
        for (int i = 0; i < t/2; ++i) {
            int v = perm[i];
            perm[i] = perm[t-1-i];
            perm[t-1-i] = v;
        }

        // Fill in remaining words not yet added
        for (int i = 0; i < N; ++i) if (!seen[i])
            perm[t++] = i;

        // Reconstruct final answer given perm
        StringBuilder ans = new StringBuilder(A[perm[0]]);
        for (int i = 1; i < N; ++i) {
            int overlap = overlaps[perm[i-1]][perm[i]];
            ans.append(A[perm[i]].substring(overlap));
        }

        return ans.length();
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(solve(new String[]{"abb", "bbc", "bbb"}), 5);
    }

}
