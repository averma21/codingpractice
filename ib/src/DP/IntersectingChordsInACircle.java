package DP;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class IntersectingChordsInACircle {

    private Map<Integer, Integer> countMap;
    private int mod = (int)Math.pow(10,9) + 7;

    private int countInternal(int n) {
        if (n == 0)
            return 1;
        if (n == 2)
            return 1;
        if (countMap.containsKey(n))
            return countMap.get(n);
        long count = 0;
        for (int j = 2; j <= n; j+=2) {
            // assume (i,j) is the chord. It divides circle into two parts
            int pointCountInRightCircle = n - j;
            int pointCountInLeftCircle = n - pointCountInRightCircle - 2;
            long prod = (countInternal(pointCountInLeftCircle) * (long)countInternal(pointCountInRightCircle))%mod;
            count += prod;
            count %= mod;
        }
        countMap.put(n, (int)count);
        return (int)count;
    }

    private int count(int n) {
        countMap = new HashMap<>();
        return countInternal(2*n);
    }

    public static void main(String[] args) {
        IntersectingChordsInACircle icic = new IntersectingChordsInACircle();
        Verifier.verifyEquals(icic.count(1), 1);
        Verifier.verifyEquals(icic.count(2), 2);
        Verifier.verifyEquals(icic.count(4), 14);
    }
}
