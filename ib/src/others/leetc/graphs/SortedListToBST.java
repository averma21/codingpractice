package others.leetc.graphs;

import java.util.ArrayList;

public class SortedListToBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    private TreeNode create(ArrayList<Integer> list, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start+end)/2;
        TreeNode node = new TreeNode(list.get(mid));
        node.left = create(list, start, mid - 1);
        node.right = create(list, mid+1, end);
        return node;
    }

    public TreeNode sortedListToBST(ListNode a) {

        ArrayList<Integer> list = new ArrayList<Integer>();
        ListNode start = a;
        while (start != null) {
            list.add(start.val);
            start = start.next;
        }
        return create(list, 0 , list.size() - 1);
    }

}
