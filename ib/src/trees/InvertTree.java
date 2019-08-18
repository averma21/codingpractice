package trees;

public class InvertTree {

    private static void invert(TreeNode A) {
        if (A == null)
            return;
        TreeNode temp = A.left;
        A.left = A.right;
        A.right = temp;
        invert(A.left);
        invert(A.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = root.addLeft(2);
        left.addLeft(4);
        TreeNode right = root.addRight(3);
        right.addLeft(5);
        invert(root);
        System.out.println("done");
    }

}
