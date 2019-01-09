package binarysearch;

import util.Verifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchForARange {

    // DO NOT MODIFY THE LIST
    public ArrayList<Integer> searchRange(final List<Integer> a, int b) {
        ArrayList<Integer> result = new ArrayList<>(2);
        if (a == null || a.size() == 0) {
            result.add(-1);
            result.add(-1);
            return result;
        }
        long begin = 0, end = a.size() - 1, mid = begin;
        // find first occurrence
        while (begin <= end) {
            mid = (begin + end) / 2;
            int num = a.get((int)mid);
            if (num < b) {
                begin = mid+1;
            } else {
                end = mid - 1;
            }
        }
        if (begin >= a.size() || a.get((int)begin) != b) {
            result.add(-1);
            result.add(-1);
            return result;
        }
        result.add((int)begin);
        begin = 0;
        end = a.size() - 1;
        mid = begin;
        // find last occurrence
        while (begin <= end) {
            mid = (begin + end) / 2;
            int num = a.get((int)mid);
            if (num > b) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        result.add((int)end);
        return result;
    }

    public static void main(String[] args) {
        SearchForARange searchForARange = new SearchForARange();
        List<Integer> a = Arrays.asList(new Integer[]{5, 7, 7, 8, 8, 10});
        Verifier.verifyEquals(searchForARange.searchRange(a, 8), new ArrayList<Integer>() {{add(3);add(4);}});
        Verifier.verifyEquals(searchForARange.searchRange(a, 5), new ArrayList<Integer>() {{add(0);add(0);}});
        Verifier.verifyEquals(searchForARange.searchRange(a, 11), new ArrayList<Integer>() {{add(-1);add(-1);}});
        Verifier.verifyEquals(searchForARange.searchRange(a, 10), new ArrayList<Integer>() {{add(5);add(5);}});
        Verifier.verifyEquals(searchForARange.searchRange(a, 6), new ArrayList<Integer>() {{add(-1);add(-1);}});
        Verifier.verifyEquals(searchForARange.searchRange(a, 4), new ArrayList<Integer>() {{add(-1);add(-1);}});

        Verifier.verifyEquals(searchForARange.searchRange(null, 6), new ArrayList<Integer>() {{add(-1);add(-1);}});
        a = new ArrayList<Integer>(){{add(1);}};
        Verifier.verifyEquals(searchForARange.searchRange(a, 1), new ArrayList<Integer>() {{add(0);add(0);}});
        Verifier.verifyEquals(searchForARange.searchRange(a, 2), new ArrayList<Integer>() {{add(-1);add(-1);}});
        Verifier.verifyEquals(searchForARange.searchRange(a, 0), new ArrayList<Integer>() {{add(-1);add(-1);}});

        a = new ArrayList<Integer>(){{add(5);add(6);add(7);}};
        Verifier.verifyEquals(searchForARange.searchRange(a, 6), new ArrayList<Integer>() {{add(1);add(1);}});
    }

}
