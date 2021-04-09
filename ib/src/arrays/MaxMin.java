package arrays;

import java.util.List;

public class MaxMin {

    int sum(List<Integer> A) {
        if (A == null || A.size() == 0) {
            return 0;
        }
        int max = A.get(0);
        int min = max;
        for (int i = 1; i < A.size(); i++) {
            int ai = A.get(i);
            if (ai > max) {
                max = ai;
            } else if (ai < min) {
                min = ai;
            }
        }
        return max + min;
    }

}
