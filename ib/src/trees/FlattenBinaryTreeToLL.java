package trees;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * Example :
 * Given
 *
 *
 *          1
 *         / \
 *        2   5
 *       / \   \
 *      3   4   6
 * The flattened tree should look like:
 *
 *    1
 *     \
 *      2
 *       \
 *        3
 *         \
 *          4
 *           \
 *            5
 *             \
 *              6
 * Note that the left child of all nodes should be NULL.
 * https://www.interviewbit.com/problems/flatten-binary-tree-to-linked-list/
 */
public class FlattenBinaryTreeToLL {

    TreeNode flatten(TreeNode a) {
        if (a == null)
            return a;
        TreeNode root = a;
        while (a != null) {
            if (a.left != null) {
                TreeNode newRight = a.left;
                while (newRight.right != null)
                    newRight = newRight.right;
                newRight.right = a.right;
                a.right = a.left;
                a.left = null;
            }
            a = a.right;
        }
        return root;
    }

}
