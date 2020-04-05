package others.leetc.contests.oneeighty.one;

import util.Verifier;

import java.util.HashSet;
import java.util.Set;

public class FourDivisors {

    private int getSum(int num) {
        Set<Integer> divisors = new HashSet<>();
        int limit = (int)Math.floor(Math.sqrt(num));
        divisors.add(1);
        divisors.add(num);
        for (int i = 2; i <= limit; i++) {
            if (num%i == 0) {
                divisors.add(i);
                divisors.add(num/i);
            }
        }
        if (divisors.size() == 4) {
            return divisors.stream().reduce(Integer::sum).get();
        }
        return 0;
    }

    public int sumFourDivisors(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += getSum(num);
        }
        return sum;
    }

    public static void main(String[] args) {
        FourDivisors fd = new FourDivisors();
        Verifier.verifyEquals(fd.sumFourDivisors(new int[]{21,4,7}), 32);
    }

}
