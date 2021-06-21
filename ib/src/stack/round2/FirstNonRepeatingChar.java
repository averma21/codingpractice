package stack.round2;

import util.Verifier;

import java.util.*;

public class FirstNonRepeatingChar {

    String find(String A) {
        if (A == null || A.length() <= 1) {
            return A;
        }
        Set<Character> allElementsSet = new HashSet<>();
        Queue<Character> uniqElementsQueue = new LinkedList<>();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < A.length(); i++) {
            char ai = A.charAt(i);
            if (!allElementsSet.contains(ai)) {
                uniqElementsQueue.add(ai);
                allElementsSet.add(ai);
            } else {
                uniqElementsQueue.remove(ai);
            }
            ans.append(uniqElementsQueue.isEmpty() ? "#" : uniqElementsQueue.iterator().next());
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        FirstNonRepeatingChar fnrc = new FirstNonRepeatingChar();
        Verifier.verifyEquals(fnrc.find("a"), "a");
        Verifier.verifyEquals(fnrc.find("aa"), "a#");
        Verifier.verifyEquals(fnrc.find("aaat"), "a##t");
        Verifier.verifyEquals(fnrc.find("abadbc"), "aabbdd");
        Verifier.verifyEquals(fnrc.find("abadbc"), "aabbdd");
        Verifier.verifyEquals(fnrc.find("abcabc"), "aaabc#");
        Verifier.verifyEquals(fnrc.find("abbcad"), "aaaacc");
        Verifier.verifyEquals(fnrc.find("jpxvxivxkkthvpqhhhjuzhkegnzqriokhsgea"), "jjjjjjjjjjjjjjjjjjiiiiiiiiiiitttttttt");
    }
}
