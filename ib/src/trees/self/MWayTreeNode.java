package trees.self;

import java.util.ArrayList;
import java.util.List;

public class MWayTreeNode<T> {
    T val;
    List<MWayTreeNode> children;
    MWayTreeNode parent;

    public MWayTreeNode(T val, MWayTreeNode parent) {
        this.val = val;
        children = new ArrayList<>();
        this.parent = parent;
    }

    public T getVal() {
        return val;
    }

    public MWayTreeNode addChild(int val) {
        MWayTreeNode child = new MWayTreeNode(val, this);
        this.children.add(child);
        return child;
    }

    public MWayTreeNode addChild(MWayTreeNode node) {
        this.children.add(node);
        node.parent = this;
        return node;
    }

    void removeChild(T val) {
        int i = 0;
        for (MWayTreeNode child : children) {
            if (child.val.equals(val)) {
                break;
            }
            i++;
        }
        children.remove(i);
    }

    private StringBuilder getJSONB() {
        StringBuilder builder = new StringBuilder("{" + val + ":[");
        for (MWayTreeNode child : children) {
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
        MWayTreeNode root = new MWayTreeNode(1, null);
        root.addChild(10).addChild(100);
        root.addChild(20).addChild(200);
        System.out.println(root.getJSON());
    }
}
