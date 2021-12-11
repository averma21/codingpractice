package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CloneGraph {

    private static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }

    Map<Integer, UndirectedGraphNode> visited;

    private UndirectedGraphNode cloneInternal(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        visited.put(node.label, clone);
        for (UndirectedGraphNode ne : node.neighbors) {
            if (!visited.containsKey(ne.label)) {
                clone.neighbors.add(cloneInternal(ne));
            } else {
                clone.neighbors.add(visited.get(ne.label));
            }
        }
        return clone;
    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        visited = new HashMap<>();
        return cloneInternal(node);
    }

    public static void main(String[] args) {

    }

}
