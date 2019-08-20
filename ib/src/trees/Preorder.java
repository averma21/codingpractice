package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Preorder {

    public ArrayList<Integer> preorderTraversal(TreeNode A) {
        ArrayList<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (A == null)
            return res;
        stack.push(A);

        while (!stack.empty())
        {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        return res;
    }

}
