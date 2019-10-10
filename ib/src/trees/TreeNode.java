package trees;

public class TreeNode implements Comparable<TreeNode> {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }

    public TreeNode addLeft(int val) {
        this.left = new TreeNode(val);
        return this.left;
    }

    public TreeNode addRight(int val) {
        this.right = new TreeNode(val);
        return this.right;
    }

    @Override
    public int compareTo(TreeNode treeNode) {
        return val - treeNode.val;
    }
}
