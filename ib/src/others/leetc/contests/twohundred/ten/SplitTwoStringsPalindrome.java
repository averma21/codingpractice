package others.leetc.contests.twohundred.ten;

import util.Verifier;

public class SplitTwoStringsPalindrome {

    private boolean isPalindrome(String s, int start, int end) {
        //System.out.println("Checking string " + s.substring(start, end +1 ));
        for (int i = start, j = end; i < j; i++ , j--) {
            if (s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }

    public boolean checkPalindrome(String a, String b) {
        for (int i = 0, j = a.length() - 1; i < j; ++i, --j)
            if (a.charAt(i) != b.charAt(j))
                return isPalindrome(a, i, j) || isPalindrome(b, i, j);
        return true;
    }


    public boolean checkPalindromeFormation(String a, String b) {
        return checkPalindrome(a, b) || checkPalindrome(b, a);
    }

    public static void main(String[] args) {
        SplitTwoStringsPalindrome stsp = new SplitTwoStringsPalindrome();
        Verifier.verifyEquals(stsp.checkPalindromeFormation("x", "y"), true);
        Verifier.verifyEquals(stsp.checkPalindromeFormation("abdef", "fecab"), true);
        Verifier.verifyEquals(stsp.checkPalindromeFormation("ulacfd", "jizalu"), true);
        Verifier.verifyEquals(stsp.checkPalindromeFormation("xbdef", "xecab"), false);
        Verifier.verifyEquals(stsp.checkPalindromeFormation("aejbaalflrmkswrydwdkdwdyrwskmrlfqizjezd", "uvebspqckawkhbrtlqwblfwzfptanhiglaabjea"), true);
    }
}
