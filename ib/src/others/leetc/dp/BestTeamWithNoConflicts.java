package others.leetc.dp;

import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/best-team-with-no-conflicts/discuss/899475/Fairly-easy-DP
//https://leetcode.com/problems/best-team-with-no-conflicts/

/**
 * Fairly easy DP
 *
 * Idea is to first sort the players by their age so that we don't have to always check both the scores and the age to see whether these two players can be in the same team.
 *
 * We sort first in the descending order of the ages. It is necessary to sort by score when there's a tie in age to guarantee priority for "stronger peers" ; otherwise,
 * "stronger peers" may appear after "weaker peers", and thus cause fault.
 * Now we know that for any player i, we can choose any player from 0 to i-1 as long as that player has higher score than this i-th player.
 *
 * dp[i] stores the maximum score that can be obtained when i-th player is included and all other players are between indices 0 and i-1.
 * Once we get the answer for all indices, we can simply find the max and that will be the answer.
 *
 * Code would help understand this even more easily.
 */
public class BestTeamWithNoConflicts {

    private static class SA implements Comparable<SA> {
        int score;
        int age;

        SA(int s, int a) {
            score = s;
            age = a;
        }

        public int compareTo(SA o) {
            if (age == o.age) {
                return Integer.compare(score, o.score); // this is really important
            }
            return Integer.compare(age, o.age);
        }
    }

    public int bestTeamScore(int[] scores, int[] ages) {
        List<SA> saList = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            saList.add(new SA(scores[i], ages[i]));
        }
        saList.sort(Collections.reverseOrder());
        int [] maxScores = new int[saList.size()];
        int ans = 0;
        for (int i = 0; i < saList.size(); i++) {
            int score = saList.get(i).score;
            maxScores[i] = score;
            for (int j = 0; j < i; j++) {
                SA cur = saList.get(j);
                if (cur.score >= score) {
                    maxScores[i] = Math.max(maxScores[i], maxScores[j] + score);
                }
            }
            ans = Math.max(ans, maxScores[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        BestTeamWithNoConflicts btw = new BestTeamWithNoConflicts();
        Verifier.verifyEquals(btw.bestTeamScore(new int[] {1,3,5,10,15}, new int[] {1,2,3,4,5}), 34);
        Verifier.verifyEquals(btw.bestTeamScore(new int[] {4,5,6,5}, new int[] {2,1,2,1}), 16);
        Verifier.verifyEquals(btw.bestTeamScore(new int[] {1,2,3,5}, new int[] {8,9,10,1}), 6);
        Verifier.verifyEquals(btw.bestTeamScore(new int[] {3,3,3,9}, new int[] {6,6,6,3}), 9);
        Verifier.verifyEquals(btw.bestTeamScore(new int[] {3,4,3,9}, new int[] {6,6,6,3}), 10);
        Verifier.verifyEquals(btw.bestTeamScore(new int[] {1,3,7,3,2,4,10,7,5}, new int[] {4,5,2,1,1,2,4,1,4}), 29);
    }
}
