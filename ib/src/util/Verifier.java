package util;

import others.leetc.trees.TreeNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Verifier {

    private static class IntArray {
        int [] arr;

        public IntArray(int [] arr) {
            this.arr = arr;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IntArray intArray = (IntArray) o;
            return Arrays.equals(arr, intArray.arr);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(arr);
        }
    }

    public static void verifyEquals(int a, int b) {
        if (a != b)
            throw new RuntimeException("Unequal " + a + " " + b);
    }

    public static void verifyEquals(Boolean a, Boolean b) {
        if (a != null && !a.equals(b) || b != null && !b.equals(a))
            throw new RuntimeException("Unequal " + a + " " + b);
    }


    public static void verifyEquals(double a, double b) {
        if (Double.compare(a,b) != 0)
            throw new RuntimeException("Unequal " + a + " " + b);
    }

    public static void verifyEquals(Object a, Object b) {
        if ( (a == null && b!= null) || (a != null && b == null) || (a!= null && !a.equals(b))) {
            throw new RuntimeException("Unequal " + a + " " + b);
        }
    }

    public static void verifyEquals(String a, String b) {
        if (a==null && b!= null || a!= null && b == null || (a != null && !a.equals(b)))
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

    public static void verifyEqualsUnordered(List<Integer> A, List<Integer> B) {
        Collections.sort(A);
        Collections.sort(B);
        verifyEquals(A, B);
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

    public static void verifyArrayEquals(int [][] A, int [][] B) {
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

    public static void verifyArrayEqualsUnordered(int [][] A, int [][] B) {
        if (A.length != B.length)
            throw new RuntimeException("Unequal lengths");
        Map<IntArray, Integer> arrayFreqMapA = new HashMap<>();
        Map<IntArray, Integer> arrayFreqMapB = new HashMap<>();
        for (int [] arr1 : A) {
            IntArray ob = new IntArray(arr1);
            arrayFreqMapA.putIfAbsent(ob, 0);
            arrayFreqMapA.computeIfPresent(ob, (k,v) -> v+1);
        }
        for (int [] arr1 : B) {
            IntArray ob = new IntArray(arr1);
            arrayFreqMapB.putIfAbsent(ob, 0);
            arrayFreqMapB.computeIfPresent(ob, (k,v) -> v+1);
        }
        if (arrayFreqMapA.keySet().size() != arrayFreqMapB.keySet().size()) {
            throw new RuntimeException("Unequal key set => unequal arrays");
        }
        for (Map.Entry<IntArray, Integer> entry : arrayFreqMapA.entrySet()) {
            Integer c = arrayFreqMapB.get(entry.getKey());
            if (c == null || c.intValue() != entry.getValue().intValue()) {
                throw new RuntimeException("unequal arrays");
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

    public static void verifyEquals(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return;
        }
        if (t1 == null || t2 == null) {
            throw new RuntimeException("Un equal nodes. One is null");
        }
        if (t1.val != t2.val) {
            throw new RuntimeException("Un equal nodes. t1=" + t1.val + ", t2=" + t2.val);
        }
        verifyEquals(t1.left, t2.left);
        verifyEquals(t1.right, t2.right);
    }

    public static void verifyEqualsTrees(List<TreeNode> l1, List<TreeNode> l2) {
        if (l1 == null && l2 == null) {
            return;
        }
        if (l1 == null || l2 == null) {
            throw new RuntimeException("Un equal nodes. One is null");
        }
        if (l1.size() != l2.size()) {
            throw new RuntimeException("Size unequal");
        }
        for (int i = 0; i < l1.size(); i++) {
            verifyEquals(l1.get(i), l2.get(i));
        }
    }

}
