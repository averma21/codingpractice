package trees.round2;

import trees.TreeNode;
import util.Creator;
import util.Verifier;

import java.util.ArrayList;

public class RecoverBST {

    TreeNode smaller, larger;
    TreeNode prev;

    private void inorder(TreeNode A) {
        if (A == null) {
            return;
        }
        inorder(A.left);
        if (prev != null) {
            if (prev.val > A.val) {
                if (larger == null) {
                    larger = prev;
                    smaller = A; // edge case when two consecutive elements are exchanged
                } else {
                    smaller = A;
                }
            }
        }
        prev = A;
        inorder(A.right);
    }

    public ArrayList<Integer> recoverTree(TreeNode A) {
        smaller = null;
        larger = null;
        prev = null;
        inorder(A);
        return new ArrayList<>() {{
            add(smaller.val);
            add(larger.val);
        }};
    }

    public static void main(String[] args) {
        RecoverBST rbst = new RecoverBST();
        TreeNode root = new TreeNode(1);
        root.addRight(3);
        root.addLeft(2);
        Verifier.verifyEquals(rbst.recoverTree(root), Creator.getList(1, 2));
        root = new TreeNode(20);
        root.addRight(30).addLeft(5);
        root.addLeft(10).addLeft(40);
        Verifier.verifyEquals(rbst.recoverTree(root), Creator.getList(5, 40));
    }

}
