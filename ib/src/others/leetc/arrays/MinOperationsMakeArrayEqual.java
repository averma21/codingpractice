package others.leetc.arrays;

//https://leetcode.com/problems/minimum-operations-to-make-array-equal/

/**
 * Just a Maths question.
 * First observation = all elements should be equal => We can find what the value of each element should be summing all elements and diving by n
 * Since elements are of form (2*i + 1), sum is
 *  (2*0 + 1)  + (2*1 + 1)  + (2*2 + 1)  + ... + (2(n-1) + 1) = n^2
 *
 *  This means that final value of each element should be n.
 *
 *  Now, if it is sure that final state is achievable, we must increase each element smaller than n to n and decrease each number greater than n to n.
 *  The magnitude of increase should be equal to magnitude of decrease. Since at a time one number can be increased so the magnitude of increase itself
 *  would be our answer for the minimum operations required.
 *
 *  Now, for elements < n, the following should hold -
 *  2*i + 1 < n => i < (n-1)/2
 *
 *  If n is odd, n = 2k + 1 => i < k
 *  i = 0 to k-1
 *  Sum of elements from 0 to k-1 = k^2
 *  If each of them was n, sum would have been n*k
 *
 *  So to make each element from 0 to k-1, the magnitude of increase would be nk - k^2
 *
 *  If n is even, n = 2e => i < e - 0.5 => i = 0 to e
 *  In this case also magnitude of increase = ne - e^2
 *
 */

public class MinOperationsMakeArrayEqual {

    public int minOperations(int n) {
        int k = n/2;
        return n*k - k*k;
    }

}
