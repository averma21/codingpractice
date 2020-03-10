package others.leetc.dp;

import util.Verifier;

//https://leetcode.com/problems/wildcard-matching/
public class WildcardMatching {

    public static boolean isMatchRecur(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        char p1 = p.charAt(0);
        switch (p1) {
            case '?' : return s.length() > 0 && isMatch(s.substring(1), p.substring(1));
            case '*' : return isMatch(s, p.substring(1)) || (s.length() > 0 && isMatch(s.substring(1), p));
            default: return s.length() > 0 && s.charAt(0) == p1 && isMatch(s.substring(1), p.substring(1));
        }
    }

    public static boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean [][] m = new boolean[p.length() + 1][s.length() + 1];
        m[p.length()][s.length()] = true;
        for (int j = 0; j < s.length(); j++)
            m[p.length()][j] = false;
        for (int i = p.length() - 1; i >= 0; i--) {
            if (p.charAt(i) != '*') {
                break; // breaking as other entries in last column will be false by default
            }
            m[i][s.length()] = true;
        }
        for (int i = p.length() - 1; i >= 0; i--) {
            for (int j = s.length() - 1; j >= 0; j--) {
                switch (p.charAt(i)) {
                    case '*': m[i][j] = m[i+1][j] || m[i][j+1];
                    break;
                    case '?': m[i][j] = m[i+1][j+1];
                    break;
                    default:m[i][j] = p.charAt(i) == s.charAt(j) && m[i+1][j+1];
                }
            }
        }
        return m[0][0];
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(isMatch("a", "*"), true);
        Verifier.verifyEquals(isMatch("aa", "*"), true);
        Verifier.verifyEquals(isMatch("cb", "?b"), true);
        Verifier.verifyEquals(isMatch("cb", "?a"), false);
        Verifier.verifyEquals(isMatch("adceb", "a*b"), true);
        Verifier.verifyEquals(isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba","a*******b"), false);
        Verifier.verifyEquals(isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba","a*******a"), true);
    }

}
