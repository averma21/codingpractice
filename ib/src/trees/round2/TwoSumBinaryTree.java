package trees.round2;

import trees.TreeNode;
import util.Verifier;

import java.util.Stack;

public class TwoSumBinaryTree {

    private int findNodeCount(TreeNode A) {
        if (A == null) {
            return 0;
        }
        return 1 + findNodeCount(A.left) + findNodeCount(A.right);
    }

    public int t2Sum(TreeNode A, int B) {
        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();
        TreeNode leftPtr = A, rightPtr = A;
        int left = 0, right = findNodeCount(A) - 1;
        while (left < right && (leftPtr != null || !leftStack.isEmpty()) && (rightPtr != null || !rightStack.isEmpty())) {
            if (leftPtr != null) {
                leftStack.push(leftPtr);
                leftPtr = leftPtr.left;
            }
            if (rightPtr != null) {
                rightStack.push(rightPtr);
                rightPtr = rightPtr.right;
            }
            if (leftPtr == null && rightPtr == null) {
                int a = leftStack.peek().val, b = rightStack.peek().val;
                if (a+b == B) {
                    return 1;
                }
                if (a+b < B) {
                    leftPtr = leftStack.pop().right;
                    left++;
                } else {
                    rightPtr = rightStack.pop().left;
                    right--;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        TwoSumBinaryTree tbt = new TwoSumBinaryTree();
        TreeNode root = new TreeNode(10);
        TreeNode nine = root.addLeft(9);
        TreeNode twenty = root.addRight(20);
        //Verifier.verifyEquals(countNodes(root), 3);
        Verifier.verifyEquals(tbt.t2Sum(root, 19), 1);
        Verifier.verifyEquals(tbt.t2Sum(root, 40), 0);
        root = new TreeNode(100);
        TreeNode fifty = root.addLeft(50);
        TreeNode oneFifty = root.addRight(150);
        fifty.addRight(70).addLeft(60);
        oneFifty.addLeft(120);
        oneFifty.addRight(170);
        //Verifier.verifyEquals(countNodes(root), 7);
        Verifier.verifyEquals(tbt.t2Sum(root, 150), 1);
        Verifier.verifyEquals(tbt.t2Sum(root, 130), 1);
        Verifier.verifyEquals(tbt.t2Sum(root, 240), 1);
        Verifier.verifyEquals(tbt.t2Sum(root, 19), 0);
        Verifier.verifyEquals(tbt.t2Sum(root, 135), 0);

    }

}
