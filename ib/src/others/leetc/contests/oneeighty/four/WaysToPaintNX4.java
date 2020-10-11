package others.leetc.contests.oneeighty.four;

import util.Verifier;

public class WaysToPaintNX4 {

    //https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/discuss/574923/JavaC%2B%2BPython-DP-O(1)-Space
    public int numOfWays(int n) {
        long a121 = 6, a123 = 6, b121, b123, mod = (long)1e9 + 7;
        for (int i = 1; i < n; ++i) {
            b121 = a121 * 3 + a123 * 2;
            b123 = a121 * 2 + a123 * 2;
            a121 = b121 % mod;
            a123 = b123 % mod;
        }
        return (int)((a121 + a123) % mod);
    }

    public static void main(String[] args) {
        WaysToPaintNX4 ways = new WaysToPaintNX4();
        Verifier.verifyEquals(ways.numOfWays(2), 54);
        Verifier.verifyEquals(ways.numOfWays(3), 246);
        Verifier.verifyEquals(ways.numOfWays(4), 1122);
        Verifier.verifyEquals(ways.numOfWays(5), 5118);
        Verifier.verifyEquals(ways.numOfWays(7), 106494);
        Verifier.verifyEquals(ways.numOfWays(100), 905790447);
        Verifier.verifyEquals(ways.numOfWays(5000), 30228214);
    }

}
