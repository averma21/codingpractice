package TwoPointers;

import util.Creator;
import util.Verifier;

import java.util.Arrays;
import java.util.List;

public class ContainerWithMostWater {
    static int maxArea(List<Integer> A) {
        int max = Integer.MIN_VALUE;
        if (A == null || A.size() == 0 || A.size() == 1)
            return 0;
        int i = 0, j = A.size() - 1;
        while (i < j) {
            int area = (j - i) * Math.min(A.get(i), A.get(j));
            if (area > max)
                max = area;
            if (A.get(i) < A.get(j))
                i++;
            else j--;
        }
        return max;
    }

    public static void main(String[] args) {
        List<Integer> list = Creator.getList(1, 5, 4, 3);
        Verifier.verifyEquals(maxArea(list), 6);
        Verifier.verifyEquals(maxArea(Creator.getList(1,1,1,1)), 3);
        Verifier.verifyEquals(maxArea(null), 0);
        Verifier.verifyEquals(maxArea(Creator.getList()), 0);
        Verifier.verifyEquals(maxArea(Creator.getList(1)), 0);
    }
}
