package others.leetc.trees;

import util.Creator;
import util.Verifier;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Maintain a queue which tells which tree you need traverse next. The node (next tree's root) inserted into this queue
 * should not be one of the elements to be deleted for the further logic to work. Write a helper method for such insert
 * with required checks.
 *
 * Traverse the tree (obtained from queue), and whenever you find a child node which has to be deleted, add the child node's children to the
 * queue using the helper method. Then break the relations. The root of each traversal would be one of the answers. To ensure that a node
 * gets added to answer only once, add the root to answer after its children have been traversed.
 */
//https://leetcode.com/problems/delete-nodes-and-return-forest/
public class DeleteNodesAndReturnForest {

    Queue<TreeNode> treeQueue;
    TreeNode currentRoot;
    Set<Integer> toDelete;
    List<TreeNode> answer;

    private void addToQueue(TreeNode node) {
        if (node == null) {
            return;
        }
        if (toDelete.contains(node.val)) {
            addToQueue(node.left);
            addToQueue(node.right);
        } else {
            treeQueue.add(node);
        }
    }

    private void traverse(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            if (toDelete.contains(node.left.val)) {
                addToQueue(node.left.left);
                addToQueue(node.left.right);
                node.left.left = null;
                node.left.right = null;
               // toDelete.remove(node.left.val);
                node.left = null;
            } else {
                traverse(node.left);
            }
        }
        if (node.right != null) {
            if (toDelete.contains(node.right.val)) {
                addToQueue(node.right.left);
                addToQueue(node.right.right);
                node.right.left = null;
                node.right.right = null;
               // toDelete.remove(node.right.val);
                node.right = null;
            } else {
                traverse(node.right);
            }
        }
        if (currentRoot.val == node.val) {
            answer.add(currentRoot);
        }
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        treeQueue = new LinkedList<>();
        toDelete = new HashSet<>();
        answer = new LinkedList<>();
        for (int t : to_delete) {
            toDelete.add(t);
        }
        addToQueue(root);
        while (!treeQueue.isEmpty()) {
            TreeNode node = treeQueue.remove();
            currentRoot = node;
            traverse(node);
        }
        return answer;
    }

    public static void main(String[] args) {
        DeleteNodesAndReturnForest dna = new DeleteNodesAndReturnForest();
        Verifier.verifyEqualsTrees(dna.delNodes(Creator.createTree(new Integer[] {1,2,3,4,5,6,7}), new int[] {1}),
                Creator.getList(
                        Creator.createTree(new Integer[]{2,4,5}),
                        Creator.createTree(new Integer[]{3,6,7})
                ));
        Verifier.verifyEqualsTrees(dna.delNodes(Creator.createTree(new Integer[] {1,2,3,4,5,6,7}), new int[] {3,5}),
                Creator.getList(
                        Creator.createTree(new Integer[]{1, 2, null, 4}),
                        Creator.createTree(new Integer[]{6}),
                        Creator.createTree(new Integer[]{7})
                ));
    }

}
