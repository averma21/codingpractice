package DP.round2;

import util.Creator;

import java.util.*;

public class WordBreakII {

    private Map<Integer, List<String>> cache;
    private Set<String> dict;

    private List<String> breakInternal(String A, int index) {
        if (cache.containsKey(index)) {
            return cache.get(index);
        }
        StringBuilder sb = new StringBuilder();
        List<String> ans = new ArrayList<>();
        for (int i = index; i < A.length(); i++) {
            sb.append(A.charAt(i));
            if (dict.contains(sb.toString())) {
                List<String> result = breakInternal(A, i+1);
                if (!result.isEmpty()) {
                    for (String breaking : result) {
                        ans.add(sb.toString() + " " + breaking);
                    }
                } else if (i == A.length() - 1) {
                    ans.add(sb.toString());
                }

            }
        }
        cache.put(index, ans);
        return ans;
    }

    public List<String> wordBreak(String A, List<String> B) {
        cache = new HashMap<>();
        dict = new HashSet<>(B);
        return breakInternal(A, 0);
    }

    public static void main(String[] args) {
        WordBreakII breaker = new WordBreakII();
        System.out.println(breaker.wordBreak("catsanddog", Creator.getList("cat", "cats", "and", "sand", "dog")));
        System.out.println(breaker.wordBreak("pineapplepenapple", Creator.getList("apple", "pen", "applepen", "pine", "pineapple")));
        System.out.println(breaker.wordBreak("a", Creator.getList("a")));
        System.out.println(breaker.wordBreak("aa", Creator.getList("a", "aa")));
        System.out.println(breaker.wordBreak("aba", Creator.getList("a", "aa")));
    }
}
