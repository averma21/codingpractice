package trees;

import util.Verifier;

import java.util.Stack;

public class TwoSumBinaryTree {

    private static int countNodes(TreeNode A) {
        if (A == null)
            return 0;
        return 1 + countNodes(A.left) + countNodes(A.right);
    }

    public static int t2Sum(TreeNode A, int B) {
        Stack<TreeNode> inoderStack = new Stack<>();
        Stack<TreeNode> revInorderStack = new Stack<>();
        TreeNode cur1 = A, cur2 = A;
        int left = 0, right = countNodes(A) - 1;
        while (left < right && (!inoderStack.empty() || cur1 != null) && (!revInorderStack.empty() || cur2 != null)) {
            if (cur1 != null) {
                inoderStack.push(cur1);
                cur1 = cur1.left;
            }
            if (cur2 != null) {
                revInorderStack.push(cur2);
                cur2 = cur2.right;
            }
            if (cur1 == null && cur2 == null) {
                TreeNode pop1 = inoderStack.peek();
                TreeNode pop2 = revInorderStack.peek();
                //System.out.println("Comparing " + pop1.val + "," + pop2.val);
                long sum = (long)pop1.val + pop2.val;
                if (sum == (long)B)
                    return 1;
                if (sum < B) {
                    cur1 = inoderStack.pop().right;
                    left++;
                } else {
                    cur2 = revInorderStack.pop().left;
                    right--;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode nine = root.addLeft(9);
        TreeNode twenty = root.addRight(20);
        Verifier.verifyEquals(countNodes(root), 3);
        Verifier.verifyEquals(t2Sum(root, 19), 1);
        Verifier.verifyEquals(t2Sum(root, 40), 0);
        root = new TreeNode(100);
        TreeNode fifty = root.addLeft(50);
        TreeNode oneFifty = root.addRight(150);
        fifty.addRight(70).addLeft(60);
        oneFifty.addLeft(120);
        oneFifty.addRight(170);
        Verifier.verifyEquals(countNodes(root), 7);
        Verifier.verifyEquals(t2Sum(root, 150), 1);
        Verifier.verifyEquals(t2Sum(root, 130), 1);
        Verifier.verifyEquals(t2Sum(root, 240), 1);
        Verifier.verifyEquals(t2Sum(root, 19), 0);
        Verifier.verifyEquals(t2Sum(root, 135), 0);

    }

}
