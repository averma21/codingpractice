package trees.round2;

import trees.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Preorder {

    public List<Integer> preorderTraversal(TreeNode A) {
        List<Integer> ans = new ArrayList<>();
        TreeNode node = A;
        if (A == null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (node != null) {
            ans.add(node.val);
            stack.push(node);
            node = node.left;
            while (node == null && !stack.isEmpty()) {
                node = stack.pop().right;
            }
        }
        return ans;
    }

}
