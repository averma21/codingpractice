package others.leetc.graphs;

import util.Creator;
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

//https://leetcode.com/problems/minimum-height-trees/
public class MinHeightTrees {

    Map<Integer, List<Integer>> graph;
    Set<Integer> visited;
    Queue<Integer> queue;

    int getFarthest(int x) {
        visited = new HashSet<>();
        queue = new LinkedList<>();
        queue.add(x);
        int last = x;
        while (!queue.isEmpty()) {
            int node = queue.remove();
            visited.add(node);
            last = node;
            for (int neigh : graph.getOrDefault(node, Collections.emptyList())) {
                if (!visited.contains(neigh)) {
                    queue.add(neigh);
                }
            }
        }
        return last;
    }

    private List<Integer> getMids(List<Integer> list) {
        int size = list.size();
        List<Integer> ans = new ArrayList<>();
        if (size % 2 == 0) {
            int mid = size / 2 - 1;
            ans.add(list.get(mid));
            ans.add(list.get(mid + 1));
        } else {
            ans.add(list.get(size/2));
        }
        Collections.sort(ans);
        return ans;
    }

    List<Integer> findMid(int source, int dest, List<Integer> parents) {
        if (source == dest) {
            parents.add(dest);
            return getMids(parents);
        }
        parents.add(source);
        visited.add(source);
        for (int neigh : graph.getOrDefault(source, Collections.emptyList())) {
            if (visited.contains(neigh))
                continue;
            List<Integer> res = findMid(neigh, dest, parents);
            if (!res.isEmpty()) {
                return res;
            }
        }
        parents.remove(parents.size() - 1);
        return Collections.emptyList();
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n <= 0 || edges == null) {
            return Collections.emptyList();
        }
        graph = new HashMap<>();
        for (int [] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            graph.putIfAbsent(x, new ArrayList<>());
            graph.putIfAbsent(y, new ArrayList<>());
            graph.computeIfPresent(x, (k,v) -> {v.add(y); return v;});
            graph.computeIfPresent(y, (k,v) -> {v.add(x); return v;});
        }

        int left = getFarthest(0);
        int right = getFarthest(left);
        visited = new HashSet<>();
        return findMid(left, right, new ArrayList<>());
    }

    public static void main(String[] args) {
        MinHeightTrees mht = new MinHeightTrees();
        //System.out.println(mht.findMinHeightTrees(6, new int[][] {{0,3}, {1,3}, {2,3}, {4,3}, {5,4}}));
        Verifier.verifyEquals(mht.findMinHeightTrees(6, new int[][] {{0,3}, {1,3}, {2,3}, {4,3}, {5,4}}), Creator.getList(3,4));
    }
}
