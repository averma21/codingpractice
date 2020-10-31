package others.leetc.trees;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/
public class PathInZigZagLabelledBinaryTree {

    int findLevel(int label) {
        int i = 0;
        while (Math.pow(2, i) <= label) {
            i++;
        }
        return i;
    }

    /**
     * Given a node at a level, find the position of parent of that node in the previous level.
     * @param label label of the node
     * @param level level of the node
     * @return position of parent in previous level (index starting from 0 from left to right)
     */
    int findParentPosOf(int label, int level) {
        int leastValAtLevel = (int)Math.pow(2, level - 1);
        int maxValAtLevel = (int)Math.pow(2, level) - 1;
        boolean isLevelOdd = level % 2 != 0;
        int leftMost = isLevelOdd ? leastValAtLevel : maxValAtLevel;
        int left = 0, right = maxValAtLevel - leastValAtLevel;
        while (left <= right) {
            int mid = (left+right)/2;
            int aMid = isLevelOdd ? (leftMost + mid) : (leftMost - mid);
            if (aMid == label) {
                return mid/2;
            }
            if (aMid < label) {
                if (isLevelOdd) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (isLevelOdd) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    int findNodeValAtPosInLevel(int index, int level) {
        int leastValAtLevel = (int)Math.pow(2, level - 1);
        int maxValAtLevel = (int)Math.pow(2, level) - 1;
        boolean isLevelOdd = level % 2 != 0;
        int leftMost = isLevelOdd ? leastValAtLevel : maxValAtLevel;
        return isLevelOdd ? leftMost + index : leftMost - index;
    }

    public List<Integer> pathInZigZagTree(int label) {
        int level = findLevel(label);
        List<Integer> ans = new ArrayList<>();
        ans.add(label);
        if (level == 1) {
            return ans;
        }
        do {
            int parentPos = findParentPosOf(label, level);
            level--;
            int parentLabel = findNodeValAtPosInLevel(parentPos, level);
            ans.add(parentLabel);
            label = parentLabel;
        } while (level > 1);
        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        PathInZigZagLabelledBinaryTree piz = new PathInZigZagLabelledBinaryTree();
        //Verifier.verifyEquals(piz.pathInZigZagTree(14), Creator.getList(1,3,4,14));
        Verifier.verifyEquals(piz.pathInZigZagTree(26), Creator.getList(1,2,6,10,26));
    }

}
