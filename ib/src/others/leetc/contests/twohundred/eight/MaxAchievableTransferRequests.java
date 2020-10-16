package others.leetc.contests.twohundred.eight;

import util.Verifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Find cycles and eliminate cycles which use same edge. However only this doesn't work. We need to choose longest cycle.
 * So came up with approach using Johnson's cycle finding algo but it gave out of memory. See {@link MaxAchievableTransferRequestsJohnsonCycles}
 * Brute force approach worked. See {@link MaxAchievableTransferRequestsBrute}
 */
public class MaxAchievableTransferRequests {

    Map<Integer, List<Integer>> graph;
    Set<Integer> unvisitedVertices;
    Set<Integer> visitedVertices;
    LinkedHashSet<Integer> dfsPath;
    boolean cycleFound;
    int cycleVertex;
    int requestsSatisfied;

    private void initVars() {
        graph = new HashMap<>();
        unvisitedVertices = new HashSet<>();
        visitedVertices = new HashSet<>();
        dfsPath = new LinkedHashSet<>();
        cycleFound = false;
        cycleVertex = -1;
        requestsSatisfied = 0;
    }

    private void startDFS(int vertex) {
        if (dfsPath.contains(vertex)) {
            cycleFound = true;
            cycleVertex = vertex;
            return;
        }
        dfsPath.add(vertex);
        visitedVertices.add(vertex);
        for (int v : graph.get(vertex)) {
            startDFS(v);
            if (cycleFound) {
                return;
            }
        }
        dfsPath.remove(vertex);
    }

    private void removeEdge(int v1, int v2) {
        System.out.println("Removing edge " + v1 + "->" + v2);
        graph.get(v1).remove(new Integer(v2));
        if (graph.get(v1).size() == 0) {
            unvisitedVertices.remove(v1);
        }
        requestsSatisfied++;
    }

    public int maximumRequests(int n, int[][] requests) {
        initVars();
        for (int i = 0; i < n; i++) {
            graph.putIfAbsent(i, new ArrayList<>());
        }
        for (int [] request : requests) {
            graph.computeIfPresent(request[0], (v,es) -> {es.add(request[1]); return es;});
            unvisitedVertices.add(request[0]);
            unvisitedVertices.add(request[1]);
        }
        Iterator<Integer> iter =  unvisitedVertices.stream().iterator();
        int startVertex = iter.hasNext() ? iter.next() : -1;
        while (startVertex != -1) {
            startDFS(startVertex);
            if (cycleFound) {
                System.out.println("Cycle found");
                boolean startDeletion = false;
                int prevVertex = -1;
                for (int v : dfsPath) {
                    if (!startDeletion && v == cycleVertex) {
                        startDeletion = true;
                    }
                    if (startDeletion && v != cycleVertex) {
                        removeEdge(prevVertex, v);
                    }
                    prevVertex = v;
                }
                removeEdge(prevVertex, cycleVertex);
            } else {
                unvisitedVertices.removeAll(visitedVertices);
            }
            dfsPath.clear();
            cycleFound = false;
            cycleVertex = -1;
            visitedVertices.clear();
            iter = unvisitedVertices.stream().iterator();
            startVertex = iter.hasNext() ? iter.next() : -1;
        }
        return requestsSatisfied;
    }

    public static void main(String[] args) {
        MaxAchievableTransferRequests matr = new MaxAchievableTransferRequests();
//        Verifier.verifyEquals(matr.maximumRequests(5, new int[][]{{0,1},{1,0},{0,1},{1,2},{2,0},{3,4}}), 5);
//        Verifier.verifyEquals(matr.maximumRequests(3, new int[][]{{0,0},{1,2},{2,1}}), 3);
//        Verifier.verifyEquals(matr.maximumRequests(4, new int[][]{{0,3},{3,1},{1,2},{2,0}}), 4);
//        Verifier.verifyEquals(matr.maximumRequests(4, new int[][]{{1,2},{1,2},{2,2},{0,2},{2,1},{1,1},{1,2}}), 4);
        Verifier.verifyEquals(matr.maximumRequests(3, new int[][]{{1,2},{0,0},{0,2},{0,1},{0,0},{0,2},{1,0},{0,1},{2,0}}), 7);
    }

}
