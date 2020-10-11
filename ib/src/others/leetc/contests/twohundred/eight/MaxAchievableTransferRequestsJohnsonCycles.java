package others.leetc.contests.twohundred.eight;

import org.omg.PortableInterceptor.INACTIVE;
import sun.security.provider.certpath.Vertex;
import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class MaxAchievableTransferRequestsJohnsonCycles {

    static class Graph {
        Map<Integer, List<Integer>> graph;
        Map<Integer, List<Integer>> reverseGraph;
        final Set<Integer> visited;
        final Set<Integer> unvisitedVertices;
        final Stack<Integer> orderStack;
        final Set<Integer> connectedPath;

        public Graph(int n, Set<Integer> vertices) {
            visited = new HashSet<>();
            orderStack = new Stack<>();
            unvisitedVertices = new HashSet<>();
            connectedPath = new HashSet<>();
            this.graph = new HashMap<>();
            this.reverseGraph = new HashMap<>();
            for (Integer v : vertices) {
                graph.putIfAbsent(v, new ArrayList<>());
                unvisitedVertices.add(v);
            }
            if (vertices.isEmpty()) {
                for (int i = 0; i < n; i++) {
                    graph.putIfAbsent(i, new ArrayList<>());
                    unvisitedVertices.add(i);
                }
            }
        }

        public Graph(int n, int[][] requests) {
            this(n, Collections.emptySet());
            for (int [] request : requests) {
                graph.computeIfPresent(request[0], (v,es) -> {es.add(request[1]); return es;});
                unvisitedVertices.add(request[0]);
                unvisitedVertices.add(request[1]);
            }
        }

        private void addEdge(int from, int to) {
            graph.computeIfPresent(from, (k,v) -> {v.add(to); return v;});
        }

        private void startDFS(int vertex) {
            if (visited.contains(vertex)) {
                return;
            }
            visited.add(vertex);
            unvisitedVertices.remove(vertex);
            try {
                for (int v : graph.get(vertex)) {
                    startDFS(v);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            orderStack.add(vertex);
        }

        private void startDFS2(int vertex) {
            if (visited.contains(vertex)) {
                return;
            }
            visited.add(vertex);
            connectedPath.add(vertex);
            for (int v : reverseGraph.get(vertex)) {
                startDFS2(v);
            }
        }

        private void reverseGraph() {
            graph.keySet().forEach(k -> reverseGraph.put(k , new ArrayList<>()));
            for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
                for (int n : entry.getValue()) {
                    reverseGraph.computeIfPresent(n, (k,v) -> {v.add(entry.getKey()); return v;});
                }
            }
        }

        List<Graph> getStronglyConnectedComponents() {
            while (!unvisitedVertices.isEmpty()) {
                startDFS(unvisitedVertices.stream().iterator().next());
            }
            reverseGraph();
            visited.clear();
            unvisitedVertices.clear();
            List<Graph> graphs = new ArrayList<>();
            while (!orderStack.isEmpty()) {
                int startVertex = orderStack.pop();
                if (!visited.contains(startVertex)) {
                    startDFS2(startVertex);
                    Graph graph1 = new Graph(connectedPath.size(), connectedPath);
                    for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
                        if (connectedPath.contains(entry.getKey())) {
                            entry.getValue().stream().filter(connectedPath::contains).forEach(e -> {
                                graph1.addEdge(entry.getKey(), e);
                            });
                        }
                    }
                    graphs.add(graph1);
                }
                connectedPath.clear();
            }
            return graphs;
        }
    }

    public void print(Graph graph) {
        for (Map.Entry<Integer, List<Integer>> entry : graph.graph.entrySet()) {
            System.out.print("    " + entry.getKey() + " -> ");
            for (int v : entry.getValue()) {
                System.out.print(v + ", ");
            }
            System.out.println("");
        }
        System.out.println("==================");
    }

    Stack<Integer> cyclePath;
    Set<Integer> blockedSet;
    Map<Integer, Set<Integer>> dependencies;
    List<List<Integer>> cycles;
    Set<Integer> foundCycle;

    void removeVertex(Graph graph, int v) {
        graph.graph.remove(v);
        graph.unvisitedVertices.remove(v);
        Map<Integer, List<Integer>> copyMap = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : graph.graph.entrySet()) {
            copyMap.put(entry.getKey(),  entry.getValue().stream().filter(e -> e != v).collect(Collectors.toList()));
        }
        graph.graph = copyMap;
    }

    void startCycleDFS(int startVertex, int vertex, Graph graph) {
        cyclePath.add(vertex);
        blockedSet.add(vertex);
        for (Integer v : graph.graph.get(vertex)) {
            if (v == startVertex) {
                List<Integer> copy = new ArrayList<>(cyclePath);
                copy.add(startVertex);
                foundCycle.addAll(copy);
                cycles.add(copy);
            } else if (blockedSet.contains(v)) {
                dependencies.putIfAbsent(v, new HashSet<>());
                dependencies.computeIfPresent(v, (k,v1) -> {v1.add(vertex); return v1;});
            } else {
                startCycleDFS(startVertex, v, graph);
            }
        }
        if (foundCycle.contains(vertex)) {
            blockedSet.remove(vertex);
            dependencies.getOrDefault(vertex, Collections.emptySet()).forEach(blockedSet::remove);
        }
        cyclePath.pop();
    }

    public int maximumRequests(int n, int[][] requests) {
        Graph graph = new Graph(n, requests);
        cyclePath = new Stack<>();
        blockedSet = new HashSet<>();
        dependencies = new HashMap<>();
        cycles = new ArrayList<>();
        foundCycle = new HashSet<>();
       // print(graph);
        List<Graph> components = graph.getStronglyConnectedComponents();
       // System.out.println("Strongly connected components -");
        int i = 1;
        Queue<Graph> strongCompQueue = new LinkedList<>();
        for (Graph g : components) {
            //System.out.println("Component " + i++);
            //print(g);
            strongCompQueue.add(g);
        }
        int answer = 0;
        while (!strongCompQueue.isEmpty()) {
            Graph scg = strongCompQueue.remove();
            if (scg.graph.keySet().size() == 1) {
                int key = scg.graph.keySet().stream().iterator().next();
                if (scg.graph.get(key).contains(key)) {
                    answer+=scg.graph.get(key).size();
                }
                removeVertex(scg, key);
            } else {
                int startVertex = scg.graph.keySet().stream().iterator().next();
                foundCycle.clear();
                startCycleDFS(startVertex, startVertex, scg);
                removeVertex(scg, startVertex);
                strongCompQueue.addAll(scg.getStronglyConnectedComponents());
            }
        }
        cycles.sort(Collections.reverseOrder(Comparator.comparing(List::size)));
        for (List<Integer> cycle : cycles) {
            boolean canBeFulfilled = true;
            for (int j = 0; j < cycle.size() - 1; j++) {
                int from = cycle.get(j), to = cycle.get(j+1);
                if (!graph.graph.getOrDefault(from, Collections.emptyList()).contains(to)) {
                    canBeFulfilled = false;
                    break;
                }
            }
            if (canBeFulfilled) {
                for (int j = 0; j < cycle.size() - 1; j++) {
                    int from = cycle.get(j), to = cycle.get(j + 1);
                    graph.graph.getOrDefault(from, Collections.emptyList()).remove(new Integer(to));
                    answer++;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        MaxAchievableTransferRequestsJohnsonCycles matr = new MaxAchievableTransferRequestsJohnsonCycles();
        Verifier.verifyEquals(matr.maximumRequests(5, new int[][]{{0,1},{1,0},{0,1},{1,2},{2,0},{3,4}}), 5);
        Verifier.verifyEquals(matr.maximumRequests(3, new int[][]{{0,0},{1,2},{2,1}}), 3);
        Verifier.verifyEquals(matr.maximumRequests(4, new int[][]{{0,3},{3,1},{1,2},{2,0}}), 4);
        Verifier.verifyEquals(matr.maximumRequests(4, new int[][]{{1,2},{1,2},{2,2},{0,2},{2,1},{1,1},{1,2}}), 4);
        Verifier.verifyEquals(matr.maximumRequests(3, new int[][]{{1,2},{0,0},{0,2},{0,1},{0,0},{0,2},{1,0},{0,1},{2,0}}), 7);
        Verifier.verifyEquals(matr.maximumRequests(3, new int[][]{{1,1},{1,0},{0,1},{0,0},{0,0},{0,1},{0,1},{1,0},{1,0},{1,1},{0,0},{1,0}}), 11);
    }

}
