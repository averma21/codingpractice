package linkedlist;

import org.w3c.dom.NodeList;
import util.Creator;
import util.ListNode;
import util.Verifier;

public class SortList {
    
    private static ListNode getMid(ListNode A) {
        if (A == null)
            return null;
        ListNode first = A, second = A.next;
        while (second != null && second.next != null) {
            first = first.next;
            second = second.next.next;
        }
        return first;
    }

    private static ListNode merge(ListNode A, ListNode B) {
        if (A == null)
            return B;
        if (B == null)
            return A;
        ListNode C, head;
        if (A.val <= B.val) {
            C = A;
            A = A.next;
        } else {
            C = B;
            B = B.next;
        }
        head = C;
        while (A != null && B != null) {
            if (A.val <= B.val) {
                C.next = A;
                A = A.next;
            } else {
                C.next = B;
                B = B.next;
            }
            C = C.next;
        }
        if (A != null)
            C.next = A;
        else if (B != null)
            C.next = B;
        return head;
    }

    static ListNode sortList(ListNode A) {
        if (A == null || A.next == null)
            return A;
        ListNode l1 = A, mid = getMid(A);
        ListNode l2 = mid.next;
        mid.next = null;
        return merge(sortList(l1), sortList(l2));
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(sortList(Creator.getListNode(2)), Creator.getListNode(2));
        Verifier.verifyEquals(sortList(Creator.getListNode(2,1)), Creator.getListNode(1,2));
        Verifier.verifyEquals(sortList(Creator.getListNode(2,3,1)), Creator.getListNode(1,2,3));
        Verifier.verifyEquals(sortList(Creator.getListNode(2,2,1,1)), Creator.getListNode(1,1,2,2));
    }

}
