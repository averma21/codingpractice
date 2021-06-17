package linkedlist.round2;

import util.Creator;
import util.ListNode;
import util.Verifier;

public class ReorderList {

    ListNode reverse(ListNode A) {
        ListNode prev = null, cur = A;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public ListNode reorder(ListNode A) {
        if (A == null || A.next == null) {
            return A;
        }
        ListNode slow = A, fast = A;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode list1 = A, list2 = slow.next;
        slow.next = null;
        list2 = reverse(list2);
        while (list1 != null && list2 != null) {
            ListNode l1n = list1.next;
            ListNode l2n = list2.next;
            list1.next = list2;
            list2.next = l1n;
            list1 = l1n;
            list2 = l2n;
        }
        return A;
    }

    public static void main(String[] args) {
        ReorderList rl = new ReorderList();
        Verifier.verifyEquals(rl.reorder(Creator.getListNode(1)), Creator.getListNode(1));
        Verifier.verifyEquals(rl.reorder(Creator.getListNode(1,2)), Creator.getListNode(1,2));
        Verifier.verifyEquals(rl.reorder(Creator.getListNode(1,2,3)), Creator.getListNode(1,3,2));
        Verifier.verifyEquals(rl.reorder(Creator.getListNode(1,2,3,4)), Creator.getListNode(1,4,2,3));
        Verifier.verifyEquals(rl.reorder(Creator.getListNode(1,2,3,4,5)), Creator.getListNode(1,5,2,4,3));
    }

}
