package binarysearch.round2;

import util.Creator;
import util.Verifier;

import java.util.List;

public class AllocateBooks {

    public int books(List<Integer> A, int B) {
        int sum = 0;
        if(A.size() < B) return -1;
        int maxPagesInABook = Integer.MIN_VALUE;
        for (int a: A) {
            sum += a;
            maxPagesInABook = Math.max(a, maxPagesInABook);
        }
        int low = Math.max((int)Math.ceil(sum*1.0/B), maxPagesInABook);
        int high = sum;
        while (low <= high) {
            int mid = (low + high) / 2;
            int readersRequired = getReaders(A, mid);
            if (readersRequired > B) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private int getReaders(List<Integer> bookPages, int maxPagesPerReader) {
        int readers = 1;
        int pagesAssignedToCurReader = 0;
        for (int i = 0; i < bookPages.size(); i++) {
            int pages = bookPages.get(i);
            pagesAssignedToCurReader += pages;
            if (pagesAssignedToCurReader > maxPagesPerReader) {
                readers++;
                pagesAssignedToCurReader = pages;
            }
        }
        return readers;
    }

    public static void main(String[] args) {
        AllocateBooks books = new AllocateBooks();
        Verifier.verifyEquals(books.books(Creator.getList(12, 34, 67, 90), 2), 113);
        Verifier.verifyEquals(books.books(Creator.getList(67, 34, 90, 12), 2), 102);
        Verifier.verifyEquals(books.books(Creator.getList(34, 14, 19, 75), 12), -1);
        Verifier.verifyEquals(books.books(Creator.getList( 97, 26, 12, 67, 10, 33, 79, 49, 79, 21, 67, 72, 93,
                36, 85, 45, 28, 91, 94, 57, 1, 53, 8, 44, 68, 90, 24 ), 26), 97);
        Verifier.verifyEquals(books.books(Creator.getList(  31, 14, 19, 75 ), 12), -1);
    }

}
