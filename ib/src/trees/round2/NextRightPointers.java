package trees.round2;

/**
 * Approach - Tree traversal without recursion and without stack... confused? :)
 * The trick is to use the right pointers themselves for tree traversal. We connect the right pointers level by level.
 * After a level is connected, we traverse its nodes using the right pointers and just look at children of the nodes being
 * traversed.
 * If a node has no children, it is ignored, as it will not contribute to next level nodes.
 * If a node has both left and right children, we point left child's right to the right child. We also keep track
 * of previous node (which had at least one child) and connect its rightmost (right != null ? right : left) child's right
 * pointer to this node's leftmost (left != null ? left : right) child.
 * The first child encountered at next level will mark the beginning of traversal for next level.
 * So basically we treat each level as a list and connect next level list by traversing this list and looking at children.
 */
public class NextRightPointers {

    private static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    private TreeLinkNode getChildVisibleToLeft(TreeLinkNode node) {
        return node.left != null ? node.left : node.right;
    }

    private TreeLinkNode getChildVisibleToRight(TreeLinkNode node) {
        return node.right != null ? node.right : node.left;
    }

    public void connect(TreeLinkNode root) {
        TreeLinkNode listStart = root;
        while (listStart != null) {
            TreeLinkNode curListPointerPrev = null;
            TreeLinkNode curListPointerCur = listStart;
            TreeLinkNode savedPointerToNextLevelList = null;
            while (curListPointerCur != null) {
                TreeLinkNode firstChild = getChildVisibleToLeft(curListPointerCur);
                if (firstChild != null) {
                    if (savedPointerToNextLevelList == null) {
                        savedPointerToNextLevelList = firstChild;
                    }
                    if (curListPointerCur.left != null) {
                        curListPointerCur.left.next = curListPointerCur.right;
                    }
                    if (curListPointerPrev != null) {
                        getChildVisibleToRight(curListPointerPrev).next = firstChild;
                    }
                    curListPointerPrev = curListPointerCur;
                }
                curListPointerCur = curListPointerCur.next;
            }
            listStart = savedPointerToNextLevelList;
        }
    }

}
