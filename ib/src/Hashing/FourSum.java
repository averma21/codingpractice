package Hashing;

import util.Creator;
import util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 *
 *  Note:
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
 * The solution set must not contain duplicate quadruplets.
 * Example :
 * Given array S = {1 0 -1 0 -2 2}, and target = 0
 * A solution set is:
 *
 *     (-2, -1, 1, 2)
 *     (-2,  0, 0, 2)
 *     (-1,  0, 0, 1)
 * Also make sure that the solution set is lexicographically sorted.
 * Solution[i] < Solution[j] iff Solution[i][0] < Solution[j][0] OR (Solution[i][0] == Solution[j][0] AND ... Solution[i][k] < Solution[j][k])
 */
public class FourSum {

    static ArrayList<ArrayList<Integer>> fourSum(List<Integer> A, int B) {
        if (A == null || A.size() < 4)
            return new ArrayList<ArrayList<Integer>>();
        Set<ArrayList<Integer>> ansSet = new LinkedHashSet<>();
        Collections.sort(A);
        HashMap<Integer, List<Pair>> pairSums = new HashMap<>();
        int aSize = A.size();
        for (int i = 0; i < aSize; i++) {
            for (int j = i + 1; j < aSize; j++) {
                int ai = A.get(i), aj = A.get(j);
                int sum = ai + aj;
                final int i1 = i, j1 = j;
                pairSums.computeIfPresent(sum, (k, v) -> {v.add(new Pair(i1, j1)); return v;});
                pairSums.computeIfAbsent(sum, (k) -> new ArrayList<Pair>(){{add(new Pair(i1, j1));}});
            }
        }
        for (int i = 0; i < aSize; i++) {
            for (int j = i+1; j < aSize; j++) {
                int ai = A.get(i), aj = A.get(j);
                int remainingSum = B - ai - aj;
                final int i1 = i, j1 = j;
                for (Pair p : pairSums.getOrDefault(remainingSum, Collections.emptyList())) {
                    if (p.getX() != i1 && p.getX() != j1 && p.getY() != i1 && p.getY() != j1) {
                        ArrayList<Integer> list = new ArrayList<Integer>() {{
                            add(A.get(i1));
                            add(A.get(j1));
                            add(A.get(p.getX()));
                            add(A.get(p.getY()));
                        }};
                        Collections.sort(list);
                        ansSet.add(list);
                    }
                }
            }
        }
        ArrayList<ArrayList<Integer>> list = new ArrayList<>(ansSet.size());
        for (ArrayList<Integer> l : ansSet)
            list.add(l);
        return list;
    }

    public static void main(String[] args) {
        System.out.println(fourSum(Creator.getList(1, 0, -1, 0, -2, 2), 0));
        System.out.println(fourSum(Creator.getList(1, 0, -1), 0));
    }

}
