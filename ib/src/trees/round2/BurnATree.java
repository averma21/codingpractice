package trees.round2;

import trees.TreeNode;
import util.Creator;
import util.Verifier;

import java.util.*;

public class BurnATree {

    private Map<Integer, TreeNode> parentMap;
    private TreeNode foundNode;
    private Set<Integer> burnt;
    private Queue<TreeNode> toBurn;

    private void findAncestors(TreeNode curNode, int target, Stack<TreeNode> pathStack) {
        if (curNode == null || foundNode != null) {
            return;
        }
        pathStack.add(curNode);
        if (curNode.val == target) {
            TreeNode child = pathStack.pop();
            while (!pathStack.isEmpty()) {
                TreeNode node = pathStack.pop();
                parentMap.put(child.val, node);
                child = node;
            }
            parentMap.put(child.val, null);
            foundNode = curNode;
            return;
        }
        findAncestors(curNode.left, target, pathStack);
        findAncestors(curNode.right, target, pathStack);
        if (!pathStack.isEmpty()) {
            //will be empty if target node is found
            pathStack.pop();
        }
    }

    private void addConnections(TreeNode node) {
        TreeNode parent = parentMap.get(node.val);
        if (parent != null && !burnt.contains(parent.val)) {
            toBurn.add(parent);
        }
        if (node.left != null && !burnt.contains(node.left.val)) {
            toBurn.add(node.left);
        }
        if (node.right != null && !burnt.contains(node.right.val)) {
            toBurn.add(node.right);
        }
    }

    private int countBurnTime() {
        toBurn.add(foundNode);
        addConnections(foundNode);
        int count = 0;
        while (!toBurn.isEmpty()) {
            count++;
            List<TreeNode> toExploreNeighbours = new ArrayList<>();
            while (!toBurn.isEmpty()) {
                TreeNode burn = toBurn.poll();
                burnt.add(burn.val);
                toExploreNeighbours.add(burn);
            }
            for (TreeNode node : toExploreNeighbours) {
                addConnections(node);
            }
        }
        return count;
    }

    public int solve(TreeNode A, int B) {
        toBurn = new LinkedList<>();
        parentMap = new HashMap<>();
        burnt = new HashSet<>();
        foundNode = null;
        findAncestors(A, B, new Stack<>());
        return countBurnTime();
    }

    public static void main(String[] args) {
        TreeNode [] l = Creator.createTreeNodes(6);
        l[1].left = l[2];
        l[2].left = l[4];
        l[1].right = l[3];
        l[3].left = l[5];
        l[3].right = l[6];
        BurnATree bat = new BurnATree();
        Verifier.verifyEquals(bat.solve(l[1], 4), 4);
        Verifier.verifyEquals(bat.solve(l[1], 3), 3);
        Verifier.verifyEquals(bat.solve(l[1], 1), 2);
        l = Creator.createTreeNodes(5);
        l[1].left = l[2];l[1].right = l[3];
        l[2].left = l[4];l[3].right=l[5];
        Verifier.verifyEquals(bat.solve(l[1], 5), 4);
    }

}
