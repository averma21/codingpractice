package others.leetc.contests.twohundred.sixty.eight;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class RangeFreqQuery {

    private static class Range {
        final int lIndex, rIndex;


        public Range(int lIndex, int rIndex) {
            this.lIndex = lIndex;
            this.rIndex = rIndex;
        }

        boolean coversPos(int pos) {
            return lIndex <= pos && pos <= rIndex;
        }

        boolean isSingular() {
            return lIndex == rIndex;
        }

        boolean isExactRange(Range r) {
            return lIndex == r.lIndex && rIndex == r.rIndex;
        }

        Range getIntersection(Range range) {
            if (rIndex < range.lIndex || lIndex > range.rIndex) {
                return null;
            }
            return new Range(Math.max(lIndex, range.lIndex), Math.min(rIndex, range.rIndex));
        }
    }

    private static class TreeNode {
        final Range range;
        private final Map<Integer, Integer> freqMap;
        TreeNode left, right;

        public TreeNode(int lIndex, int rIndex) {
            this.range = new Range(lIndex, rIndex);
            freqMap = new HashMap<>();
        }

        void incFreq(int v) {
            freqMap.compute(v, (k,f) -> f == null ? 1 : f+1);
        }

        int getFreq(int v) {
            return freqMap.getOrDefault(v, 0);
        }



        TreeNode getLeft() {
            if (range.isSingular()) {
                return null;
            }
            if (left == null) {
                left = new TreeNode(range.lIndex, (range.lIndex + range.rIndex)/2);
            }
            return left;
        }

        TreeNode getRight() {
            if (range.isSingular()) {
                return null;
            }
            if (right == null) {
                right = new TreeNode((range.lIndex + range.rIndex)/2 + 1, range.rIndex);
            }
            return right;
        }


    }

    TreeNode root;

    void insertVal(int val, int pos, TreeNode node) {
        if (!node.range.coversPos(pos)) {
            throw new IllegalArgumentException("Can't add val");
        }
        node.incFreq(val);
        TreeNode left = node.getLeft(), right = node.getRight();
        if (left != null) {
            if (left.range.coversPos(pos)) {
                insertVal(val, pos, left);
                return;
            }
        }
        if (right != null) {
            if (right.range.coversPos(pos)) {
                insertVal(val, pos, right);
                return;
            }
            throw new IllegalStateException("Can't add val pos=(" + pos +") to any child of node " + node.range.lIndex + ", " + node.range.rIndex);
        }
    }

    private int queryInternal(TreeNode node, Range range, int value) {
        if (node == null || node.getFreq(value) == 0) {
            return 0;
        }
        if (node.range.isExactRange(range)) {
            return node.getFreq(value);
        }
        int leftFreq = node.left == null || node.left.range.getIntersection(range) == null ?
                0 : queryInternal(node.left, node.left.range.getIntersection(range), value);
        int rightFreq = node.right == null || node.right.range.getIntersection(range) == null ?
                0 : queryInternal(node.right, node.right.range.getIntersection(range), value);
        return leftFreq + rightFreq;
    }

    public RangeFreqQuery(int[] arr) {
        root = new TreeNode(0, arr.length);
        for (int i = 0; i < arr.length; i++) {
            insertVal(arr[i], i, root);
        }
    }



    public int query(int left, int right, int value) {
        return queryInternal(root, new Range(left, right), value);
    }

    public static void main(String[] args) {
        RangeFreqQuery query = new RangeFreqQuery(new int[] {12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56});
        Verifier.verifyEquals(query.query(1,2,4), 1);
        Verifier.verifyEquals(query.query(0,11,33), 2);
        Verifier.verifyEquals(query.query(1,11,33), 2);
        Verifier.verifyEquals(query.query(2,11,33), 1);
        Verifier.verifyEquals(query.query(2,5,100), 0);

    }

}
