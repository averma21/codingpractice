package trees;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;

public class RecoverBST {

    private Integer first, second, nextToFirst;
    private TreeNode prev;

    private void inorder(TreeNode A) {
        if (A == null)
            return;
        inorder(A.left);
        if (prev != null) {
            if (first == null && prev.val > A.val) {
                first = prev.val;
                nextToFirst = A.val;
            } else if (second == null && prev.val > A.val) {
                second = A.val;
            }
        }
        prev = A;
        inorder(A.right);
    }

    public ArrayList<Integer> recoverTree(TreeNode A) {
        first = null;second = null; prev = null;
        if (A == null)
            return null;
        inorder(A);
        if (second == null)
            second = nextToFirst;
        if (first < second)
            return new ArrayList<Integer>() {{
                add(first);
                add(second);
            }};
        else
            return new ArrayList<Integer>() {{
                    add(second);
                    add(first);
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
