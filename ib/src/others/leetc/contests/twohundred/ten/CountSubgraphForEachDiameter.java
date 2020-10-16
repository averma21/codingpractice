package others.leetc.contests.twohundred.ten;

import util.Verifier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/problems/count-subtrees-with-max-distance-between-cities/
//https://leetcode.com/problems/count-subtrees-with-max-distance-between-cities/discuss/889106/Brute-force-checking-all-subgraphs-with-neat-bfs-trick-to-find-diameter

/**
 * The solution consists of the following steps:
 *
 * Use bit-masking to generate all possible subgraphs
 * For each subgraph, bfs to check if it is connected (and therefore a subtree);
 * For each subtree, do a bfs again to compute its diameter.
 * The way I compute the diameter is based on the following theorem:
 * For any given node a of the tree, a farthest node b from a must be one of the end points of a path with maximal length. i.e. there exists a path from b to c whose length is maximal.
 * Using this fact, when we do the first bfs with a random root, we find b. Then we do another bfs with b as the root to find c and the corresponding diameter of the tree.
 */
public class CountSubgraphForEachDiameter {

    private int [][] edges;
    private Map<Integer, Set<Integer>> mappings;
    Queue<Integer> traverseQueue;

    private Set<Integer> traverse(int vertex, Set<Integer> vertices) {
        traverseQueue.clear();
        traverseQueue.add(vertex);
        Set<Integer> visited = new HashSet<>();
        while (!traverseQueue.isEmpty()) {
            int popped = traverseQueue.remove();
            visited.add(popped);
            try {
                for (int v : mappings.get(popped)) {
                    if (vertices.contains(v) && !visited.contains(v)) {
                        traverseQueue.add(v);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return visited;
    }

    private boolean isConnected(Set<Integer> vertices) {
        if (vertices.size() == 0)
            return false;
        return traverse(vertices.stream().iterator().next(), vertices).size() == vertices.size();
    }

    private static class VertexAtDistance {
        int vertex;
        int distance;

        public VertexAtDistance(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    private VertexAtDistance findFarthestFrom(int vertex, Set<Integer> vertices) {
        traverseQueue.clear();
        traverseQueue.add(vertex);
        traverseQueue.add(null);
        Set<Integer> visited = new HashSet<>();
        int lastPopped = -1, distance = 0;
        while (!traverseQueue.isEmpty()) {
            Integer popped = traverseQueue.remove();
            if (popped == null) {
                if (traverseQueue.size() > 0) {
                    traverseQueue.add(null);
                    distance++;
                }
                continue;
            }
            visited.add(popped);
            lastPopped = popped;
            for (int v : mappings.get(popped)) {
                if (vertices.contains(v) && !visited.contains(v)) {
                    traverseQueue.add(v);
                }
            }
        }
        return new VertexAtDistance(lastPopped, distance);
    }

    private int findLongestPath(Set<Integer> vertices) {
        int farthestVertex = findFarthestFrom(vertices.stream().iterator().next(), vertices).vertex;
        return findFarthestFrom(farthestVertex, vertices).distance;
    }

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        this.edges = edges;
        this.mappings = new HashMap<>();
        traverseQueue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            mappings.put(i, new HashSet<>());
        }
        for (int [] edge : edges) {
            mappings.computeIfPresent(edge[0], (k,s) -> {s.add(edge[1]); return s;});
            mappings.computeIfPresent(edge[1], (k,s) -> {s.add(edge[0]); return s;});
        }
        Set<Integer> vertices = new HashSet<>(n);
        Map<Integer, Integer> distanceCount = new HashMap<>();
        // generate all subsets of vertices. 2^n possible subsets.
        for (int i = 0; i < Math.pow(2, n); i++) {
            String s = Integer.toBinaryString(i);
            vertices.clear();
            // This is tricky. Suppose there are 4 vertices, 1,2,3,4 and the binary string is 11. For selecting vertices
            // we are applying the binary string towards the end of the vertex list. So in this case we choose vertex 3 and 4.
            // We are not choosing starting from beginning because if we do so, the binary strings 1, 10, 100, 1000 and
            // so on would mean the same thing - choose 1st vertex only.
            //
            // So, binary string 1 should be treated as 0001, 10 as 0010.
            int offset = n - s.length();
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    vertices.add(offset + j +1);
                }
            }
            if (isConnected(vertices)) {
                int longestPath = findLongestPath(vertices);
                distanceCount.putIfAbsent(longestPath, 0);
                distanceCount.computeIfPresent(longestPath, (k,v) -> v+1);
                //System.out.println("Adding 1 for " + s + " for length " + longestPath);
            }
        }
        int [] answer = new int[n-1];
        for (int i = 1; i <= n - 1; i++) {
            answer[i-1] = distanceCount.getOrDefault(i, 0);
        }
        return answer;
    }

    public static void main(String[] args) {
        CountSubgraphForEachDiameter csed = new CountSubgraphForEachDiameter();
        Verifier.verifyEquals(csed.countSubgraphsForEachDiameter(4, new int[][]{{1,2},{2,3},{2,4}}), new int[]{3,4,0});
        Verifier.verifyEquals(csed.countSubgraphsForEachDiameter(2, new int[][]{{1,2}}), new int[]{1});
        Verifier.verifyEquals(csed.countSubgraphsForEachDiameter(3, new int[][]{{1,2},{2,3}}), new int[]{2,1});
        Verifier.verifyEquals(csed.countSubgraphsForEachDiameter(4, new int[][]{{1,3},{1,4},{2,3}}), new int[]{3,2,1});
    }

}
