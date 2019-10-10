package DP;

import util.Verifier;

public class WaysToDecode {

    int [] waysDP;

    private int waysInternal(String s, int i) {
        int size = s.length();
        if (i == size)
            return 1;
        if (waysDP[i] != 0)
            return waysDP[i];
        char si = s.charAt(i);
        if (si == '0')
            return 0;
        if (i < size-1 && s.charAt(i+1) == '0') {
            if (si > '2') {
                return 0;
            }
        }
        int way1 = i == size - 1 || s.charAt(i+1) != '0'? waysInternal(s, i+1) : 0;
        int way2 = 0;
        if (i < size - 1) {
            char siNext = s.charAt(i+1);
            if (Integer.parseInt(si + "" + siNext) <= 26) {
                way2 = waysInternal(s, i+2);
            }
        }
        waysDP[i] = way1 + way2;
        return waysDP[i];
    }

    int ways(String A) {
        if (A.isEmpty()) {
            return 1;
        }
        waysDP = new int[A.length()];
        return waysInternal(A, 0);
    }

    public static void main(String[] args) {
        WaysToDecode wtd = new WaysToDecode();
        Verifier.verifyEquals(wtd.ways(""), 1);
        Verifier.verifyEquals(wtd.ways("0"), 0);
        Verifier.verifyEquals(wtd.ways("1"), 1);
        Verifier.verifyEquals(wtd.ways("12"), 2);
        Verifier.verifyEquals(wtd.ways("26"), 2);
        Verifier.verifyEquals(wtd.ways("27"), 1);
        Verifier.verifyEquals(wtd.ways("121"), 3);
        Verifier.verifyEquals(wtd.ways("1234"), 3);
        Verifier.verifyEquals(wtd.ways("12340"), 0);
        Verifier.verifyEquals(wtd.ways("120"), 1);
        Verifier.verifyEquals(wtd.ways("130"), 0);
        Verifier.verifyEquals(wtd.ways("301"), 0);
        Verifier.verifyEquals(wtd.ways("1301"), 0);
        Verifier.verifyEquals(wtd.ways("12012"), 2);
    }

}
