package graphs;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadderI {

    String end;
    List<String> dict;
    Map<String, List<String>> neighbours;

    private boolean isConnected(String s1, String s2) {
        short diffCount = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCount++;
            }
            if (diffCount == 2) {
                return false;
            }
        }
        return true;
    }

    private void fillNeighbours() {
        for (int i = 0; i < dict.size(); i++) {
            String s1 = dict.get(i);
            neighbours.putIfAbsent(s1, new ArrayList<>());
            for (int j = i+1; j < dict.size(); j++) {
                String s2 = dict.get(j);
                neighbours.putIfAbsent(s2, new ArrayList<>());
                if (isConnected(s1, s2)) {
                    neighbours.computeIfPresent(s1, (k,v) -> {v.add(s2); return v;});
                    neighbours.computeIfPresent(s2, (k,v) -> {v.add(s1); return v;});
                }
            }
        }
    }

    private int bfs(String cur) {
        if (cur.equals(end)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(cur);
        queue.add(null);
        int pathSize = 1;
        Set<String> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            String s = queue.poll();
            if (s == null) {
                if (queue.size() > 0) {
                    queue.add(null);
                    pathSize++;
                } else {
                    break;
                }
            } else {
                if (s.equals(end)) {
                    return pathSize;
                }
                for (String n : neighbours.get(s)) {
                    if (!visited.contains(n)) {
                        queue.add(n);
                    }
                }
                visited.add(s);
            }
        }
        return 0;
    }

    public int solve(String A, String B, List<String> C) {
        if (A == null || B == null || A.equals(B)) {
            return 0;
        }
        end = B;
        dict = new ArrayList<>();
        dict.addAll(C);
        dict.add(A);
        dict.add(B);
        neighbours = new HashMap<>();
        fillNeighbours();
        return bfs(A);
    }

    public static void main(String[] args) {
        WordLadderI wl1 = new WordLadderI();
        Verifier.verifyEquals(wl1.solve("abc", "bbb", Creator.getList("abb")), 3);
        Verifier.verifyEquals(wl1.solve("abc", "bbb", Creator.getList("abd")), 0);
        Verifier.verifyEquals(wl1.solve("abcd", "bcbd", Creator.getList("abdd", "acdd", "acbd")), 5);
        Verifier.verifyEquals(wl1.solve("abcd", "bcbd", Creator.getList("abdd", "abcd")), 0);
        Verifier.verifyEquals(wl1.solve("hit", "cog", Creator.getList("hot", "dot", "dog", "lot", "log")), 5);
    }

}
