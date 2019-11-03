package util;

public class GCD {

    private int getGCD(int x, int y) {
        if (y < x) {
            int temp = x;
            x = y;
            y = temp;
        }
        if (x == 0)
            return y;
        return getGCD(y%x , x);
    }

    public static void main(String[] args) {
        GCD gcd = new GCD();
        Verifier.verifyEquals(gcd.getGCD(1,3), 1);
        Verifier.verifyEquals(gcd.getGCD(3,0), 3);
        Verifier.verifyEquals(gcd.getGCD(10,15), 5);
        Verifier.verifyEquals(gcd.getGCD(30,15), 15);
    }
}
