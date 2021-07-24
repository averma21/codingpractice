package trees.round2;

import util.Creator;
import util.Verifier;

import java.util.*;

/**
 * Solution approach - since the number of nodes in tree is even, you can remove any number of subtrees which also have
 * even number of nodes each and the result would be a set of trees all of which have even number of nodes. And even from
 * those subtrees you can keep on removing subtress with even number of nodes. The resulting forest would still have
 * trees with even number of nodes. So, to calculate the final answer we just need to find out the number of nodes in initial
 * tree which have even number of nodes. Then we subtract 1 from it (because original tree root was also counted in this count)
 * to get the answer.
 */
public class MaximumEdgeRemoval {

    private static class Info {
        private int size;
        private Set<Integer> children;

        public Info() {
            size = 1;
            children = new HashSet<>();
        }

        private void addChild(int child) {
            children.add(child);
        }

        private Iterable<Integer> getChildren() {
            return children;
        }
    }

    Map<Integer, Info> tree;
    int root;

    private void doDFS(int node) {
        int sum = 0;
        for (int child : tree.get(node).getChildren()) {
            doDFS(child);
            sum += tree.get(child).size;
        }
        tree.get(node).size += sum;
    }

    private void createGraph(int A, List<List<Integer>> B) {
        tree = new HashMap<>();
        Set<Integer> nodes = new HashSet<>(); // to identify root element of the tree
        for (int i = 1; i <= A; i++) {
            nodes.add(i);
            tree.put(i, new Info());
        }
        for (List<Integer> edge : B) {
            int from = edge.get(0), to = edge.get(1);
            nodes.remove(to);
            tree.computeIfPresent(from, (k,v) -> {v.addChild(to); return v;});
        }
        root = nodes.iterator().next();
    }

    public int solve(int A, List<List<Integer>> B) {
        createGraph(A, B);
        doDFS(root);
        int ans = 0;
        for (Map.Entry<Integer, Info> entry : tree.entrySet()) {
            Info nodeInfo = entry.getValue();
            if (nodeInfo.size % 2 != 0) {
                continue;
            }
            ans++;
        }
        return ans - 1;
    }

    public static void main(String[] args) {
        MaximumEdgeRemoval mer = new MaximumEdgeRemoval();
        Verifier.verifyEquals(mer.solve(6, Creator.getList(
                Creator.getList(1,2),
                Creator.getList(1,3),
                Creator.getList(1,4),
                Creator.getList(3,5),
                Creator.getList(4,6)
        )), 2);
        Verifier.verifyEquals(mer.solve(2, Creator.getList(
                Creator.getList(1,2)
        )), 0);
        Verifier.verifyEquals(mer.solve(16, Creator.getList(
                Creator.getList(6,11),
                Creator.getList(8,15),
                Creator.getList(5,6),
                Creator.getList(12,16),
                Creator.getList(2,5),
                Creator.getList(2,13),
                Creator.getList(8,12),
                Creator.getList(1,2),
                Creator.getList(2,4),
                Creator.getList(1,8),
                Creator.getList(6,14),
                Creator.getList(3,10),
                Creator.getList(1,3),
                Creator.getList(1,7),
                Creator.getList(3,9)
        )), 3);
    }

}
