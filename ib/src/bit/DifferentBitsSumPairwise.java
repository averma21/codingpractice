package bit;

import util.Verifier;

import java.util.Arrays;
import java.util.List;

public class DifferentBitsSumPairwise {

    public static int cntBits(List<Integer> A) {
        long ans = 0;
        long mod = 1000000007L;
        for (int i = 30; i >= 0; i--) {
            //find numbers with 1 as ith bit
            int numWith1=0;
            for (int num : A) {
                if (((num>>i) & 1) == 1) {
                    numWith1++;
                }
            }
            int numWith0 = A.size() - numWith1;
            ans += (numWith0 * (long)numWith1 * 2)%mod;
            if (ans >= mod)
                ans -= mod;
        }
        return (int)ans;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(new Integer[]{1,3,5});
        Verifier.verifyEquals(cntBits(list), 8);
        list = Arrays.asList(new Integer[]{1,2147483647});
        Verifier.verifyEquals(cntBits(list), 60);
        list = Arrays.asList(new Integer[]{1,1});
        Verifier.verifyEquals(cntBits(list), 0);
    }

}
