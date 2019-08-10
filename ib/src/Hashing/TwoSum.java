package Hashing;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {

    static List<Integer> twoSum(List<Integer> A, int B) {
        Map<Integer, Integer> nums = new HashMap<>(A.size());
        for (int i = 0; i < A.size(); i++) {
            int ai = A.get(i);
            if (nums.containsKey(B - ai)) {
                int j = i;
                return new ArrayList<Integer>() {{
                    add(nums.get(B - ai) + 1);
                    add(j + 1);
                }};
            }
            nums.putIfAbsent(ai, i);
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(twoSum(Creator.getList(3,2,3,2), 5), Creator.getList(1, 2));
        Verifier.verifyEquals(twoSum(Creator.getList(3,2,3,2), 6), Creator.getList(1, 3));
        Verifier.verifyEquals(twoSum(Creator.getList(-1,3,2,3,2), 2), Creator.getList(1, 2));
        Verifier.verifyEquals(twoSum(Creator.getList(3,3,3,5), 8), Creator.getList(1, 4));
        Verifier.verifyEquals(twoSum(Creator.getList(3,3,3,5), 9), Creator.getList());
    }

}
