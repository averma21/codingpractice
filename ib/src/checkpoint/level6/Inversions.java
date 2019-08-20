package checkpoint.level6;

import util.Creator;
import util.Verifier;

import java.util.List;

public class Inversions {

    private class BSTNode implements Comparable<BSTNode> {

        int val;
        int rightCount = 1;
        BSTNode left, right;

        public BSTNode(int val) {
            this.val = val;
            this.left=null;
            this.right = null;
        }

        @Override
        public int compareTo(BSTNode bstNode) {
            return val - bstNode.val;
        }
    }

    private class BST {

        BSTNode root;

        private int insert(int val) {
            if (root == null) {
                root = new BSTNode(val);
                return 0;
            }
            return insertInternal(root, new BSTNode(val));
        }

        private int insertInternal(BSTNode insNode, BSTNode node) {
            if (insNode.compareTo(node) > 0) {
                if (insNode.left != null) {
                    return insNode.rightCount + insertInternal(insNode.left, node);
                } else {
                    insNode.left = node;
                    return insNode.rightCount;
                }
            } else {
                insNode.rightCount++;
                if (insNode.right != null) {
                    return insertInternal(insNode.right, node);
                } else {
                    insNode.right = node;
                }
                return 0;
            }
        }
    }

    public int countInversions(List<Integer> A) {
        BST bst = new BST();
        int count = 0;
        for (int a: A) {
            count += bst.insert(a);
        }
        return count;
    }

    public static void main(String[] args) {
        Inversions inversions = new Inversions();
        Verifier.verifyEquals(inversions.countInversions(Creator.getList(2,4,1,3,5)), 3);
    }

}
