package TwoPointers;

import java.util.List;

public class Array3Pointers {

    int minimize(List<Integer> A, List<Integer> B, List<Integer> C) {
        int result = Integer.MAX_VALUE;
        int i=0, j=0, k=0;
        while (i < A.size() && j < B.size() && k < C.size()) {
            int ai = A.get(i), bj = B.get(j), ck = C.get(k);
            int mod1 = Math.abs(ai - bj);
            int mod2 = Math.abs(bj - ck);
            int mod3 = Math.abs(ck - ai);
            result = Math.min(result, Math.max(mod1, Math.max(mod2, mod3)));
            if (mod1 >= mod2 && mod1 >= mod3) {
                if (ai > bj)
                    j++;
                else
                    i++;
            } else if (mod2 >= mod1 && mod2 >= mod3) {
                if (bj > ck)
                    k++;
                else
                    j++;
            } else {
                if (ck > ai)
                    i++;
                else
                    k++;
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }

}
