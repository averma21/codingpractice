package trees;

import java.util.ArrayList;
import java.util.Stack;

public class Inorder {

    public ArrayList<Integer> inorderTraversal(TreeNode A) {

        ArrayList<Integer> res = new ArrayList<>();
        TreeNode pCurrent = A;
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.empty() || pCurrent != null)
        {
            if (pCurrent != null)
            {
                stack.push(pCurrent);
                pCurrent = pCurrent.left;
            }
            else
            {
                TreeNode  pNode = stack.pop();
                res.add(pNode.val);
                pCurrent = pNode.right;
            }
        }
        return res;

    }

}
