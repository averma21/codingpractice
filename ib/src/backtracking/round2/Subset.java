package backtracking.round2;

import util.Creator;
import util.Verifier;

import java.util.*;

public class Subset {

    private List<List<Integer>> ans;

    private void subsets(List<Integer> A, Stack<Integer> curEntries, int pos) {
        if (pos >= A.size()) {
            ans.add(new ArrayList<>(curEntries));
            return;
        }
        subsets(A, curEntries, pos+1);
        curEntries.push(A.get(pos));
        subsets(A, curEntries, pos+1);
        curEntries.pop();
    }

    public List<List<Integer>> subsets(List<Integer> A) {
        ans = new ArrayList<>();
        if (A == null || A.isEmpty()) {
            ans.add(new ArrayList<>());
            return ans;
        }
        Collections.sort(A);
        subsets(A, new Stack<>(), 0);
        ans.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                int min = Math.min(o1.size(), o2.size());
                for (int i = 0; i < min; i++) {
                    int a = o1.get(i), b = o2.get(i);
                    if (a != b) {
                        return Integer.compare(a,b);
                    }
                }
                return Integer.compare(o1.size(), o2.size());
            }
        });
        return ans;
    }

    public static void main(String[] args) {
        Subset subset = new Subset();
        Verifier.verifyEquals(subset.subsets(Creator.getList(1,2,3)), Creator.getList(
                Creator.getList(),
                Creator.getList(1),
                Creator.getList(1,2),
                Creator.getList(1,2,3),
                Creator.getList(1,3),
                Creator.getList(2),
                Creator.getList(2,3),
                Creator.getList(3)
        ));
    }

}
