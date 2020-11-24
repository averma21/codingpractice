package trees;

public class NextRightPointers {

    private static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    private TreeLinkNode nextRight(TreeLinkNode node) {
        TreeLinkNode linkNode = node.next;
        while (linkNode != null) {
            if (linkNode.left != null)
                return linkNode.left;
            if (linkNode.right != null)
                return linkNode.right;
            linkNode = linkNode.next;
        }
        return null;
    }

    public void connect(TreeLinkNode root) {
        while (root != null) {
            TreeLinkNode B = root;
            while (B != null) {
                if (B.left != null) {
                    if (B.right != null) {
                        B.left.next = B.right;
                    } else {
                        B.left.next = nextRight(B);
                    }
                }
                if (B.right != null) {
                    B.right.next = nextRight(B);
                }
                B = B.next;
            }
            if (root.left != null)
                root = root.left;
            else if (root.right != null)
                root = root.right;
            else
                root = nextRight(root);
        }
    }

}
