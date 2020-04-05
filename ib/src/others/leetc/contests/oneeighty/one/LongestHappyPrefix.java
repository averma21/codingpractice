package others.leetc.contests.oneeighty.one;

import util.Verifier;

public class LongestHappyPrefix {

    private int [] getComputedArrayKMP(String s) {
        int j = 0, i = 1;
        int [] computed = new int[s.length()];
        computed[0] = 0;
        int n = s.length();
        while (j < n && i < n) {
            if (s.charAt(i) == s.charAt(j)) {
                j++;
                computed[i] = j;
                i++;
            } else {
                if (j != 0) {
                    j = computed[j-1];
                } else {
                    computed[i] = 0;
                    i++;
                }

            }
        }
        return computed;
    }

    public String longestPrefix(String s) {
        if (s == null || s.length() == 0)
            return s;
        int [] com = getComputedArrayKMP(s);
        if (com[com.length - 1] == 0)
            return "";
        return s.substring(0, com[com.length - 1]);
    }

    public static void main(String[] args) {
        LongestHappyPrefix lhp = new LongestHappyPrefix();
        Verifier.verifyEquals(lhp.longestPrefix("level"), "l");
        Verifier.verifyEquals(lhp.longestPrefix("ababab"), "abab");
        Verifier.verifyEquals(lhp.longestPrefix("leetcodeleet"), "leet");
        Verifier.verifyEquals(lhp.longestPrefix("a"), "");
        Verifier.verifyEquals(lhp.longestPrefix("ababd"), "");
    }

}
