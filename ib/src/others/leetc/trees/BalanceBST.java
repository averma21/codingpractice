package others.leetc.trees;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/balance-a-binary-search-tree/
public class BalanceBST {

    List<Integer> nodeValues;

    void fillInorder(TreeNode node) {
        if (node == null) {
            return;
        }
        fillInorder(node.left);
        nodeValues.add(node.val);
        fillInorder(node.right);
    }

    TreeNode constructTree(int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return new TreeNode(nodeValues.get(left));
        }
        int mid = (left + right)/2;
        TreeNode leftT = constructTree(left, mid - 1);
        TreeNode rightT = constructTree(mid + 1, right);
        return new TreeNode(nodeValues.get(mid), leftT, rightT);
    }

    public TreeNode balanceBST(TreeNode root) {
        nodeValues = new ArrayList<>();
        fillInorder(root);
        return constructTree(0, nodeValues.size() - 1);
    }

}
