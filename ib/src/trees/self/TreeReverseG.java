package trees.self;

/**
 * Assume nodes are balls connected by string. You hold one node and the trees falls down. Create the new trees.
 */
public class TreeReverseG {

    static MWayTreeNode rev(MWayTreeNode node) {
        if (node == null)
            return node;
        MWayTreeNode revTree = rev(node.parent);
        if (revTree != null) {
            revTree.parent = node;
            revTree.removeChild(node.val);
            node.addChild(revTree);
        }
        node.parent = null;
        return node;
    }

    public static void main(String[] args) {
        MWayTreeNode root = new MWayTreeNode<Integer>(1, null);
        root.addChild(8).addChild(9);
        MWayTreeNode four = root.addChild(2).addChild(3).addChild(4);
        MWayTreeNode seven = four.addChild(5).addChild(7);
        four.addChild(6);
        System.out.println(root.getJSON());
        rev(seven);
        System.out.println(seven.getJSON());

        root = new MWayTreeNode<Integer>(1, null);
        root.addChild(8).addChild(9);
        four = root.addChild(2).addChild(3).addChild(4);
        seven = four.addChild(5).addChild(7);
        four.addChild(6);
        System.out.println(rev(four).getJSON());

    }

}
