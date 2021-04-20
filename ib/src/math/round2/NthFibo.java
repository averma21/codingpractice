package math.round2;

import util.Verifier;

public class NthFibo {

    static int find(int A) {
        int mod = (int)1e9 + 7;
        if (A == 1) {
            return 1;
        }
        if (A == 2) {
            return 1;
        }
        long x = 1, y = 1;
        long sum = 0;
        for (int i = 3; i <= A; i++) {
            sum = x + y;
            sum %= mod;
            x = y;
            y = sum;
        }
        return (int)sum;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(find(1), 1);
        Verifier.verifyEquals(find(2), 1);
        Verifier.verifyEquals(find(3), 2);
        Verifier.verifyEquals(find(4), 3);
    }

}
