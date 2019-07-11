package bit;

import util.Verifier;

public class Reverse {

    public static long reverse(long A) {
        long rev = 0;
        for (int i = 31; i >= 0; i--) {
            long bit = A&1;
            rev = rev << 1;
            if (bit == 1) {
                rev += 1;
            }
            A = A>>1;
        }
        return rev;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(reverse(0), 0);
        Verifier.verifyEquals(reverse(3), 3221225472L);
    }

}
