package others.leetc.trees;

import java.util.HashSet;
import java.util.Set;

public class FindElements {

    Set<Integer> presentElementsSet;

    public FindElements(TreeNode root) {
        presentElementsSet = new HashSet<>();
        traverse(root, 0);
    }

    private void traverse(TreeNode node, int expectedVal) {
        if (node == null) {
            return;
        }
        presentElementsSet.add(expectedVal);
        traverse(node.left, 2*expectedVal + 1);
        traverse(node.right, 2*expectedVal + 2);
    }

    public boolean find(int target) {
        return presentElementsSet.contains(target);
    }

}
