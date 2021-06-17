package linkedlist.round2;

import util.Creator;
import util.ListNode;
import util.Verifier;

public class SortList {

    ListNode merge(ListNode A, ListNode B) {
        ListNode head = null, cur = null;
        while (A != null && B != null) {
            ListNode small;
            if (A.val < B.val) {
                small = A;
                A = A.next;
            } else {
                small = B;
                B = B.next;
            }
            if (head == null) {
                head = small;
            } else {
                cur.next = small;
            }
            cur = small;
        }
        while (A != null) {
            if (head == null) {
                head = A;
            } else {
                cur.next = A;
            }
            cur = A;
            A = A.next;
        }
        while (B != null) {
            if (head == null) {
                head = B;
            } else {
                cur.next = B;
            }
            cur = B;
            B = B.next;
        }
        return head;
    }

    ListNode sortList(ListNode A, int size) {
        if (A == null || A.next == null) {
            return A;
        }
        int s1 = 0;
        ListNode next = A, prev = null;
        while (s1 < size/2) {
            prev = next;
            next = next.next;
            s1++;
        }
        prev.next = null;
        ListNode left = sortList(A, s1);
        ListNode right = sortList(next, size - s1);
        return merge(left, right);
    }

    public ListNode sortList(ListNode A) {
        if (A == null || A.next == null) {
            return A;
        }
        int size = 0;
        for (ListNode n = A; n != null; n = n.next) {
            size++;
        }
        return sortList(A, size);
    }

    public static void main(String[] args) {
        SortList sl = new SortList();
        Verifier.verifyEquals(sl.sortList(Creator.getListNode(2)), Creator.getListNode(2));
        Verifier.verifyEquals(sl.sortList(Creator.getListNode(2,1)), Creator.getListNode(1,2));
        Verifier.verifyEquals(sl.sortList(Creator.getListNode(2,3,1)), Creator.getListNode(1,2,3));
        Verifier.verifyEquals(sl.sortList(Creator.getListNode(2,2,1,1)), Creator.getListNode(1,1,2,2));
    }

}
