package trees.round2;

import trees.TreeNode;
import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Inorder {

    public List<Integer> inorderTraversal(TreeNode A) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        TreeNode node = A;
        while (node != null) {
            stack.push(node);
            node = node.left;
            while (node == null && !stack.isEmpty()) {
                node = stack.pop();
                ans.add(node.val);
                node = node.right;
            }
        }
        return ans;
    }

    private List<Integer> inList;

    private void inorderRec(TreeNode node) {
        if (node == null) {
            return;
        }
        inorderRec(node.left);
        inList.add(node.val);
        inorderRec(node.right);
    }

    public static void main(String[] args) {
        TreeNode [] n = Creator.createTreeNodes(10);
        n[1].left = n[2];n[1].right = n[6];
        n[2].left = n[5]; n[2].right = n[3];
        n[3].left = n[4];
        n[6].right = n[7];
        n[7].left = n[8]; n[7].right = n[9];
        n[9].left = n[10];
        Inorder inorder = new Inorder();
        inorder.inList = new ArrayList<>();
        inorder.inorderRec(n[1]);
        Verifier.verifyEquals(inorder.inorderTraversal(n[1]), inorder.inList);
    }

}
