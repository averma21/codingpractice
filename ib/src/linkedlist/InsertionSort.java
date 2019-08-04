package linkedlist;

import util.Creator;
import util.ListNode;
import util.Verifier;

public class InsertionSort {

    static ListNode insertionSort(ListNode A) {
        if (A == null)
            return A;
        ListNode list2 = A;
        A = A.next;
        list2.next = null;
        while (A != null) {
            ListNode aNext = A.next;
            ListNode newPtr = list2;
            ListNode prevNew = null;
            while (newPtr != null && newPtr.val <= A.val) {
                prevNew = newPtr;
                newPtr = newPtr.next;
            }
            if (prevNew != null) {
                ListNode newNext = prevNew.next;
                prevNew.next = A;
                A.next = newNext;
            } else {
                A.next = list2;
                list2 = A;
            }
            A = aNext;
        }
        return list2;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(insertionSort(Creator.getListNode(1)), Creator.getListNode(1));
        Verifier.verifyEquals(insertionSort(Creator.getListNode(1,2)), Creator.getListNode(1,2));
        Verifier.verifyEquals(insertionSort(Creator.getListNode(1,2,3)), Creator.getListNode(1,2,3));
        Verifier.verifyEquals(insertionSort(Creator.getListNode(2,1)), Creator.getListNode(1,2));
        Verifier.verifyEquals(insertionSort(Creator.getListNode(2,3,1)), Creator.getListNode(1,2,3));
        Verifier.verifyEquals(insertionSort(Creator.getListNode(2,3,1,2)), Creator.getListNode(1,2,2,3));
        Verifier.verifyEquals(insertionSort(Creator.getListNode(5,5,2,3,1,2)), Creator.getListNode(1,2,2,3,5,5));
    }

}
