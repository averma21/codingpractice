package Hashing.round2;

import util.Creator;
import util.Verifier;

import java.util.*;

public class FourSum {

    private static class Pair {
        int i, j;

        public Pair(int i, int j) {
            if (i >= j) {
                throw new IllegalArgumentException("Invalid");
            }
            this.i = i;
            this.j = j;
        }

        public boolean isBefore(Pair p) {
            return j < p.i;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return i == pair.i &&
                    j == pair.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    public List<List<Integer>> fourSum(List<Integer> A, int B) {
        List<List<Integer>> ans = new ArrayList<>();
        if (A == null || A.size() ==  0) {
            return ans;
        }
        Collections.sort(A);
        Map<Integer, Set<Pair>> sumPairs = new HashMap<>();
        int n = A.size();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int ai = A.get(i), aj = A.get(j);
                int sum = ai + aj;
                int i1 = i, j1 = j;
                sumPairs.compute(sum, (k,v) -> {
                    if (v == null) {
                        v = new LinkedHashSet<>();
                    }
                    v.add(new Pair(i1, j1));
                    return v;
                });
            }
        }
        Set<List<Integer>> temp = new LinkedHashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int ai = A.get(i), aj = A.get(j);
                Pair curPair = new Pair(i,j);
                int sum = ai + aj;
                int remaining = B - sum;
                Set<Pair> existing = sumPairs.getOrDefault(remaining, Collections.emptySet());
                existing.stream().filter(curPair::isBefore).forEach(pair -> {
                    List<Integer> quad = new ArrayList<>();
                    quad.add(ai);
                    quad.add(aj);
                    quad.add(A.get(pair.i));
                    quad.add(A.get(pair.j));
                    temp.add(quad);
                });
            }
        }
        ans.addAll(temp);
        return ans;
    }

    public static void main(String[] args) {
        FourSum fs = new FourSum();
        Verifier.verifyEquals(fs.fourSum(Creator.getList(1, 0, -1, 0, -2, 2), 0),
                Creator.getList(
                        Creator.getList(-2, -1, 1, 2),
                        Creator.getList(-2, 0, 0, 2),
                        Creator.getList(-1, 0, 0, 1)
                ));
        Verifier.verifyEquals(fs.fourSum(Creator.getList(1, 0, -1), 0),
                Creator.getList());
        Verifier.verifyEquals(fs.fourSum(Creator.getList(1, 1, 2, 2, 2), 6),
                Creator.getList(Creator.getList(1,1,2,2)));
        Verifier.verifyEquals(fs.fourSum(Creator.getList(9, -8, -10, -7, 7, -8, 2, -7, 4, 7, 0, -3, -4, -5, -1, -4,
                5, 8, 1, 9, -2, 5, 10, -5, -7, -1, -6, 4, 1, -5, 3, 8, -4, -10, -9, -3, 10, 0, 7, 9, -8, 10, -9, 7, 8,
                0, 6, -6, -7, 6, -4, 2, 0, 10, 1, -2, 5, -2), 0),
                Creator.getList(
                        Creator.getList(-10,-10,10,10),Creator.getList(-10,-9,9,10),Creator.getList(-10,-8,8,10),
                        Creator.getList(-10,-8,9,9),Creator.getList(-10,-7,7,10),Creator.getList(-10,-7,8,9),
                        Creator.getList(-10,-6,6,10),Creator.getList(-10,-6,7,9),Creator.getList(-10,-6,8,8),
                        Creator.getList(-10,-5,5,10),Creator.getList(-10,-5,6,9),Creator.getList(-10,-5,7,8),
                        Creator.getList(-10,-4,4,10),Creator.getList(-10,-4,5,9),Creator.getList(-10,-4,6,8),
                        Creator.getList(-10,-4,7,7),Creator.getList(-10,-3,3,10),Creator.getList(-10,-3,4,9),
                        Creator.getList(-10,-3,5,8),Creator.getList(-10,-3,6,7),Creator.getList(-10,-2,2,10),Creator.getList(-10,-2,3,9),Creator.getList(-10,-2,4,8),Creator.getList(-10,-2,5,7),Creator.getList(-10,-2,6,6),Creator.getList(-10,-1,1,10),Creator.getList(-10,-1,2,9),Creator.getList(-10,-1,3,8),Creator.getList(-10,-1,4,7),Creator.getList(-10,-1,5,6),Creator.getList(-10,0,0,10),Creator.getList(-10,0,1,9),Creator.getList(-10,0,2,8),Creator.getList(-10,0,3,7),Creator.getList(-10,0,4,6),Creator.getList(-10,0,5,5),Creator.getList(-10,1,1,8),Creator.getList(-10,1,2,7),Creator.getList(-10,1,3,6),Creator.getList(-10,1,4,5),Creator.getList(-10,2,2,6),Creator.getList(-10,2,3,5),Creator.getList(-10,2,4,4),Creator.getList(-9,-9,8,10),Creator.getList(-9,-9,9,9),Creator.getList(-9,-8,7,10),Creator.getList(-9,-8,8,9),Creator.getList(-9,-7,6,10),Creator.getList(-9,-7,7,9),Creator.getList(-9,-7,8,8),Creator.getList(-9,-6,5,10),Creator.getList(-9,-6,6,9),Creator.getList(-9,-6,7,8),Creator.getList(-9,-5,4,10),Creator.getList(-9,-5,5,9),Creator.getList(-9,-5,6,8),Creator.getList(-9,-5,7,7),Creator.getList(-9,-4,3,10),Creator.getList(-9,-4,4,9),Creator.getList(-9,-4,5,8),Creator.getList(-9,-4,6,7),Creator.getList(-9,-3,2,10),Creator.getList(-9,-3,3,9),Creator.getList(-9,-3,4,8),Creator.getList(-9,-3,5,7),Creator.getList(-9,-3,6,6),Creator.getList(-9,-2,1,10),Creator.getList(-9,-2,2,9),Creator.getList(-9,-2,3,8),Creator.getList(-9,-2,4,7),Creator.getList(-9,-2,5,6),Creator.getList(-9,-1,0,10),Creator.getList(-9,-1,1,9),Creator.getList(-9,-1,2,8),Creator.getList(-9,-1,3,7),Creator.getList(-9,-1,4,6),Creator.getList(-9,-1,5,5),Creator.getList(-9,0,0,9),Creator.getList(-9,0,1,8),Creator.getList(-9,0,2,7),Creator.getList(-9,0,3,6),Creator.getList(-9,0,4,5),Creator.getList(-9,1,1,7),Creator.getList(-9,1,2,6),Creator.getList(-9,1,3,5),Creator.getList(-9,1,4,4),Creator.getList(-9,2,2,5),Creator.getList(-9,2,3,4),Creator.getList(-8,-8,6,10),Creator.getList(-8,-8,7,9),Creator.getList(-8,-8,8,8),Creator.getList(-8,-7,5,10),Creator.getList(-8,-7,6,9),Creator.getList(-8,-7,7,8),Creator.getList(-8,-6,4,10),Creator.getList(-8,-6,5,9),Creator.getList(-8,-6,6,8),Creator.getList(-8,-6,7,7),Creator.getList(-8,-5,3,10),Creator.getList(-8,-5,4,9),Creator.getList(-8,-5,5,8),Creator.getList(-8,-5,6,7),Creator.getList(-8,-4,2,10),Creator.getList(-8,-4,3,9),Creator.getList(-8,-4,4,8),Creator.getList(-8,-4,5,7),Creator.getList(-8,-4,6,6),Creator.getList(-8,-3,1,10),Creator.getList(-8,-3,2,9),Creator.getList(-8,-3,3,8),Creator.getList(-8,-3,4,7),Creator.getList(-8,-3,5,6),Creator.getList(-8,-2,0,10),Creator.getList(-8,-2,1,9),Creator.getList(-8,-2,2,8),Creator.getList(-8,-2,3,7),Creator.getList(-8,-2,4,6),
                        Creator.getList(-8,-2,5,5),Creator.getList(-8,-1,-1,10),Creator.getList(-8,-1,0,9),Creator.getList(-8,-1,1,8),Creator.getList(-8,-1,2,7),Creator.getList(-8,-1,3,6),Creator.getList(-8,-1,4,5),Creator.getList(-8,0,0,8),Creator.getList(-8,0,1,7),Creator.getList(-8,0,2,6),Creator.getList(-8,0,3,5),Creator.getList(-8,0,4,4),Creator.getList(-8,1,1,6),Creator.getList(-8,1,2,5),Creator.getList(-8,1,3,4),Creator.getList(-8,2,2,4),Creator.getList(-7,-7,4,10),Creator.getList(-7,-7,5,9),Creator.getList(-7,-7,6,8),Creator.getList(-7,-7,7,7),Creator.getList(-7,-6,3,10),Creator.getList(-7,-6,4,9),Creator.getList(-7,-6,5,8),Creator.getList(-7,-6,6,7),Creator.getList(-7,-5,2,10),Creator.getList(-7,-5,3,9),Creator.getList(-7,-5,4,8),Creator.getList(-7,-5,5,7),Creator.getList(-7,-5,6,6),Creator.getList(-7,-4,1,10),Creator.getList(-7,-4,2,9),Creator.getList(-7,-4,3,8),Creator.getList(-7,-4,4,7),Creator.getList(-7,-4,5,6),Creator.getList(-7,-3,0,10),Creator.getList(-7,-3,1,9),Creator.getList(-7,-3,2,8),Creator.getList(-7,-3,3,7),Creator.getList(-7,-3,4,6),Creator.getList(-7,-3,5,5),Creator.getList(-7,-2,-1,10),Creator.getList(-7,-2,0,9),Creator.getList(-7,-2,1,8),Creator.getList(-7,-2,2,7),Creator.getList(-7,-2,3,6),Creator.getList(-7,-2,4,5),Creator.getList(-7,-1,-1,9),Creator.getList(-7,-1,0,8),Creator.getList(-7,-1,1,7),Creator.getList(-7,-1,2,6),Creator.getList(-7,-1,3,5),Creator.getList(-7,-1,4,4),Creator.getList(-7,0,0,7),Creator.getList(-7,0,1,6),Creator.getList(-7,0,2,5),Creator.getList(-7,0,3,4),Creator.getList(-7,1,1,5),Creator.getList(-7,1,2,4),Creator.getList(-7,2,2,3),Creator.getList(-6,-6,2,10),Creator.getList(-6,-6,3,9),Creator.getList(-6,-6,4,8),Creator.getList(-6,-6,5,7),Creator.getList(-6,-6,6,6),Creator.getList(-6,-5,1,10),Creator.getList(-6,-5,2,9),Creator.getList(-6,-5,3,8),Creator.getList(-6,-5,4,7),Creator.getList(-6,-5,5,6),Creator.getList(-6,-4,0,10),Creator.getList(-6,-4,1,9),Creator.getList(-6,-4,2,8),Creator.getList(-6,-4,3,7),Creator.getList(-6,-4,4,6),Creator.getList(-6,-4,5,5),Creator.getList(-6,-3,-1,10),Creator.getList(-6,-3,0,9),Creator.getList(-6,-3,1,8),Creator.getList(-6,-3,2,7),Creator.getList(-6,-3,3,6),Creator.getList(-6,-3,4,5),Creator.getList(-6,-2,-2,10),Creator.getList(-6,-2,-1,9),Creator.getList(-6,-2,0,8),Creator.getList(-6,-2,1,7),Creator.getList(-6,-2,2,6),Creator.getList(-6,-2,3,5),Creator.getList(-6,-2,4,4),Creator.getList(-6,-1,-1,8),Creator.getList(-6,-1,0,7),Creator.getList(-6,-1,1,6),Creator.getList(-6,-1,2,5),Creator.getList(-6,-1,3,4),Creator.getList(-6,0,0,6),Creator.getList(-6,0,1,5),Creator.getList(-6,0,2,4),Creator.getList(-6,1,1,4),Creator.getList(-6,1,2,3),Creator.getList(-5,-5,0,10),Creator.getList(-5,-5,1,9),Creator.getList(-5,-5,2,8),Creator.getList(-5,-5,3,7),Creator.getList(-5,-5,4,6),Creator.getList(-5,-5,5,5),Creator.getList(-5,-4,-1,10),Creator.getList(-5,-4,0,9),Creator.getList(-5,-4,1,8),Creator.getList(-5,-4,2,7),Creator.getList(-5,-4,3,6),Creator.getList(-5,-4,4,5),
                        Creator.getList(-5,-3,-2,10),Creator.getList(-5,-3,-1,9),Creator.getList(-5,-3,0,8),Creator.getList(-5,-3,1,7),Creator.getList(-5,-3,2,6),Creator.getList(-5,-3,3,5),Creator.getList(-5,-3,4,4),Creator.getList(-5,-2,-2,9),Creator.getList(-5,-2,-1,8),Creator.getList(-5,-2,0,7),Creator.getList(-5,-2,1,6),Creator.getList(-5,-2,2,5),Creator.getList(-5,-2,3,4),Creator.getList(-5,-1,-1,7),Creator.getList(-5,-1,0,6),Creator.getList(-5,-1,1,5),Creator.getList(-5,-1,2,4),Creator.getList(-5,0,0,5),Creator.getList(-5,0,1,4),Creator.getList(-5,0,2,3),Creator.getList(-5,1,1,3),Creator.getList(-5,1,2,2),Creator.getList(-4,-4,-2,10),Creator.getList(-4,-4,-1,9),Creator.getList(-4,-4,0,8),Creator.getList(-4,-4,1,7),Creator.getList(-4,-4,2,6),Creator.getList(-4,-4,3,5),Creator.getList(-4,-4,4,4),Creator.getList(-4,-3,-3,10),Creator.getList(-4,-3,-2,9),Creator.getList(-4,-3,-1,8),Creator.getList(-4,-3,0,7),Creator.getList(-4,-3,1,6),Creator.getList(-4,-3,2,5),Creator.getList(-4,-3,3,4),Creator.getList(-4,-2,-2,8),Creator.getList(-4,-2,-1,7),Creator.getList(-4,-2,0,6),Creator.getList(-4,-2,1,5),Creator.getList(-4,-2,2,4),Creator.getList(-4,-1,-1,6),Creator.getList(-4,-1,0,5),Creator.getList(-4,-1,1,4),Creator.getList(-4,-1,2,3),Creator.getList(-4,0,0,4),Creator.getList(-4,0,1,3),Creator.getList(-4,0,2,2),Creator.getList(-4,1,1,2),Creator.getList(-3,-3,-2,8),Creator.getList(-3,-3,-1,7),Creator.getList(-3,-3,0,6),Creator.getList(-3,-3,1,5),Creator.getList(-3,-3,2,4),Creator.getList(-3,-2,-2,7),Creator.getList(-3,-2,-1,6),Creator.getList(-3,-2,0,5),Creator.getList(-3,-2,1,4),Creator.getList(-3,-2,2,3),Creator.getList(-3,-1,-1,5),Creator.getList(-3,-1,0,4),Creator.getList(-3,-1,1,3),Creator.getList(-3,-1,2,2),Creator.getList(-3,0,0,3),Creator.getList(-3,0,1,2),Creator.getList(-3,1,1,1),Creator.getList(-2,-2,-2,6),Creator.getList(-2,-2,-1,5),Creator.getList(-2,-2,0,4),Creator.getList(-2,-2,1,3),Creator.getList(-2,-2,2,2),Creator.getList(-2,-1,-1,4),Creator.getList(-2,-1,0,3),Creator.getList(-2,-1,1,2),Creator.getList(-2,0,0,2),Creator.getList(-2,0,1,1),Creator.getList(-1,-1,0,2),
                        Creator.getList(-1,-1,1,1),Creator.getList(-1,0,0,1),Creator.getList(0,0,0,0)
                ));
    }

}
