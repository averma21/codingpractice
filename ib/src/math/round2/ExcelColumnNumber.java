package math.round2;

import util.Verifier;

public class ExcelColumnNumber {

    public int titleToNumber(String A) {
        if (A == null || A.length() == 0) {
            return 0;
        }
        int mul = 1;
        int ans = 0;
        for (int i = A.length() - 1; i >= 0; i--) {
            char a = A.charAt(i);
            int prod = (a - 'A' + 1)*mul;
            ans += prod;
            mul *= 26;
        }
        return ans;
    }

    public static void main(String[] args) {
        ExcelColumnNumber excelColumnNumber = new ExcelColumnNumber();
        Verifier.verifyEquals(excelColumnNumber.titleToNumber("AB"), 28);
        Verifier.verifyEquals(excelColumnNumber.titleToNumber("C"), 3);
        Verifier.verifyEquals(excelColumnNumber.titleToNumber("BA"), 53);
        Verifier.verifyEquals(excelColumnNumber.titleToNumber("AAA"), 703);
    }

}
