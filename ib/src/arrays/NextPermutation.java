package arrays;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NextPermutation {

    public static List<Integer> nextPermutation(List<Integer> A) {
        if (A == null || A.size() == 0) {
            return A;
        }
        int n = A.size();
        int prev = A.get(n-1);
        int pivot = -1;
        for (int i = n - 2; i >= 0; i--) {
            int ai = A.get(i);
            if (ai < prev) {
                pivot = i;
                break;
            }
            prev = ai;
        }
        if (pivot == -1) {
            Collections.reverse(A);
            return A;
        }
        int aPivot = A.get(pivot);
        for (int i = n-1; i > pivot; i--) {
            int ai = A.get(i);
            if (ai > aPivot) {
                A.set(pivot, ai);
                A.set(i, aPivot);
                break;
            }
        }
        for (int i = n-1, j = pivot+1; i > j; i--, j++) {
            int temp = A.get(i);
            A.set(i, A.get(j));
            A.set(j, temp);
        }
        return A;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(nextPermutation(Creator.getList(1,2,3)), Creator.getList(1,3,2));
        Verifier.verifyEquals(nextPermutation(Creator.getList(2,1)), Creator.getList(1,2));
        Verifier.verifyEquals(nextPermutation(Creator.getList(11,21,3)), Creator.getList(21,3,11));
        Verifier.verifyEquals(nextPermutation(Creator.getList(11,21,5,20,15,10)), Creator.getList(11,21,10,5,15,20));
    }

}
