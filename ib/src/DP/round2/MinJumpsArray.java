package DP.round2;

public class MinJumpsArray {

    public int jump(int[] A) {
        if (A == null)
            return 0;
        if (A.length == 1) {
            return 0;
        }
        /*
         * We start from position 0. The maximum position (MP) that be reached from here is A[0]. After this we move one by one
         * till the MP and calculate the position achievable from current position. If that is greater than MP, then we set MP
         * to that, but in this case jump should be increased. However we may encounter another greater jump from next position
         * so we don't increment the jump right now. We increment the jump once we reach the old MP because we won't be
         * able to reach beyond this point from position 0 in one jump.
         */
        int maxReachablePos = A[0]; // maximum reachable position encountered till now
        int lastReachedPos = A[0];  // position after which a jump should be considered
        int jumps = 1;
        for (int i = 1; i < A.length; i++) {
            if (i > maxReachablePos) {
                return -1;
            }
            if (i > lastReachedPos) {
                jumps++;
                lastReachedPos = maxReachablePos;
            }
            maxReachablePos = Math.max(maxReachablePos, i + A[i]);
        }
        return jumps;
    }

}
