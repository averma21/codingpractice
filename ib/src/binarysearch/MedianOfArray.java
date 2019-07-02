package binarysearch;

import util.Verifier;

import java.util.Arrays;
import java.util.List;

public class MedianOfArray {

    private Double findMedian(final List<Integer> a, final List<Integer> b, int expectedElementsOnRight) {
        int aSize = a.size(), bSize = b.size();
        int low = 0, high = aSize - 1;

        while (low <= high) {
            int mid = (low + high)/2;
            int aMid = a.get(mid);
            int elementsFromA = aSize - mid - 1;
            int expectedElementsFromB = expectedElementsOnRight - elementsFromA;
            if (expectedElementsFromB < 0) {
                low = mid + 1;
                continue;
            } else if (expectedElementsFromB > bSize) {
                high = mid - 1;
                continue;
            }
            int expectedBPos = bSize - expectedElementsFromB - 1;
            if (expectedBPos == -1) {
                if (aMid <= b.get(0)) {
                    return (double)aMid;
                } else {
                    high = mid - 1;
                }
            } else if (expectedBPos == bSize - 1) {
                if (aMid >= b.get(bSize - 1)) {
                    return (double)aMid;
                } else {
                    low = mid + 1;
                }
            } else {
                if (aMid <= b.get(expectedBPos + 1)) {
                    if (aMid >= b.get(expectedBPos))
                        return (double)aMid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return null;
    }

    // DO NOT MODIFY BOTH THE LISTS
    public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
        if (a == null && b == null)
            return -1;
        if (b == null || b.size() == 0) {
            int aSize = a == null ? 0 : a.size();
            if (aSize == 0)
                return -1;
            if (aSize % 2 == 0) {
                return ((double)a.get(aSize/2) + (double)a.get(aSize/2 - 1))/2;
            } else {
                return a.get(aSize/2);
            }
        }
        if (a == null || a.size() == 0) {
            int bSize = b.size();
            if (bSize % 2 == 0) {
                return ((double)b.get(bSize/2) + (double)b.get(bSize/2 - 1))/2;
            } else {
                return b.get(bSize/2);
            }
        }
        int aSize = a.size(), bSize = b.size();
        long total = aSize + (long)bSize;
        if (total % 2 == 0) {
            int med1Pos = (int)(total/2);
            int med2Pos = med1Pos - 1;
            Double med1 = findMedian(a, b, (int)(total - med1Pos - 1));
            if (med1 == null) {
                med1 = findMedian(b, a, (int)(total - med1Pos - 1));
            }
            Double med2 = findMedian(a, b, (int)(total - med2Pos - 1));
            if (med2 == null) {
                med2 = findMedian(b, a, (int)(total - med2Pos - 1));
            }
            return (med1 + med2)/2;
        } else {
            int med1Pos = (int)(total/2);
            Double med1 = findMedian(a, b, (int)(total - med1Pos - 1));
            if (med1 == null) {
                med1 = findMedian(b, a, (int)(total - med1Pos - 1));
            }
            return med1;
        }
    }

    public static void main(String[] args) {
        MedianOfArray moa = new MedianOfArray();
        List<Integer> a = Arrays.asList(new Integer[]{6, 10, 15, 28, 40, 60});
        List<Integer> b = Arrays.asList(new Integer[]{20, 40, 80, 120});
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 34);
        b = Arrays.asList(new Integer[]{20});
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 20);
        b = Arrays.asList(new Integer[]{15});
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 15);
        b = Arrays.asList(new Integer[]{10});
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 15);
        b = Arrays.asList(new Integer[]{14});
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 15);
        b = Arrays.asList(new Integer[]{1});
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 15);
        b = Arrays.asList(new Integer[]{20, 40, 80, 120});
        a = Arrays.asList(new Integer[]{15});
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 40);
        a = Arrays.asList(new Integer[]{15, 40});
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 40);
        a = Arrays.asList(new Integer[]{125});
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 80);
        a = Arrays.asList(new Integer[]{85, 85, 85});
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 85);
        a = Arrays.asList(new Integer[]{85, 125});
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 82.5);
        a = Arrays.asList(new Integer[]{-43, -25, -18, -15, -10, 9, 39, 40});
        b = Arrays.asList(new Integer[]{-2});
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), -10);
        a = Arrays.asList(new Integer[]{0, 23});
        b = Arrays.asList(new Integer[]{});
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 11.5);
        a = Arrays.asList(new Integer[]{-50, -42, -38, 1, 4, 9, 16, 33, 47});
        b = Arrays.asList(new Integer[]{-44});
        Verifier.verifyEquals(moa.findMedianSortedArrays(a, b), 2.5);
    }
}
