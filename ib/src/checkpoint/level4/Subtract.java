package checkpoint.level4;

import util.Creator;
import util.ListNode;
import util.Verifier;

public class Subtract {

    private static ListNode getMid(ListNode A) {
        if (A == null || A.next == null) {
            return A;
        }
        ListNode slow = A, fast = A.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static ListNode reverse(ListNode A) {
        if (A == null)
            return A;
        ListNode prev = null, cur = A;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public static ListNode subtract(ListNode A) {
        if (A == null || A.next == null)
            return A;
        if (A.next.next == null) {
            A.val = A.next.val - A.val;
            return A;
        }
        ListNode mid = getMid(A);
        ListNode list1 = A, list2 = mid.next;
        mid.next = null;
        list2 = reverse(list2);
        ListNode lastNode = list1;
        ListNode backUp = list2;
        while (list1 != null) { // list1 would be smaller or equal to list2 in length
            list1.val = list2.val - list1.val;
            lastNode = list1;
            list1 = list1.next;
            list2 = list2.next;
        }
        lastNode.next = reverse(backUp);
        return A;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(subtract(Creator.getListNode(1)), Creator.getListNode(1));
        Verifier.verifyEquals(subtract(Creator.getListNode(1,2)), Creator.getListNode(1,2));
        Verifier.verifyEquals(subtract(Creator.getListNode(1,2,3)), Creator.getListNode(2,2,3));
        Verifier.verifyEquals(subtract(Creator.getListNode(1,2,3,4)), Creator.getListNode(3,1,3,4));
        Verifier.verifyEquals(subtract(Creator.getListNode(1,2,3,4,5)), Creator.getListNode(4,2,3,4,5));
    }

}
