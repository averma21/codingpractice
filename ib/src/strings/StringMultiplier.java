package strings;

import util.Verifier;

import java.util.ArrayList;
import java.util.List;

public class StringMultiplier {

    private static String addZerosAtBegin(String s, int num) {
        StringBuilder sb = new StringBuilder("");
        while (num > 0) {
            sb.append("0");
            num--;
        }
        return sb.toString() + s;
    }


    private static String add(String A, String B) {
        StringBuilder s = new StringBuilder("");
        if (A.length() == 0)
            return B;
        if (B.length() == 0)
            return A;
        int aSize = A.length(), bSize = B.length();
        if (aSize < bSize) {
            A = addZerosAtBegin(A, bSize - aSize);
        } else if (aSize > bSize) {
            B = addZerosAtBegin(B, aSize - bSize);
        }
        int carry = 0;
        for (int b = B.length() - 1; b >= 0; b--) {
            int aNum = Character.getNumericValue(A.charAt(b));
            int bNum = Character.getNumericValue(B.charAt(b));
            int sum = aNum + bNum + carry;
            s.append(sum%10);
            carry = sum/10;
        }
        if (carry != 0)
            s.append(carry);
        return s.reverse().toString();
    }

    public static String multiply(String A, String B) {
        List<String> muls = new ArrayList<>();
        for (int b = B.length() - 1; b >= 0; b--) {
            StringBuilder s = new StringBuilder("");
            int carry = 0;
            int bNum = Character.getNumericValue(B.charAt(b));
            for (int a = A.length() - 1; a>=0; a--) {
                int aNum = Character.getNumericValue(A.charAt(a));
                int res = aNum * bNum + carry;
                s.append(res%10);
                carry = res/10;
            }
            if (carry != 0)
                s.append(carry);
            muls.add(s.reverse().toString());
        }
        String s = "";
        int shift = 0;
        for (int i = 0; i < muls.size(); i++) {
            String zeros = "";
            for (int j = 0; j < i; j++) {
                zeros += "0";
            }
            s = add(s, muls.get(i) + zeros);
        }
        s = s.startsWith("0") ? s.replaceFirst("0+", "") : s;
        return s.length() != 0 ? s : "0";
    }

    public static void main(String[] args) {
//        Verifier.verifyEquals(multiply("2", "2"), "4");
//        Verifier.verifyEquals(multiply("21", "2"), "42");
//        Verifier.verifyEquals(multiply("21", "21"), "441");
//        Verifier.verifyEquals(multiply("2", "021"), "42");
//        Verifier.verifyEquals(multiply("0", "21"), "0");
//        Verifier.verifyEquals(multiply("9", "9"), "81");
        Verifier.verifyEquals(multiply("99", "99"), "9801");
        Verifier.verifyEquals(multiply("99999", "99999"), "9999800001");
    }
}
