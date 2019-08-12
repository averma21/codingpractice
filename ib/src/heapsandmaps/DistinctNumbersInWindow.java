package heapsandmaps;

import util.Creator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DistinctNumbersInWindow {

    private static List<Integer> dNums(List<Integer> A, int B) {
        Set<Integer> set = new HashSet<>(B);
        Map<Integer, Integer> countMap = new HashMap<>();
        if (A == null || B > A.size()) {
            return new ArrayList<>();
        }
        for (int i = 0; i < B; i++) {
            Integer ai = A.get(i);
            set.add(ai);
            countMap.computeIfPresent(ai, (k,v) -> {return v+1;});
            countMap.putIfAbsent(ai, 1);
        }
        List<Integer> ans = new ArrayList<>(A.size() - B);
        for (int i = B; i < A.size(); i++) {
            ans.add(set.size());
            Integer charToRemove = A.get(i - B);
            countMap.computeIfPresent(charToRemove, (k,v)-> {return v-1;});
            if (countMap.get(charToRemove) == 0) {
                set.remove(charToRemove);
            }
            Integer ai = A.get(i);
            set.add(ai);
            countMap.computeIfPresent(ai, (k,v) -> {return v+1;});
            countMap.putIfAbsent(ai, 1);
        }
        ans.add(set.size());
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(dNums(Creator.getList(1, 2, 1, 3, 4, 3), 3));
        System.out.println(dNums(Creator.getList(1,2,3,4,5), 3));
        System.out.println(dNums(Creator.getList(2,2,2,2,2), 3));
        System.out.println(dNums(Creator.getList(2,2,2,2,2), 8));
        System.out.println(dNums(Creator.getList(2,2,2,2,2), 5));
    }
}
