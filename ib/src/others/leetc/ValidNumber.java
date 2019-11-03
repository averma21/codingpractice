package others.leetc;

import util.Verifier;

// https://leetcode.com/problems/valid-number/
public class ValidNumber {

    private boolean isNumberWithoutDecimal(String s, int start, int end) {
        if (end <= start || start >= s.length())
            return false;
        if (s.charAt(start) == '-' || s.charAt(start) == '+')
            start++;
        if (start == end)
            return false;
        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            if ('0' > c || c > '9') {
                return false;
            }
        }
        return true;
    }

    private boolean isNumberWithOrWithoutDecimal(String s, int start, int end) {
        if (end <= start || start >= s.length())
            return false;
        if (s.charAt(start) == '-' || s.charAt(start) == '+')
            start++;
        if (start == end)
            return false;
        int dotCount = 0;
        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            if (c == '.')
                dotCount++;
            if (dotCount > 1 || (('0' > c || c > '9') && c != '.')) {
                return false;
            }
        }
        return s.charAt(end - 1) != '.';
    }

    public boolean isNumber(String s) {
        s = s.trim();
        int firstEnd = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'e') {
                firstEnd = i;
                break;
            }
        }
        if (firstEnd != s.length()) {
            return isNumberWithOrWithoutDecimal(s, 0, firstEnd) && isNumberWithoutDecimal(s,firstEnd + 1, s.length());
        }
        return isNumberWithOrWithoutDecimal(s, 0, firstEnd);
    }

    public static void main(String[] args) {
        ValidNumber vn = new ValidNumber();
        Verifier.verifyEquals(vn.isNumber("0"), true);
        Verifier.verifyEquals(vn.isNumber("0.1"), true);
        Verifier.verifyEquals(vn.isNumber("abc"), false);
        Verifier.verifyEquals(vn.isNumber("1 a"), false);
        Verifier.verifyEquals(vn.isNumber("2e10"), true);
        Verifier.verifyEquals(vn.isNumber("."), false);
        Verifier.verifyEquals(vn.isNumber("1 "), true);
    }

}
