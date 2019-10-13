package trees;

import util.Range;

public class SegmentTreeNode extends TreeTemplateNode<SegmentTreeNode, Integer> {

    private Range range;

    public SegmentTreeNode(int val, Range range) {
        super(val);
        this.range = range;
    }

    public boolean contains(Range r) {
        return r.liesInside(range);
    }

    public boolean liesOutside(Range r) {
        return range.liesOutside(r);
    }

    public boolean liesInside(Range r) {
        return range.liesInside(r);
    }


    public boolean isSame(Range r) {
        return range.isSameAs(r);
    }

}
