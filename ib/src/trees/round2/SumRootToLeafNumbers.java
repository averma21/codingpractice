package trees.round2;

import trees.TreeNode;
import util.Verifier;

public class SumRootToLeafNumbers {

    private int sum = 0;

    private void sum(TreeNode A, int sumTillNow) {
        if (A == null) {
            return;
        }
        sumTillNow = (sumTillNow*10 + A.val)%1003;
        if (A.left == null && A.right == null) {
            sum += sumTillNow;
            sum %= 1003;
        }
        sum(A.left, sumTillNow);
        sum(A.right, sumTillNow);
    }

    public int sumNumbers(TreeNode A) {
        sum = 0;
        sum(A, 0);
        return sum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.addLeft(2);
        root.addRight(3);
        SumRootToLeafNumbers srt = new SumRootToLeafNumbers();
        Verifier.verifyEquals(srt.sumNumbers(root), 25);
        root.left.addLeft(5);
        Verifier.verifyEquals(srt.sumNumbers(root), 138);
    }

}
