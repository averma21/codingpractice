package binarysearch;

import util.Verifier;

import java.util.ArrayList;

public class SortedInsertPosition {

    public int searchInsert(ArrayList<Integer> a, int b) {
        if (a == null || a.size() == 0) {
            return 0;
        }
        int start = 0, end = a.size() - 1;
        int mid = start;
        if (a.get(0) > b) {
            return 0;
        }
        if (a.get(end) < b) {
            return end + 1;
        }
        while (start <= end) {
            mid = (start + end) / 2;
            int elem = a.get(mid);
            if (elem == b) {
                break;
            }
            if (elem < b) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (a.get(mid) < b) {
            mid = mid + 1;
        }
        return mid;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>() {{
            add(10);add(20);
            add(30);
        }};
        SortedInsertPosition insertPosition = new SortedInsertPosition();
        Verifier.verifyEquals(insertPosition.searchInsert(new ArrayList<>(), 1), 0);
        Verifier.verifyEquals(insertPosition.searchInsert(list, 1), 0);
        Verifier.verifyEquals(insertPosition.searchInsert(list, 25), 2);
        Verifier.verifyEquals(insertPosition.searchInsert(list, 20), 1);
        Verifier.verifyEquals(insertPosition.searchInsert(list, 30), 2);
        Verifier.verifyEquals(insertPosition.searchInsert(list, 35), 3);
        list = new ArrayList<Integer>() {{
            add(1);add(3);
            add(5);add(6);
        }};
        Verifier.verifyEquals(insertPosition.searchInsert(list, 5), 2);
        Verifier.verifyEquals(insertPosition.searchInsert(list, 2), 1);
        Verifier.verifyEquals(insertPosition.searchInsert(list, 7), 4);
        Verifier.verifyEquals(insertPosition.searchInsert(list, 0), 0);
    }

}
