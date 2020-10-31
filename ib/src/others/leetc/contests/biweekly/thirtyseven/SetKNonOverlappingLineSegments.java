package others.leetc.contests.biweekly.thirtyseven;

import util.Verifier;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SetKNonOverlappingLineSegments {

    private static int MOD = (int)1.0E9 + 7;

    BigInteger fact(int n) {
        BigInteger ans = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            ans = ans.multiply(new BigInteger("" + i));
        }
        return ans;
    }

//    private static class Fraction {
//        long n, d;
//
//        public Fraction() {
//            this.n = 1;
//            this.d = 1;
//            gcdMap = new HashMap<>();
//        }
//
//        private Map<Long, Map<Long, Long>> gcdMap;
//
//        private long getGCD(long x, long y) {
//            boolean isN = x < 0 || y < 0;
//            if (isN)
//            System.out.println("Calculating GCD for " + x + "," + y);
//            long existing = gcdMap.getOrDefault(x, Collections.emptyMap()).getOrDefault(y, -1L);
//            if (isN)
//            System.out.println("Not found");
//            if (existing != -1) {
//                return existing;
//            }
//            if (y < x) {
//                long temp = x;
//                x = y;
//                y = temp;
//            }
//            if (x == 0)
//                return y;
//            return getGCD(y%x , x);
//        }
//
//        void multiply(int n1, int d1) {
//            long n2 = n*n1;
//            long d2 = d*d1;
//            long gcd = getGCD(n2, d2);
//            System.out.printf("GCD for %s %s=%s\n", n2, d2, gcd );
//            n = n2/gcd;
//            d = d2/gcd;
//            if (d == 1) {
//                n = n%MOD;
//            }
//        }
//    }

//    int nCr(int n, int r) {
//        int min = Math.min(r, n-r);
//        Fraction fraction = new Fraction();
//
//        for (int i = min; i >= 1; i--) {
//            int numerator = n - (min-i);
//            fraction.multiply(numerator, i);
//        }
//        return (int)(fraction.n/fraction.d);
//    }

    int nCr(int n, int r) {
        int min = Math.min(n-r, r);
        BigInteger nr = fact(min);
        BigInteger mul = BigInteger.ONE;
        for (int i = 0; i < min; i++) {
            mul = mul.multiply(new BigInteger("" + (n-i)));
        }
        mul = mul.divide(nr);
        mul = mul.mod(new BigInteger("" + MOD));
        return mul.intValue();
    }

    public int numberOfSets(int n, int k) {
        return nCr(n+k-1, 2*k);
    }

    public static void main(String[] args) {
        SetKNonOverlappingLineSegments ss = new SetKNonOverlappingLineSegments();
        Verifier.verifyEquals(ss.numberOfSets(4,2), 5);
        Verifier.verifyEquals(ss.numberOfSets(3,1), 3);
        Verifier.verifyEquals(ss.numberOfSets(30,7), 796297179);
        Verifier.verifyEquals(ss.numberOfSets(5,3), 7);
        Verifier.verifyEquals(ss.numberOfSets(3,2), 1);
        Verifier.verifyEquals(ss.numberOfSets(52,23), 963678472);

    }

}
