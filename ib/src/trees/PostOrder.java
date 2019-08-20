package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class PostOrder {

    public ArrayList<Integer> postorderTraversal(TreeNode A) {
        ArrayList<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (A == null)
            return res;
        stack.push(A);
        while (!stack.empty())
        {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.left != null)
                stack.push(node.left);
            if (node.right != null)
                stack.push(node.right);
        }
        Collections.reverse(res);
        return res;
    }



}
