package others.leetc.linkedlist;

import util.Creator;
import util.ListNode;
import util.Verifier;

public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        if (count < k) {
            // less than k elements
            return head;
        }
        //reverse current part
        cur = head;
        ListNode prev= null;
        for (int i  = 0; i < k && cur != null; i++) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        head.next = reverseKGroup(cur, k);
        return prev;
    }

    public static void main(String[] args) {
        ReverseNodesInKGroup rn = new ReverseNodesInKGroup();
        Verifier.verifyEquals(rn.reverseKGroup(Creator.getListNode(1,2,3,4,5), 3), Creator.getListNode(3,2,1,4,5));
    }

}
