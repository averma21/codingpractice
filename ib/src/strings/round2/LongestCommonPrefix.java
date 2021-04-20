package strings.round2;

import util.Creator;
import util.Verifier;

import java.util.List;

public class LongestCommonPrefix {

    String prefix(List<String> A) {
        if (A == null || A.size() == 0)
            return "";
        if (A.size() == 1) {
            return A.get(0);
        }
        int pos = 0;
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (pos == A.get(0).length()) {
                break;
            }
            char prev = A.get(0).charAt(pos);
            boolean diff = false;
            for (int i = 1; i < A.size(); i++) {
                if (pos == A.get(i).length()) {
                    diff = true;
                    break;
                }
                char c = A.get(i).charAt(pos);
                if (c != prev) {
                    diff = true;
                    break;
                }
            }
            if (!diff) {
                sb.append(prev);
            } else {
                break;
            }
            pos++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LongestCommonPrefix lcp = new LongestCommonPrefix();
        Verifier.verifyEquals(lcp.prefix(Creator.getList("abc")), "abc");
        Verifier.verifyEquals(lcp.prefix(Creator.getList("abcdefgh", "abcefgh")), "abc");
        Verifier.verifyEquals(lcp.prefix(Creator.getList("abcdefgh", "abcefgh", "ab")), "ab");
        Verifier.verifyEquals(lcp.prefix(Creator.getList("abcdefgh", "abcefgh", "ab", "a")), "a");
        Verifier.verifyEquals(lcp.prefix(Creator.getList("abcdefgh", "bbcefgh", "cb")), "");
        Verifier.verifyEquals(lcp.prefix(Creator.getList("aaa", "aaaaa")), "aaa");
    }

}
