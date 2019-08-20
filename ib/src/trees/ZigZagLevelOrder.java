package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigZagLevelOrder {

    static List<List<Integer>> zigzagLevelOrder(TreeNode A) {
        boolean l2r = true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(A);queue.add(null);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                if (list.size() == 0)
                    break;
                if (!l2r) {
                    Collections.reverse(list);
                }
                ans.add(list);
                list = new ArrayList<>();
                l2r = !l2r;
                queue.add(null);
            } else {
                list.add(cur.val);
                if (cur.left != null)
                    queue.add(cur.left);
                if (cur.right != null)
                    queue.add(cur.right);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode two = root.addLeft(2);
        TreeNode three = root.addRight(3);
        two.addLeft(4);
        TreeNode five = three.addLeft(5);
        three.addRight(6);
        five.addLeft(7);
        five.addRight(8);
        System.out.println(zigzagLevelOrder(root));
        root = new TreeNode(3);
        root.addLeft(9);
        TreeNode twenty = root.addRight(20);
        twenty.addLeft(15);
        twenty.addRight(7);
        System.out.println(zigzagLevelOrder(root));
    }

}
