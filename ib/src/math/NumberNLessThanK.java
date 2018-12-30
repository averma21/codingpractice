package math;

import util.Verifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberNLessThanK {

    private int factorial(int n) {
        if (n <= 1)
            return 1;
        else return n * factorial(n - 1);
    }

    private int permutation(int n, int r) {
        return factorial(n) / factorial(n - r);
    }

    private int getNumberCountStartingFromDigitGreaterThan(List<Integer> A, int D, int length) {
        int lengthOfA = A.size();
        int DIndex = 0;
        boolean found = false;
        for (int a : A) {
            if (a <= D) {
                DIndex++;
            } else {
                break;
            }
        }
        int numbersGreaterThanD = lengthOfA - DIndex;
        int perm = permutation(lengthOfA - 1, length - 1);
        return perm * numbersGreaterThanD;
    }

    public int solve(List<Integer> A, int B, int C) {
        int lengthOfC = (int)Math.log10(C) + 1;
        int lengthOfA = A.size();
        if (B > lengthOfC || lengthOfA == 0) {
            return 0;
        }
        if (B < lengthOfC) {
            int total = permutation(lengthOfA, B);
            if (A.contains(0) && B > 1) {
                total -= factorial(lengthOfA - 1);
            }
            return total;
        }
        int total = permutation(lengthOfA, B);
        if (A.contains(0) && B > 1) {
            total -= factorial(lengthOfA - 1);
        }
        String s = "" + C;
        A = new ArrayList<>(A);
        for (int i = 0; i < s.length(); i++) {
            int d = Integer.parseInt("" + s.charAt(i));
            total -= getNumberCountStartingFromDigitGreaterThan(A, d, B - i);
            if (A.contains(d))
                A.remove(d);
        }
        return total;
    }

    public static void main(String[] args) {
        NumberNLessThanK numberNLessThanK = new NumberNLessThanK();
//        Verifier.verifyEquals(numberNLessThanK.solve(Arrays.asList(new Integer[] {0, 1, 5}), 1, 2), 2);
//        Verifier.verifyEquals(numberNLessThanK.solve(Arrays.asList(new Integer[] {0}), 1, 5), 1);
        Verifier.verifyEquals(numberNLessThanK.solve(Arrays.asList(new Integer[] {0, 1, 2, 5}), 2, 21), 5);
    }

}
