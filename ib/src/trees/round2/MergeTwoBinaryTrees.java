package trees.round2;

import trees.TreeNode;

public class MergeTwoBinaryTrees {

    private TreeNode copy(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode copy = new TreeNode(node.val);
        copy.left = copy(node.left);
        copy.right = copy(node.right);
        return copy;
    }

    public TreeNode solve(TreeNode A, TreeNode B) {
        if (A == null) {
            return copy(B);
        }
        if (B == null) {
            return copy(A);
        }
        TreeNode node = new TreeNode(A.val + B.val);
        node.left = solve(A.left, B.left);
        node.right = solve(A.right, B.right);
        return node;
    }

}
