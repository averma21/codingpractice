package bit;

import util.Verifier;

import static java.lang.Math.abs;

public class Division {

    public static int divide(int A, int B) {
        int sign = 1;
        if (A<0){sign = -sign;}
        if (B<0){sign = -sign;}

        long tmp = abs((long)A); // abs(-2147483648) = -2147483648 so convert to long first and then take abs
        long tmp2 = abs((long)B);

        long c = 1;
        while (tmp2 < tmp){
            tmp2 = tmp2<<1;
            c = c<<1;
        }

        long res = 0;
        while (tmp>=abs((long)B)){
            if (tmp2 <= tmp){
                tmp -= tmp2;
                res = res+c;
            }
            tmp2=tmp2>>1;
            c=c>>1;
        }

        return (int)((sign*res >= Integer.MAX_VALUE ||  sign*res < Integer.MIN_VALUE) ? Integer.MAX_VALUE : sign*res);
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
