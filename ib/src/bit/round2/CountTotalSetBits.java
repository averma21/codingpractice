package bit.round2;

import util.Verifier;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * If the given number is a power of two, we can easily calculate the number of 1s till that number by using permutation/combination.
 * Eg. for number 8, the binary representation is 1000. Now, every number before 8 will have at most 3 ones since the 4th MSB has 1
 * starting from 8 only. So, if we calculate the number of 1s till 7, we add 1 to it to get number of 1s till 8.
 * Now, we have three places to fill and one can be either one time, two times or at most three times.
 * So the total number of 1s till 7 would be = 1*number_of_ways_in_which_one_can_occur_one_time + 2*number_of_ways_in_which_one_can_occur_two_times
 *  + 3*number_of_ways_in_which_one_can_occur_three_times = 1*3C1 + 2*3C2 + 3*3C3 = 3 + 6 + 3 = 12
 * So number of ones till 8 = 12 + 1 = 13.
 * Now let's consider another example. Suppose given number is 11. The binary representation is 1011.
 * 1011 (11) = 1000 (8) + 11 (3)
 * This means number of ones in 11 will be equal to number of 1s till 8 + 3 + number of ones till 3.
 * The middle 3 in above equation is because starting from 9 to 11, there are 3 numbers and each of these would have one at MSB position.
 * So we find all one positions in the number and keep those in array. Then starting with MSB one, we find number of ones till all those numbers and keep
 * on subtracting those numbers from given number.
 *
 * So this could be solved recursively in this manner.
 */
public class CountTotalSetBits {

    private static final int MOD = (int)1e9 + 7;
    private static final BigInteger BIG_MOD = new BigInteger("" + MOD);

    private int nCr(int n, int r) {
        int big = Math.max(r, n-r);
        int small = Math.min(r, n-r);
        BigInteger numerator = BigInteger.ONE;
        for (int i = n; i > big; i--) {
            numerator = numerator.multiply(new BigInteger("" + i));
        }
        BigInteger denominator = BigInteger.ONE;
        for (int i = 1; i <= small; i++) {
            denominator = denominator.multiply(new BigInteger("" + i));
        }
        return numerator.divide(denominator).mod(BIG_MOD).intValue();
    }

    private long numberOfOnesTillCheckpoint(int numberOfZeros) {
        int countOfOnes = numberOfZeros;
        long count = 1;
        while (countOfOnes >= 1) {
            count += countOfOnes * (long)nCr(numberOfZeros, countOfOnes);
            count %= MOD;
            countOfOnes--;
        }
        return count;
    }

    public int solve(int A) {

        List<Integer> checkpoints = new ArrayList<>();
        int temp = A, numberOfZeros = 0;
        while (temp > 0) {
            if ((temp & 1) == 1) {
                checkpoints.add(numberOfZeros);
            }
            temp = temp >> 1;
            numberOfZeros++;
        }
        int cPos = checkpoints.size() - 1;
        long ans = 0;
        while (A > 0) {
            long onesCount = numberOfOnesTillCheckpoint(checkpoints.get(cPos));
            A -= (1 << checkpoints.get(cPos));
            cPos--;
            ans += onesCount;
            ans %= MOD;
            ans += A;
            ans %= MOD;
        }
        return (int)ans;
    }

    private int solveSimple(int A) {
        long count = 0;
        for (int i = 1; i <= A; i++) {
            int num = i;
            while (num > 0) {
                if ((num & 1) == 1) {
                    count++;
                }
                num = num >> 1;
            }
            count %= MOD;
        }
        return (int)count;
    }

    public static void main(String[] args) {
        CountTotalSetBits ctsb = new CountTotalSetBits();
        Verifier.verifyEquals(ctsb.solve(0), 0);
        Verifier.verifyEquals(ctsb.solve(1), 1);
        Verifier.verifyEquals(ctsb.solve(2), 2);
        Verifier.verifyEquals(ctsb.solve(3), 4);
        Verifier.verifyEquals(ctsb.solve(4), 5);
        Verifier.verifyEquals(ctsb.solve(5), 7);
        Verifier.verifyEquals(ctsb.solve(11), 20);
        Verifier.verifyEquals(ctsb.solve(20), ctsb.solveSimple(20));
        Verifier.verifyEquals(ctsb.solve(33), ctsb.solveSimple(33));
        Verifier.verifyEquals(ctsb.solve(47), ctsb.solveSimple(47));
        Verifier.verifyEquals(ctsb.solve(1034), ctsb.solveSimple(1034));
        Verifier.verifyEquals(ctsb.solve(987654), ctsb.solveSimple(987654));
        Verifier.verifyEquals(ctsb.solve((int)1e9), ctsb.solveSimple((int)1e9));
    }

}
