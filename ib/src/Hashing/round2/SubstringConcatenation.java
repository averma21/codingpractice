package Hashing.round2;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringConcatenation {

    static boolean isEqual(Map<Character, Integer> inputMap, Map<Character, Integer> countMap) {
        if (inputMap.size() != countMap.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            if (!inputMap.getOrDefault(entry.getKey(), 0).equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    static List<Integer> findSubstring(String A, List<String> B) {
        if (A == null || A.isEmpty() || B == null || B.isEmpty()) {
            return new ArrayList<>();
        }
        Map<String, Integer> bCount = new HashMap<>();
        Map<Character, Integer> charCounts = new HashMap<>();
        B.stream().forEach(b-> {
            bCount.putIfAbsent(b, 0);
            bCount.computeIfPresent(b, (k,v) -> v+1);
            for (int i = 0; i < b.length(); i++) {
                char c = b.charAt(i);
                charCounts.putIfAbsent(c, 0);
                charCounts.computeIfPresent(c, (k,v) -> v+1);
            }
        });
        int curStart = -1;
        Map<Character, Integer> curMap = new HashMap<>();
        int stepSize = B.get(0).length();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < A.length(); i++) {
            char c = A.charAt(i);
            if (!charCounts.containsKey(c)) {
                curStart = -1;
                curMap.clear();
                continue;
            }
            if (curStart == -1) {
                curStart = i;
            }
            curMap.compute(c, (k,v) -> v == null ? 1 : v+1);
            if (curMap.get(c) > charCounts.get(c)) {
                while (A.charAt(curStart) != c) {
                    curMap.computeIfPresent(A.charAt(curStart), (k,v) -> v-1);
                    curStart++;
                }
                curMap.computeIfPresent(A.charAt(curStart), (k,v) -> v-1);
                curStart++;
            }
            if (isEqual(curMap, charCounts)) {
                Map<String, Integer> temp = new HashMap<>(bCount);
                StringBuilder substrings = new StringBuilder();
                boolean match = true;
                for (int i1 = curStart; i1 <= i; i1++) {
                    substrings.append(A.charAt(i1));
                    if (substrings.length() == stepSize) {
                        String s = substrings.toString();
                        if (!temp.containsKey(s)) {
                            match = false;
                            break;
                        }
                        temp.computeIfPresent(s, (k,v) -> v-1);
                        if (temp.get(s) == 0) {
                            temp.remove(s);
                        }
                        substrings.setLength(0);
                    }
                }
                if (match && temp.isEmpty()) {
                    ans.add(curStart);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        Verifier.verifyEquals(findSubstring("barfoothefoobarman", Creator.getList("foo", "bar")),
//                Creator.getList(0,9));
        Verifier.verifyEquals(findSubstring("aaaaaaaaaaaa", Creator.getList("aaa", "aaa")),
                Creator.getList(0,1,2,3,4,5,6));
    }

}
