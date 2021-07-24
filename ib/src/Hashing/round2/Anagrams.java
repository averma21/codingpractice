package Hashing.round2;

import util.Creator;
import util.Verifier;

import java.util.*;

public class Anagrams {

    private boolean checkMaps(Map<Character, Integer> m1, Map<Character, Integer> m2) {
        if (m1.size() != m2.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> entry : m1.entrySet()) {
            if (!m2.containsKey(entry.getKey()) || !m2.get(entry.getKey()).equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public List<List<Integer>> anagrams(final List<String> A) {
        List<Map<Character, Integer>> maps = new ArrayList<>();

        for (String a : A) {
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < a.length(); i++) {
                map.compute(a.charAt(i), (k,v) -> v == null ? 1 : v + 1);
            }
            maps.add(map);
        }
        Set<Integer> done = new HashSet<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < maps.size(); i++) {
            if (!done.contains(i)) {
                done.add(i);
                List<Integer> list = new ArrayList<>();
                list.add(i+1);
                for (int j = i+1; j < maps.size(); j++) {
                    if (!done.contains(j) && checkMaps(maps.get(i), maps.get(j))) {
                        done.add(j);
                        list.add(j+1);
                    }
                }
                ans.add(list);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Anagrams anagrams = new Anagrams();
        Verifier.verifyEquals(anagrams.anagrams(Creator.getList("cat", "dog", "god", "tca")),
                Creator.getList(
                        Creator.getList(1,4),
                        Creator.getList(2,3)
                ));
        Verifier.verifyEquals(anagrams.anagrams(Creator.getList("aaa", "aaa", "aaa")),
                Creator.getList(
                        Creator.getList(1,2,3)
                ));
    }

}
