package trees.round2;

import trees.TreeNode;
import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Postorder {

    private static class Info {
        TreeNode node;
        char c;

        public Info(TreeNode node, char c) {
            this.node = node;
            this.c = c;
        }
    }

    public List<Integer> postorderTraversal(TreeNode A) {
        List<Integer> ans = new ArrayList<>();
        TreeNode node = A;
        char c = 'L';
        Stack<Info> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(new Info(node, c));
                node = c == 'R' ? node.right : node.left;
                c = 'L';
            } else {
                Info info = stack.pop();
                while (info != null && info.c == 'R') {
                    ans.add(info.node.val);
                    info = stack.isEmpty() ? null : stack.pop();
                }
                if (info != null && info.c == 'L') {
                    node = info.node;
                    c = 'R';
                }
            }
        }
        return ans;
    }

    private List<Integer> postRec;

    private void postRec(TreeNode node) {
        if (node == null) {
            return;
        }
        postRec(node.left);
        postRec(node.right);
        postRec.add(node.val);
    }

    public static void main(String[] args) {
        TreeNode [] n = Creator.createTreeNodes(10);
        n[1].left = n[2];n[1].right = n[6];
        n[2].left = n[5]; n[2].right = n[3];
        n[3].left = n[4];
        n[6].right = n[7];
        n[7].left = n[8]; n[7].right = n[9];
        n[9].left = n[10];
        Postorder postorder = new Postorder();
        postorder.postRec = new ArrayList<>();
        postorder.postRec(n[1]);
        Verifier.verifyEquals(postorder.postRec, postorder.postorderTraversal(n[1]));
    }
}
