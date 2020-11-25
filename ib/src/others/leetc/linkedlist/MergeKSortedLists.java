package others.leetc.linkedlist;

import util.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null, cur = null;
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(Comparator.comparingInt(ln -> ln.val));
        for (ListNode node : lists) {
            heap.add(node);
        }
        while (!heap.isEmpty()) {
            if (head == null) {
                head = heap.remove();
                cur = head;
            } else {
                cur.next = heap.remove();
                cur = cur.next;
            }
            if (cur.next != null) {
                heap.add(cur.next);
            }
        }
        return head;
    }

}
