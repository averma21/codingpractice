package TwoPointers.round2;

import util.Creator;
import util.Verifier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SubarraysWithDistinctIntegers {

    private int atMostB(List<Integer> A, int B) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0, left = 0, right = 0;
        int n = A.size();
        while (right < n) {
            int aj = A.get(right);
            map.compute(aj, (k,v)->v == null ? 1 : v+1);
            while (map.size() > B) {
                int ai = A.get(left);
                int f = map.get(ai);
                if (f == 1) {
                    map.remove(ai);
                } else {
                    map.put(ai, f-1);
                }
                left++;
            }
            count += right-left+1;
            right++;
        }
        return count;
    }

    int count(List<Integer> A, int B) {
        return atMostB(A, B) - atMostB(A, B-1);
    }

    public static void main(String[] args) {
        SubarraysWithDistinctIntegers swd = new SubarraysWithDistinctIntegers();
        Verifier.verifyEquals(swd.count(Creator.getList(1,2,3), 1), 3);
        Verifier.verifyEquals(swd.count(Creator.getList(1,2,3), 2), 2);
        Verifier.verifyEquals(swd.count(Creator.getList(1,2,3), 3), 1);
        Verifier.verifyEquals(swd.count(Creator.getList(1,2,3,3), 3), 2);
        Verifier.verifyEquals(swd.count(Creator.getList(1,2,1,2,3), 2), 7);
        Verifier.verifyEquals(swd.count(Creator.getList(1,2,1,3,4), 3), 3);
    }

}
