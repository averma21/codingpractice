package DP.round2;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class UniqueBinarySearchTreesII {

    private Map<Integer, Integer> cache;

    private int numTreesInternal(int start, int end) {
        if (start >= end) {
            return 1;
        }
        if (cache.containsKey(end-start)) {
            return cache.get(end-start);
        }
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += numTreesInternal(start, i-1) * numTreesInternal(i+1, end);
        }
        cache.put(end - start, sum);
        return sum;
    }

    public int numTrees(int A) {
        if (A == 1) {
            return 1;
        }
        cache = new HashMap<>();
        return numTreesInternal(1, A);
    }

    public static void main(String[] args) {
        UniqueBinarySearchTreesII ubst2 = new UniqueBinarySearchTreesII();
        Verifier.verifyEquals(ubst2.numTrees(3), 5);
    }

}
