package others.leetc.contests.twohundred.nine;

import others.leetc.trees.TreeNode;
import util.Creator;
import util.Verifier;

import java.util.LinkedList;
import java.util.Queue;

public class EvenOddTree {

    public boolean isEvenOddTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        int expectation = 1; // 1 for odd 0 for even
        //System.out.println("Set expect " + expectation);
        int prev = -1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node == null) {
                if (!queue.isEmpty()) {
                    queue.add(null);
                    expectation = (expectation == 1 ? 0 : 1);
                    //System.out.println("Encountered null. Set expect " + expectation);
                    prev = -1;
                }
            } else {
                //System.out.println("Encountered  " + node.val + " prev " + prev);
                if (expectation == 1) {
                    if (node.val % 2 != 1 || (prev != -1 && node.val <= prev)) {
                        return false;
                    }
                } else {
                    if (node.val % 2 != 0 || (prev != -1 && node.val >= prev)) {
                        return false;
                    }
                }
                prev = node.val;
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        EvenOddTree eot = new EvenOddTree();
        TreeNode node = Creator.createTree(new Integer[]{1,10,4,3,null,7,9,12,8,null, null, 6,null,null,2});
        Verifier.verifyEquals(eot.isEvenOddTree(node), true);
        node = Creator.createTree(new Integer[]{5,4,2,3,3,7});
        Verifier.verifyEquals(eot.isEvenOddTree(node), false);
    }

}
