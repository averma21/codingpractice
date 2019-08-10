package backtracking;

import util.Creator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubsetsII {

    Set<ArrayList<Integer>> subsets(List<Integer> A, int pos) {
        Set<ArrayList<Integer>> ans = new HashSet<>();
        if (pos >= A.size()) {
            return ans;
        }
        Set<ArrayList<Integer>> nextList = subsets(A, pos+1);
        for (List<Integer> list : nextList) {
            ArrayList<Integer> newList = new ArrayList<>(list);
            newList.add(0, A.get(pos));
            ans.add(newList);
        }
        ans.addAll(nextList);
        ans.add(new ArrayList<Integer>(){{add(A.get(pos));}});
        return ans;
    }

    ArrayList<ArrayList<Integer>> subsets(List<Integer> A) {
        Collections.sort(A);
        Set<ArrayList<Integer>> set = subsets(A, 0);
        set.add(new ArrayList<Integer>());
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>(set);
        Collections.sort(ans, new ListComparator<Integer>());
        return ans;
    }

    private class ListComparator<T extends Comparable<T>> implements Comparator<List<T>> {
        @Override
        public int compare(List<T> first, List<T> second) {
            int i =0, j =0;
            for (; i < first.size() && j < second.size(); i++, j++) {
                if (first.get(i).compareTo(second.get(j)) != 0) {
                    return first.get(i).compareTo(second.get(j));
                }
            }
            if (i < first.size())
                return 1;
            else if (j < second.size())
                return -1;
            return 0;
        }
    }

    public static void main(String[] args) {
        SubsetsII s = new SubsetsII();
        System.out.println(s.subsets(Creator.getList(1,2,2)));
        System.out.println(s.subsets(Creator.getList(1,1,2)));
    }


}
