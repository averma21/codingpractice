package Hashing;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringConcatenation {

    static List<Integer> findSubstring(String A, List<String> B) {
        List<Integer> ans = new ArrayList<>();
        if (A == null || B == null || A.length() == 0 || B.size() == 0)
            return ans;
        Map<String, Integer> bCount = new HashMap<>();
        for (String b : B) {
            bCount.computeIfPresent(b, (k,v) -> {return v+1;});
            bCount.putIfAbsent(b, 1);
        }
        int numWords = B.size(), wordSize = B.get(0).length();
        for (int i = 0; i + numWords*wordSize -1 < A.length(); i++) {
            Map<String, Integer> aMap = new HashMap<>();
            int j = 0;
            while (j <= numWords) {
                String word = A.substring(i + j*wordSize, i + j*wordSize + wordSize);
                if (!bCount.containsKey(word)) {
                    break;
                }
                aMap.computeIfPresent(word, (k,v) -> {return v+1;});
                aMap.putIfAbsent(word, 1);
                if (aMap.get(word) > bCount.get(word))
                    break;
                j++;
                if (j == numWords) {
                    ans.add(i);
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(findSubstring("barfoothefoobarman", Creator.getList("foo", "bar")),
                Creator.getList(0,9));
    }
}
