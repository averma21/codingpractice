package binarysearch;

import util.Verifier;

public class SquareRootOfInteger {

    public int sqrt(int a) {

        if (a <= 1) {
            return a;
        }

        if (a == 2 || a == 3) {
            return 1;
        }

        int start = 0, end = a/2, root = start; long sq = root * root;
        while (start <= end) {
            root = (start + end) /2;
            sq = root * (long)root;
            if (sq == a) {
                return root;
            }
            if (sq < a) {
                start = root + 1;
            } else {
                end = root - 1;
            }
        }
        if (sq > a) {
            root--;
        }
        return root;
    }

    public static void main(String[] args) {
        SquareRootOfInteger squareRootOfInteger = new SquareRootOfInteger();
        Verifier.verifyEquals(squareRootOfInteger.sqrt(0), 0);
        Verifier.verifyEquals(squareRootOfInteger.sqrt(1), 1);
        Verifier.verifyEquals(squareRootOfInteger.sqrt(2), 1);
        Verifier.verifyEquals(squareRootOfInteger.sqrt(3), 1);
        int num = 4;
        while (num < 9) {
            Verifier.verifyEquals(squareRootOfInteger.sqrt(num), 2);
            num++;
        }
        while (num < 16) {
            Verifier.verifyEquals(squareRootOfInteger.sqrt(num), 3);
            num++;
        }
        Verifier.verifyEquals(squareRootOfInteger.sqrt(100), 10);
        Verifier.verifyEquals(squareRootOfInteger.sqrt(1000), 31);
        Verifier.verifyEquals(squareRootOfInteger.sqrt(10000), 100);
        Verifier.verifyEquals(squareRootOfInteger.sqrt(Integer.MAX_VALUE), 46340);
    }

}
