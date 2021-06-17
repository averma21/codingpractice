package linkedlist.round2;

import util.Creator;
import util.ListNode;
import util.Verifier;

public class RemoveDuplicatesII {

    ListNode remove(ListNode A) {
        if (A == null || A.next == null) {
            return A;
        }
        ListNode prev = null, ans = null, anscur = null, cur = A;
        int count = 0;
        while (cur != null) {
            if (prev == null || cur.val == prev.val) {
                count++;
            } else {
                if (count == 1) {
                    if (ans == null) {
                        ans = prev;
                        anscur = prev;
                    } else {
                        anscur.next = prev;
                        anscur = prev;
                    }
                    anscur.next = null;
                }
                count = 1;
            }
            prev = cur;
            cur = cur.next;
        }
        if (count == 1) {
            if (ans == null) {
                ans = prev;
                anscur = prev;
            } else {
                anscur.next = prev;
                anscur = prev;
            }
            anscur.next = null;
        }
        return ans;
    }

    public static void main(String[] args) {
        RemoveDuplicatesII rd2 = new RemoveDuplicatesII();
        Verifier.verifyEquals(rd2.remove(Creator.getListNode(1,1,2,3)), Creator.getListNode(2,3));
        Verifier.verifyEquals(rd2.remove(Creator.getListNode(1,2,3)), Creator.getListNode(1,2,3));
        Verifier.verifyEquals(rd2.remove(Creator.getListNode(1,1,2,2,3,3)), null);
        Verifier.verifyEquals(rd2.remove(Creator.getListNode(1,2,2,3,3)), Creator.getListNode(1));
        Verifier.verifyEquals(rd2.remove(Creator.getListNode(1,2,2,3,4,5,5)), Creator.getListNode(1,3,4));
    }

}
