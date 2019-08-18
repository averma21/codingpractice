package trees;

import util.Verifier;

public class SumRootToLeafNumbers {

    static private int sum(TreeNode root, int total) {
        if (root == null)
            return 0;
        total = (total*10 + root.val)%1003;
        if (root.left == null && root.right == null)
            return total;
        return (sum(root.left, total)%1003 + sum(root.right, total)%1003)%1003;
    }

    static int sum(TreeNode root) {
        return sum(root, 0);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.addLeft(2);
        root.addRight(3);
        Verifier.verifyEquals(sum(root), 25);
        root.left.addLeft(5);
        Verifier.verifyEquals(sum(root), 138);
    }

}
