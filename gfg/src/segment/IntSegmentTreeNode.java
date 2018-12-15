package segment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IntSegmentTreeNode implements SegmentTreeNode<Integer> {

    private final List<Integer> integers;
    private final int leftIndex;
    private final int rightIndex;
    private SegmentTreeNode<Integer> leftChild;
    private SegmentTreeNode<Integer> rightChild;

    public IntSegmentTreeNode(List<Integer> integers, int leftIndex, int rightIndex) {
        this.integers = new ArrayList<>(integers);
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
    }

    @Override
    public List<Integer> getValues() {
        return integers;
    }

    @Override
    public int getLeftIndex() {
        return leftIndex;
    }

    @Override
    public int getRightIndex() {
        return rightIndex;
    }

    @Override
    public void setLeftChild(SegmentTreeNode<Integer> segmentTreeNode) {
        this.leftChild = segmentTreeNode;
    }

    @Override
    public void setRightChild(SegmentTreeNode<Integer> segmentTreeNode) {
        this.rightChild = segmentTreeNode;
    }

    @Override
    public Optional<SegmentTreeNode<Integer>> getLeftChild() {
        return Optional.ofNullable(leftChild);
    }

    @Override
    public Optional<SegmentTreeNode<Integer>> getRightChild() {
        return Optional.ofNullable(rightChild);
    }
}
