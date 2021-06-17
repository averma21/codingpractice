package strings.round2;

import util.Verifier;

public class MinCharPalindrome {

    public static int [] getFailureArray(String s) {
        int n = s.length();
        int [] arr = new int[n];
        arr[0] = 0;
        for (int i = 1; i < n; i++) {
            int j = arr[i - 1];
            while (j > 0 && s.charAt(j) != s.charAt(i)) {
                j = arr[j-1];
            }
            if (s.charAt(j) == s.charAt(i)) {
                arr[i] = j + 1;
            } else {
                arr[i] = 0;
            }

        }
        return arr;
    }

    int check(String A) {
        int [] arr = getFailureArray(A + "$" + new StringBuilder(A).reverse().toString());
        return A.length() - arr[arr.length - 1];
    }

    public static void main(String[] args) {
        MinCharPalindrome mcp = new MinCharPalindrome();
        Verifier.verifyEquals(mcp.check("a"), 0);
        Verifier.verifyEquals(mcp.check("aaa"), 0);
        Verifier.verifyEquals(mcp.check("ab"), 1);
        Verifier.verifyEquals(mcp.check("abaa"), 1);
        Verifier.verifyEquals(mcp.check("abcd"), 3);
        Verifier.verifyEquals(mcp.check("abba"), 0);
        Verifier.verifyEquals(mcp.check("hqghumeaylnlfdxfi"), 16);
        Verifier.verifyEquals(mcp.check("eylfpbnpljvrvipyamyehwqnq"), 24);
    }

}
