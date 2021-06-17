package TwoPointers;

import util.Verifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {

    public static int func(List<Integer> A, int B) {
        long ansSum = 0;
        long diff = Long.MAX_VALUE;
        Collections.sort(A);
        for (int i = 0; i < A.size() - 2; i++) {
            int l = i+1, r = A.size() - 1;
            int cur = A.get(i);
            while (l < r) {
                long sum = (long)A.get(l) + A.get(r);
                long currDiff = cur + sum > B ? cur + sum - B : B - cur - sum;
                if (currDiff <  diff) {
                    diff = currDiff;
                    ansSum = cur + sum;
                }
                if (cur + sum == B)
                    break;
                if (cur + sum < B)
                    l++;
                else r--;
            }
        }
        return (int)ansSum;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(new Integer[]{6, 3, 2, 1,4});
        Verifier.verifyEquals(func(list, 6), 6);
        Verifier.verifyEquals(func(list, 11), 11);
        Verifier.verifyEquals(func(list, 14), 13);
        Verifier.verifyEquals(func(list, 7), 7);
        list = Arrays.asList(new Integer[]{-1, 2, 1, -4});
        Verifier.verifyEquals(func(list, 1), 2);
        list = Arrays.asList(new Integer[]{2147483647, -2147483648, -2147483648, 0, 1 });
        Verifier.verifyEquals(func(list, 0), 0);
    }

}
