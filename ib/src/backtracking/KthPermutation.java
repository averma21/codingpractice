package backtracking;

import util.Verifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/*
    The set [1,2,3,…,n] contains a total of n! unique permutations.
    By listing and labeling all of the permutations in order,
    We get the following sequence (ie, for n = 3 ) :
    1. "123"
    2. "132"
    3. "213"
    4. "231"
    5. "312"
    6. "321"
    Given n and k, return the kth permutation sequence.
    For example, given n = 3, k = 4, ans = "231"
    Good questions to ask the interviewer :
        What if n is greater than 10. How should multiple digit numbers be represented in string?
            > In this case, just concatenate the number to the answer.
            > so if n = 11, k = 1, ans = "1234567891011"
        Whats the maximum value of n and k?
            > In this case, k will be a positive integer that's less than INT_MAX.
            > n is reasonable enough to make sure the answer does not bloat up a lot.
    https://www.interviewbit.com/problems/kth-permutation-sequence/
*/
public class KthPermutation {

    private int fact(int n) {
        if (n>12)
            return Integer.MAX_VALUE;
        int f = 1;
        for (int i = 2; i<=n; ++i)
            f *= i;
        return f;
    }

    String getKthPermutation(List<Integer> numList, int k) {
        int n = numList.size();
        if (n == 0 || k > fact(n)) {
            return "";
        }
        int f = fact(n-1);
        int pos = k/f;
        k %= f;
        String ch = numList.remove(pos).toString();
        return ch + getKthPermutation(numList, k);
    }

    String getKthPermutation(int A, int B) {
        List<Integer> numList = new ArrayList<>(A);
        for (int i = 1; i <= A; i++) {
            numList.add(i);
        }
        return getKthPermutation(numList, B-1);
    }

    public static void main(String[] args) {
        KthPermutation kp = new KthPermutation();
        Verifier.verifyEquals(kp.getKthPermutation(3,3), "213");
    }
}
