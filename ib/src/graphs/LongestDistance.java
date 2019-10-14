package graphs;

import trees.self.MWayTreeNode;
import trees.self.MaxInRange;
import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://www.interviewbit.com/problems/largest-distance-between-nodes-of-a-tree/
 */
public class LongestDistance {

    private int maxDist(List<Integer> A) {
        int n = A.size();
        if (n <= 1)
            return 0;
        int rootIndex = -1;
        Map<Integer, List<Integer>> connections = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = A.get(i);
            if (num == -1)
               rootIndex = i;
            connections.putIfAbsent(num, new ArrayList<>());
            final int index = i;
            connections.computeIfPresent(num, (k,v) -> {v.add(index);return v;});
        }
        Set<Integer> currentNodes = new HashSet<>();
        for (int i = 0; i < n; i++) {
            currentNodes.add(i);
        }
        for (int a: A) {
            currentNodes.remove(a);
        }
        int [] maxHeight = new int[n];
        int [] maxDist = new int[n];
        for (int node : currentNodes) {
            maxHeight[node] = 1;
            maxDist[node] = 1;
        }
        while (!currentNodes.isEmpty()) {
            Set<Integer> parents = new HashSet<>();
            for (int node: currentNodes) {
                if (A.get(node) == -1)
                    continue;
                maxHeight[A.get(node)] = maxHeight[node] + 1;
                parents.add(A.get(node));
            }
            currentNodes = parents;
        }
        int rootHeight = maxHeight[rootIndex];
        Map<Integer, List<Integer>> nodeByHeight = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int height = maxHeight[i];
            nodeByHeight.putIfAbsent(height, new ArrayList<>());
            int index = i;
            nodeByHeight.computeIfPresent(height, (k,v) -> {v.add(index); return v;});
        }
        for (int i = 2; i <= rootHeight; i++) {
            List<Integer> nodes = nodeByHeight.get(i);
            for (int node : nodes) {
                List<Integer> children = connections.get(node);
                int maxD = 0, maxHt = 0, secondMaxHt = -1;
                for (int child : children) {
                    if (maxHeight[child] > maxHt) {
                        secondMaxHt = maxHt;
                        maxHt = maxHeight[child];
                    } else if (maxHeight[child] > secondMaxHt) {
                        secondMaxHt = maxHeight[child];
                    }
                    if (maxDist[child] > maxD)
                        maxD = maxDist[child];
                }
                maxDist[node] = Math.max(maxHt + secondMaxHt + 1, maxD);
            }
        }
        return maxDist[rootIndex] - 1;
    }

    public static void main(String[] args) {
        LongestDistance ld = new LongestDistance();
        Verifier.verifyEquals(ld.maxDist(Creator.getList(-1)), 0);
        Verifier.verifyEquals(ld.maxDist(Creator.getList(-1,0)), 1);
        Verifier.verifyEquals(ld.maxDist(Creator.getList(-1, 0, 0, 0, 3)), 3);
        Verifier.verifyEquals(ld.maxDist(Creator.getList(-1, 0, 0, 0, 3, 2, 2, 5, 6, 7, 8, 10)), 7);
        Verifier.verifyEquals(ld.maxDist(Creator.getList(-1,0,1,2,3,4,5,6,7,8)), 9);
        Verifier.verifyEquals(ld.maxDist(Creator.getList(-1, 0, 0, 1, 2, 1, 5 )), 5);
        List<Integer> l = new ArrayList<>(40000);
        l.add(-1);
        for (int i = 0; i < 40000; i++) {
            l.add(i);
        }
        Verifier.verifyEquals(ld.maxDist(l), 40000);
    }

}

// gives stack overflow due to recursion depth of ~40000
//public class LongestDistance {
//
//    Map<Integer, List<Integer>> connections;
//
//    private class Node {
//        private int index;
//        private int maxHeight;
//        private int maxDistance;
//
//        Node(int index, int maxHeight, int maxDistance) {
//            this.index = index;
//            this.maxHeight = maxHeight;
//            this.maxDistance = maxDistance;
//        }
//    }
//
//    private MWayTreeNode<Node> construct(int index) {
//        MWayTreeNode<Node> node = new MWayTreeNode<>(new Node(index, 0, 0), null);
//        int maxDist = 0, maxHeight = 0, secondMaxHeight = -1;
//        for (int ind : connections.getOrDefault(index, Collections.emptyList())) {
//            MWayTreeNode<Node> child = construct(ind);
//            node.addChild(child);
//            int childHeight = child.getVal().maxHeight;
//            if (childHeight> maxHeight) {
//                secondMaxHeight = maxHeight;
//                maxHeight = childHeight;
//            } else if (childHeight > secondMaxHeight) {
//                secondMaxHeight = childHeight;
//            }
//            if (child.getVal().maxDistance > maxDist) {
//                maxDist = child.getVal().maxDistance;
//            }
//        }
//        node.getVal().maxHeight = maxHeight + 1;
//        node.getVal().maxDistance = Math.max(maxHeight + secondMaxHeight + 1, maxDist);
//        return node;
//    }
//
//    private int maxDist(List<Integer> A) {
//        int rootIndex = -1;
//        if (A.size() <= 1)
//            return 0;
//        connections = new HashMap<>();
//        for (int i = 0; i < A.size(); i++) {
//            int num = A.get(i);
//            if (num == -1)
//                rootIndex = i;
//            connections.putIfAbsent(num, new ArrayList<>());
//            final int index = i;
//            connections.computeIfPresent(num, (k,v) -> {v.add(index);return v;});
//        }
//        MWayTreeNode<Node> root = construct(rootIndex);
//        return root.getVal().maxDistance - 1;
//    }
//
//    public static void main(String[] args) {
//        LongestDistance ld = new LongestDistance();
//        Verifier.verifyEquals(ld.maxDist(Creator.getList(-1)), 0);
//        Verifier.verifyEquals(ld.maxDist(Creator.getList(-1,0)), 1);
//        Verifier.verifyEquals(ld.maxDist(Creator.getList(-1, 0, 0, 0, 3)), 3);
//        Verifier.verifyEquals(ld.maxDist(Creator.getList(-1, 0, 0, 0, 3, 2, 2, 5, 6, 7, 8, 10)), 7);
//        Verifier.verifyEquals(ld.maxDist(Creator.getList(-1,0,1,2,3,4,5,6,7,8)), 9);
//        List<Integer> l = new ArrayList<>(40000);
//        l.add(-1);
//        for (int i = 0; i < 40000; i++) {
//            l.add(i);
//        }
//        Verifier.verifyEquals(ld.maxDist(l), 40000);
//    }
//
//}
