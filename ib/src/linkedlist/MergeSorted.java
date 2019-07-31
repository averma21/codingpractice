package linkedlist;

import util.Creator;
import util.ListNode;
import util.Verifier;


public class MergeSorted {

    public static ListNode merge(ListNode A, ListNode B) {
        ListNode head = null;
        if (A == null && B == null)
            return null;
        if (A == null)
            return B;
        if (B == null)
            return A;
        ListNode listTillNow = null;
        while (A != null && B != null) {
            ListNode current;
            if (A.val <= B.val) {
                current = A;
                A = A.next;
            } else {
                current = B;
                B = B.next;
            }
            if (listTillNow == null) {
                listTillNow = current;
            } else {
                listTillNow.next = current;
                listTillNow = current;
            }
            if (head == null)
                head = current;
        }
        if (A != null) {
            listTillNow.next = A;
        }
        if (B != null)
            listTillNow.next = B;
        return head;
    }

//    private static List<Integer> merge(List<Integer> A, List<Integer> B) {
//        if (A == null && B == null)
//            return null;
//        if (A == null) {
//            return new ArrayList<>(B);
//        }
//        if (B == null) {
//            return new ArrayList<>(A);
//        }
//        List<Integer> C = new ArrayList<>(A.size() + B.size());
//        int i = 0, j = 0;
//        while (i < A.size() && j < B.size()) {
//            int ai = A.get(i), bj = B.get(j);
//            if (ai <= bj) {
//                C.add(ai);
//                i++;
//            } else {
//                C.add(bj);
//                j++;
//            }
//        }
//        while (i < A.size())
//            C.add(A.get(i++));
//        while (j < B.size())
//            C.add(B.get(j++));
//        return C;
//    }

    public static void main(String[] args) {
        ListNode A = Creator.getListNode(10,20,30);
        ListNode B = Creator.getListNode(20,25,30);
        A.print();
        B.print();
        merge(A, B).print();
    }

}
