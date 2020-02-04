package others.leetc.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/clone-graph/
public class CloneGraph {

    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
    }

    Map<Integer, Node> created;
    Set<Integer> printed;

    public Node cloneGraphInternal(Node node) {
        if (created.containsKey(node.val)) {
            return created.get(node.val);
        }
        Node clone = new Node(node.val);
        created.put(node.val, clone);
        for (Node neighbour : node.neighbors) {
            clone.neighbors.add(cloneGraphInternal(neighbour));
        }
        return clone;
    }

    public void printGraphInternal(Node node) {
        if (printed.contains(node.val)) {
            return;
        }
        String s = "" + node.val + "-> [";
        for (Node neigh : node.neighbors) {
            s += neigh.val + ", ";
        }
        s = s.substring(0, s.length() - 2);
        s += "]";
        System.out.println(s);
        printed.add(node.val);
        for (Node neigh : node.neighbors) {
            printGraphInternal(neigh);
        }
    }

    public void printGraph(Node node) {
        printed = new HashSet<>();
        printGraphInternal(node);
    }

    public Node cloneGraph(Node node) {
        created = new HashMap<>();
        return cloneGraphInternal(node);
    }

    public static void main(String[] args) {
        CloneGraph graph = new CloneGraph();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.add(node1);
        node1.neighbors.add(node2);
        node2.neighbors.add(node3);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        System.out.println("Printing graph1");
        graph.printGraph(node1);
        Node clone = graph.cloneGraph(node1);
        System.out.println("Printing clone");
        graph.printGraph(clone);
    }

}
