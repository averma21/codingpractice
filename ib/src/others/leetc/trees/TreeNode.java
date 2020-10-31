package others.leetc.trees;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        String leftS = left != null ? left.toString() : "";
        String rightS = right != null ? right.toString() : "";
        return "" + val + " (" + leftS + ") - (" + rightS + ")";
    }
}
