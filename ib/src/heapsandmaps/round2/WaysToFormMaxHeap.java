package heapsandmaps.round2;

import util.Verifier;

import java.math.BigInteger;

public class WaysToFormMaxHeap {

    private static final BigInteger MODB = new BigInteger("1000000007");

    private static boolean willFormFullHeap(int n) {
        int i = 0, prod = 1;
        while (prod <= n + 1) {
            if (n == prod - 1) {
                return true;
            }
            prod = prod * 2;
        }
        return false;
    }

    private static int getLastLevel(int n) {
        if (n <= 2) {
            return n;
        }
        int prevPower = 2, curPower = 4;
        while (n >= curPower) {
            if (curPower - 1 >= n && prevPower < n) {
                return curPower;
            }
            prevPower = curPower;
            curPower *= 2;
        }
        return (int)(Math.log(curPower)/Math.log(2));
    }

    static int getLeftCount(int n) {
        int left = (int) Math.ceil(n/2.0);
        int right = n - left;
        while (!willFormFullHeap(left) && (getLastLevel(left) <= getLastLevel(right))) {
            left++;
            right--;
        }
        return left;
    }

    private static BigInteger combination(int n, int r) {
        int D1 = Math.max(r, n - r);
        BigInteger D2 = new BigInteger("" + (n - D1));
        BigInteger num = new BigInteger("1");
        BigInteger bign = new BigInteger("" + n);
        while (n >= D1 + 1) {
            num = num.multiply(bign);
            bign = bign.subtract(BigInteger.ONE);
            n--;
        }
        while (D2.compareTo(BigInteger.ONE) > 0) {
            num = num.divide(D2);
            D2 = D2.subtract(BigInteger.ONE);
        }
        return num;
    }

    public static BigInteger solveInternal(int A) {
        if (A <= 2) {
            return BigInteger.ONE;
        }
        int leftCount = getLeftCount(A-1);
        int rightCount = A - 1 - leftCount;
        BigInteger waysToChooseRightNodes = combination(A-1, rightCount);
        return waysToChooseRightNodes.multiply(solveInternal(leftCount)).multiply(solveInternal(rightCount)).mod(MODB);
    }

    public static int solve(int a) {
        return solveInternal(a).intValue();
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(getLastLevel(0), 0);
        Verifier.verifyEquals(getLastLevel(1), 1);
        Verifier.verifyEquals(getLastLevel(2), 2);
        Verifier.verifyEquals(getLastLevel(3), 2);
        Verifier.verifyEquals(getLastLevel(4), 3);
        Verifier.verifyEquals(getLastLevel(6), 3);
        Verifier.verifyEquals(getLastLevel(15), 4);
        Verifier.verifyEquals(combination(1,1).intValue(), 1);
        Verifier.verifyEquals(combination(2,1).intValue(), 2);
        Verifier.verifyEquals(combination(3,2).intValue(), 3);
        Verifier.verifyEquals(combination(3,0).intValue(), 1);
        Verifier.verifyEquals(combination(10,3).intValue(), 120);
        Verifier.verifyEquals(solve(3), 2);
        Verifier.verifyEquals(solve(4), 3);
        Verifier.verifyEquals(solve(5), 8);
        Verifier.verifyEquals(solve(6), 20);
        Verifier.verifyEquals(solve(7), 80);
        Verifier.verifyEquals(solve(8), 210);
        Verifier.verifyEquals(solve(20), 258365767);
    }

}
