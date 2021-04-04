package arrays;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.List;


//https://www.interviewbit.com/problems/maximum-unsorted-subarray/
public class MaximumUnsortedSubArray {

    /**
     * https://www.geeksforgeeks.org/minimum-length-unsorted-subarray-sorting-which-makes-the-complete-array-sorted/
     * Solution:
     * 1) Find the candidate unsorted subarray
     *    * a) Scan from left to right and find the first element which is greater than the next element. Let s be the index of such an element. In the above example 1, s is 3 (index of 30).
     *    * b) Scan from right to left and find the first element (first in right to left order) which is smaller than the next element (next in right to left order). Let e be the index of such an element. In the above example 1, e is 7 (index of 31).
     * 2) Check whether sorting the candidate unsorted subarray makes the complete array sorted or not. If not, then include more elements in the subarray.
     *    * a) Find the minimum and maximum values in arr[s..e]. Let minimum and maximum values be min and max. min and max for [30, 25, 40, 32, 31] are 25 and 40 respectively.
     *    * b) Find the first element (if there is any) in arr[0..s-1] which is greater than min, change s to index of this element. There is no such element in above example 1.
     *    * c) Find the last element (if there is any) in arr[e+1..n-1] which is smaller than max, change e to index of this element. In the above example 1, e is changed to 8 (index of 35)
     * 3) Print s and e.
     * @param A list
     * @return list
     */
    public ArrayList<Integer> subUnsort(List<Integer> A) {
        ArrayList<Integer> nop = new ArrayList<>();
        nop.add(-1);
        if (A == null || A.size() <= 1) {
            return nop;
        }
        int s = -1, e = -1;
        int prev = A.get(0);
        int n = A.size();
        for (int i = 1; i < n; i++) {
            int ai = A.get(i);
            if (prev > ai) {
                s = i - 1;
                break;
            }
            prev = ai;
        }
        prev = A.get(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            int ai = A.get(i);
            if (prev < ai) {
                e = i + 1;
                break;
            }
            prev = ai;
        }
        if (s == -1 && e == -1) {
            return nop;
        }
        int min = A.get(s), max = A.get(s);
        for (int i = s; i <= e; i++) {
            int ai = A.get(i);
            if (ai < min) {
                min = ai;
            } else if (ai > max) {
                max = ai;
            }
        }
        for (int i = 0; i < s; i++) {
            int ai = A.get(i);
            if (ai > min) {
                s = i;
                break;
            }
        }
        for (int i = n - 1; i > e; i--) {
            int ai = A.get(i);
            if (ai < max) {
                e = i;
                break;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(s);
        ans.add(e);
        return ans;
    }

    public static void main(String[] args) {
        MaximumUnsortedSubArray mus = new MaximumUnsortedSubArray();
        Verifier.verifyEquals(mus.subUnsort(Creator.getList(1, 3, 2, 4, 5)), Creator.getList(1,2));
        Verifier.verifyEquals(mus.subUnsort(Creator.getList(1)), Creator.getList(-1));
        Verifier.verifyEquals(mus.subUnsort(Creator.getList(1,2)), Creator.getList(-1));
        Verifier.verifyEquals(mus.subUnsort(Creator.getList(10,40,45,30,35,36,70,50,60,80)), Creator.getList(1,8));
    }

}
