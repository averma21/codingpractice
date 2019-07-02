package binarysearch;

import util.Verifier;

import java.util.Arrays;
import java.util.List;

public class RotatedSortedArraySearch {

    private int findRotationIndex(final List<Integer> a) {
        int rotationIndex = -1;
        int low = 0, high = a.size() - 1;
        int lastNo = a.get(high);
        while (low <= high) {
            int mid = (int)(((long)low + (long)high)/2);
            if (a.get(mid) <= lastNo) {
                high = mid - 1;
            } else if (mid < a.size() - 1 && a.get(mid + 1) <= lastNo) {
                rotationIndex = mid;
                break;
            } else {
                low = mid + 1;
            }
        }
        return rotationIndex;
    }

    // DO NOT MODIFY THE LIST
    public int search(final List<Integer> a, int b) {
        if (a == null || a.size() == 0)
            return -1;
        int rotationIndex = findRotationIndex(a);
        int low = 0, high = a.size() - 1;
        if (rotationIndex != -1) {
            int rotA = a.get(rotationIndex);
            if (rotA == b)
                return rotationIndex;
            if (rotA < b) {
                return -1;
            }
            int aHigh = a.get(high);
            if (aHigh == b) {
                return high;
            } else if (aHigh > b) {
                low = rotationIndex;
            } else {
                high = rotationIndex;
            }
        }
        while (low <= high) {
            int mid = (int)(((long)low + (long)high)/2);
            int amid = a.get(mid);
            if (amid == b)
                return mid;
            if (amid < b) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        RotatedSortedArraySearch rsas = new RotatedSortedArraySearch();
        List<Integer> list = Arrays.asList(new Integer[]{10, 30, 40, 10, 10, 10});
        Verifier.verifyEquals(rsas.findRotationIndex(list), 2);
        Verifier.verifyEquals(rsas.search(list, 30), 1);
        Verifier.verifyEquals(rsas.search(list, 40), 2);
        list = Arrays.asList(new Integer[]{20, 30, 40, 40, 10});
        Verifier.verifyEquals(rsas.findRotationIndex(list), 3);
        Verifier.verifyEquals(rsas.search(list, 30), 1);
        Verifier.verifyEquals(rsas.search(list, 12), -1);
        Verifier.verifyEquals(rsas.search(list, 10), 4);
        list = Arrays.asList(new Integer[]{20, 30, 10});
        Verifier.verifyEquals(rsas.findRotationIndex(list), 1);
        Verifier.verifyEquals(rsas.search(list, 20), 0);
        Verifier.verifyEquals(rsas.search(list, 30), 1);
        Verifier.verifyEquals(rsas.search(list, 12), -1);
        list = Arrays.asList(new Integer[]{20, 30, 40});
        Verifier.verifyEquals(rsas.findRotationIndex(list), -1);
        Verifier.verifyEquals(rsas.search(list, 30), 1);
        Verifier.verifyEquals(rsas.search(list, 40), 2);
        Verifier.verifyEquals(rsas.search(list, 15), -1);
        list = Arrays.asList(new Integer[]{20, 30, 10, 15});
        Verifier.verifyEquals(rsas.findRotationIndex(list), 1);
        Verifier.verifyEquals(rsas.search(list, 30), 1);
        Verifier.verifyEquals(rsas.search(list, 10), 2);
        Verifier.verifyEquals(rsas.search(list, 20), 0);
        Verifier.verifyEquals(rsas.search(list, 15), 3);
    }

}
