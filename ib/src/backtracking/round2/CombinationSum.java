package backtracking.round2;

import util.Creator;
import util.Verifier;

import java.util.*;

public class CombinationSum {

    private List<List<Integer>> ans;

    private void combinationInternal(List<Integer> A, Stack<Integer> selection, int curPos, int remainingSum) {
        if (remainingSum == 0) {
            ans.add(new ArrayList<>(selection));
            return;
        }
        if (curPos >= A.size()) {
            return;
        }
        int ai = A.get(curPos);
        if (ai <= remainingSum) {
            //pick
            selection.push(ai);
            combinationInternal(A, selection, curPos, remainingSum - ai);
            selection.pop();
            combinationInternal(A, selection, curPos+1, remainingSum);
        }
    }

    public List<List<Integer>> combinationSum(List<Integer> A, int B) {
        if (A == null || A.size() == 0) {
            return Collections.emptyList();
        }
        //since duplicate combinations are not allowed, we remove duplicate entries from given list by adding it to set.
        Set<Integer> set = new HashSet<>(A);
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        ans = new ArrayList<>();
        combinationInternal(list, new Stack<>(), 0, B);
        return ans;
    }

    public static void main(String[] args) {
        CombinationSum cs = new CombinationSum();
        Verifier.verifyEquals(cs.combinationSum(Creator.getList(2,3,6,7), 7),
                Creator.getList(Creator.getList(2,2,3), Creator.getList(7)));
        Verifier.verifyEquals(cs.combinationSum(Creator.getList(2), 4),
                Creator.getList(Creator.getList(2,2)));
        Verifier.verifyEquals(cs.combinationSum(Creator.getList(2,4), 1),
                Creator.getList());
        Verifier.verifyEquals(cs.combinationSum(Creator.getList(2,4), 4),
                Creator.getList(Creator.getList(2,2), Creator.getList(4)));
        Verifier.verifyEquals(cs.combinationSum(Creator.getList(2,3,5,7,9), 14),
                Creator.getList(Creator.getList(2, 2, 2, 2, 2, 2, 2), Creator.getList(2, 2, 2, 2, 3, 3),
                        Creator.getList(2, 2, 2, 3, 5), Creator.getList(2, 2, 3, 7),
                        Creator.getList(2, 2, 5, 5), Creator.getList(2, 3, 3, 3, 3),
                        Creator.getList(2, 3, 9), Creator.getList(2, 5, 7),
                        Creator.getList(3, 3, 3, 5), Creator.getList(5, 9),
                        Creator.getList(7, 7)));
        Verifier.verifyEquals(cs.combinationSum(Creator.getList(8, 10, 6, 11, 1, 16, 8), 28),
                Creator.getList(Creator.getList(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1),
                        Creator.getList(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,6),
                        Creator.getList(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,8),
                        Creator.getList(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,10),
                        Creator.getList(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,11),
                        Creator.getList(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,6,6),
                        Creator.getList(1,1,1,1,1,1,1,1,1,1,1,1,1,1,6,8),
                        Creator.getList(1,1,1,1,1,1,1,1,1,1,1,1,6,10),
                        Creator.getList(1,1,1,1,1,1,1,1,1,1,1,1,8,8),
                        Creator.getList(1,1,1,1,1,1,1,1,1,1,1,1,16),
                        Creator.getList(1,1,1,1,1,1,1,1,1,1,1,6,11),
                        Creator.getList(1,1,1,1,1,1,1,1,1,1,6,6,6),
                        Creator.getList(1,1,1,1,1,1,1,1,1,1,8,10),
                        Creator.getList(1,1,1,1,1,1,1,1,1,8,11),
                        Creator.getList(1,1,1,1,1,1,1,1,6,6,8),
                        Creator.getList(1,1,1,1,1,1,1,1,10,10),
                        Creator.getList(1,1,1,1,1,1,1,10,11),
                        Creator.getList(1,1,1,1,1,1,6,6,10),
                        Creator.getList(1,1,1,1,1,1,6,8,8),
                        Creator.getList(1,1,1,1,1,1,6,16),
                        Creator.getList(1,1,1,1,1,1,11,11),
                        Creator.getList(1,1,1,1,1,6,6,11),
                        Creator.getList(1,1,1,1,6,6,6,6),
                        Creator.getList(1,1,1,1,6,8,10),
                        Creator.getList(1,1,1,1,8,8,8),
                        Creator.getList(1,1,1,1,8,16),
                        Creator.getList(1,1,1,6,8,11),
                        Creator.getList(1,1,6,6,6,8),
                        Creator.getList(1,1,6,10,10),
                        Creator.getList(1,1,8,8,10),
                        Creator.getList(1,1,10,16),
                        Creator.getList(1,6,10,11),
                        Creator.getList(1,8,8,11),
                        Creator.getList(1,11,16),
                        Creator.getList(6,6,6,10),
                        Creator.getList(6,6,8,8),
                        Creator.getList(6,6,16),
                        Creator.getList(6,11,11),
                        Creator.getList(8,10,10)));
    }

}
