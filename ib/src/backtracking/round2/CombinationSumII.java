package backtracking.round2;

import util.Creator;
import util.Verifier;

import java.util.*;

public class CombinationSumII {

    private List<List<Integer>> ans;

    private void combinationInternal(List<Integer> A, Stack<Integer> selection, int curPos, int remainingSum) {
        if (remainingSum == 0) {
            ans.add(new ArrayList<>(selection));
            return;
        }
        if (curPos >= A.size()) {
            return;
        }
        int prev = -1;
        for (int i = curPos; i < A.size(); i++) {
            int ai = A.get(i);
            if (ai == prev) {
                continue;
            }
            prev = ai;
            if (ai <= remainingSum) {
                //pick
                selection.push(ai);
                combinationInternal(A, selection, i+1, remainingSum - ai);
                selection.pop();
            } else {
                break;
            }
        }

    }

    public List<List<Integer>> combinationSum(List<Integer> A, int B) {
        if (A == null || A.size() == 0) {
            return Collections.emptyList();
        }
        Collections.sort(A);
        ans = new ArrayList<>();
        combinationInternal(A, new Stack<>(), 0, B);
        return ans;
    }

    public static void main(String[] args) {
        CombinationSumII cs = new CombinationSumII();
        Verifier.verifyEquals(cs.combinationSum(Creator.getList(2,3,6,7), 7),
                Creator.getList(Creator.getList(7)));
        Verifier.verifyEquals(cs.combinationSum(Creator.getList(2), 4),
                Creator.getList());
        Verifier.verifyEquals(cs.combinationSum(Creator.getList(2,4), 1),
                Creator.getList());
        Verifier.verifyEquals(cs.combinationSum(Creator.getList(2,4), 4),
                Creator.getList(Creator.getList(4)));
        Verifier.verifyEquals(cs.combinationSum(Creator.getList(2,3,5,7,9), 14),
                Creator.getList(Creator.getList(2, 3, 9), Creator.getList(2, 5, 7),
                        Creator.getList(5, 9)));
        Verifier.verifyEquals(cs.combinationSum(Creator.getList(10,1,2,7,6,1,5), 8),
                Creator.getList(Creator.getList(1, 1, 6), Creator.getList(1, 2, 5), Creator.getList(1,7),
                        Creator.getList(2, 6)));
    }

}
