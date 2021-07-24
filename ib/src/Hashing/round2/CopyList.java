package Hashing.round2;

public class CopyList {

    private static class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    public RandomListNode copyRandomList(RandomListNode head) {

        if (head == null) {
            return null;
        }

        if (head.next == null) {
            RandomListNode node = new RandomListNode(head.label);
            if (head.random == head) {
                node.random = node;
            }
            return node;
        }

        RandomListNode temp = head;
        while (temp != null) {
            RandomListNode copy = new RandomListNode(temp.label);
            RandomListNode next = temp.next;
            temp.next = copy;
            copy.next = next;
            temp = next;
        }

        temp = head;
        while (temp != null) {
            if (temp.random != null) {
                temp.next.random = temp.random.next;
            }
            temp = temp.next.next; // number of nodes is even, so we are safe wrt NPE
        }

        RandomListNode ans = head.next;
        RandomListNode prev = head;
        RandomListNode cur = head.next;
        while (cur != null) {
            prev.next = cur.next;
            RandomListNode next1 = cur.next;
            RandomListNode next2 = next1 != null ? next1.next : null;
            cur.next = next2;
            prev = next1;
            cur = next2;
        }

        return ans;
    }

}
