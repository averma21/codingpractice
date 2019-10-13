package graphs;

import util.Creator;
import util.Verifier;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadderI {

    private boolean isAdjacent(String A, String B) {
        int diffCount = 0;
        if (A.length() != B.length())
            return false;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) {
                diffCount++;
                if (diffCount > 1)
                    break;
            }
        }
        return diffCount == 1;
    }

    private class StringDist {
        private final String string;
        private final int dist;

        public StringDist(String string, int dist) {
            this.string = string;
            this.dist = dist;
        }
    }

    private int shortestDist(String source, String target, Set<String> dictionary) {
        Queue<StringDist> toVisit = new LinkedList<>();
        toVisit.add(new StringDist(source, 1));
        dictionary.add(target);
        while (!toVisit.isEmpty()) {
            StringDist next = toVisit.poll();
            Set<String> adjacent = new HashSet<>();
            for (String word : dictionary) {
                if (isAdjacent(next.string, word)) {
                    adjacent.add(word);
                    if (word.equals(target))
                        return next.dist + 1;
                }
            }
            for (String ad : adjacent) {
                toVisit.add(new StringDist(ad, next.dist + 1));
                dictionary.remove(ad);
            }
        }
        return 0;
    }

    public int solve(String A, String B, List<String> C) {
        return shortestDist(A, B, new HashSet<>(C));
    }

    public static void main(String[] args) {
        WordLadderI wl = new WordLadderI();
        Verifier.verifyEquals(wl.solve("abc", "bbb", Creator.getList("abb")), 3);
        Verifier.verifyEquals(wl.solve("abc", "bbb", Creator.getList("abd")), 0);
        Verifier.verifyEquals(wl.solve("abcd", "bcbd", Creator.getList("abdd", "acdd", "acbd")), 5);
        Verifier.verifyEquals(wl.solve("abcd", "bcbd", Creator.getList("abdd", "abcd")), 0);
        Verifier.verifyEquals(wl.solve("hit", "cog", Creator.getList("hot", "dot", "dog", "lot", "log")), 5);
    }

}
