package trees;

import util.Creator;

import java.util.List;

public class CreateTreeFromInAndPost {

    int postIndex;

    TreeNode build(List<Integer> inorder, List<Integer> postorder, int left, int right) {
        if (left > right)
            return null;
        TreeNode node = new TreeNode(postorder.get(postIndex--));
        if (left == right)
            return node;
        int index = inorder.indexOf(node.val);
        node.right = build(inorder, postorder, index + 1, right);
        node.left = build(inorder, postorder, left, index - 1);
        return node;
    }

    TreeNode buildTree(List<Integer> A, List<Integer> B) {
        postIndex = B.size()-1;
        return A.isEmpty() ? null : build(A, B, 0, A.size()-1);
    }

    public static void main(String[] args) {
        CreateTreeFromInAndPost c = new CreateTreeFromInAndPost();
        TreeNode node = c.buildTree(Creator.getList(2,4,1,6,5,7,3), Creator.getList(4,2,6,7,5,3,1));
        System.out.println(node.val);
    }

}
