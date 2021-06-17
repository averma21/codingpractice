package linkedlist.round2;

import util.Creator;
import util.ListNode;
import util.Verifier;

public class ReverseLinkedListII {

    public ListNode reverseBetween(ListNode A, int B, int C) {
        if (A == null || A.next == null) {
            return A;
        }
        int step = 1;
        ListNode prev = null, cur = A, start = null, end = null;
        while (cur != null) {
            if (step == B) {
                start = cur;
            }
            ListNode next = cur.next;
            if (step >= B && step <= C) {
                cur.next = prev;
            }
            if (step == C) {
                if (start.next != null)
                    start.next.next = cur;
                start.next = next;
                cur.next = prev;
                break;
            }
            prev = cur;
            cur = next;
            step++;
        }
        return B == 1 ? cur : A;
    }

    public static void main(String[] args) {
        ReverseLinkedListII rl2 = new ReverseLinkedListII();
        Verifier.verifyEquals(rl2.reverseBetween(Creator.getListNode(1,2,3,4,5), 2,3), Creator.getListNode(1,3,2,4,5));
        Verifier.verifyEquals(rl2.reverseBetween(Creator.getListNode(1,2,3,4,5), 2,4), Creator.getListNode(1,4,3,2,5));
        Verifier.verifyEquals(rl2.reverseBetween(Creator.getListNode(1,2,3,4,5), 1,4), Creator.getListNode(4,3,2,1,5));
        Verifier.verifyEquals(rl2.reverseBetween(Creator.getListNode(1,2,3,4,5), 2,5), Creator.getListNode(1,5,4,3,2));
        Verifier.verifyEquals(rl2.reverseBetween(Creator.getListNode(1,2,3,4), 1,4), Creator.getListNode(4,3,2,1));
    }

}
