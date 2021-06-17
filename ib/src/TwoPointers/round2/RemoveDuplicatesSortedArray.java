package TwoPointers.round2;

import util.Creator;
import util.Verifier;

import java.util.List;

public class RemoveDuplicatesSortedArray {

    int remove(List<Integer> a) {
        if (a == null || a.size() == 0) {
            return 0;
        }
        if (a.size() == 1) {
            return 1;
        }
        int filledPos = 0, prev = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            int ai = a.get(i);
            if (ai != prev) {
                a.set(++filledPos, ai);
            }
            prev = ai;
        }
        return filledPos+1;
    }

    public static void main(String[] args) {
        RemoveDuplicatesSortedArray rda = new RemoveDuplicatesSortedArray();
        Verifier.verifyEquals(rda.remove(Creator.getList(1)), 1);
        Verifier.verifyEquals(rda.remove(Creator.getList(20,20,20)), 1);
        Verifier.verifyEquals(rda.remove(Creator.getList(1,1,2)), 2);
        Verifier.verifyEquals(rda.remove(Creator.getList(1,2,3)), 3);
        List<Integer> A = Creator.getList(1,2,3,3,4,5,5,5,6);
        Verifier.verifyEquals(rda.remove(A), 6);
        Verifier.verifyEquals(A, Creator.getList(1,2,3,4,5,6,5,5,6));
    }

}
