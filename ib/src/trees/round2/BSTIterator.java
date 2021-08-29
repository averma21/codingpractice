package trees.round2;

import trees.TreeNode;
import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BSTIterator {

    Stack<TreeNode> stack;

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode popped = stack.pop();
        if (popped == null) {
            throw new NoSuchElementException();
        }
        fillStack(popped.right);
        return popped.val;
    }

    private void fillStack(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    BSTIterator(TreeNode root) {
        stack = new Stack<>();
        fillStack(root);
    }

    List<Integer> inorder;
    private void createInList(TreeNode node) {
        if (node == null) {
            return;
        }
        createInList(node.left);
        inorder.add(node.val);
        createInList(node.right);
    }

    public static void main(String[] args) {
        TreeNode [] l = Creator.createTreeNodes(8);
        l[1].left = l[2];
        l[1].right = l[3];
        l[2].right = l[4];
        l[4].left = l[5];
        l[4].right = l[6];
        l[3].right = l[7];
        l[7].right = l[8];
        BSTIterator iterator = new BSTIterator(l[1]);
        iterator.inorder = new ArrayList<>();
        iterator.createInList(l[1]);
        for (int val : iterator.inorder) {
            Verifier.verifyEquals(iterator.hasNext(), true);
            Verifier.verifyEquals(iterator.next(), val);
        }
    }

}
