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

//slower. See ProcessRestrictedFriendReqsV2
public class ProcessRestrictedFriendReqsV1 {

    private static class Graph {
        Map<Integer, List<Integer>> adjList;
        Map<Integer, List<Integer>> restrictions;

        public Graph(int n, int [][] rests) {
            this.adjList = new HashMap<>();
            this.restrictions = new HashMap<>();
            for (int i = 0; i < n; i++) {
                adjList.put(i, new ArrayList<>());
                restrictions.put(i, new ArrayList<>());
            }
            for (int [] res : rests) {
                restrictions.get(res[0]).add(res[1]);
                restrictions.get(res[1]).add(res[0]);
            }
        }

        private boolean canConnect(int from, Set<Integer> toSet) {
//            System.out.printf("Checking connection of %d with\n", from);
//            for (Integer v : toSet) {
//                System.out.print(v + ",");
//            }
//            //System.out.println("");
            Queue<Integer> queue = new LinkedList<>();
            queue.add(from);
            Set<Integer> visited = new HashSet<>();
            while (!queue.isEmpty()) {
                int pop = queue.poll();
                if (!visited.contains(pop)) {
                    visited.add(pop);
                    for (int to : toSet) {
                        if (restrictions.get(pop).contains(to)) {
                            //System.out.println("false");
                            return false;
                        }
                    }
                    queue.addAll(adjList.get(pop));
                }
            }
            //System.out.println("true");
            return true;
        }

        private void createConnectSet(int vertex, Set<Integer> conn, Set<Integer> visited) {
            if (visited.contains(vertex)) {
                return;
            }
            visited.add(vertex);
            conn.addAll(adjList.get(vertex));
            for (Integer v : adjList.get(vertex)) {
                createConnectSet(v, conn, visited);
            }
        }

        public boolean connect(int v1, int v2) {
            Set<Integer> conn = new HashSet<>();
            conn.add(v2);
            createConnectSet(v2, conn, new HashSet<>());
            if (!canConnect(v1, conn)) {
                return false;
            }
            adjList.get(v1).add(v2);
            adjList.get(v2).add(v1);
            return true;
        }
    }

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        Graph graph = new Graph(n, restrictions);
        boolean [] ans = new boolean[requests.length];
        int i = 0;
        for (int [] req : requests) {
            ans[i++] = graph.connect(req[0], req[1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        ProcessRestrictedFriendReqsV1 prfr = new ProcessRestrictedFriendReqsV1();
        Verifier.verifyEquals(prfr.friendRequests(3, new int[][] {{0,1}}, new int[][]{{0,2}, {2,1}}), new boolean[] {true, false});
        Verifier.verifyEquals(prfr.friendRequests(3, new int[][] {{0,1}}, new int[][]{{1,2}, {0,2}}), new boolean[] {true, false});
        Verifier.verifyEquals(prfr.friendRequests(5, new int[][] {{0,1},{1,2},{2,3}}, new int[][]{{0,4},{1,2},{3,1},{3,4}}),
                new boolean[] {true, false, true, false});
    }

}
