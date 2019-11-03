package others.leetc.contests.onesixtyone;

import util.Verifier;

/**
 * https://leetcode.com/contest/weekly-contest-161/problems/check-if-it-is-a-good-array/
 * Given an array nums of positive integers. Your task is to select some subset of nums, multiply each element by an integer and add all these numbers. The array is said to be good if you can obtain a sum of 1 from the array by any possible subset and multiplicand.
 *
 * Return True if the array is good otherwise return False.
 *
 * Input: nums = [12,5,7,23]
 * Output: true
 * Explanation: Pick numbers 5 and 7.
 * 5*3 + 7*(-2) = 1
 *
 * https://leetcode.com/problems/check-if-it-is-a-good-array/discuss/419707/Java-Best-Solution
 * https://en.wikipedia.org/wiki/B%C3%A9zout%27s_identity
 *
 */
public class CheckGoodArray {

    public boolean isGoodArray(int[] nums) {
        int gcd = nums[0];
        for(int i = 1 ; i < nums.length ; i++) {
            gcd = getGCD(gcd, nums[i]);
            if(gcd == 1)
                return true;
        }
        return gcd == 1;
    }

    private int getGCD(int x, int y) {
        if (y < x) {
            int temp = x;
            x = y;
            y = temp;
        }
        if (x == 0)
            return y;
        return getGCD(y%x , x);
    }

    public static void main(String[] args) {
        CheckGoodArray cga = new CheckGoodArray();
        Verifier.verifyEquals(cga.isGoodArray(new int[]{12,5,7,23}), true);
        Verifier.verifyEquals(cga.isGoodArray(new int[]{29,6,10}), true);
        Verifier.verifyEquals(cga.isGoodArray(new int[]{3,6}), false);
    }

}
