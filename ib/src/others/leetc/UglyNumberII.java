package others.leetc;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/ugly-number-ii/
//https://leetcode.com/problems/ugly-number-ii/discuss/69364/My-16ms-C%2B%2B-DP-solution-with-short-explanation

/**
 * We have an array k of first n ugly number. We only know, at the beginning, the first one, which is 1. Then
 *
 * k[1] = min( k[0]x2, k[0]x3, k[0]x5). The answer is k[0]x2. So we move 2's pointer to 1. Then we test:
 *
 * k[2] = min( k[1]x2, k[0]x3, k[0]x5). And so on. Be careful about the cases such as 6, in which we need to forward both pointers of 2 and 3.
 *
 * <ol>
 *     <li>Essentially, we have to multiply the existed ugly numbers by 2, 3 and 5 to get a bigger ugly number, however,
 *     if we blindly multiply all the existed numbers by 2, 3 and 5, then the number could grow much faster than needed</li>
 *     <li>Hence, every time we only try to find the next smallest ugly number</li>
 *     <li>Also, since any existed number will be multiplied by 2, 3 and 5 once and only once, otherwise duplicate, we
 *     can use a pointer to keep track of where the 2, 3 and 5 are going to multiply in the next step.</li>
 *     <li>Once, we find the next minimum, we can move on the corresponding pointer, otherwise it always stays at the
 *     already existed ugly number which would makes pointer useless</li>
 * </ol>
 *
 */
public class UglyNumberII {

    public int nthUglyNumber(int n) {
        if(n <= 0) return 0; // get rid of corner cases
        if(n == 1) return 1; // base case
        int t2 = 0, t3 = 0, t5 = 0; //pointers for 2, 3, 5
        List<Integer> k = new ArrayList<>(n);
        k.add(1);
        int next = -1;
        for(int i  = 1; i < n ; i ++)
        {
            int kt2 = k.get(t2)*2, kt3 = k .get(t3)*3, kt5 = k.get(t5)*5;
            next = Math.min(kt2,Math.min(kt3,kt5));
            k.add(next);
            if(next == kt2) t2++;
            if(next == kt3) t3++;
            if(next == kt5) t5++;
        }
        return next;
    }

}
