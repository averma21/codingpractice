package binarysearch;

import util.Creator;
import util.Verifier;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AllocateBooks {

    public int books(List<Integer> A, int B) {
        int len = A.size();
        if(len < B) return -1;
        int total =0 ; int max = Integer.MIN_VALUE;
        for(int i=0;i<len;i++){
            total += A.get(i);
            max = Math.max(max,A.get(i));
        }

        int lo = max;int hi = total;
        while(lo < hi) {
            int mid = lo + (hi-lo)/2;
            int requiredReaders = getRequiredReaders(A, mid);
            if(requiredReaders <= B) {
                hi = mid;
            }else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private int getRequiredReaders(List<Integer> a, int maxLengthPerReader) {
        int total = 0;
        int readers = 1;
        for(int i = 0; i < a.size(); i++) {
            total += a.get(i);
            if(total > maxLengthPerReader) {
                total = a.get(i);
                readers++;
            }
        }
        return readers;
    }

    public static void main(String[] args) {
        AllocateBooks books = new AllocateBooks();
//        Verifier.verifyEquals(books.books(Arrays.asList(new Integer[]{12, 34, 67, 90}), 2), 113);
//        Verifier.verifyEquals(books.books(Arrays.asList(new Integer[]{67, 34, 90, 12}), 2), 102);
//        Verifier.verifyEquals(books.books(Arrays.asList(new Integer[]{34, 14, 19, 75}), 12), -1);
//        Verifier.verifyEquals(books.books(Creator.getList( 97, 26, 12, 67, 10, 33, 79, 49, 79, 21, 67, 72, 93,
//                36, 85, 45, 28, 91, 94, 57, 1, 53, 8, 44, 68, 90, 24 ), 26), 97);
        Verifier.verifyEquals(books.books(Creator.getList(  31, 14, 19, 75 ), 12), -1);
    }

}
