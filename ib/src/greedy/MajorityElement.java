package greedy;

import util.Creator;
import util.Verifier;

import java.util.List;

public class MajorityElement {

    private static int majority(List<Integer> A) {
        int count = 1;
        int majority = A.get(0);
        for (int i = 1; i < A.size(); i++) {
            int ai = A.get(i);
            if (ai == majority) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    majority = ai;
                    count = 1;
                }
            }
        }
        return majority;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(majority(Creator.getList(1,2,3,3,3,3,4)), 3);
        Verifier.verifyEquals(majority(Creator.getList(1,2,2,2,2,3,4)), 2);
        Verifier.verifyEquals(majority(Creator.getList(1,2,3,2,4,2,2,5)), 2);
        Verifier.verifyEquals(majority(Creator.getList(1)), 1);
        Verifier.verifyEquals(majority(Creator.getList(1,1,2)), 1);
        Verifier.verifyEquals(majority(Creator.getList(2,1,2)), 2);
    }

}
