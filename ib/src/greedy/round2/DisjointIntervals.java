package greedy.round2;

import util.Verifier;

import java.util.Arrays;
import java.util.Comparator;

public class DisjointIntervals {

    private boolean doesOverlap(int s1, int e1, int s2, int e2) {
        return !(e1 < s2 || e2 < s1);
    }

    public int solve(int[][] A) {
        Arrays.sort(A, Comparator.comparing(x->x[0]));
        int ans = 0;
        int s = -1, e = -1;
        for (int[] r : A) {
            if (ans == 0) {
                s = r[0];
                e = r[1];
                ans++;
            } else {
                if (doesOverlap(s, e, r[0], r[1])) {
                    if (r[1] < e) {
                        s = r[0];
                        e = r[1];
                    }
                } else {
                    s = r[0];
                    e = r[1];
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        DisjointIntervals di = new DisjointIntervals();
        Verifier.verifyEquals(di.solve(new int[][] {
            {1, 4},
            {2, 3},
            {4, 6},
            {8, 9}
        }), 3);
        Verifier.verifyEquals(di.solve(new int[][] {
                {5, 7},
                {2, 3},
                {1, 9}
        }), 2);
    }

}
