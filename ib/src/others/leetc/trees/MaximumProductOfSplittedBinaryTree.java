package others.leetc.trees;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class MaximumProductOfSplittedBinaryTree {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static class ExtendedTreeNode {
        private int leftSum;
        private int rightSum;
        private int val;
        ExtendedTreeNode left;
        ExtendedTreeNode right;

        public ExtendedTreeNode(TreeNode x, int leftSum, int rightSum) {
            this.val = x.val;
            this.leftSum = leftSum;
            this.rightSum = rightSum;
        }

        private int getSum() {
            return leftSum + val + rightSum;
        }
    }

    private ExtendedTreeNode construct(TreeNode node) {
        if (node == null)
            return null;
        ExtendedTreeNode et = new ExtendedTreeNode(node, 0, 0);
        if (node.left != null) {
            et.left = construct(node.left);
            et.leftSum = et.left.getSum();
        }
        if (node.right != null) {
            et.right = construct(node.right);
            et.rightSum = et.right.getSum();
        }
        return et;
    }

    private long maxProduct;
    private static final int MOD = (int)(1E9 + 7);
    private int totalSum;

    private void updateMax(ExtendedTreeNode node) {
        if (node == null)
            return;
        long leftCutProduct = (long)node.leftSum * (totalSum - node.leftSum);
        long rightCutProduct = (long)node.rightSum * (totalSum - node.rightSum);
        maxProduct = Math.max(leftCutProduct, maxProduct);
        maxProduct = Math.max(rightCutProduct, maxProduct);
        updateMax(node.left);
        updateMax(node.right);
    }

    public int maxProduct(TreeNode root) {
        ExtendedTreeNode etRoot = construct(root);
        totalSum = etRoot.getSum();
        maxProduct = Integer.MIN_VALUE;
        updateMax(etRoot);
        return (int) (maxProduct%MOD);
    }

    private static TreeNode[] getTreeNodes(int n) {
        TreeNode [] nodes = new TreeNode[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new TreeNode(i);
        }
        return nodes;
    }

    // assumes full tree is given even for null nodes;
    private static TreeNode construct(Integer [] values) {
        if (values == null || values.length == 0)
            return null;
        TreeNode root = new TreeNode(values[0]);
        Map<Integer, TreeNode> map = new HashMap<>();
        map.put(0, root);
        int nullCountTillNow = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[i] == null) {
                nullCountTillNow++;
                continue;
            }
            TreeNode node = new TreeNode(values[i]);
            int effectiveIndex = i - nullCountTillNow*2;
            if (i%2 == 1) {
                int parent = (i-1)/2;
                map.get(parent).left = node;
            } else {
                int parent = (i-2)/2;
                map.get(parent).right = node;
            }
            map.put(i, node);
        }
        return root;
    }

    public static void main(String[] args) {
        MaximumProductOfSplittedBinaryTree mps = new MaximumProductOfSplittedBinaryTree();
        TreeNode [] nodes = getTreeNodes(6);
        nodes[1].right = nodes[2];
        nodes[2].left = nodes[3];
        nodes[2].right = nodes[4];
        nodes[4].left = nodes[5];
        nodes[4].right = nodes[6];
        Verifier.verifyEquals(mps.maxProduct(nodes[1]), 90);
        Verifier.verifyEquals(mps.maxProduct(construct(new Integer[]{2,3,9,10,7,8,6,5,4,11,1})), 1025);
        nodes= getTreeNodes(2);
        nodes[2].val = 1;
        nodes[1].left = nodes[2];
        Verifier.verifyEquals(mps.maxProduct(nodes[1]), 1);

    }

}
