package strings.round2;

import util.Verifier;

import java.util.stream.IntStream;

public class MultiplyStrings {

    private StringBuilder add(StringBuilder A, StringBuilder B) {
        int diff = Math.abs(A.length() - B.length());
        StringBuilder zeros = new StringBuilder();
        for (int i = 0; i < diff; i++) {
            zeros.append("0");
        }
        if (A.length() <  B.length()) {
            A = zeros.append(A);
        } else {
            B = zeros.append(B);
        }
        int m = A.length();
        int carry = 0;
        StringBuilder sum = new StringBuilder();
        for (int i = m - 1; i >= 0; i--) {
            int c1 = Character.getNumericValue(A.charAt(i));
            int c2 = Character.getNumericValue(B.charAt(i));
            int res = c1+c2+carry;
            sum.append(res % 10);
            carry = res/10;
        }
        if (carry == 1) {
            sum.append(carry);
        }
        return sum.reverse();
    }

    public String multiply(String A, String B) {
        StringBuilder ans = new StringBuilder("0");
        int zerosToAppend = 0;
        for (int i = B.length() - 1; i >= 0; i--) {
            int b = Character.getNumericValue(B.charAt(i));
            int carry = 0;
            StringBuilder product = new StringBuilder();
            for (int j = A.length() - 1; j >= 0; j--) {
                int a = Character.getNumericValue(A.charAt(j));
                int res = a*b + carry;
                product.append(res % 10);
                carry = res/10;
            }
            if (carry != 0) {
                product.append(carry);
            }
            product = product.reverse();
            for (int z = 0; z < zerosToAppend; z++) {
                product.append("0");
            }
            ans = add(ans, product);
            zerosToAppend++;
        }
        int allZerosLastIndex = -1;
        for (int i = 0; i < ans.length(); i++) {
            if (ans.charAt(i) == '0') {
                allZerosLastIndex = i;
            } else {
                break;
            }
        }
        if (allZerosLastIndex != -1) {
            ans.replace(0, allZerosLastIndex + 1, "");
        }
        if (ans.length() == 0) {
            ans.append("0");
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        MultiplyStrings ms = new MultiplyStrings();
        Verifier.verifyEquals(ms.multiply("200", "0"), "0");
        Verifier.verifyEquals(ms.multiply("2", "3"), "6");
        Verifier.verifyEquals(ms.multiply("21", "3"), "63");
        Verifier.verifyEquals(ms.multiply("99", "9"), "891");
        Verifier.verifyEquals(ms.multiply("99", "99"), "9801");
        Verifier.verifyEquals(ms.multiply("99999", "99999"), "9999800001");
    }

}
