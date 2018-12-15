package segment;

import java.util.List;
import java.util.Optional;

public interface SegmentTreeNode<T> {

    /**
     * The values present in this node.
     * @return the values.
     */
    List<T> getValues();

    /**
     * Left boundary of the range represented by this node.
     * @return Left boundary.
     */
    int getLeftIndex();

    /**
     * Right boundary of the range represented by this node.
     * @return Right boundary.
     */
    int getRightIndex();

    void setLeftChild(SegmentTreeNode<T> segmentTreeNode);

    void setRightChild(SegmentTreeNode<T> segmentTreeNode);

    Optional<SegmentTreeNode<T>> getLeftChild();

    Optional<SegmentTreeNode<T>> getRightChild();

}
