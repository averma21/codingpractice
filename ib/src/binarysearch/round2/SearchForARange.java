package binarysearch.round2;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.List;

public class SearchForARange {

    public List<Integer> searchRange(final List<Integer> A, int B) {
        ArrayList<Integer> result = new ArrayList<>(2);
        if (A == null || A.size() == 0) {
            result.add(-1);
            result.add(-1);
            return result;
        }
        long begin = 0, end = A.size() - 1, mid = begin;
        // find first occurrence
        while (begin <= end) {
            mid = (begin + end) / 2;
            int num = A.get((int)mid);
            if (num < B) {
                begin = mid+1;
            } else {
                end = mid - 1;
            }
        }
        if (begin >= A.size() || A.get((int)begin) != B) {
            result.add(-1);
            result.add(-1);
            return result;
        }
        result.add((int)begin);
        begin = 0;
        end = A.size() - 1;
        mid = begin;
        // find last occurrence
        while (begin <= end) {
            mid = (begin + end) / 2;
            int num = A.get((int)mid);
            if (num > B) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        result.add((int)end);
        return result;
    }

    public static void main(String[] args) {
        SearchForARange sfr = new SearchForARange();
        Verifier.verifyEquals(sfr.searchRange(Creator.getList(1,2,3,4,5,6), 3), Creator.getList(2,2));
        Verifier.verifyEquals(sfr.searchRange(Creator.getList(1,2,3,3,4,5,6), 3), Creator.getList(2,3));
        Verifier.verifyEquals(sfr.searchRange(Creator.getList(1,2,3,3,3,4,5,6), 3), Creator.getList(2,4));
        Verifier.verifyEquals(sfr.searchRange(Creator.getList(1,2,3,3,4,5,6,6,6,6), 6), Creator.getList(6,9));
        Verifier.verifyEquals(sfr.searchRange(Creator.getList(1,1,1,2,3,3,4,5,6,6), 1), Creator.getList(0,2));
        Verifier.verifyEquals(sfr.searchRange(Creator.getList(1,1,1), 1), Creator.getList(0,2));
        Verifier.verifyEquals(sfr.searchRange(Creator.getList(1), 1), Creator.getList(0,0));
        Verifier.verifyEquals(sfr.searchRange(Creator.getList(1), 2), Creator.getList(-1,-1));
        Verifier.verifyEquals(sfr.searchRange(Creator.getList(1,2,3), 4), Creator.getList(-1,-1));

        List<Integer> a = Creator.getList(5, 7, 7, 8, 8, 10);
        Verifier.verifyEquals(sfr.searchRange(a, 8), new ArrayList<Integer>() {{add(3);add(4);}});
        Verifier.verifyEquals(sfr.searchRange(a, 5), new ArrayList<Integer>() {{add(0);add(0);}});
        Verifier.verifyEquals(sfr.searchRange(a, 11), new ArrayList<Integer>() {{add(-1);add(-1);}});
        Verifier.verifyEquals(sfr.searchRange(a, 10), new ArrayList<Integer>() {{add(5);add(5);}});
        Verifier.verifyEquals(sfr.searchRange(a, 6), new ArrayList<Integer>() {{add(-1);add(-1);}});
        Verifier.verifyEquals(sfr.searchRange(a, 4), new ArrayList<Integer>() {{add(-1);add(-1);}});

        Verifier.verifyEquals(sfr.searchRange(null, 6), new ArrayList<Integer>() {{add(-1);add(-1);}});
        a = new ArrayList<Integer>(){{add(1);}};
        Verifier.verifyEquals(sfr.searchRange(a, 1), new ArrayList<Integer>() {{add(0);add(0);}});
        Verifier.verifyEquals(sfr.searchRange(a, 2), new ArrayList<Integer>() {{add(-1);add(-1);}});
        Verifier.verifyEquals(sfr.searchRange(a, 0), new ArrayList<Integer>() {{add(-1);add(-1);}});

        a = new ArrayList<Integer>(){{add(5);add(6);add(7);}};
        Verifier.verifyEquals(sfr.searchRange(a, 6), new ArrayList<Integer>() {{add(1);add(1);}});
    }

}
