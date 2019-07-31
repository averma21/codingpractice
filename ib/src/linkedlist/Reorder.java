package linkedlist;

import util.Creator;
import util.ListNode;
import util.Verifier;

public class Reorder {

    private static ListNode reverse(ListNode head) {
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    private static ListNode getMid(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode cur = head, list2 = head.next;
        while (list2 != null && list2.next != null) {
            cur = cur.next;
            list2 = list2.next.next;
        }
        return cur;
    }

    private static ListNode reorder(ListNode A) {
        if (A == null || A.next == null || A.next.next == null)
            return A;
        ListNode list1 = A;
        ListNode mid = getMid(A);
        ListNode list2 = reverse(mid.next);
        mid.next = null;
        while (list1 != null) {
            ListNode next1 = list1.next;
            list1.next = list2;
            ListNode next2 = null;
            if (list2 != null) {
                next2 = list2.next;
                list2.next = next1;
            }
            list2 = next2;
            list1 = next1;
        }
        return A;
    }

    public static void main(String[] args) {
        reverse(Creator.getListNode(2)).print();
        reverse(Creator.getListNode(2,3)).print();
        reverse(Creator.getListNode(2,3,4)).print();
        reorder(Creator.getListNode(1)).print();
        Verifier.verifyEquals(reorder(Creator.getListNode(1,2)), Creator.getListNode(1,2));
        Verifier.verifyEquals(reorder(Creator.getListNode(1,2,3)), Creator.getListNode(1,3,2));
        Verifier.verifyEquals(reorder(Creator.getListNode(1,2,3,4)), Creator.getListNode(1,4,2,3));
    }

}
