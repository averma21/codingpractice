package others.leetc.contests.oneeighty.two;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class LuckyInteger {

    public static int findLucky(int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();
        int lucky = -1;
        for (int a : arr) {
            count.putIfAbsent(a, 0);
            count.computeIfPresent(a, (k,v) -> v+1);
        }
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getKey().equals(entry.getValue())) {
                lucky = Math.max(lucky, entry.getKey());
            }
        }
        return lucky;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(findLucky(new int[]{2,2,3,4}), 2);
        Verifier.verifyEquals(findLucky(new int[]{1,2,2,3,3,3}), 3);
        Verifier.verifyEquals(findLucky(new int[]{2,2,2,3,3}), -1);
        Verifier.verifyEquals(findLucky(new int[]{5}), -1);
        Verifier.verifyEquals(findLucky(new int[]{3,5,2,5,2,5,5,2,5,2,3,3}), 5);
    }

}
