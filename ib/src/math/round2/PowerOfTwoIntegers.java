package math.round2;

import util.Verifier;

public class PowerOfTwoIntegers {

    boolean check(int A) {
        if (A == 1) {
            return true;
        }
        for (int i = 2; i * i <= A; i++) {
            int num = A;
            while (num % i == 0 && num > 1) {
                num /= i;
            }
            if (num == 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        PowerOfTwoIntegers pt = new PowerOfTwoIntegers();
        Verifier.verifyEquals(pt.check(1), true);
        Verifier.verifyEquals(pt.check(2), false);
        Verifier.verifyEquals(pt.check(4), true);
        Verifier.verifyEquals(pt.check(8), true);
        Verifier.verifyEquals(pt.check(9), true);
        Verifier.verifyEquals(pt.check(16), true);
        Verifier.verifyEquals(pt.check(20), false);
        Verifier.verifyEquals(pt.check(81), true);
        Verifier.verifyEquals(pt.check(216), true);
        Verifier.verifyEquals(pt.check(9765625), true);
        Verifier.verifyEquals(pt.check(31), false);
        Verifier.verifyEquals(pt.check(288), false);
    }

}
