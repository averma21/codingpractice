package trees.round2;

import trees.TreeNode;

//https://www.interviewbit.com/problems/flatten-binary-tree-to-linked-list/
public class FlattenBinaryTreeToLinkedList {

    TreeNode last = null;

    //also accepted
    public TreeNode flattenRecursive(TreeNode a) {
        if (a == null) {
            return null;
        }
        last = a;
        TreeNode right = a.right;
        a.right = flattenRecursive(a.left);
        last.right = flattenRecursive(right);
        a.left = null;
        return a;
    }

    public TreeNode flattenIter(TreeNode a) {
        TreeNode root = a;
        while (a != null) {
            if (a.left != null) {
                TreeNode newRight = a.left;
                while (newRight.right != null) {
                    newRight = newRight.right;
                }
                newRight.right = a.right;
                a.right = a.left;
                a.left = null;
            }
            a = a.right;
        }
        return root;
    }

    public TreeNode flatten(TreeNode a) {
        last = null;
        return flattenIter(a);
    }

}
