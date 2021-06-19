package linkedlist.round2;

import util.Creator;
import util.ListNode;
import util.Verifier;

public class ReverseAlternateK {

    ListNode reverse(ListNode A, int B, boolean rev) {
        if (A == null || A.next == null) {
            return A;
        }
        ListNode prev = null, cur = A;
        int count = 1;
        while (cur != null && count <= B) {
            ListNode next = cur.next;
            if (rev) {
                cur.next = prev;
            }
            prev = cur;
            cur = next;
            count++;
        }
        if (rev) {
            A.next = reverse(cur, B, false);
            return prev;
        }
        prev.next = reverse(cur, B, true);
        return A;
    }


    ListNode reverse(ListNode A, int B) {
        return reverse(A, B, true);
    }

    public static void main(String[] args) {
        ReverseAlternateK rak = new ReverseAlternateK();
        Verifier.verifyEquals(rak.reverse(Creator.getListNode(1,2,3,4,5,6), 1), Creator.getListNode(1,2,3,4,5,6));
        Verifier.verifyEquals(rak.reverse(Creator.getListNode(1,2,3,4,5,6), 2), Creator.getListNode(2,1,3,4,6,5));
        Verifier.verifyEquals(rak.reverse(Creator.getListNode(1,2,3,4,5,6), 3), Creator.getListNode(3,2,1,4,5,6));
        Verifier.verifyEquals(rak.reverse(Creator.getListNode(1,2,3,4,5,6), 4), Creator.getListNode(4,3,2,1,5,6));
        Verifier.verifyEquals(rak.reverse(Creator.getListNode(1,2,3,4,5,6,7,8,9,10), 4), Creator.getListNode(4,3,2,1,5,6,7,8,10,9));
    }

}
