package others.leetc.contests.oneeighty.three;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class StoneGameIII {

    private int [] stoneValue;
    private int N;
    private int [] sumTillEnd;
    private Map<Integer, Integer> cache;

    /**
     * Returns the maximum points a player whose chance comes at position <code>pos</code> could obtain.
     * @param pos position
     * @return maximum points.
     */
    private int check(int pos) {
        if (pos >= N)
            return 0;
        if (pos == N - 1)
            return stoneValue[pos];
        if (cache.containsKey(pos))
            return cache.get(pos);
        int cur = stoneValue[pos];
        // Case 1 - Current player picks only one stone, next player gets chance at position pos+1.
        int p1 = check(pos + 1); // next player's points
        // Current player's points are obtained by subtracting next players
        // points from sum of points beginning from "pos+1" to end and adding that to current stone's point
        int myP1 = cur + sumTillEnd[pos+1] - p1;
        // Case 2 - Current player picks two stones, next player gets chance at position pos+2.
        int p2 = check(pos + 2); // next player's points
        // Current player's points are obtained by subtracting next players points from sum of points beginning from "pos+2"
        // to end and adding that to sum of current and next stone's points
        int myP2 = cur + stoneValue[pos+1] + (pos + 2 < N ? sumTillEnd[pos+2] - p2 : 0);
        // Case 3 - Current player picks three stones, next player gets chance at position pos+3.
        int p3 = check(pos + 3);
        // Current player's points are obtained by subtracting next players points from sum of points beginning from "pos+3"
        // to end and adding that to sum of current and next two stone's points
        int myP3 = cur + stoneValue[pos+1] + (pos + 2 < N ? (stoneValue[pos+2] + (pos + 3 < N ? sumTillEnd[pos+3] - p3 : 0)) : 0);
        int ans = Math.max(myP1, Math.max(myP2, myP3));
        cache.put(pos, ans);
        return ans;
    }

    public String stoneGameIII(int[] stoneValue) {
        this.stoneValue = stoneValue;
        this.cache = new HashMap<>();
        this.N = stoneValue.length;
        this.sumTillEnd = new int[N];
        sumTillEnd[N - 1] = stoneValue[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            sumTillEnd[i] = stoneValue[i] + sumTillEnd[i+1];
        }
        cache = new HashMap<>();
        int aP = check(0);
        int bP = sumTillEnd[0] - aP;
        if (aP> bP)
            return "Alice";
        if (bP > aP)
            return "Bob";
        return "Tie";
    }

    public static void main(String[] args) {
        StoneGameIII sg3 = new StoneGameIII();
        Verifier.verifyEquals(sg3.stoneGameIII(new int[]{1,2,3,7}), "Bob");
        Verifier.verifyEquals(sg3.stoneGameIII(new int[]{1,2,3,-9}), "Alice");
        Verifier.verifyEquals(sg3.stoneGameIII(new int[]{1,2,3,6}), "Tie");
        Verifier.verifyEquals(sg3.stoneGameIII(new int[]{1,2,3,-1,-2,-3,7}), "Alice");
        Verifier.verifyEquals(sg3.stoneGameIII(new int[]{-1,-2,-3}), "Tie");
        Verifier.verifyEquals(sg3.stoneGameIII(new int[]{-7,17,-2,-13,-6,-7,-13,11,-3,15,0,-11,-5,1,2,13,-14,-16,1,-8,6,-2,-14}), "Alice");

    }

}
