package bit.round2;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleNumberII {

    int singleNumber(List<Integer> A) {
        int ans = 0;
        List<Integer> B = new ArrayList<>(A);
        for (int i = 0; i <= 30; i++) {
            int sum = 0;
            for (int j = 0; j < B.size(); j++) {
                int aj = B.get(j);
                sum += aj&1; // obtain last bit
                B.set(j, aj >> 1);
            }
            if (sum % 3 == 1) {
                ans += Math.pow(2, i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SingleNumberII s2 = new SingleNumberII();
        Verifier.verifyEquals(s2.singleNumber(Creator.getList(2,2,2,3,3,3,4)), 4);
        Verifier.verifyEquals(s2.singleNumber(Creator.getList(6,4,4,4,3,3,5,5,5,3)), 6);
        Verifier.verifyEquals(s2.singleNumber(Creator.getList(100,100,100,200)), 200);
    }

}
