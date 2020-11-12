package others.leetc.trie;

import util.Verifier;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/count-substrings-that-differ-by-one-character/
public class CountSubstringsThatDifferByOneCharII {

    public int countSubstrings(String s, String t) {
        int m = s.length(), n = t.length();
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int diff = 0;
                int i1 = i, j1 = j;
                while (diff <= 1 && i1 < m && j1 < n) {
                    char a = s.charAt(i1);
                    char b = t.charAt(j1);
                    if (a != b) {
                        diff++;
                    }
                    if (diff == 1) {
                        ans++;
                    }
                    i1++;
                    j1++;
                }

            }
        }
        return ans;
    }

    public static void main(String[] args) {
        CountSubstringsThatDifferByOneCharII cs = new CountSubstringsThatDifferByOneCharII();
        Verifier.verifyEquals(cs.countSubstrings("aba", "baba"), 6);
        Verifier.verifyEquals(cs.countSubstrings("ab", "bb"), 3);
        Verifier.verifyEquals(cs.countSubstrings("a", "a"), 0);
        Verifier.verifyEquals(cs.countSubstrings("abe", "bbc"), 10);
        Verifier.verifyEquals(cs.countSubstrings("computer", "computation"), 90);
    }

}
