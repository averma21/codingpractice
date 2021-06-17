package bit.round2;

import util.Verifier;

public class Division {

    private static long getQuotientPart(long dividend, long divisor) {
        long quotientPart = 1;
        boolean executed = false;
        while (dividend > divisor) {
            quotientPart = quotientPart << 1;
            divisor = divisor << 1;
            executed = true;
        }
        return !executed ? quotientPart : quotientPart >> 1;
    }

    /**
     * The general approach is to keep on subtracting the divisor from dividend until dividend becomes less than the divisor. The number of times
     * we subtract is the quotient. So this approach has time complexity of O(quotient).
     *
     * dividend = divisor * quotient + remainder
     *
     * We could improve on it by expressing quotient as quotient = 2^A + B. If we find A, we would only need to find B to get our quotient.
     *
     * To find A, we could keep multiplying divisor by 2 (by using left shift 1) till it is less than dividend. The number of times we are able to do
     * that is A.
     *
     * Now to find B, similar technique could be applied. The remaining dividend is dividend-2^A. Divisor is unchanged. So we just break B as 2^C + D
     * and the loop goes on.
     *
     * The quotientPart term used in solution below corresponds to 2^A (and in next loop 2^C and so on...) part.
     *
     * @param A dividend
     * @param B divisor
     * @return quotient
     */
    public static int divide(int A, int B) {
        int sign = 1;
        if (A<0){sign = -sign;}
        if (B<0){sign = -sign;}
        long dividend = Math.abs((long) A);
        long divisor = Math.abs((long) B);
        long quotient = 0;
        while (dividend >= divisor) {
            long quotientPart = getQuotientPart(dividend, divisor);
            dividend -= divisor*quotientPart;
            quotient += quotientPart;
        }
        return (int)((sign*quotient >= Integer.MAX_VALUE ||  sign*quotient < Integer.MIN_VALUE) ? Integer.MAX_VALUE : sign*quotient);
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(divide(4,2), 2);
        Verifier.verifyEquals(divide(5,2), 2);
        Verifier.verifyEquals(divide(5,2), 2);
        Verifier.verifyEquals(divide(-1,1), -1);
        Verifier.verifyEquals(divide(-2147483648, -1), 2147483647);
        Verifier.verifyEquals(divide(-2147483648, 1), -2147483648);
        Verifier.verifyEquals(divide(-2147483648, 2), -2147483648/2);
        Verifier.verifyEquals(divide(-2147483648, 3), -2147483648/3);

    }

}
