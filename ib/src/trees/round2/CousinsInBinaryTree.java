package trees.round2;

import trees.TreeNode;
import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.interviewbit.com/problems/cousins-in-binary-tree/
 *
 * Solution - Do level order traversal. Keep track of current level nodes by adding them to a list when removing from queue.
 * If element is found, continue till this level is complete and then print nodes from the list. To skip the node and its
 * sibling just determine the position of the node in this level (can be done using size of list when element is found).
 * If even, the this node and previous entry need to skipped, else this and next need to be skipped.
 *
 * NOTE - here you also need to add NULL entries to queue for non existing children since that impacts the answer - if there
 * are no siblings, you should not skip cousins :). So level length is used as indicator of level ending.
 */
public class CousinsInBinaryTree {

    public List<Integer> solve(TreeNode A, int B) {
        List<Integer> levelList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(A);
        queue.add(null);
        boolean found = false;
        int toSkip1 = -1, toSkip2 = -1;
        int expectedLevelLength = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (levelList.size() == expectedLevelLength) {
                if (found) {
                    break;
                } else {
                    levelList.clear();
                    expectedLevelLength *= 2;
                    if (!queue.isEmpty()) {
                        queue.add(null);
                    }
                }
                continue;
            }
            if (node != null) {
                if (node.val == B) {
                    found = true;
                    if (levelList.size() % 2 == 1) {
                        // the element is at even position in current level, so we need to skip
                        // previous and this element while creating answer
                        toSkip1 = levelList.size() - 1;
                        toSkip2 = toSkip1+1;
                    } else {
                        // the element is at odd position in current level, so we need to skip
                        // this and next element while creating answer
                        toSkip1 = levelList.size();
                        toSkip2 = toSkip1+1;
                    }
                }
                levelList.add(node.val);
                queue.add(node.left); // add even null values
                queue.add(node.right); // add even null values
            } else {
                levelList.add(null);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < levelList.size(); i++) {
            if (i == toSkip1 || i == toSkip2) {
                continue;
            }
            Integer entry = levelList.get(i);
            if (entry != null)
                ans.add(entry);
        }
        return ans;
    }

    public static void main(String[] args) {
        CousinsInBinaryTree cibt = new CousinsInBinaryTree();
        TreeNode [] n = Creator.createTreeNodes(14);
        n[1].left = n[2];n[1].right = n[6];
        n[2].left = n[5]; n[2].right = n[3];
        n[3].left = n[4];
        n[6].right = n[7];
        n[7].left = n[8]; n[7].right = n[9];
        n[9].left = n[10];
        Verifier.verifyEquals(cibt.solve(n[1], 4), Creator.getList(8,9));
        Verifier.verifyEquals(cibt.solve(n[1], 10), Creator.getList());
        Verifier.verifyEquals(cibt.solve(n[1], 1), Creator.getList());
        Verifier.verifyEquals(cibt.solve(n[1], 2), Creator.getList());
        Verifier.verifyEquals(cibt.solve(n[1], 5), Creator.getList(7));
        n[4].left = n[11];n[4].right = n[12];
        n[8].left=n[13];n[8].right=n[14];
        Verifier.verifyEquals(cibt.solve(n[1], 12), Creator.getList(13,14,10));
    }

}
