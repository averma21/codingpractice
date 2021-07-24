package Hashing.round2;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.interviewbit.com/problems/subarray-with-equal-occurences/
 *
 * Given an integer array A and two integers B and C.
 *
 * You need to find the number of subarrays in which the number of occurrences of B is equal to number of occurrences of C.
 *
 * NOTE: Don't count empty subarrays.
 *
 * Problem Constraints
 * 1 <= |A| <= 104
 * 1 <= A[i], B, C <= 108
 * B != C
 * Input Format
 * First argument is an integer array A.
 * Second argument is an integer B.
 * Third argument is an integer C.
 * Output Format
 * Return an integer denoting the number of subarrays in which the number of occurrences of B is equal to number of occurrences of C.
 *
 * Example Input
 * Input 1:
 *  A = [1, 2, 1]
 *  B = 1
 *  C = 2
 * Input 2:
 *  A = {1, 2, 1}
 *  B = 4
 *  C = 6
 * Example Output
 * Output 1: 2
 * Output 2: 6
 *
 * Example Explanation
 * Explanation 1:
 *
 *  The possible sub-arrays have same equal number of occurrences of B and C are:
 *     1) {1, 2}, B and C have same occurrence(1).
 *     2) {2, 1}, B and C have same occurrence(1).
 * Explanation 2:
 *
 *  The possible sub-arrays have same equal number of occurrences of B and C are:
 *     1) {1}, B and C have same occurrence(0).
 *     2) {2}, B and C have same occurrence(0).
 *     3) {1}, B and C have same occurrence(0).
 *     4) {1, 2}, B and C have same occurrence(0).
 *     5) {2, 1}, B and C have same occurrence(0).
 *     6) {1, 2, 1}, B and C have same occurrence(0).
 */
public class SubarrayWithEqualOccurrences {

    public int solve(List<Integer> A, int B, int C) {
        if (A == null || A.size() == 0) {
            return 0;
        }
        Map<Integer, List<Integer>> sumMap = new HashMap<>();
        int count = 0, sum = 0;
        for (int i = 0; i < A.size(); i++) {
            int ai = A.get(i);
            if (ai == B) {
                sum += 1;
            } else if (ai == C){
                sum -= 1;
            }
            if (sum == 0) {
                count++; // 0 to i have same number of B and C
            }
            if (sumMap.containsKey(sum)) {
                /*
                 If we have sum X between 0 to i, 0 to j and now we are at k with sum X, then sum is 0 from i+1 to k and j+1 to k.
                 */
                count += sumMap.get(sum).size();
            }
            sumMap.putIfAbsent(sum, new ArrayList<>());
            int index = i;
            sumMap.computeIfPresent(sum, (k,list) -> {
                list.add(index);
                return list;
            });
        }
        return count;
    }

    public static void main(String[] args) {
        SubarrayWithEqualOccurrences swe = new SubarrayWithEqualOccurrences();
        Verifier.verifyEquals(swe.solve(Creator.getList(1, 2, 1), 1, 2), 2);
        Verifier.verifyEquals(swe.solve(Creator.getList(1, 2, 1), 4, 6), 6);
        Verifier.verifyEquals(swe.solve(Creator.getList(1,2,1,4,6,1,4), 4, 6), 16);
    }

}
