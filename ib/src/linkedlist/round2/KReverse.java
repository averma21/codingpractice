package linkedlist.round2;

import util.Creator;
import util.ListNode;
import util.Verifier;

public class KReverse {

    public ListNode kReverse(ListNode A, int B) {
        int count = 0;
        ListNode prev = null, cur = A, ans = null, prevListStart = null, curListStart = A;
        while (cur != null) {
            count++;
            if (count == B) {
                count = 0;
                if (ans == null) {
                    ans = cur;
                }
                if (prevListStart != null) {
                    prevListStart.next = cur;
                }
                prevListStart = curListStart;
                curListStart = cur.next;
            }
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        if (prevListStart != null)
            prevListStart.next = null;
        return ans;
    }

    public static void main(String[] args) {
        KReverse kr = new KReverse();
        Verifier.verifyEquals(kr.kReverse(Creator.getListNode(1,2), 1), Creator.getListNode(1,2));
        Verifier.verifyEquals(kr.kReverse(Creator.getListNode(1,2), 2), Creator.getListNode(2,1));
        Verifier.verifyEquals(kr.kReverse(Creator.getListNode(1,2,3,4), 2), Creator.getListNode(2,1,4,3));
        Verifier.verifyEquals(kr.kReverse(Creator.getListNode(1,2,3,4,5,6), 3), Creator.getListNode(3,2,1,6,5,4));
        Verifier.verifyEquals(kr.kReverse(Creator.getListNode(1,2,3,4,5,6), 2), Creator.getListNode(2,1,4,3,6,5));
        Verifier.verifyEquals(kr.kReverse(Creator.getListNode(1,2,3,4,5), 5), Creator.getListNode(5,4,3,2,1));
    }

}
