package others.leetc.contests.twohundred.sixty.seven;

import util.Creator;
import util.ListNode;
import util.Verifier;

import java.util.ArrayList;
import java.util.List;

public class ReverseEvenLength {

    private ListNode reverse(ListNode start) {
        ListNode cur = start, prev = null, next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    int size(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    ListNode cutAt(ListNode cur, int pos) {
        if (pos == 0) {
            return cur;
        }
        int i = 0;
        ListNode toReturn = null;
        while (i < pos && cur != null) {
            i++;
            if (i == pos) {
                toReturn = cur.next;
                cur.next = null;
                break;
            }
            cur = cur.next;
        }
        return toReturn;
    }

    private List<ListNode> breakList(ListNode head) {
        int breakPoint = 1;
        List<ListNode> ans = new ArrayList<>();
        ListNode next;
        do {
            next = cutAt(head, breakPoint);
            ans.add(head);
            head = next;
            breakPoint++;
        } while (next != null);
        return ans;
    }

    public ListNode reverseEvenLengthGroups(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        List<ListNode> lists = breakList(head);
        ListNode ans = lists.get(0);
        ListNode cur = ans;
        for (int i = 1; i < lists.size() - 1; i++) {
            if (i % 2 == 1) {
                cur.next = reverse(lists.get(i));
            } else {
                cur.next = lists.get(i);
            }
            while (cur.next != null) {
                cur = cur.next;
            }
        }
        ListNode lastList = lists.get(lists.size() - 1);
        int lastSIze = size(lastList);
        if (lastSIze % 2 == 0) {
            cur.next = reverse(lastList);
        } else {
            cur.next = lastList;
        }
        return ans;
    }

    public static void main(String[] args) {
        ReverseEvenLength rel = new ReverseEvenLength();
        Verifier.verifyEquals(rel.reverseEvenLengthGroups(Creator.getListNode(1)), Creator.getListNode(1));
        Verifier.verifyEquals(rel.reverseEvenLengthGroups(Creator.getListNode(1,2)), Creator.getListNode(1,2));
        Verifier.verifyEquals(rel.reverseEvenLengthGroups(Creator.getListNode(1,2,3)), Creator.getListNode(1,3,2));
        Verifier.verifyEquals(rel.reverseEvenLengthGroups(Creator.getListNode(1,2,3,4,5)), Creator.getListNode(1,3,2,5,4));
        Verifier.verifyEquals(rel.reverseEvenLengthGroups(Creator.getListNode(10,20,30,40,50,60,70)), Creator.getListNode(10,30,20,40,50,60,70));
    }

}
