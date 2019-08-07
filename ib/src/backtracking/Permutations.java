package backtracking;

import util.Creator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations {

    private static Set<Integer> visited;

    static private void getPermInternal(List<Integer> A, List<List<Integer>> ansList, List<Integer> ans) {
        if (ans.size() == A.size()) {
            ansList.add(new ArrayList<>(ans));
            return;
        }
        for (int i = 0; i < A.size(); i++) {
            if (visited.contains(i)) {
                continue;
            }
            visited.add(i);
            ans.add(A.get(i));
            getPermInternal(A, ansList, ans);
            visited.remove(i);
            ans.remove(A.get(i));
        }
    }

    private static List<List<Integer>> getPerms(List<Integer> A) {
        List<List<Integer>> ans = new ArrayList<>();
        visited = new HashSet<>(A.size());
        getPermInternal(A, ans, new ArrayList<>());
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(getPerms(Creator.getList(1)));
        System.out.println(getPerms(Creator.getList(1,2,3)));
        System.out.println(getPerms(Creator.getList(1,2,3,3)));
    }

}
