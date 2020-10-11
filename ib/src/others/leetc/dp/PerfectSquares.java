package others.leetc.dp;

import util.Verifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerfectSquares {

    Map<Integer, Integer> cache;

    private int find(int n) {
        if (cache.containsKey(n))
            return cache.get(n);
        int index = (int)Math.sqrt(n);
        int min = Integer.MAX_VALUE;
        for (int i = index; i >= 1; i--) {
            int sq = i*i;
            if (sq == n)
                return 1;
            int remainder = n - sq;
            min = Math.min(min, 1 + find(remainder));
        }
        cache.put(n, min);
        return min;
    }

    public int numSquares(int n) {
        cache = new HashMap<>();
        if (n == 0)
            return 0;
        return find(n);
    }

    public static void main(String[] args) {
        PerfectSquares ps = new PerfectSquares();
        Verifier.verifyEquals(ps.numSquares(0), 0);
        Verifier.verifyEquals(ps.numSquares(1), 1);
        Verifier.verifyEquals(ps.numSquares(2), 2);
        Verifier.verifyEquals(ps.numSquares(3), 3);
        Verifier.verifyEquals(ps.numSquares(4), 1);
        Verifier.verifyEquals(ps.numSquares(5), 2);
        Verifier.verifyEquals(ps.numSquares(12), 3);
        Verifier.verifyEquals(ps.numSquares(11), 3);
        Verifier.verifyEquals(ps.numSquares(13), 2);
        Verifier.verifyEquals(ps.numSquares(99), 3);
        Verifier.verifyEquals(ps.numSquares(100), 1);
        Verifier.verifyEquals(ps.numSquares(97), 2);
    }
}
