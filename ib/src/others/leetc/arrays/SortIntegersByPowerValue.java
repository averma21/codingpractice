package others.leetc.arrays;

import util.Verifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortIntegersByPowerValue {

    Map<Integer, Integer> powerMap;
    int getPow(int n) {
        if (n == 1) {
            return 0;
        }
        if (powerMap.containsKey(n)) {
            return powerMap.get(n);
        }
        int ans;
        if (n % 2 == 0) {
            ans = 1 + getPow(n/2);
        } else {
            ans = 1 + getPow(3*n + 1);
        }
        powerMap.put(n, ans);
        return ans;
    }

    public int getKth(int lo, int hi, int k) {
        this.powerMap = new HashMap<>();
        Integer [] arr = new Integer[hi - lo + 1];
        for (int i = lo; i <= hi; i++) {
            arr[i-lo] = i;
        }
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                int p1 = getPow(t1), p2 = getPow(t2);
                if (p1 < p2) {
                    return -1;
                }
                if (p2 < p1) {
                    return 1;
                }
                return Integer.compare(t1, t2);
            }
        };
        Arrays.sort(arr, comparator);
        return arr[k-1];
    }

    public static void main(String[] args) {
        SortIntegersByPowerValue sibp = new SortIntegersByPowerValue();
        Verifier.verifyEquals(sibp.getKth(12, 15, 2), 13);
        Verifier.verifyEquals(sibp.getKth(1, 1, 1), 1);
        Verifier.verifyEquals(sibp.getKth(7, 11, 4), 7);
        Verifier.verifyEquals(sibp.getKth(10, 20, 5), 13);
        Verifier.verifyEquals(sibp.getKth(1, 1000, 777), 570);
    }

}
