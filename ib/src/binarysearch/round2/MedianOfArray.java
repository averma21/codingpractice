package binarysearch.round2;

import util.Creator;
import util.Verifier;

import java.util.List;

//https://www.youtube.com/watch?v=LPFhl65R7ww&t=1217s&ab_channel=TusharRoy-CodingMadeSimple
public class MedianOfArray {

    private double findMedian(List<Integer> A, int start, int end) {
        if (A == null || start > end) {
            return 0;
        }
        int n = end - start + 1;
        return n%2 == 0 ? (A.get(start + n/2) + A.get(start + n/2 - 1))/2.0 : A.get(start + n/2);
    }

    private double getMedian(int x1, int y1, int x2, int y2, boolean isEven) {
        if (isEven) {
            return (Math.max(x1, y1) + Math.min(x2, y2))/2.0;
        }
        return Math.max(x1, y1);
    }

    public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
        if (a == null || a.size() == 0) {
            return findMedian(b, 0, b.size() - 1);
        }
        if (b == null || b.size() == 0) {
            return findMedian(a, 0, a.size() - 1);
        }
        List<Integer> smaller, larger;
        if (a.size() < b.size()) {
            smaller = a;
            larger = b;
        } else {
            larger = a;
            smaller = b;
        }
        int smallerSize = smaller.size(), largerSize = larger.size();
        int start = 0, end = smaller.size();
        int x = (smallerSize + largerSize + 1)/2;
        boolean isEven = (smallerSize + largerSize) % 2 == 0;
        while (start <= end) {
            int smallerListIndex = (start + end)/2;
            int largerListIndex = x - smallerListIndex;
            int x1 = smallerListIndex == 0 ? Integer.MIN_VALUE : smaller.get(smallerListIndex - 1);
            int y1 = largerListIndex == 0 ? Integer.MIN_VALUE : larger.get(largerListIndex - 1);
            int x2 = smallerListIndex == smallerSize ? Integer.MAX_VALUE : smaller.get(smallerListIndex);
            int y2 = largerListIndex == largerSize ? Integer.MAX_VALUE : larger.get(largerListIndex);

            if (x1 <= y2 && y1 <= x2) {
                return getMedian(x1, y1, x2, y2, isEven);
            }
            if (x1 > y2) {
                end = smallerListIndex - 1;
            } else {
                start = smallerListIndex + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MedianOfArray moa = new MedianOfArray();
        List<Integer> a = Creator.getList(6, 10, 15, 28, 40, 60);
        List<Integer> b = Creator.getList(20, 40, 80, 120);
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 34);
        b = Creator.getList(20);
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 20);
        b = Creator.getList(15);
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 15);
        b = Creator.getList(10);
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 15);
        b = Creator.getList(14);
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 15);
        b = Creator.getList(1);
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 15);
        b = Creator.getList(20, 40, 80, 120);
        a = Creator.getList(15);
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 40);
        a = Creator.getList(15, 40);
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 40);
        a = Creator.getList(125);
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 80);
        a = Creator.getList(85, 85, 85);
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 85);
        a = Creator.getList(85, 125);
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 82.5);
        a = Creator.getList(-43, -25, -18, -15, -10, 9, 39, 40);
        b = Creator.getList(-2);
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), -10);
        a = Creator.getList(0, 23);
        b = Creator.getList();
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 11.5);
        a = Creator.getList(-50, -42, -38, 1, 4, 9, 16, 33, 47);
        b = Creator.getList(-44);
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 2.5);
    }

}
