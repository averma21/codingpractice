package others.leetc.thirtydaychallenge;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class CountingElements {

    public static int countElements(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            map.putIfAbsent(a, 0);
            map.computeIfPresent(a, (k,v)->v+1);
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (map.containsKey(entry.getKey() + 1)) {
                count += entry.getValue();
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(countElements(new int[]{1,2,3}), 2);
        Verifier.verifyEquals(countElements(new int[]{1,1,3,3,5,5,7,7}), 0);
        Verifier.verifyEquals(countElements(new int[]{1,3,2,3,5,0}), 3);
        Verifier.verifyEquals(countElements(new int[]{1,1,2,2}), 2);
    }

}
