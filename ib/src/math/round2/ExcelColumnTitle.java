package math.round2;

import util.Verifier;

public class ExcelColumnTitle {

    private static String convertToTitle(int A) {
        StringBuilder sb = new StringBuilder();
        while (A > 26) {
            int mod = A%26;
            sb.append(mod == 0 ? 'Z' : (char)('A' + (mod - 1)));
            if (mod == 0) {
                A -= 26;
            }
            A/=26;
        }
        sb.append(A == 0 ? 'Z' : (char)('A' + (A - 1)));
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(convertToTitle(1), "A");
        Verifier.verifyEquals(convertToTitle(52), "AZ");
        Verifier.verifyEquals(convertToTitle(943566), "BAQTZ");
        Verifier.verifyEquals(convertToTitle(26), "Z");
        Verifier.verifyEquals(convertToTitle(27), "AA");
        Verifier.verifyEquals(convertToTitle(53), "BA");
        Verifier.verifyEquals(convertToTitle(703), "AAA");
    }

}
