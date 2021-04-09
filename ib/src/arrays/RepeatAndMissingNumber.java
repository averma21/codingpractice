package arrays;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.List;

/**
 * The solution below uses extra space since given array has to be unmodified. If array was modifiable we could have negated
 * elements in place to find duplicate.
 *
 * Better solution without extra memory is -
 *
 * Sum(Actual) = Sum(1…N) + A - B
 *
 * Sum(Actual) - Sum(1…N) = A - B.
 *
 * We need one more relation. How about the sum of squares?
 *
 * Sum(1^2 …. N^2) and Sum(A[0]^2 … A[N-1]^2)?
 */
public class RepeatAndMissingNumber {

    //A is twice, B is missing
    static List<Integer> repeat(List<Integer> A) {
        ArrayList<Integer> C = new ArrayList<>(A);
        long SA = 0;
        for (int a : C) {
            SA = SA+a;
        }
        int n = C.size();
        long Sn = (n*(n+1L))/2L;
        int a = -1, b = -1;
        for (Integer integer : C) {
            int index = Math.abs(integer);
            int val = C.get(index - 1);
            if (val < 0) {
                a = index;
                break;
            }
            C.set(index - 1, val*-1);
        }
        b = (int)(a - SA + Sn);
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(a);
        ans.add(b);
        return ans;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(repeat(Creator.getList(3,2,1,4,4)), Creator.getList(4, 5));
        Verifier.verifyEquals(repeat(Creator.getList(3,2,1,5,3)), Creator.getList(3, 4));
    }

}
