package others.leetc.contests.biweekly.sixty.five;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class AlmostEquivalent {

    private Map<Character, Integer> getFreq(String word) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int freq = map.getOrDefault(c, 0);
            map.put(c, freq + 1);
        }
        return map;
    }

    public boolean checkAlmostEquivalent(String word1, String word2) {
        Map<Character, Integer> map1 = getFreq(word1);
        Map<Character, Integer> map2 = getFreq(word2);
        for (char c = 'a'; c <= 'z'; c++) {
            int f1 =map1.getOrDefault(c, 0);
            int f2 = map2.getOrDefault(c, 0);
            if (Math.abs(f1- f2) > 3) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        AlmostEquivalent am = new AlmostEquivalent();
        Verifier.verifyEquals(am.checkAlmostEquivalent("aaaa", "bccb"), false);
        Verifier.verifyEquals(am.checkAlmostEquivalent("abcdeef", "abaaacc"), true);
        Verifier.verifyEquals(am.checkAlmostEquivalent("cccddabba", "babababab"), true);
        Verifier.verifyEquals(am.checkAlmostEquivalent("aaaa", "bccb"), false);
    }

}
