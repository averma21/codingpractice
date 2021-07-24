package Hashing.round2;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public ArrayList<Integer> twoSum(final List<Integer> A, int B) {
        Map<Integer, Integer> map = new HashMap<>();
        if (A == null || A.size() == 0) {
            return new ArrayList<>();
        }
        for (int i = 0; i < A.size(); i++) {
            int ai = A.get(i);
            int pos = i;
            if (map.containsKey(B - ai)) {
                return new ArrayList<>() {{
                    add(map.get(B-ai) + 1);
                    add(pos+1);
                }};
            }
            map.putIfAbsent(ai, i);
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        Verifier.verifyEquals(twoSum.twoSum(Creator.getList(3,2,3,2), 5), Creator.getList(1, 2));
        Verifier.verifyEquals(twoSum.twoSum(Creator.getList(3,2,3,2), 6), Creator.getList(1, 3));
        Verifier.verifyEquals(twoSum.twoSum(Creator.getList(-1,3,2,3,2), 2), Creator.getList(1, 2));
        Verifier.verifyEquals(twoSum.twoSum(Creator.getList(3,3,3,5), 8), Creator.getList(1, 4));
        Verifier.verifyEquals(twoSum.twoSum(Creator.getList(3,3,3,5), 9), Creator.getList());
    }

}
