package checkpoint.level5;

import util.Creator;
import util.ListNode;
import util.Verifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * Example:
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 *
 * Your algorithm should run in O(n) complexity.
 * https://www.interviewbit.com/problems/longest-consecutive-sequence/
 */
public class LCS {

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public static int longestConsecutive(final List<Integer> A) {
        if (A == null || A.size() == 0)
            return 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Integer a : A) {
            countMap.computeIfPresent(a, (k,v) -> {return v+1;});
            countMap.putIfAbsent(a, 1);
        }
        int max = Integer.MIN_VALUE;
        for (Integer a: A) {
            if (countMap.containsKey(a -1))
                continue;
            int length = 1;
            while (countMap.containsKey(a+1)) {
                length++;
                a = a+1;
            }
            if (length > max)
                max = length;
        }
        return max;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(longestConsecutive(Creator.getList(100, 4, 200, 1, 3, 2)), 4);
        Verifier.verifyEquals(longestConsecutive(Creator.getList(100, 105, 200, 1)), 1);
        Verifier.verifyEquals(longestConsecutive(Creator.getList(1,2,3,4,5)), 5);
        Verifier.verifyEquals(longestConsecutive(Creator.getList(5,4,3,2,1)), 5);
    }

}
