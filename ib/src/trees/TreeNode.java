package trees;

public class TreeNode extends TreeTemplateNode<TreeNode, Integer> implements Comparable<TreeNode> {

//    public int val;

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

    private void inorder(TreeNode node, StringBuilder stringBuilder) {
        if (node == null)
            return;
        inorder(node.left, stringBuilder);
        stringBuilder.append(" ").append(node.val);
        inorder(node.right, stringBuilder);
    }

    private void preorder(TreeNode node, StringBuilder stringBuilder) {
        if (node == null)
            return;
        stringBuilder.append(" ").append(node.val);
        preorder(node.left, stringBuilder);
        preorder(node.right, stringBuilder);
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder("Inorder - ");
        inorder(this, ans);
        ans.append("\\nPreorder - ");
        preorder(this, ans);
        return ans.toString();
    }
}
