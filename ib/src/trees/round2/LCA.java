package trees.round2;

import trees.TreeNode;
import util.Verifier;

public class LCA {

    private int ans = -1;
    private TreeNode foundNode;

    private boolean find(TreeNode A, int val) {
        if (A == null) {
            return false;
        }
        if (A.val == val) {
            return true;
        }
        return find(A.left, val) || find(A.right, val);
    }

    private boolean traverse(TreeNode A, int B, int C) {
        if (A == null) {
            return false;
        }
        if (A.val == B || A.val == C) {
            foundNode = A;
            return true;
        }
        boolean foundLeft = traverse(A.left, B, C);
        boolean foundRight = traverse(A.right, B, C);
        if (foundLeft && foundRight) {
            ans = A.val;
        }
        return foundLeft || foundRight;
    }

    public int lca(TreeNode A, int B, int C) {
        ans = -1;
        foundNode = null;
        traverse(A, B, C);
        if (ans == -1 && foundNode != null) {
            int toFind = foundNode.val == B ? C : B;
            if (find(foundNode, toFind)) {
                ans = foundNode.val;
            }
        }
        return ans;
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
