package others.leetc.contests.oneseventy.oneseventytwo;

//https://leetcode.com/contest/weekly-contest-172/problems/delete-leaves-with-a-given-value/
public class DeleteLeaves {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private boolean remove(TreeNode node, int target) {
        boolean left = false, right = false;
        if (node.left != null) {
            if (node.left.left == null && node.left.right == null && node.left.val == target) {
                node.left = null;
                return true;
            }
            left = remove(node.left, target);
        }
        if (node.right != null) {
            if (node.right.left == null && node.right.right == null && node.right.val == target) {
                node.right = null;
                return true;
            }
            right = remove(node.right, target);
        }
        return left || right;
    }

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        while (true) {
            if (root.left == null && root.right == null) {
                if (root.val == target)
                    return null;
            }
            if (!remove(root, target)) {
                break;
            }
        }
        return root;
    }

    private static TreeNode [] construct(int size) {
        TreeNode [] ans = new TreeNode[size];
        for (int i = 0; i < size; i++) {
            ans[i] = new TreeNode(i + 1);
        }
        return ans;
    }

    public static void main(String[] sa) {
        TreeNode [] arr = construct(6);
        arr[3].val = 2;
        arr[4].val = 2;
        arr[0].left = arr[1];
        arr[0].right = arr[2];
        arr[1].left = arr[3];
        arr[2].left = arr[4];
        arr[2].right = arr[5];

        DeleteLeaves dl = new DeleteLeaves();
        dl.removeLeafNodes(arr[0], 2);
        System.out.println(arr[0]);
    }
}
