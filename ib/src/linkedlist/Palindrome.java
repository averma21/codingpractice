package linkedlist;

import util.Creator;
import util.ListNode;
import util.Verifier;

public class Palindrome {

    private static ListNode reverse(ListNode head) {
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    private static ListNode getMid(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode cur = head, list2 = head.next;
        while (list2 != null && list2.next != null) {
            cur = cur.next;
            list2 = list2.next.next;
        }
        return cur;
    }

    private static int isPalindrome(ListNode A) {
        if (A == null || A.next == null)
            return 1;
        ListNode mid = getMid(A);
        ListNode list1 = A;
        ListNode list2 = reverse(mid.next);
        mid.next = null;
        while (list1 != null && list2 != null) {
            if (list1.val != list2.val)
                return 0;
            list1 = list1.next;
            list2 = list2.next;
        }
        return 1;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(isPalindrome(Creator.getListNode(1)), 1);
        Verifier.verifyEquals(isPalindrome(Creator.getListNode(1,2)), 0);
        Verifier.verifyEquals(isPalindrome(Creator.getListNode(1,2,1)), 1);
        Verifier.verifyEquals(isPalindrome(Creator.getListNode(1,2,2,1)), 1);
    }

}
