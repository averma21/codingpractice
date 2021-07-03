package backtracking.round2;

import util.Creator;

import java.util.*;

public class Permutations {

    ArrayList<ArrayList<Integer>> ans;
    Set<Integer> visited;

    void getPerms(List<Integer> A, Stack<Integer> curAnsList) {
        if (A == null || A.size() == 0 || curAnsList.size() == A.size()) {
            ans.add(new ArrayList<>(curAnsList));
            return;
        }
        int n = A.size();
        for (int i = 0; i < n; i++) {
            if (visited.contains(i)) {
                continue;
            }
            curAnsList.push(A.get(i));
            visited.add(i);
            getPerms(A, curAnsList);
            curAnsList.pop();
            visited.remove(i);
        }
    }

    ArrayList<ArrayList<Integer>> getPerms(List<Integer> A) {
        ans = new ArrayList<>();
        visited = new HashSet<>();
        getPerms(A, new Stack<>());
        return ans;
    }

    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        System.out.println(permutations.getPerms(Creator.getList(1)));
        System.out.println(permutations.getPerms(Creator.getList(1,2,3)));
        System.out.println(permutations.getPerms(Creator.getList(1,2,3,3)));
    }
}
