package backtracking.round2;

import util.Verifier;

import java.util.LinkedList;

public class KthPermutation {

    int getFact(int n, int max) {
        int fact = 1;
        while (n > 1 && fact < max) {
            fact *= n;
            n--;
        }
        return fact;
    }

    String getPermutation(int A, int B) {
        LinkedList<Integer> digits = new LinkedList<>();
        for (int i = 1; i <= A; i++) {
            digits.add(i);
        }
        StringBuilder ans = new StringBuilder();
        while (B > 1) {
            int possibilities = getFact(digits.size() - 1, B);
            int factor = (int)(Math.ceil(B*1.0/possibilities));
            ans.append(digits.remove(factor-1));
            B -= possibilities*(factor-1);
        }
        digits.forEach(ans::append);
        return ans.toString();
    }

    public static void main(String[] args) {
        KthPermutation kp = new KthPermutation();
        Verifier.verifyEquals(kp.getPermutation(3,3), "213");
        Verifier.verifyEquals(kp.getPermutation(3,4), "231");
    }

}
