package trees.round2;

import trees.TreeNode;

public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode A) {
        if (A == null) {
            return A;
        }
        TreeNode temp = A.left;
        A.left = invertTree(A.right);
        A.right = invertTree(temp);
        return A;
    }

}
