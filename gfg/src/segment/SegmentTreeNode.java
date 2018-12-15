package segment;

import java.util.List;
import java.util.Optional;

public interface SegmentTreeNode<T> {

    List<T> getValues();

    int getLeftIndex();

    int getRightIndex();

    void setLeftChild(SegmentTreeNode<T> segmentTreeNode);

    void setRightChild(SegmentTreeNode<T> segmentTreeNode);

    Optional<SegmentTreeNode<T>> getLeftChild();

    Optional<SegmentTreeNode<T>> getRightChild();

}
