package arrays;

import java.util.List;

//https://www.interviewbit.com/problems/maximum-absolute-difference/

/**
 * https://www.geeksforgeeks.org/maximum-absolute-difference-value-index-sums/
 *
 * An efficient solution in O(n) time complexity can be worked out using the properties of absolute values.
 * f(i, j) = |A[i] – A[j]| + |i – j| can be written in 4 ways (Since we are looking at max value, we don’t even care if the value becomes negative as long as we are also covering the max value in some way).
 *
 * Case 1: A[i] > A[j] and i > j
 * |A[i] - A[j]| = A[i] - A[j]
 * |i -j| = i - j
 * hence, f(i, j) = (A[i] + i) - (A[j] + j)
 *
 * Case 2: A[i] < A[j] and i < j
 * |A[i] - A[j]| = -(A[i]) + A[j]
 * |i -j| = -(i) + j
 * hence, f(i, j) = -(A[i] + i) + (A[j] + j)
 *
 * Case 3: A[i] > A[j] and i < j
 * |A[i] - A[j]| = A[i] - A[j]
 * |i -j| = -(i) + j
 * hence, f(i, j) = (A[i] - i) - (A[j] - j)
 *
 * Case 4: A[i] < A[j] and i > j
 * |A[i] - A[j]| = -(A[i]) + A[j]
 * |i -j| = i - j
 * hence, f(i, j) = -(A[i] - i) + (A[j] - j)
 * Note that case 1 and 2 are equivalent and so are case 3 and 4 and hence we can design our algorithm only for two cases as it will cover all the possible cases.
 *
 * 1. Calculate the value of A[i] + i and A[i] – i for every element of the array while traversing through the array.
 *
 * 2. Then for the two equivalent cases, we find the maximum possible value. For that, we have to store minimum and maximum values of expressions A[i] + i and A[i] – i for all i.
 *
 * 3. Hence the required maximum absolute difference is maximum of two values i.e. max((A[i] + i) – (A[j] + j)) and max((A[i] – i) – (A[j] – j)). These values can be found easily in linear time.
 *      a. For max((A[i] + i) – (A[j] + j)) Maintain two variables max1 and min1 which will store maximum and minimum values of A[i] + i respectively. max((A[i] + i) – (A[j] + j)) = max1 – min1
 *      b. For max((A[i] – i) – (A[j] – j)). Maintain two variables max2 and min2 which will store maximum and minimum values of A[i] – i respectively. max((A[i] – i) – (A[j] – j)) = max2 – min2
 *
 */
public class MaximumAbsoluteDifference {

    int diff(List<Integer> A) {
        if (A == null || A.size() == 0) {
            return 0;
        }
        int n = A.size();
        int [] arr1 = new int[n];
        int [] arr2 = new int[n];
        int max1 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE;
        int max2 = max1, min2 = min1;
        for (int i = 0; i < n; i++) {
            arr1[i] = A.get(i) + i;
            arr2[i] = A.get(i) - i;
            max1 = Math.max(max1, arr1[i]);
            max2 = Math.max(max2, arr2[i]);
            min1 = Math.min(min1, arr1[i]);
            min2 = Math.min(min2, arr2[i]);
        }
        return Math.max(max1 - min1, max2 - min2);
    }

}
