package others.leetc.linkedlist;

public class CopyListWithRandomPointer {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node ptr = head;
        Node newHead = null;
        while (ptr != null) {
            Node newNode = new Node(ptr.val);
            if (newHead == null) {
                newHead = newNode;
            }
            Node next = ptr.next;
            newNode.next = next;
            ptr.next = newNode;
            ptr = next;
        }
        ptr = head;
        while (ptr != null) {
            ptr.next.random = ptr.random != null ? ptr.random.next : null;
            ptr = ptr.next.next;
        }
        ptr = head;
        while (ptr != null) {
            Node copied = ptr.next;
            Node nextPtr = ptr.next.next;
            if (nextPtr != null) {
                copied.next = nextPtr.next;
            }
            ptr.next = nextPtr;
            ptr = nextPtr;
        }
        return newHead;
    }

    public static void main(String[] args) {
        CopyListWithRandomPointer clw = new CopyListWithRandomPointer();
        Node seven = new Node(7);
        Node thirteen = new Node(13);
        Node eleven = new Node(11);
        Node ten = new Node(10);
        Node one = new Node(1);
        seven.next= thirteen;
        thirteen.next = eleven;
        eleven.next = ten;
        ten.next = one;
        thirteen.random = seven;
        eleven.random = one;
        ten.random = eleven;
        one.random = seven;
        clw.copyRandomList(seven);
    }

}
