package util;

import java.util.ArrayList;
import java.util.List;

public class Creator {

    public static <T> List<T> getList(T... elements) {
        List<T> list = new ArrayList<T>(elements.length);
        for (T ele : elements) {
            list.add(ele);
        }
        return list;
    }

    public static ListNode getListNode(int ... elements) {
        ListNode head = null, list = null;
        for (int ele : elements) {
            ListNode node = new ListNode(ele);
            node.next = null;
            if (list == null)
                list = node;
            else {
                list.next = node;
                list = list.next;
            }
            if (head == null)
                head = node;
        }
        return head;
    }

}
