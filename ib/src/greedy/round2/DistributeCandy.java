package greedy.round2;

import util.Verifier;

import java.util.Arrays;

public class DistributeCandy {

    public int candy(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        if (A.length == 1) {
            return 1;
        }
        int n = A.length, sum = 0;
        int [] dist = new int[n];
        Arrays.fill(dist, 1);
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i-1]) {
                dist[i] = dist[i-1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (A[i] > A[i+1]) {
                dist[i] = Math.max(dist[i], dist[i+1] + 1);
            }
        }
        for (int val : dist) {
            sum += val;
        }
        return sum;
    }

    public static void main(String[] args) {
        DistributeCandy dc = new DistributeCandy();
        Verifier.verifyEquals(dc.candy(new int[] {1,2}), 3);
        Verifier.verifyEquals(dc.candy(new int[] {1,5,2,1}), 7);
    }

}
