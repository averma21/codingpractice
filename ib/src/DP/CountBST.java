package DP;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class CountBST {

    Map<Integer, Integer> soln = new HashMap<>();

    private int countInternal(int n) {
        if (n < 0)
            return 0;
        if (n == 0)
            return 1;
        if (n == 1 || n == 2)
            return n;
        if (soln.containsKey(n))
            return soln.get(n);
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int leftCount = countInternal(i-1);
            int rightCount = countInternal(n - i);
            sum += leftCount*rightCount;
        }
        soln.put(n, sum);
        return sum;
    }

    private int count(int n) {
        if (n <= 0)
            return 0;
        soln = new HashMap<>();
        return countInternal(n);
    }

    public static void main(String[] args) {
        CountBST cb = new CountBST();
        Verifier.verifyEquals(cb.count(1), 1);
        Verifier.verifyEquals(cb.count(2), 2);
        Verifier.verifyEquals(cb.count(3), 5);
        Verifier.verifyEquals(cb.count(4), 14);
        Verifier.verifyEquals(cb.count(18), 204);
    }

}
