package util;

import java.util.List;

public class Verifier {

    public static void verifyEquals(int a, int b) {
        if (a != b)
            throw new RuntimeException("Unequal" + a + " " + b);
    }

    public static void verifyEquals(double a, double b) {
        if (a != b)
            throw new RuntimeException("Unequal" + a + " " + b);
    }

    public static void verifyEquals(List<Integer> A, List<Integer> B) {
        if (A.size() != B.size())
            throw new RuntimeException("Unequal" + A + " " + B);
        for (int i = 0; i < A.size(); i++) {
            verifyEquals(A.get(i), B.get(i));
        }
    }

}
