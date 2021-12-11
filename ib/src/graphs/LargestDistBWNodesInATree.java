package graphs;

import util.Verifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LargestDistBWNodesInATree {

    int [] maxDepth;
    int [] longestPathInNodeSubtree;
    Map<Integer, List<Integer>> children;

    private void calcDepth(int node) {
        int max = 0;
        for (int child : children.get(node)) {
            calcDepth(child);
            max = Math.max(max, maxDepth[child]);
        }
        maxDepth[node] = max + 1;
    }

    private void calcPath(int node) {
        int maxLengthExcludingCurNode = 0;
        int longestLeafPath = 0, secondLongestLeafPath = 0;
        for (int child : children.get(node)) {
            calcPath(child);
            if (maxDepth[child] > longestLeafPath) {
                secondLongestLeafPath = longestLeafPath;
                longestLeafPath = maxDepth[child];
            } else if (maxDepth[child] > secondLongestLeafPath) {
                secondLongestLeafPath = maxDepth[child];
            }
            maxLengthExcludingCurNode = Math.max(maxLengthExcludingCurNode, longestPathInNodeSubtree[child]);
        }
        if (children.get(node).size() > 1) {
            longestPathInNodeSubtree[node] = Math.max(maxLengthExcludingCurNode, longestLeafPath + secondLongestLeafPath + 1);
        } else {
            longestPathInNodeSubtree[node] = Math.max(maxLengthExcludingCurNode, longestLeafPath + 1);
        }

    }

    public int solve(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        maxDepth = new int[A.length];
        longestPathInNodeSubtree = new int[A.length];
        Arrays.fill(maxDepth, 1);
        Arrays.fill(longestPathInNodeSubtree, 1);
        children = new HashMap<>();
        int root = -1, n = A.length;
        for (int child = 0; child < n; child++) {
            int parent = A[child];
            if (parent == -1) {
                root = child;
            } else {
                int c = child;
                children.putIfAbsent(parent, new ArrayList<>());
                children.computeIfPresent(parent, (k,v) -> {v.add(c); return v;});
            }
            children.putIfAbsent(child, new ArrayList<>());
        }
        calcDepth(root);
        calcPath(root);
        return longestPathInNodeSubtree[root] - 1;
    }

    public static void main(String[] args) {
        LargestDistBWNodesInATree ldb = new LargestDistBWNodesInATree();
        Verifier.verifyEquals(ldb.solve(new int[]{-1, 0, 0, 0, 3}), 3);
    }

}
