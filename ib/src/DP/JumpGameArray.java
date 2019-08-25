package DP;

import java.util.List;

public class JumpGameArray {

    public int canJump(List<Integer> A) {
        int lastPos = A.size() - 1;
        for (int i = A.size() - 1; i >= 0; i--) {
            if (i + A.get(i) >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0 ? 1 : 0;
    }

}
