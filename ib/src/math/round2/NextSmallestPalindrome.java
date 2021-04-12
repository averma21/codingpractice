package math.round2;

import util.Verifier;

public class NextSmallestPalindrome {

    String next(String A) {
        if (A == null || A.length() == 0) {
            return A;
        }
        String left, right;
        char middle;
        int n = A.length();
        if (n == 1) {
            int ans = Integer.parseInt(A) + 1;
            if (ans == 10) {
                ans = 11;
            }
            return "" + ans;
        }
        if (n % 2 == 0) {
            left = A.substring(0, n/2);
            right = A.substring(n/2, n);
            middle = ' ';
        } else {
            left = A.substring(0, n/2);
            right = A.substring(n/2 + 1, n);
            middle = A.charAt(n/2);
        }
        boolean copy = false, allEqual = true;
        for (int l = left.length() - 1, r = 0; l >= 0 && r < right.length(); l--, r++) {
            char ri = right.charAt(r);
            char li = left.charAt(l);
            if (ri < li) {
                copy = true;
                break;
            }
            if (li < ri) {
                break;
            }
        }
        if (copy) {
            return (left + middle).trim() + reverse(left);
        }
        String toBeIncremented = (left + middle).trim();
        String incrementedVal = increment(toBeIncremented);
        String rightAns = incrementedVal.length() != toBeIncremented.length() ?
                reverse(incrementedVal.substring(0, n%2 == 0 ? incrementedVal.length() - 1 : incrementedVal.length() - 2)) :
                reverse(incrementedVal.substring(0, n%2 == 0 ? incrementedVal.length() : incrementedVal.length() - 1));
        return incrementedVal + rightAns;
    }

    private String increment(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        int carry = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '9' && carry == 1) {
                c = '0';
                carry = 1;
            } else {
                c = (char) ((int) c + carry);
                carry = 0;
            }
            stringBuilder.append(c);
        }
        if (carry == 1) {
            stringBuilder.append(carry);
        }
        return stringBuilder.reverse().toString();
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        NextSmallestPalindrome nsp = new NextSmallestPalindrome();
        Verifier.verifyEquals(nsp.next("0"), "1");
        Verifier.verifyEquals(nsp.next("6"), "7");
        Verifier.verifyEquals(nsp.next("9"), "11");
        Verifier.verifyEquals(nsp.next("71"), "77");
        Verifier.verifyEquals(nsp.next("89"), "99");
        Verifier.verifyEquals(nsp.next("91"), "99");
        Verifier.verifyEquals(nsp.next("99"), "101");
        Verifier.verifyEquals(nsp.next("999"), "1001");
        Verifier.verifyEquals(nsp.next("9999"), "10001");
        Verifier.verifyEquals(nsp.next("23545"), "23632");
        Verifier.verifyEquals(nsp.next("25689"), "25752");
        Verifier.verifyEquals(nsp.next("7892"), "7997");
        Verifier.verifyEquals(nsp.next("25912"), "25952");
        Verifier.verifyEquals(nsp.next("546686631"), "546686645");
        Verifier.verifyEquals(nsp.next("546686661"), "546696645");
        Verifier.verifyEquals(nsp.next("546696661"), "546707645");
    }

}
