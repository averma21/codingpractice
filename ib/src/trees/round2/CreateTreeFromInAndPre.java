package trees.round2;

import trees.TreeNode;
import util.Creator;

import java.util.List;

public class CreateTreeFromInAndPre {

//    private static boolean isOutOfBound(List<Integer> list, int index) {
//        return index < 0 || index >= list.size();
//    }
//
//    private TreeNode build(List<Integer> preorder, List<Integer> inorder, int preStart, int preEnd,
//                           int inStart, int inEnd) {
//        if (isOutOfBound(preorder, preStart) || isOutOfBound(preorder, preEnd) || isOutOfBound(inorder, inStart)
//            || isOutOfBound(inorder, inEnd) || preStart > preEnd || inStart > inEnd) {
//            return null;
//        }
//        TreeNode node = new TreeNode(preorder.get(preStart));
//        if (preStart == preEnd) {
//            return node;
//        }
//        int index = inorder.indexOf(node.val);
//        int count = index - inStart;
//        node.left = build(preorder, inorder, preStart+1, preStart + count, inStart, index-1);
//        node.right = build(preorder, inorder, preStart + count + 1, preEnd, index+1, inEnd);
//        return node;
//    }

    int preIndex;

    TreeNode build(List<Integer> preOrder, List<Integer> inorder, int left, int right) {
        if (left > right || preIndex >= preOrder.size())
            return null;
        TreeNode node = new TreeNode(preOrder.get(preIndex++));
        if (left == right)
            return node;
        int index = left;
        while (index <= right && !inorder.get(index).equals(node.val)) {
            index++;
        }
        node.left = build(preOrder, inorder, left, index - 1);
        node.right = build(preOrder, inorder, index + 1, right);
        return node;
    }

    TreeNode buildTree(List<Integer> A, List<Integer> B) {
        preIndex = 0;
        return A.isEmpty() ? null : build(A, B, 0, A.size()-1);
    }


    public static void main(String[] args) {
        CreateTreeFromInAndPre c = new CreateTreeFromInAndPre();
        TreeNode node = c.buildTree(Creator.getList(2,4,1,6,5,7,3), Creator.getList(4,2,6,7,5,3,1));
        System.out.println(node.val);
    }

}
