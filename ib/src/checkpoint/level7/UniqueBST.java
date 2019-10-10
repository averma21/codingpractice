package checkpoint.level7;

import trees.TreeNode;
import util.Creator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueBST {

    Map<Integer, List<TreeNode>> structures;
    int noToFill = 1;

    private TreeNode copy(TreeNode node) {
        if (node == null)
            return node;
        TreeNode copy = new TreeNode(node.val);
        copy.left = copy(node.left);
        copy.right = copy(node.right);
        return copy;
    }

    private List<TreeNode> generateStructures(int a) {
        if (structures.containsKey(a))
            return structures.get(a);
        List<TreeNode> structureList = new ArrayList<>();
        for (int i = 1; i <= a; i++) {
            int left = i - 1;
            int right = a - i;
            List<TreeNode> leftSubtrees = generateStructures(left);
            List<TreeNode> rightSubtrees = generateStructures(right);
            for (TreeNode leftNode : leftSubtrees) {
                for (TreeNode rightNode : rightSubtrees) {
                    TreeNode root = new TreeNode(-1);
                    root.left = copy(leftNode);
                    root.right = copy(rightNode);
                    structureList.add(root);
                }
                if (rightSubtrees.size() == 0) {
                    TreeNode root = new TreeNode(-1);
                    root.left = copy(leftNode);
                    structureList.add(root);
                }
            }
            if (leftSubtrees.size() == 0) {
                for (TreeNode rightNode : rightSubtrees) {
                    TreeNode root = new TreeNode(-1);
                    root.right = copy(rightNode);
                    structureList.add(root);
                }
            }
        }
        structures.put(a, structureList);
        return structureList;
    }

    private void fillInorder(TreeNode node) {
        if (node == null)
            return;
        fillInorder(node.left);
        node.val = noToFill++;
        fillInorder(node.right);
    }

    public List<TreeNode> generateTrees(int a) {
        structures = new HashMap<>();
        TreeNode node = new TreeNode(-1);
        structures.put(0, Collections.emptyList());
        structures.put(1, Creator.getList(node));
        List<TreeNode> trees = generateStructures(a);
        for (TreeNode root : trees) {
            noToFill = 1;
            fillInorder(root);
        }
        return trees;
    }

    public static void main(String[] args) {
        UniqueBST ubst = new UniqueBST();
        List<TreeNode> trees = ubst.generateTrees(1);
        trees = ubst.generateTrees(2);
        trees = ubst.generateTrees(3);
        trees = ubst.generateTrees(4);
    }

}
