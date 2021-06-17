package TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ThreeSumZero {

    public class Triplets {
        int i, j, k;

        Triplets(int i, int j, int k) {
            this.i = i;
            this.j = j;
            this.k = k;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Triplets triplets = (Triplets) o;
            return i == triplets.i && j == triplets.j && k == triplets.k;
        }

        @Override
        public int hashCode() {
            return i+j+k;
        }
    }

    List<List<Integer>> find(List<Integer> A) {
        List<List<Integer>> ans = new ArrayList<>();
        Set<Triplets> triplets = new LinkedHashSet<>();
        if (A == null)
            return ans;
        Collections.sort(A);
        int n = A.size();
        for (int i = 0; i <= n - 2; i++) {
            int cur = A.get(i);
            int j = i + 1, k = n-1;
            while (j < k) {
                long s = A.get(j) + (long)A.get(k);
                if (s + cur == 0) {
                    triplets.add(new Triplets(cur, A.get(j), A.get(k)));
                    j++;
                } else if (s + cur < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        for (Triplets t : triplets) {
            ans.add(new ArrayList<Integer>(){{add(t.i);add(t.j);add(t.k);}});
        }
        return ans;
    }

    public static void main(String[] args) {
        ThreeSumZero tsz = new ThreeSumZero();
        List<Integer> list = Arrays.asList(new Integer[]{-1, 0, 1, 2, -1, -4});
        List<List<Integer>> ans = tsz.find(list);
        System.out.println(ans);
        list = Arrays.asList(new Integer[]{ 1, -4, 0, 0, 5, -5, 1, 0, -2, 4, -4, 1, -1, -4, 3, 4, -1, -1, -3});
        ans = tsz.find(list);
        System.out.println(ans);
        list = Arrays.asList(new Integer[] { 2147483647, -2147483648, -2147483648, 0, 1 });
        ans = tsz.find(list);
        System.out.println(ans);
    }

}
