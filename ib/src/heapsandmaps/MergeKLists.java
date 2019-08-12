package heapsandmaps;

import util.Creator;
import util.ListNode;

import java.util.List;

public class MergeKLists {

    class ListNodeWrapper implements Comparable<ListNodeWrapper> {

        final ListNode node;

        public ListNodeWrapper(ListNode node) {
            this.node = node;
        }

        @Override
        public int compareTo(ListNodeWrapper listNodeWrapper) {
            int returnVal = 0;
            if (node.val > listNodeWrapper.node.val) {
                returnVal = 1;
            } else if (node.val < listNodeWrapper.node.val) {
                returnVal = -1;
            }
            return returnVal;
        }
    }

    ListNode mergeKLists(List<ListNode> a) {
        if (a == null || a.size() == 0)
            return null;
        MinHeap<ListNodeWrapper> minHeap = new MinHeap<>(a.size());
        ListNode ansHead = null, ansIter = null;
        for (int i = 0; i < a.size(); i++) {
            ListNode listNode = a.get(i);
            minHeap.insert(new ListNodeWrapper(listNode));
            a.set(i, listNode.next);
        }
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.delete().node;
            if (ansHead == null) {
                ansHead = node;
            } else {
                ansIter.next = node;
            }
            ansIter = node;
            if (node.next != null)
                minHeap.insert(new ListNodeWrapper(node.next));
        }
        return ansHead;
    }

    public static void main(String[] args) {

        MergeKLists mkl = new MergeKLists();
        mkl.mergeKLists(Creator.getList(
                Creator.getListNode(10,20,30),
                Creator.getListNode(15,25)
        )).print();
        mkl.mergeKLists(Creator.getList(
                Creator.getListNode(10,20,30),
                Creator.getListNode(15,25),
                Creator.getListNode(1))).print();
        mkl.mergeKLists(Creator.getList(
                Creator.getListNode(10),
                Creator.getListNode(10,20),
                Creator.getListNode(20,30,40,50))).print();
    }

}
