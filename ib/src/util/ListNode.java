package util;

public class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    public void print() {
        System.out.print(val + "->");
        if (next != null) {
            next.print();
        } else {
            System.out.println("EOL");
        }
    }
}
