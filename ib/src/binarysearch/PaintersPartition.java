package binarysearch;

import util.Verifier;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaintersPartition {

    private boolean isPossible(BigInteger mid, int timePerUnit, int painterCount, List<Integer> logLengths) {
        BigInteger remainingTime = mid;
        int i = 0;
        BigInteger bigTime = new BigInteger("" + timePerUnit);
        while (i < logLengths.size()) {
            BigInteger logLength = new BigInteger("" + logLengths.get(i));
            BigInteger time = logLength.multiply(bigTime);
            if (remainingTime.compareTo(time) >= 0) {
                remainingTime = remainingTime.subtract(time);
                i++;
            } else {
                painterCount--;
                remainingTime = mid;
                if (painterCount == 0) {
                    return false;
                }
            }
        }
        return i == logLengths.size();
    }

    public int paint(int A, int B, List<Integer> C) {
        int MOD = 10000003;
        if (C == null || C.size() == 0 || B == 0) {
            return 0;
        }
        if (C.size() <= A) {
            return (int)(((long)C.stream().max(Integer::compareTo).get() * B)%MOD) ;
        }
        if (A <= 0) {
            return MOD - 1; //infinity
        }
        BigInteger streamSum = C.stream().map(x -> new BigInteger("" + x)).reduce(BigInteger.ZERO, BigInteger::add);
        BigInteger maxTime = streamSum.multiply(new BigInteger("" + B));
        BigInteger start = BigInteger.ZERO;
        BigInteger end = maxTime;
        BigInteger TWO = new BigInteger("2");
        while (start.compareTo(end) <= 0) {
            BigInteger mid = (start.add(end)).divide(TWO);
            if (isPossible(mid, B, A, C))
                end = mid.subtract(BigInteger.ONE);
            else
                start = mid.add(BigInteger.ONE);
        }
        return start.mod(new BigInteger("" + MOD)).intValue();
    }

    public static void main(String[] args) {
        PaintersPartition pp = new PaintersPartition();
        Verifier.verifyEquals(pp.paint(0, 0, null), 0);
        Verifier.verifyEquals(pp.paint(0, 0, new ArrayList<>()), 0);
        Verifier.verifyEquals(pp.paint(3, 5, Arrays.asList(new Integer[]{1, 5})), 25);
        Verifier.verifyEquals(pp.paint(2, 5, Arrays.asList(new Integer[]{1, 10})), 50);
        Verifier.verifyEquals(pp.paint(1, 5, Arrays.asList(new Integer[]{1, 10})), 55);
        Verifier.verifyEquals(pp.paint(3, 5, Arrays.asList(new Integer[]{3, 4, 5})), 25);
        Verifier.verifyEquals(pp.paint(2, 5, Arrays.asList(new Integer[]{3, 4, 5})), 35);
        Verifier.verifyEquals(pp.paint(2, 5, Arrays.asList(new Integer[]{5, 4, 3})), 35);
        Verifier.verifyEquals(pp.paint(2, 5, Arrays.asList(new Integer[]{4, 5, 3})), 40);
        Verifier.verifyEquals(pp.paint(1, 1000000, Arrays.asList(new Integer[]{1000000, 1000000})), 9400003);
    }

}
