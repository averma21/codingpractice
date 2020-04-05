package others.leetc.contests.oneseventynine;

import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;

// https://leetcode.com/contest/weekly-contest-179/problems/frog-position-after-t-seconds/
public class FrogPosition {

    Map<Integer, List<Integer>> tree;

    private static class Info {
        int node;
        double probability;

        public Info(int node, double probability) {
            this.node = node;
            this.probability = probability;
        }
    }

    static <T> Predicate<T> not(Predicate<T> p) { return o -> !p.test(o); }

    private double getCostByBfs(int target, int maxTime) {
        Queue<Info> queue = new LinkedList<>();
        Info boundary = new Info(-1, -1);
        queue.add(new Info(1, 1));
        queue.add(boundary);
        int timeTaken = 0;
        Set<Integer> visited = new HashSet<>();
        visited.add(1);
        while (!queue.isEmpty()) {
            Info pop = queue.poll();
            List<Integer> children = tree.getOrDefault(pop.node, Collections.emptyList());
            long nonVisitedCount = children.stream().filter(not(visited::contains)).count();
            if (pop.node == target) {
                // if it arrived at the expected time, return probability
                if (timeTaken == maxTime)
                    return pop.probability;
                // if it arrived earlier but there is no node to go, it will jump endlessly on same node, return true
                if (timeTaken < maxTime) {
                    if (nonVisitedCount == 0)
                        return pop.probability;
                }
                // if it arrived later, or arrived earlier but there are unvisited nodes, return 0 as it will never come
                // back to this node in future as this is tree (no cycles)
                return 0;
            }
            if (pop == boundary) {
                timeTaken++;
                if (!queue.isEmpty())
                    queue.add(boundary);
            }
            for (int child : children) {
                if (!visited.contains(child)) {
                    queue.add(new Info(child, pop.probability / nonVisitedCount));
                    visited.add(child);
                }
            }
        }
        return 0;
    }

    public double frogPosition(int n, int[][] edges, int t, int target) {
        tree = new HashMap<>();
        for (int [] edge : edges) {
            int n1 = edge[0], n2 = edge[1];
            tree.putIfAbsent(n1, new ArrayList<>());
            tree.putIfAbsent(n2, new ArrayList<>());
            tree.computeIfPresent(n1, (k,v) -> {v.add(n2); return v;});
            tree.computeIfPresent(n2, (k,v) -> {v.add(n1); return v;});
        }
        return getCostByBfs(target, t);
    }

    public static void main(String[] args) {
        FrogPosition fp = new FrogPosition();
        Verifier.verifyEquals(fp.frogPosition(8, new int[][]{{2,1},{3,2},{4,1},{5,1},{6,4},{7,1},{8,7}}, 7, 7), 0);
        Verifier.verifyEquals(fp.frogPosition(7, new int[][]{{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}, 2, 4), 0.16666666666666666);
        Verifier.verifyEquals(fp.frogPosition(7, new int[][]{{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}, 1, 7), 0.3333333333333333);
        Verifier.verifyEquals(fp.frogPosition(7, new int[][]{{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}, 20, 6), 0.16666666666666666);
        Verifier.verifyEquals(fp.frogPosition(7, new int[][]{{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}, 1, 6), 0);
        Verifier.verifyEquals(fp.frogPosition(7, new int[][]{{2,1}, {3,2}}, 1, 2), 1);
    }

}
