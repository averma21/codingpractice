package Hashing.round2;

import util.Creator;
import util.Verifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static Hashing.MaxPointsInSameLine.getList;

public class MaxPointsInSameLine {

    private int gcd(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        if (a < 0) return gcd(a*-1, b);
        if (b < 0) return gcd(a, b*-1);
        if (a < b) return gcd(b,a);
        return gcd(b, a%b);
    }

    private class Fraction {
        int numr, deno;

        public Fraction(int numr, int deno) {
            int gcd = gcd(numr, deno);
            this.numr = gcd != 0 ? numr/gcd : numr;
            this.deno = gcd != 0 ? deno/gcd : deno;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Fraction fraction = (Fraction) o;
            return numr == fraction.numr &&
                    deno == fraction.deno;
        }

        @Override
        public int hashCode() {
            return Objects.hash(numr, deno);
        }
    }

    public int maxPoints(List<Integer> a, List<Integer> b) {
        int max = Integer.MIN_VALUE;
        if (a == null || b == null || a.isEmpty() || b.isEmpty()) {
            return 0;
        }
        int n = a.size();
        for (int i = 0; i < n; i++) {
            Map<Fraction, Integer> slopeMap = new HashMap<>();
            int x1 = a.get(i), y1 = b.get(i);
            for (int j = 0; j < n; j++) {
                int x2 = a.get(j), y2 = b.get(j);
                if (i == j)
                    continue;
                Fraction slope = new Fraction(y2-y1, x2-x1);
                slopeMap.putIfAbsent(slope, 0);
                slopeMap.computeIfPresent(slope, (k,v)->v+1);
            }
            for (Map.Entry<Fraction, Integer> entry : slopeMap.entrySet()) {
                if (entry.getValue() > max) {
                    max = entry.getValue();
                }
            }
        }
        return max < 0 ? 0 : max + 1;
    }

    public static void main(String[] args) {
        MaxPointsInSameLine mp = new MaxPointsInSameLine();
        Verifier.verifyEquals(mp.maxPoints(Creator.getList(-6, 5, -18, 2, 5, -2),
                Creator.getList(-17, -16, -17, -4, -13, 20)), 2);
        String s= "15 12 9 10 -16 3 -15 15 11 -10 -5 20 -3 -15 -11 -8 -8 -3 3 6 15 -14 -16 -18 -6 -8 14 9 -1 -7 -1 -2 3 11 6 20 10 -7 0 14 19 -18 -10 -15 -17 -1 8 7 20 -18 -4 -9 -9 16 10 14 -14 -15 -2 -10 -18 9 7 -5 -12 11 -17 -6 5 -17 -2 -20 15 -2 -5 -16 1 -20 19 -12 -14 -1 18 10 1 -20 -15 19 -18 13 13 -3 -16 -17 1 0 20 -18 7 19 1 -6 -7 -11 7 1 -15 12 -1 7 -3 -13 -11 2 -17 -5 -12 -14 15 -3 15 -11 7 3 19 7 -15 19 10 -14 -14 5 0 -1 -12 -4 4 18 7 -3 -5 -3 1 -11 1 -1 2 16 6 -6 -17 9 14 3 -13 8 -9 14 -5 -1 -18 -17 9 -10 19 19 16 7 3 7 -18 -12 -11 12 -15 20 -3 4 -18 1 13 17 -16 -15 -9 -9 15 8 19 -9 9 -17";

        Verifier.verifyEquals(mp.maxPoints(getList(s, 0), getList(s, 1)), 6);
        s = "0 0 1 1 -1 -1";
        Verifier.verifyEquals(mp.maxPoints(getList(s, 0), getList(s, 1)), 3);
        s = "1 1 2 2 3 3";
        Verifier.verifyEquals(mp.maxPoints(getList(s, 0), getList(s, 1)), 3);
        s = "1 1 3 2 5 3 4 1 2 3 1 4";
        Verifier.verifyEquals(mp.maxPoints(getList(s, 0), getList(s, 1)), 4);
        s= "1 1 1 1 1 1 1 1 1 1";
        Verifier.verifyEquals(mp.maxPoints(getList(s, 0), getList(s, 1)), 5);
    }

}
