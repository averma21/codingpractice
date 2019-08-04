package util;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode add(int x) {
        this.next = new ListNode(x);
        return this.next;
    }

    public ListNode add(ListNode node) {
        this.next = node;
        return node;
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
