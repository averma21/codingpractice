package DP;

import util.Creator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WordBreakII {

    Map<Integer, List<String>> sentences;

    public List<String> wordBreak(String s, Set<String> wordDict, int start) {
        if (start >= s.length())
            return Collections.emptyList();
        if (sentences.containsKey(start)) {
            return sentences.get(start);
        }
        //System.out.println(s + " called for index " + start);
        String word = "";
        sentences.put(start, new ArrayList<>());
        for (int i = start; i < s.length(); i++) {
            word+=s.charAt(i);
            List<String> nextList = wordBreak(s, wordDict, i+1);
            if (wordDict.contains(word) && (i == s.length() -1 || nextList.size() != 0)) {
                String temp = word;
                List<String> sentencesBeginAtStart = nextList.size() != 0 ?
                        nextList.stream().map(sen -> temp + " " + sen).collect(Collectors.toList()) : new ArrayList<String >(){{add(temp);}};
                sentences.computeIfPresent(start, (k,v) -> {v.addAll(sentencesBeginAtStart); return v;});
            }
        }
        return sentences.get(start);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        sentences = new HashMap<>();
        return wordBreak(s, new HashSet<>(wordDict), 0);
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
