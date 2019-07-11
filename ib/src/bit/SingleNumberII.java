package bit;

import util.Verifier;

import java.util.Arrays;
import java.util.List;

public class SingleNumberII {
    public static int singleNumber(List<Integer> A) {
        int num = 0;
        for (int i = 30; i >=0; i--) {
            int sum = 0;
            for (int a : A) {
                sum += (a>>i)&1;
            }
            sum = sum % 3;
            if (sum == 1) {
                num |= 1L<<i;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(new Integer[]{2,2,2,3,3,3,4});
        Verifier.verifyEquals(singleNumber(list), 4);
        list = Arrays.asList(new Integer[]{6,4,4,4,3,3,5,5,5,3});
        Verifier.verifyEquals(singleNumber(list), 6);
        list = Arrays.asList(new Integer[]{100,100,100,200});
        Verifier.verifyEquals(singleNumber(list), 200);
    }
}
