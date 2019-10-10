package DP;

import trees.TreeNode;
import util.Verifier;

public class MaxPathSumInBinaryTree {

    private int maxSum = Integer.MIN_VALUE;

    private int maxPathSumInternal(TreeNode A) {
        if (A == null)
            return -1;
        if (A.left == null && A.right == null) {
            maxSum = Math.max(maxSum, A.val);
            return A.val;
        }
        int max = A.val;
        Integer leftVal = null, rightVal = null;
        if (A.left != null) {
            leftVal = maxPathSumInternal(A.left);
            max = Math.max(max, A.val + leftVal);
        }
        if (A.right != null) {
            rightVal = maxPathSumInternal(A.right);
            max = Math.max(max, A.val + rightVal);
        }
        int returnVal = max;
        if (leftVal != null && rightVal != null) {
            max = Math.max(max, A.val + leftVal + rightVal);
        }
        maxSum = Math.max(maxSum, max);
        return returnVal;
    }

    private int maxPathSum(TreeNode A) {
        maxSum = Integer.MIN_VALUE;
        maxPathSumInternal(A);
        return maxSum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.addLeft(2);
        root.addRight(3);
        MaxPathSumInBinaryTree mp = new MaxPathSumInBinaryTree();
        Verifier.verifyEquals(mp.maxPathSum(root), 6);
        root = new TreeNode(-10);
        root.addRight(-30);
        root.addLeft(-20);
        Verifier.verifyEquals(mp.maxPathSum(root), -10);
        root.left.addRight(-5);
        Verifier.verifyEquals(mp.maxPathSum(root), -5);
        root = new TreeNode(10);
        TreeNode two = root.addLeft(2);
        two.addRight(1);
        two.addLeft(20);
        TreeNode minus25 = root.addRight(10).addRight(-25);
        minus25.addLeft(3);
        minus25.addRight(4);
        Verifier.verifyEquals(mp.maxPathSum(root), 42);
    }
}
