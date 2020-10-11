package others.leetc.thirtydaychallenge;

import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

    private String sort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.putIfAbsent(c, 0);
            map.computeIfPresent(c, (k,v) -> v+1);
        }
        StringBuilder s1 = new StringBuilder();
        for (char c = 'a'; c <= 'z'; c++) {
            int count = map.getOrDefault(c, 0);
            for (int i = 0; i < count; i++) {
                s1.append(c);
            }
        }
        return s1.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null)
            return Collections.emptyList();
        List<String> sorted = new ArrayList<>(strs.length);
        for (String s : strs) {
            sorted.add(sort(s));
        }
        Map<String, List<Integer>> countMap = new HashMap<>();
        for (int i = 0; i < sorted.size(); i++) {
            String s = sorted.get(i);
            countMap.putIfAbsent(s, new ArrayList<>());
            int j = i;
            countMap.computeIfPresent(s, (k,v)->{v.add(j); return v;});
        }
        List<List<String>> ans = new ArrayList<>();
        for (List<Integer> indexes : countMap.values()) {
            List<String> anagrams = new ArrayList<>();
            for (int index : indexes) {
                anagrams.add(strs[index]);
            }
            ans.add(anagrams);
        }
        return ans;
    }

    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();
        System.out.println(ga.groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

}
