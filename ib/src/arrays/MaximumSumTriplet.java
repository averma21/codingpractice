package arrays;

import util.Creator;
import util.Verifier;

import java.util.List;
import java.util.TreeSet;

//https://www.interviewbit.com/problems/maximum-sum-triplet/
//https://www.geeksforgeeks.org/find-maximum-sum-triplets-array-j-k-ai-aj-ak/ (see approach 3)

/**
 * Simple Approach is to traverse for every triplet with three nested ‘for loops’ and find update the sum of all triplets one by one.
 * Time complexity of this approach is O(n3) which is not sufficient for larger value of ‘n’.
 *
 * Better approach is to make further optimization in above approach. Instead of traversing through every triplets with three nested loops,
 * we can traverse through two nested loops. While traversing through each number(assume as middle element(aj)), find maximum number(ai) smaller
 * than aj preceding it and maximum number(ak) greater than aj beyond it. Now after that, update the maximum answer with calculated sum of ai + aj + ak
 *
 * Best and efficient approach is use the concept of maximum suffix-array and binary search.
 *
 * For finding maximum number greater number greater than given number beyond it, we can maintain a maximum suffix-array
 * array such that for any number(suffix) it would contain maximum number from index i, i+1, … n-1. Suffix array can be calculated in O(n) time.
 *
 * For finding maximum number smaller than the given number preceding it, we can maintain a sorted list of numbers before a given number
 * such we can simply perform a binary search to find a number which is just smaller than the given number.
 */
public class MaximumSumTriplet {

    int maximumSumTriplet(List<Integer> A) {
        if (A == null || A.size() == 0) {
            return 0;
        }
        int n = A.size();

        //array which would contain the largest element(including the current element) on the right side of current element
        int [] maxNumSuffixArr = new int[n];
        int max = A.get(n-1);
        maxNumSuffixArr[n-1] = max;
        for (int i = n - 2; i >= 0; i--) {
            max = Math.max(max, A.get(i));
            maxNumSuffixArr[i] = max;
        }
        // to maintain a balanced BST of elements found till now. insertion/search is log(n)
        // used to find the largest element smaller than the current element on the left side of current element
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(A.get(0));
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < n - 1; i++) {
            int ai = A.get(i);
            treeSet.add(ai);
            Integer lower =  treeSet.lower(ai);
            if (lower != null && maxNumSuffixArr[i] > ai) {
                int sum = lower + ai + maxNumSuffixArr[i];
                ans = Math.max(ans, sum);
            }
        }
        return ans == Integer.MIN_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        MaximumSumTriplet mst = new MaximumSumTriplet();
        Verifier.verifyEquals(mst.maximumSumTriplet(Creator.getList(2, 5, 3, 1, 4, 9)), 16);
        Verifier.verifyEquals(mst.maximumSumTriplet(Creator.getList(1,2,3)), 6);
        Verifier.verifyEquals(mst.maximumSumTriplet(Creator.getList(3,3,3)), 0);
        Verifier.verifyEquals(mst.maximumSumTriplet(Creator.getList(-6,3,6)), 3);
        Verifier.verifyEquals(mst.maximumSumTriplet(Creator.getList(-6,-3,-6)), 0);
        Verifier.verifyEquals(mst.maximumSumTriplet(Creator.getList(-6,-3,-6, -7, -8)), 0);
        Verifier.verifyEquals(mst.maximumSumTriplet(Creator.getList(-5, -6,-3,-6, -7, -8, 5)), -3);
        Verifier.verifyEquals(mst.maximumSumTriplet(Creator.getList(-6,-3,-6, -2)), -11);
    }

}
