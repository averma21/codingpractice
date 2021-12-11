package greedy.round2;

import java.util.ArrayList;
import java.util.Collections;

public class MiceToHoles {

    public int mice(ArrayList<Integer> A, ArrayList<Integer> B) {
        Collections.sort(A);
        Collections.sort(B);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.size(); i++) {
            max = Math.max(max, Math.abs(A.get(i) - B.get(i)));
        }
        return max;
    }

}
