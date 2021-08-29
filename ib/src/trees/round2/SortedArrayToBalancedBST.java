package trees.round2;

import trees.TreeNode;

public class SortedArrayToBalancedBST {

    int [] nodes;

    private TreeNode buildInternal(int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start+end)/2;
        TreeNode node = new TreeNode(nodes[mid]);
        node.left = buildInternal(start, mid-1);
        node.right = buildInternal(mid+1, end);
        return node;
    }

    public TreeNode sortedArrayToBST(final int[] A) {
        this.nodes = A;
        return buildInternal(0, A.length - 1);
    }

    public static void main(String[] args) {
        SortedArrayToBalancedBST stbt = new SortedArrayToBalancedBST();
        System.out.println(stbt.sortedArrayToBST(new int[]{1}));
        System.out.println(stbt.sortedArrayToBST(new int[]{1,2}));
        System.out.println(stbt.sortedArrayToBST(new int[]{1,2,3}));
        System.out.println(stbt.sortedArrayToBST(new int[]{1,2,3,4}));
        System.out.println(stbt.sortedArrayToBST(new int[]{1,2,3,4,5}));
        System.out.println(stbt.sortedArrayToBST(new int[]{1,2,3,4,5,6}));
    }

}
