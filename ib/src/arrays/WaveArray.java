package arrays;

import java.util.ArrayList;
import java.util.Collections;

public class WaveArray {

    public ArrayList<Integer> wave(ArrayList<Integer> A) {
        if (A == null) {
            return A;
        }
        Collections.sort(A);
        for (int i = 0; i < A.size() - 1; i+=2) {
            int temp = A.get(i);
            A.set(i, A.get(i+1));
            A.set(i+1, temp);
        }
        return A;
    }

}
