package heapsandmaps.round2;

import util.Creator;
import util.Verifier;

import java.util.*;

public class DistinctNumbersInWindow {

    public List<Integer> dNums(List<Integer> A, int B) {

        if (A == null || A.size() == 0) {
            return A;
        }

        if (B > A.size()) {
            return new ArrayList<>();
        }

        Map<Integer, Integer> countMap = new HashMap<>();

        for (int i = 0; i < B; i++) {
            int ai = A.get(i);
            int newCount = countMap.getOrDefault(ai, 0) + 1;
            countMap.put(ai, newCount);
        }
        List<Integer> ans = new ArrayList<>();
        ans.add(countMap.size());

        for (int i = B; i < A.size(); i++) {
            int toRemove = A.get(i-B);
            int toAdd = A.get(i);
            int count = countMap.get(toRemove);
            if (count == 1) {
                countMap.remove(toRemove);
            } else {
                countMap.put(toRemove, count - 1);
            }
            countMap.compute(toAdd, (k,v) -> v == null ? 1 : v+1);
            ans.add(countMap.size());
        }
        return ans;
    }

    public static void main(String[] args) {
        DistinctNumbersInWindow dniw = new DistinctNumbersInWindow();
        Verifier.verifyEquals(dniw.dNums(Creator.getList(1, 2, 1, 3, 4, 3), 3),
                Creator.getList(2,3,3,2));
        Verifier.verifyEquals(dniw.dNums(Creator.getList(1,2,3,4,5), 3), Creator.getList(3,3,3));
        Verifier.verifyEquals(dniw.dNums(Creator.getList(2,2,2,2,2), 3), Creator.getList(1,1,1));
        Verifier.verifyEquals(dniw.dNums(Creator.getList(2,2,2,2,2), 8), Creator.getList());
        Verifier.verifyEquals(dniw.dNums(Creator.getList(2,2,2,2,2), 5), Creator.getList(1));
    }

}
