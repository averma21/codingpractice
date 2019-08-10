package backtracking;

import util.Creator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Subset {

    ArrayList<ArrayList<Integer>> subsets(List<Integer> A, int pos) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (pos >= A.size()) {
            return ans;
        }
        ArrayList<ArrayList<Integer>> nextList = subsets(A, pos+1);
        for (List<Integer> list : nextList) {
            ArrayList<Integer> newList = new ArrayList<>(list);
            newList.add(0, A.get(pos));
            ans.add(newList);
        }
        ans.addAll(nextList);
        ans.add(0, new ArrayList<Integer>(){{add(A.get(pos));}});
        return ans;
    }

    ArrayList<ArrayList<Integer>> subsets(List<Integer> A) {
        Collections.sort(A);
        ArrayList<ArrayList<Integer>> ans = subsets(A, 0);
        ans.add(0, new ArrayList<Integer>());
        return ans;
    }

    public static void main(String[] args) {
        Subset s = new Subset();
        System.out.println(s.subsets(Creator.getList(1,2,3)));
        System.out.println(s.subsets(Creator.getList(15, 20, 12, 19, 4)));
    }

}
