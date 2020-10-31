package others.leetc.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
public class LowestCommonAncestor {

    Set<Integer> deepestLeaves;
    int deepestLevel;
    TreeNode lca;

    void findDeepest(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            if (level > deepestLevel) {
                deepestLeaves.clear();
                deepestLeaves.add(node.val);
                deepestLevel = level;
            } else if (level == deepestLevel) {
                deepestLeaves.add(node.val);
            }
        }
        findDeepest(node.left, level+1);
        findDeepest(node.right, level+1);
    }

    List<TreeNode> findLca(TreeNode node) {
        if (node == null) {
            return Collections.emptyList();
        }
        if (deepestLeaves.contains(node.val)) {
            if (deepestLeaves.size() == 1) {
                lca = node;
            }
            return Collections.singletonList(node);
        }
        List<TreeNode> leftList = findLca(node.left);
        List<TreeNode> rightList = findLca(node.right);
        if (lca != null) {
            return null;
        }
        List<TreeNode> combined = new ArrayList<>(leftList);
        combined.addAll(rightList);
        if (combined.size() == deepestLeaves.size()) {
            lca = node;
        }
        return combined;
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        deepestLeaves = new HashSet<>();
        deepestLevel = -1;
        lca = null;
        findDeepest(root, 0);
        findLca(root);
        return lca;
    }

}
