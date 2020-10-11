package others.leetc.thirtydaychallenge;

import util.Creator;
import util.ListNode;
import util.Verifier;

public class LinkedListMiddle {

    public static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;
        if (head.next.next == null)
            return head.next;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            if (fast.next == null) {
                return slow;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(middleNode(Creator.getListNode(1)).val, 1);
        Verifier.verifyEquals(middleNode(Creator.getListNode(1,2)).val, 2);
        Verifier.verifyEquals(middleNode(Creator.getListNode(1,2,3)).val, 2);
        Verifier.verifyEquals(middleNode(Creator.getListNode(1,2,3,4)).val, 3);
    }
}
