package trees.self;

import trees.SegmentTreeNode;
import util.Creator;
import util.Range;
import util.Verifier;

import java.util.List;

public class MaxInRange {

    private SegmentTreeNode constructSegmentTree(List<Integer> nums, int left, int right) {
        if (left == right) {
            return new SegmentTreeNode(nums.get(left), new Range(left, right));
        }
        int mid = (left + right)/2;
        SegmentTreeNode leftTree = constructSegmentTree(nums, left, mid);
        SegmentTreeNode rightTree = constructSegmentTree(nums, mid + 1, right);
        SegmentTreeNode self = new SegmentTreeNode(Math.max(leftTree.val, rightTree.val), new Range(left, right));
        self.left = leftTree;
        self.right = rightTree;
        return self;
    }

    private SegmentTreeNode constructSegmentTree(List<Integer> nums) {
        return constructSegmentTree(nums, 0, nums.size() - 1);
    }

    private Integer max(SegmentTreeNode node, Range range) {
        if (node == null)
            return null;
        if (node.liesInside(range)) {
            return node.val;
        }
        if (node.liesOutside(range)) {
            return null;
        }
        Integer m1 = max(node.left, range);
        Integer m2 = max(node.right, range);
        if (m1 != null) {
            if (m2 != null)
                return Math.max(m1, m2);
            return m1;
        }
        return m2;
    }

    private int max(List<Integer> list, int left, int right) {
        SegmentTreeNode stn = constructSegmentTree(list);
        Range r = new Range(left, right);
        return max(stn, r);
    }

    public static void main(String[] args) {
        MaxInRange mR = new MaxInRange();
//        SegmentTreeNode stn = mR.constructSegmentTree(Creator.getList(1));
//        stn = mR.constructSegmentTree(Creator.getList(1,2));
//        stn = mR.constructSegmentTree(Creator.getList(1,2,3));
//        stn = mR.constructSegmentTree(Creator.getList(1,4,3,2));
//        stn = mR.constructSegmentTree(Creator.getList(1,3,2,5,4));
        List l = Creator.getList(1,3,5,9,7,11);
        Verifier.verifyEquals(mR.max(l, 1,1), 3);
        Verifier.verifyEquals(mR.max(l, 1,2), 5);
        Verifier.verifyEquals(mR.max(l, 0,1), 3);
        Verifier.verifyEquals(mR.max(l, 2,5), 11);
        Verifier.verifyEquals(mR.max(l, 2,4), 9);
    }

}
