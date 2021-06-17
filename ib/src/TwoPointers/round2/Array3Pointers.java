package TwoPointers.round2;

import java.util.List;

public class Array3Pointers {

    int minimize(List<Integer> A, List<Integer> B, List<Integer> C) {
        int ans = Integer.MAX_VALUE;
        int i = 0, j = 0, k = 0;
        while (i < A.size() && j < B.size() && k < C.size()) {
            int ai = A.get(i), bj = B.get(j), ck = C.get(k);
            int x = Math.abs(ai - bj), y = Math.abs(bj - ck), z = Math.abs(ck - ai);
            int min = Math.min(ai, Math.min(bj, ck));
            ans = Math.min(ans, Math.max(x, Math.max(y, z)));
            if (ai == min) {
                i++;
            } else if (bj == min) {
                j++;
            } else {
                k++;
            }
        }
        return ans;
    }

}
