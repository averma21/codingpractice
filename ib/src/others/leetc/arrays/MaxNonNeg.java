package others.leetc.arrays;

import util.Creator;

import java.util.ArrayList;
import java.util.List;

public class MaxNonNeg {

    class Patch {
        int start;int end;long sum;
    }

    private Patch getNext(Patch p, List<Integer> A) {
        Patch ans = null;
        for (int i = p.end + 1; i < A.size(); i++) {
            int ai = A.get(i);
            if (ans == null) {
                if (ai < 0)
                    continue;
                else {
                    ans = new Patch();
                    ans.start = i;
                    ans.sum = ai;
                }
            } else {
                if (ai < 0) {
                    ans.end = i - 1;
                    return ans;
                } else {
                    ans.sum += ai;
                }
            }
        }
        if (ans != null)
        ans.end = A.size() - 1;
        return ans;
    }

    public List<Integer> maxset(List<Integer> A) {
        boolean allNeg = true;
        for (int a : A) {
            if (a > 0) {
                allNeg = false;
                break;
            }
        }
        if (allNeg)
            return new ArrayList<>();

        Patch start = new Patch();
        start.end = -1;
        start.sum = Integer.MIN_VALUE;
        long sum = Integer.MIN_VALUE;
        Patch ans = new Patch();
        while (start != null) {
            if (start.sum > sum) {
                ans = start;
                sum = start.sum;
            }
            start = getNext(start, A);
        }
        ArrayList<Integer> list = new ArrayList();
        for (int i = ans.start; i <= ans.end; i++)
            list.add(A.get(i));
        return list;
    }

    public static void main(String[] args) {
        MaxNonNeg mnn = new MaxNonNeg();
        System.out.println(mnn.maxset(Creator.getList(1,2,5,-7,2,5)));
    }
}
