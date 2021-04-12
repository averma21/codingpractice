package math.round2;

import util.Verifier;

public class GCD {

    int gcd(int A, int B) {
        if (A == 0) {
            return B;
        }
        if (B == 0) {
            return A;
        }
        if (A < B) {
            return gcd(B, A);
        }
        return gcd(A%B, B);
    }

    public static void main(String[] args) {
        GCD gcd = new GCD();
        Verifier.verifyEquals(gcd.gcd(10, 2), 2);
        Verifier.verifyEquals(gcd.gcd(15, 20), 5);
        Verifier.verifyEquals(gcd.gcd(7, 15), 1);
        Verifier.verifyEquals(gcd.gcd(17, 15), 1);
        Verifier.verifyEquals(gcd.gcd(3, 15), 3);
        Verifier.verifyEquals(gcd.gcd(3, 0), 3);
        Verifier.verifyEquals(gcd.gcd(0, 3), 3);
    }

}
