package trees;

public class TreeNode implements Comparable<TreeNode> {

    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }

    TreeNode addLeft(int val) {
        this.left = new TreeNode(val);
        return this.left;
    }

    TreeNode addRight(int val) {
        this.right = new TreeNode(val);
        return this.right;
    }

    @Override
    public int compareTo(TreeNode treeNode) {
        return val - treeNode.val;
    }
}
