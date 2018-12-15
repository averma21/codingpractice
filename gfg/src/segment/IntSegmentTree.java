package segment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class IntSegmentTree implements SegmentTree<Integer> {

    private final int arr [];
    protected final int inputSize;
    private final SegmentTreeNode<Integer> root;


    public IntSegmentTree(int[] arr) {
        this.arr = Arrays.copyOf(arr, arr.length);
        inputSize = arr.length;
        root = create(0, arr.length - 1, 0, arr.length - 1);
    }

    private SegmentTreeNode<Integer> create(int leftIndex, int rightIndex, int arrLower, int arrUpper) {
        if (leftIndex > rightIndex) {
            throw new IllegalArgumentException("Invalid leftIndex " + leftIndex + " and rightIndex " + rightIndex);
        }
        if (arrLower > arrUpper) {
            throw new IllegalArgumentException("Invalid arrLower " + arrLower + " and arrUpper " + arrUpper);
        }
        if (leftIndex == rightIndex && arrLower != arrUpper || leftIndex != rightIndex && arrLower == arrUpper) {
            throw new IllegalArgumentException("Invalid combination [leftIndex, rightIndex] and [arrLower, arrUpper]"
                    + "[" + leftIndex + ", " + rightIndex + "] and [" + arrLower + ", " + arrUpper + "]");
        }
        if (leftIndex == rightIndex) {
            return new IntSegmentTreeNode(new ArrayList<Integer>() {{add(arr[arrLower]);}}, leftIndex, rightIndex);
        }
        if (leftIndex < 0 || arrLower < 0) {
            throw new IllegalArgumentException("Invalid leftIndex or arrLower " + leftIndex + ", " + arrLower);
        }
        if (rightIndex >= inputSize || arrUpper >= inputSize) {
            throw new IllegalArgumentException("Invalid leftIndex or arrLower " + leftIndex + ", " + arrLower);
        }
        int midIndex = (leftIndex + rightIndex) / 2;
        int midArr = (arrLower + arrUpper) / 2;
        SegmentTreeNode<Integer> left = create(leftIndex, midIndex, arrLower, midArr);
        SegmentTreeNode<Integer> right = create(midIndex + 1, rightIndex, midArr + 1, arrUpper);
        SegmentTreeNode<Integer> thisNode = new IntSegmentTreeNode(getParentValue(left, right), leftIndex, rightIndex);
        thisNode.setLeftChild(left);
        thisNode.setRightChild(right);
        return thisNode;
    }

    /**
     * This implementation of this method should contain the logic for value calculation for a parent node of two nodes
     * in the tree.
     * @param leftChild left child of the parent node.
     * @param rightChild right child of the parent node.
     * @return value to insert in the parent node.
     */
    abstract List<Integer> getParentValue(SegmentTreeNode<Integer> leftChild, SegmentTreeNode<Integer> rightChild);

    @Override
    public SegmentTreeNode<Integer> getRoot() {
        return root;
    }
}
