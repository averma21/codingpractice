package linkedlist.round2;

import util.Creator;
import util.ListNode;
import util.Verifier;

public class SwapListNodeInPairs {

    ListNode swap(ListNode A) {
        if (A == null || A.next == null) {
            return A;
        }
        ListNode first = A, second = A.next, prevStart = null;
        ListNode ans = second;
        while (first != null && second != null) {
            ListNode next = second.next;
            second.next = first;
            if (prevStart != null) {
                prevStart.next = second;
            }
            prevStart = first;
            first = next;
            second = first != null ? first.next : null;
        }
        prevStart.next = first;
        return ans;
    }

    public static void main(String[] args) {
        SwapListNodeInPairs sl = new SwapListNodeInPairs();
        Verifier.verifyEquals(sl.swap(Creator.getListNode(1)), Creator.getListNode(1));
        Verifier.verifyEquals(sl.swap(Creator.getListNode(1,2)), Creator.getListNode(2,1));
        Verifier.verifyEquals(sl.swap(Creator.getListNode(1,2,3)), Creator.getListNode(2,1,3));
        Verifier.verifyEquals(sl.swap(Creator.getListNode(1,2,3,4)), Creator.getListNode(2,1,4,3));
        Verifier.verifyEquals(sl.swap(Creator.getListNode(1,2,3,4,5)), Creator.getListNode(2,1,4,3,5));
    }

}
