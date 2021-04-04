package arrays;

import util.Creator;
import util.Verifier;

import java.util.List;

public class MaxDistance {

    public int maximumGap(List<Integer> A) {
        if (A == null || A.size() == 1) {
            return 0;
        }
        int n = A.size();
        int [] leftArr = new int[n];
        int [] rightArr = new int[n];

        int min = Integer.MAX_VALUE, pos = 0;
        for (int a : A) {
            min = Math.min(min, a);
            leftArr[pos++] = min;
        }
        int max = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            max = Math.max(max, A.get(i));
            rightArr[i] = max;
        }

        int i = 0, j = 0, maxDist = 0;
        while (i < n && j < n) {
            if (leftArr[i] <= rightArr[j]) {
                maxDist = Math.max(maxDist, j - i);
                j++;
            } else {
                i++;
            }
        }
        return maxDist;
    }

    public static void main(String[] args) {
        MaxDistance maxDistance = new MaxDistance();
        Verifier.verifyEquals(maxDistance.maximumGap(Creator.getList(3, 5, 4, 2)), 2);
        Verifier.verifyEquals(maxDistance.maximumGap(Creator.getList(3)), 0);
        Verifier.verifyEquals(maxDistance.maximumGap(Creator.getList(3,2,1)), 0);
        Verifier.verifyEquals(maxDistance.maximumGap(Creator.getList(3,3,3)), 2);
    }

}
