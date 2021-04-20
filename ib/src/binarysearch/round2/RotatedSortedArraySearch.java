package binarysearch.round2;

import util.Creator;
import util.Verifier;

import java.util.List;

/**
 * for better solution see {@link binarysearch.RotatedSortedArraySearch#search2(List, int)}
 */

public class RotatedSortedArraySearch {

    private int findPivot(List<Integer> A) {
        int n = A.size();
        int last = A.get(n - 1);
        int low = 0, high = n-1;
        while (low <= high) {
            int mid = (low+high)/2;
            int aMid = A.get(mid);
            if (mid < n-1 && A.get(mid + 1) < aMid) {
                return mid;
            } else if (aMid < last) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public int find(List<Integer> A, int B) {
        if (A == null || A.size() == 0) {
            return -1;
        }
        int pivot = findPivot(A);
        int low, high;
        if (pivot != -1) {
            int aPivot = A.get(pivot);
            if (aPivot == B) {
                return pivot;
            }
            if (aPivot < B) {
                return -1;
            }
        }
        int n = A.size();
        if (pivot == -1) {
            low = 0;
            high = n-1;
        } else if (B < A.get(n-1)) {
            low = pivot + 1;
            high = n-1;
        } else {
            high = pivot;
            low = 0;
        }
        while (low <= high) {
            int mid = (low + high)/2;
            int aMid = A.get(mid);
            if (aMid == B) {
                return mid;
            }
            if (aMid < B) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        RotatedSortedArraySearch rsa = new RotatedSortedArraySearch();
//        Verifier.verifyEquals(rsa.findPivot(Creator.getList(1,1,1,1)), -1);
//        Verifier.verifyEquals(rsa.findPivot(Creator.getList(2,2,1,1)), 1);
//        Verifier.verifyEquals(rsa.findPivot(Creator.getList(1,2,3,4)), -1);
        Verifier.verifyEquals(rsa.findPivot(Creator.getList(6,7,8,1,2,3)), 2);
        Verifier.verifyEquals(rsa.find(Creator.getList(1, 7, 67, 133, 178 ), 1), 0);
    }

}
