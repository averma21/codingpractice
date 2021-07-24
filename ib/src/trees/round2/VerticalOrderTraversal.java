package trees.round2;

import trees.TreeNode;

import java.util.*;

public class VerticalOrderTraversal {

    private Map<Integer, ArrayList<Integer>> columnNodesMap;
    private int leftMostCol;

    private static class Info {
        final TreeNode node;
        final int col;

        public Info(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    private void traverse(TreeNode node) {
        if (node == null) {
            return;
        }
        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(node, 0));
        while (!queue.isEmpty()) {
            Info popped = queue.poll();
            leftMostCol = Math.min(leftMostCol, popped.col);
            columnNodesMap.compute(popped.col, (k,list) -> {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(popped.node.val);
                return list;
            });
            if (popped.node.left != null) {
                queue.add(new Info(popped.node.left, popped.col - 1));
            }
            if (popped.node.right != null) {
                queue.add(new Info(popped.node.right, popped.col + 1));
            }
        }
    }

    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
        columnNodesMap = new HashMap<>();
        leftMostCol = Integer.MAX_VALUE;
        traverse(A);
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int i = leftMostCol;
        while (columnNodesMap.containsKey(i)) {
            ans.add(columnNodesMap.get(i));
            i++;
        }
        return ans;
    }

}
