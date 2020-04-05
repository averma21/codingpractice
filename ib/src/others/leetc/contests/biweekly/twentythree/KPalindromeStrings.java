package others.leetc.contests.biweekly.twentythree;

import java.util.HashMap;
import java.util.Map;

public class KPalindromeStrings {

    /**
     * Explanation
     * Condition 1. odd characters <= k
     * Count the occurrences of all characters.
     * If one character has odd times occurrences,
     * there must be at least one palindrome,
     * with odd length and this character in the middle.
     * So we count the characters that appear odd times,
     * the number of odd character should not be bigger than k.
     *
     * Condition 2. k <= s.length()
     * Also, if we have one character in each palindrome,
     * we will have at most s.length() palindromes,
     * so we need k <= s.length().
     *
     * The above two condition are necessary and sufficient conditions for this problem.
     * So we return odd <= k <= n
     *
     *
     * Complexity
     * Time O(N)
     * Space O(1)
     *
     * https://leetcode.com/contest/biweekly-contest-23/problems/construct-k-palindrome-strings/
     */
    public boolean canConstruct(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.putIfAbsent(c, 0);
            map.computeIfPresent(c, (k1,v) -> v+1);
        }
        int oddCount = (int)map.values().stream().filter(c -> c%2==1).count();
        return oddCount <= k && k <= s.length();
    }

    //https://leetcode.com/problems/construct-k-palindrome-strings/discuss/563379/JavaC%2B%2BPython-Straight-Forward
    public boolean copiedSolution(String s, int k) {
        int odd = 0, n = s.length(), count[] = new int[26];
        for (int i = 0; i < n; ++i) {
            count[s.charAt(i) - 'a'] ^= 1;
            odd += count[s.charAt(i) - 'a'] > 0 ? 1 : -1;
        }
        return odd <= k && k <= n;
    }

}
