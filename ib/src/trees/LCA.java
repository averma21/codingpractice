package trees;

import util.Verifier;

public class LCA {

    boolean find(TreeNode A, int val) {
        if (A == null)
            return false;
        if (A.val == val)
            return true;
        return find(A.left, val) ||  find(A.right, val);
    }

    TreeNode lcaInternal(TreeNode A, int val1, int val2) {
        if (A == null || A.val == val1 || A.val ==  val2)
            return A;
        TreeNode left = lcaInternal(A.left, val1, val2);
        TreeNode right = lcaInternal(A.right, val1, val2);
        if (left != null && right != null)
            return A;
        if (left != null)
            return left;
        return right;
    }

    int lca(TreeNode A, int B, int C) {
        if (!find(A, B) || !find(A, C))
            return -1;
        TreeNode ans = lcaInternal(A, B, C);
        if (ans != null)
            return ans.val;
        return -1;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.addLeft(2);
        node.left.addLeft(4);
        node.left.addRight(8);
        node.left.left.addRight(5);
        node.addRight(3);
        node.right.addLeft(6);
        node.right.left.addRight(7);
        LCA lca = new LCA();
        Verifier.verifyEquals(lca.lca(node, 1,3), 1);
        Verifier.verifyEquals(lca.lca(node, 1,9), -1);
        Verifier.verifyEquals(lca.lca(node, 3,7), 3);
        Verifier.verifyEquals(lca.lca(node, 4,6), 1);
        Verifier.verifyEquals(lca.lca(node, 4,8), 2);
    }

}
