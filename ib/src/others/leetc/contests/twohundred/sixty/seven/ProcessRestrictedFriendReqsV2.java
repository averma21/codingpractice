package others.leetc.contests.twohundred.sixty.seven;

import util.Verifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//faster
public class ProcessRestrictedFriendReqsV2 {

    private static class Graph {
        Set<Integer> vertices;
        Set<Integer> restrictions;

        public Graph(int n) {
            this.vertices = new HashSet<>();
            vertices.add(n);
            this.restrictions = new HashSet<>();
        }

        public void addRestriction(int v) {
            restrictions.add(v);
        }

        public boolean addGraph(Graph graph) {
            for (int v : graph.vertices) {
                if (restrictions.contains(v)) {
                    return false;
                }
            }
            vertices.addAll(graph.vertices);
            restrictions.addAll(graph.restrictions);
            return true;
        }

    }

    Map<Integer, Graph> graphs;

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        graphs = new HashMap<>();
        for (int i = 0; i< n; i++) {
            graphs.put(i, new Graph(i));
        }
        for (int [] rests : restrictions) {
            graphs.get(rests[0]).addRestriction(rests[1]);
            graphs.get(rests[1]).addRestriction(rests[0]);
        }
        boolean [] ans = new boolean[requests.length];
        int i = 0;
        for (int [] req : requests) {
            Graph g1 = graphs.get(req[0]);
            Graph g2 = graphs.get(req[1]);
            if (g1.addGraph(g2)) {
                ans[i++] = true;
                for (int v : g2.vertices) {
                    graphs.put(v, g1);
                }
            } else {
                ans[i++] = false;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ProcessRestrictedFriendReqsV2 prfr = new ProcessRestrictedFriendReqsV2();
        Verifier.verifyEquals(prfr.friendRequests(3, new int[][] {{0,1}}, new int[][]{{0,2}, {2,1}}), new boolean[] {true, false});
        Verifier.verifyEquals(prfr.friendRequests(3, new int[][] {{0,1}}, new int[][]{{1,2}, {0,2}}), new boolean[] {true, false});
        Verifier.verifyEquals(prfr.friendRequests(5, new int[][] {{0,1},{1,2},{2,3}}, new int[][]{{0,4},{1,2},{3,1},{3,4}}),
                new boolean[] {true, false, true, false});
    }

}
