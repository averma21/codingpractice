package strings;

import util.Verifier;

import java.math.BigInteger;

public class CompareVersions {

    public static int compareVersion(String A, String B) {
        if (A == null && B == null) {
            return 0;
        }
        if (A == null) {
            return -1;
        }
        if (B == null) {
            return 1;
        }
        String [] aParts = A.split("\\.");
        String [] bParts = B.split("\\.");
        int i = 0, j = 0;
        while (i < aParts.length && j < bParts.length) {
            BigInteger a = new BigInteger(aParts[i]);
            BigInteger b = new BigInteger(bParts[j]);
            int com = a.compareTo(b);
            if (com != 0)
                return com;
            i++;j++;
        }
        while (j < bParts.length) {
            BigInteger b = new BigInteger(bParts[j++]);
            if (b.compareTo(BigInteger.ZERO) != 0)
                return -1;
        }
        while (i < aParts.length) {
            BigInteger a = new BigInteger(aParts[i++]);
            if (a.compareTo(BigInteger.ZERO) != 0)
                return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(compareVersion("1", "1"), 0);
        Verifier.verifyEquals(compareVersion("1.1", "1.1"), 0);
        Verifier.verifyEquals(compareVersion("1.0", "1"), 0);
        Verifier.verifyEquals(compareVersion("2.1", "2"), 1);
        Verifier.verifyEquals(compareVersion("2.2.5", "2.3"), -1);
        Verifier.verifyEquals(compareVersion("4", "2.3"), 1);
    }

}
