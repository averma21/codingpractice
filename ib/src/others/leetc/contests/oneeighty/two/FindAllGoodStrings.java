package others.leetc.contests.oneeighty.two;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FindAllGoodStrings {

    private String s1;
    private String s2;
    private String evil;
    private int [] computedArr;
    private int mod;
    private int n;
    private Map<CacheKey, Integer> solutionsMap;

    static void getKMPComputedArr(String pattern, int[] computed) {
        int i = 1, j = 0;
        computed[0] = 0;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                computed[i] = j;
                i++;
            } else {
                if (j != 0) {
                    j = computed[j - 1];

                } else {
                    computed[i] = j;
                    i++;
                }
            }
        }
    }

    private static class CacheKey {
        int index;
        boolean isPrefixOfS1, isPrefixOfS2;
        int evilPrefixMatchLength;

        public CacheKey(int index, boolean isPrefixOfS1, boolean isPrefixOfS2, int evilPrefixMatchLength) {
            this.index = index;
            this.isPrefixOfS1 = isPrefixOfS1;
            this.isPrefixOfS2 = isPrefixOfS2;
            this.evilPrefixMatchLength = evilPrefixMatchLength;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CacheKey cacheKey = (CacheKey) o;
            return index == cacheKey.index &&
                    isPrefixOfS1 == cacheKey.isPrefixOfS1 &&
                    isPrefixOfS2 == cacheKey.isPrefixOfS2 &&
                    evilPrefixMatchLength == cacheKey.evilPrefixMatchLength;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, isPrefixOfS1, isPrefixOfS2, evilPrefixMatchLength);
        }
    }

    /**
     * We try to build target string character by character. We need to get the count of valid strings which begin with
     * a special prefix but if we use the prefix as a parameter, the DP space will be too big. So we just extract three
     * params from the prefix: if it's prefix of s1 or s2 or evil string.
     * @param index index of character in target string we are building
     * @param evilPrefixMatchLength length of common prefix between current position and evil string.
     * @param isPrefixOfS1 if the prefix of current position is a prefix of s1.
     * @param isPrefixOfS2 if the prefix of current position is a prefix of s2.
     * @return count of valid strings starting from passed index
     */
    private int count(int index, int evilPrefixMatchLength, boolean isPrefixOfS1, boolean isPrefixOfS2) {
        if (evilPrefixMatchLength == evil.length())
            return 0;
        if (index == n)
            return 1;
        CacheKey cacheKey = new CacheKey(index, isPrefixOfS1, isPrefixOfS2, evilPrefixMatchLength);
        if (solutionsMap.containsKey(cacheKey))
            return solutionsMap.get(cacheKey);
        int total = 0;
        int first = isPrefixOfS1 ? s1.charAt(index) : 'a';
        int last = isPrefixOfS2 ? s2.charAt(index) : 'z';

        for (int ci  = first; ci < last + 1; ci++) {
            char c = (char)ci;
            boolean p1 = isPrefixOfS1 && ci ==first;
            boolean p2 = isPrefixOfS2 && ci ==last;
            int matchLength = evilPrefixMatchLength;
            while (matchLength > 0 && c != evil.charAt(matchLength)) {
                matchLength = computedArr[matchLength - 1];
            }
            if (c == evil.charAt(matchLength)) {
                matchLength += 1;
            }

            total += count(index + 1, matchLength, p1, p2);
            total %= mod;
        }
        solutionsMap.put(cacheKey, total);
        return total;
    }


    int findGoodStrings(int n, String s1, String s2, String evil) {
        mod = (int)(1E9 + 7);
        this.s1 = s1;
        this.s2 = s2;
        this.evil = evil;
        computedArr = new int[evil.length()];
        getKMPComputedArr(evil, computedArr);
        this.n = n;
        solutionsMap = new HashMap<>();
        return count(0, 0,true, true);
    }

    public static void main(String[] args) {
        FindAllGoodStrings fags = new FindAllGoodStrings();
        Verifier.verifyEquals(fags.findGoodStrings(2, "aa", "da", "b"), 51);
        Verifier.verifyEquals(fags.findGoodStrings(8, "leetcode", "leetgoes", "leet"), 0);
        Verifier.verifyEquals(fags.findGoodStrings(2, "gx", "gz", "x"), 2);
    }

}
