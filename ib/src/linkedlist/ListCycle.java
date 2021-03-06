package linkedlist;

import util.Creator;
import util.ListNode;
import util.Verifier;

public class ListCycle {

    private static ListNode getFirstInCycle(ListNode a) {
        if (a == null || a.next == null)
            return null;
        ListNode slow = a, fast = a;
        while (fast != null) {
            if (fast.next == null)
                return null;
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }
        if (fast == null)
            return null;
        slow = a;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    static void verify(ListNode x, ListNode y) {
        if (x != y)
            throw new RuntimeException("Unequal x y");
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(getFirstInCycle(Creator.getListNode(1)), null);
        Verifier.verifyEquals(getFirstInCycle(Creator.getListNode(1,2)), null);
        Verifier.verifyEquals(getFirstInCycle(Creator.getListNode(1,2,3)), null);
        ListNode node = new ListNode(1);
        node.next = node;
        verify(getFirstInCycle(node), node);
        node.next = new ListNode(2);
        node.next.next = node;
        verify(getFirstInCycle(node), node);
        node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = node.next;
        verify(getFirstInCycle(node), node.next);
        node = new ListNode(1);
        ListNode four = node.add(2).add(3).add(4);
        four.add(5).add(6).add(7).add(four);
        verify(getFirstInCycle(node), four);
    }

}
