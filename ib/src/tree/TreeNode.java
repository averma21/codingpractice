package tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    int val;
    List<TreeNode> children;
    TreeNode parent;

    public TreeNode(int val, TreeNode parent) {
        this.val = val;
        children = new ArrayList<>();
        this.parent = parent;
    }

    TreeNode addChild(int val) {
        TreeNode child = new TreeNode(val, this);
        this.children.add(child);
        return child;
    }

    TreeNode addChild(TreeNode node) {
        this.children.add(node);
        return node;
    }

    void removeChild(int val) {
        int i = 0;
        for (TreeNode child : children) {
            if (child.val == val) {
                break;
            }
            i++;
        }
        children.remove(i);
    }

    private StringBuilder getJSONB() {
        StringBuilder builder = new StringBuilder("{" + val + ":[");
        for (TreeNode child : children) {
            builder.append(child.getJSONB()).append(",");
        }
        if (children.size() > 0)
            builder.deleteCharAt(builder.length() - 1);
        builder.append("]}");
        return builder;
    }

    String getJSON() {
        return getJSONB().toString();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null);
        root.addChild(10).addChild(100);
        root.addChild(20).addChild(200);
        System.out.println(root.getJSON());
    }
}
