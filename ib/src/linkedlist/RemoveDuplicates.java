package linkedlist;

import util.Creator;
import util.ListNode;
import util.Verifier;

public class RemoveDuplicates {

    static ListNode removeDup(ListNode A) {
        if (A == null || A.next == null)
            return A;
        ListNode first = A, second = A.next;
        while (second != null) {
            if (second.val != first.val) {
                first.next = second;
                first = second;
            }
            second = second.next;
        }
        first.next = null;
        return A;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(removeDup(Creator.getListNode(1)), Creator.getListNode(1));
        Verifier.verifyEquals(removeDup(Creator.getListNode(1,2)), Creator.getListNode(1,2));
        Verifier.verifyEquals(removeDup(Creator.getListNode(1,1,2)), Creator.getListNode(1,2));
        Verifier.verifyEquals(removeDup(Creator.getListNode(1,2,2)), Creator.getListNode(1,2));
        Verifier.verifyEquals(removeDup(Creator.getListNode(1,2,2,3)), Creator.getListNode(1,2,3));
        Verifier.verifyEquals(removeDup(Creator.getListNode(1,2,2,2,3)), Creator.getListNode(1,2,3));
    }

}
