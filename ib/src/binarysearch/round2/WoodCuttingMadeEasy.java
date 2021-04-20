package binarysearch.round2;

import util.Creator;
import util.Verifier;

import java.util.Collections;
import java.util.List;

public class WoodCuttingMadeEasy {

    private int getWood(List<Integer> A, int cutAt, int B) {
        int collection = 0;
        for (int i = A.size() - 1; i >= 0; i--) {
            int a = A.get(i);
            if (a <= cutAt || collection >= B) {
                break;
            }
            collection += a - cutAt;
        }
        return collection;
    }

    public int cut(List<Integer> A, int B) {
        if (A == null || A.size() == 0) {
            return -1;
        }
        Collections.sort(A);
        int n = A.size();
        int low = 0, high = A.get(n - 1);
        int ans = 0;
        while (low <= high) {
            int mid = (low + high)/2;
            int collection = getWood(A, mid, B);
            if (collection < B) {
                high = mid-1;
            } else {
                ans = mid;
                low = mid+1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        WoodCuttingMadeEasy wcm = new WoodCuttingMadeEasy();
        Verifier.verifyEquals(wcm.cut(Creator.getList(20, 15, 10, 17), 7), 15);
        Verifier.verifyEquals(wcm.cut(Creator.getList(4, 42, 40, 26, 46), 20), 36);
    }

}
