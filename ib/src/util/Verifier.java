package util;

import java.util.Arrays;
import java.util.List;

public class Verifier {

    public static void verifyEquals(int a, int b) {
        if (a != b)
            throw new RuntimeException("Unequal " + a + " " + b);
    }

    public static void verifyEquals(double a, double b) {
        if (a != b)
            throw new RuntimeException("Unequal " + a + " " + b);
    }

    public static void verifyEquals(String a, String b) {
        if (a==null && b!= null || a!= null && b == null || !a.equals(b))
            throw new RuntimeException("Unequal " + a + " " + b);
    }

    public static void verifyEquals(List<Integer> A, List<Integer> B) {
        if (A == null && B == null)
            return;
        if (A == null || B == null) {
            throw new RuntimeException("Either A or b is null");
        }
        if (A.size() != B.size())
            throw new RuntimeException("Unequal " + A + " " + B);
        for (int i = 0; i < A.size(); i++) {
            verifyEquals(A.get(i), B.get(i));
        }
    }

    public static void verifyEquals(int [] A, int [] B) {
        if (A.length != B.length)
            throw new RuntimeException("Unequal lengths");
        for (int i = 0; i < A.length; i++) {
            try {
                verifyEquals(A[i], B[i]);
            } catch (RuntimeException e) {
                System.out.println("Unequal element at index " + i);
                throw e;
            }
        }
    }

    public static void verifyEquals(ListNode A, ListNode B) {
        while (A != null && B != null){
            if (A.val == B.val) {
                A = A.next;
                B = B.next;
            } else {
                throw new RuntimeException("Unequal " + A.val + ", " + B.val);
            }
        }
        if (A != null || B != null)
            throw new RuntimeException("Unequal lengths");
    }

}
