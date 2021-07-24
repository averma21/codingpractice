package Hashing.round2;

import java.util.*;

public class TwoOutOfThree {

    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {
        Set<Integer> aSet = new HashSet<>(A);
        Set<Integer> bSet = new HashSet<>(B);
        Set<Integer> ansSet = new HashSet<>();
        for (int c : C) {
            if (aSet.contains(c) || bSet.contains(c)) {
                ansSet.add(c);
            }
        }
        for (int a : aSet) {
            if (bSet.contains(a)) {
                ansSet.add(a);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>(ansSet);
        Collections.sort(ans);
        return ans;
    }

}
