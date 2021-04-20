package binarysearch.round2;

import util.Verifier;

public class PowerFunction {

    int pow(int x, int n, int d) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        int ans;
        if (n == 1) {
            ans = x%d;
        }
        if (n%2 == 0) {
            int p = pow(x, n/2, d)%d;
            ans = (int)(((long)p * p)%d);
        } else {
            int p = pow(x, (n-1)/2, d)%d;
            ans = (int)((((long)p * p)%d * x%d)%d);
        }
        if (ans < 0) {
            ans += d;
        }
        return ans;

    }

    public static void main(String[] args) {
        PowerFunction pf = new PowerFunction();
        Verifier.verifyEquals(pf.pow(71045970,41535484, 64735492), 20805472);
    }

}
