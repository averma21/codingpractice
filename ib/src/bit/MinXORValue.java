package bit;

import util.Verifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinXORValue {

    public static int findMinXor(List<Integer> A) {
        // Sort given array \
        Collections.sort(A);

        int minXor = Integer.MAX_VALUE;
        int val = 0;

        // calculate min xor of consecutive pairs
        for (int i = 0; i < A.size() - 1; i++) {
            val = A.get(i) ^ A.get(i + 1);
            minXor = Math.min(minXor, val);
        }

        return minXor;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(new Integer[]{0, 2, 5, 7});
        Verifier.verifyEquals(findMinXor(list), 2);
        list = Arrays.asList(new Integer[]{0, 4, 7, 9});
        Verifier.verifyEquals(findMinXor(list), 3);
    }

}
