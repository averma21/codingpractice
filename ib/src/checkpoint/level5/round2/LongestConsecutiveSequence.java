package checkpoint.level5.round2;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestConsecutiveSequence {

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public static int longestConsecutive(final List<Integer> A) {
        if (A == null || A.size() == 0)
            return 0;
        Set<Integer> set = new HashSet<>(A);
        List<Integer> lowerEnds = set.stream().filter(a -> !set.contains(a-1)).collect(Collectors.toList());
        int max = Integer.MIN_VALUE;
        for (int start : lowerEnds) {
            int length = 1;
            while (set.contains(start+1)) {
                length++;
                start++;
            }
            max = Math.max(max, length);
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
