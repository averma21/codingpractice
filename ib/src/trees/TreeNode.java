package trees;

public class TreeNode extends TreeTemplateNode<TreeNode, Integer> implements Comparable<TreeNode> {

//    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        super(val);
    }

    public TreeNode addLeft(int val) {
        TreeNode t = new TreeNode(val);
        super.addLeft(t);
        return t;
    }

    public TreeNode addRight(int val) {
        TreeNode t = new TreeNode(val);
        super.addRight(t);
        return t;
    }

    @Override
    public int compareTo(TreeNode treeNode) {
        return val - treeNode.val;
    }
}
