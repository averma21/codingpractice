package arrays;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://www.interviewbit.com/problems/find-permutation/

/**
 * Given a positive integer n and a string s consisting only of letters D or I, you have to find any permutation of first n positive integer that satisfy the given input string.
 *
 * D means the next number is smaller, while I means the next number is greater.
 *
 * Notes
 *
 * Length of given string s will always equal to n - 1
 * Your solution should run in linear time and space
 *
 * Input 1:
 *
 * n = 3
 *
 * s = ID
 *
 * Return: [1, 3, 2]
 */
public class FindPermutation {

    /**
     * The answer is a permutation of 1 to n, so each number b/w 1 to n can only be used once.
     * We need to decide which number to start from. For example, if the first letter in given pattern is 'D' then we can't
     * start from 1 because we won't be able to decrement it further. Similarly if n=8, and first letter is 'I' we can't start
     * from 8.<br>
     *
     * There are multiple possible answers but we will be considering one approach here.
     * For the example below assume n = 7 => lowerBound = 1 and upperBound = 7
     * <h3>Case 1 - Beginning number</h3>
     * To decide the beginning number, we look at first char of given pattern and find out how many times it occurs consecutively
     * in the beginning of string.<br>
     * Eg "IIDIDI" means I occurs twice consecutively while "DIIDDI" means D occurs once consecutively. In case of I we start near upper
     * bound permissible limit and in case of D we start near lower bound of permissible limit. Meaning for first case we start from 7-2=5
     * so that we could get space for incrementing twice and add the result of two increments. We add to answer [5,6,7]. After this the upper
     * bound becomes 4 (since 5,6 and 7 are already used up).<br>
     * Similarly, for second case we start with 1+1=2 (so we get space for one decrement) and after this the lower bound becomes 3. We add
     * [2,1] to the answer (result of one increment).
     * <h3>Case 2 - Middle of the pattern</h3>
     * In this case we don't have to decide the starting number, but decide which number to jump to (and then add more increment/decrement if count > 1).
     * Here also we find how many consecutive occurrences (count) of current char are there. Eg for the case of "IIDIDI", we have used up two I's and added
     * [5,6,7] to the answer. Now we get one 'D'. So we need space for one decrement before increment starts again. So we go to lowerBound + 1 - 1 and
     * hence push [1] into the answer. So answer becomes [5,6,7,1]. Lower bound becomes 2. <br>
     * For the case of "DIIDDI" we have used up the first D and now we get two 'I's. So we go to upperLimit - 2 + 1 = 6 and add [6,7] to answer.
     * So answer becomes [2,1,6,7]. Upper bound becomes 5.
     *
     * @param B the number n
     * @param A the string consisting of 'D' or 'I'
     * @return list of integers
     */
    List<Integer> find(int B, String A) {
        if (A == null || A.length() == 0) {
            return Collections.emptyList();
        }
        int curPos = 0;
        char cur;
        int lowerBound = 1, upperBound = B;
        boolean beginning = true;
        ArrayList<Integer> ans = new ArrayList<>();
        while (curPos < A.length()) {
            cur = A.charAt(curPos);
            int count = 1;
            while (curPos + 1 < A.length() && A.charAt(curPos + 1) == cur) {
                curPos++;
                count++;
            }
            if (beginning) {
                beginning = false;
                if (cur == 'D') {
                    int start = lowerBound + count;
                    lowerBound = start + 1;
                    for (int i = 1; i <= count; i++) {
                        ans.add(start--);
                    }
                    ans.add(start);
                } else {
                    int start = upperBound - count;
                    upperBound = start - 1;
                    for (int i = 1; i <= count; i++) {
                        ans.add(start++);
                    }
                    ans.add(start);
                }
            } else {
                if (cur == 'D') {
                    int start = lowerBound + count - 1;
                    lowerBound = start + 1;
                    for (int i = 1; i <= count; i++) {
                        ans.add(start--);
                    }
                } else {
                    int start = upperBound - count + 1;
                    upperBound = start - 1;
                    for (int i = 1; i <= count; i++) {
                        ans.add(start++);
                    }
                }
            }
            curPos++;
        }
        return ans;
    }

    public static void main(String[] args) {
        FindPermutation fp = new FindPermutation();
        Verifier.verifyEquals(fp.find(3, "ID"), Creator.getList(2,3,1));
        Verifier.verifyEquals(fp.find(2, "I"), Creator.getList(1,2));
        Verifier.verifyEquals(fp.find(2, "D"), Creator.getList(2,1));
        Verifier.verifyEquals(fp.find(3, "DD"), Creator.getList(3,2,1));
        Verifier.verifyEquals(fp.find(3, "II"), Creator.getList(1,2,3));
        Verifier.verifyEquals(fp.find(3, "DI"), Creator.getList(2,1,3));
        Verifier.verifyEquals(fp.find(7, "IDIDID"), Creator.getList(6,7,1,5,2,4,3));
        Verifier.verifyEquals(fp.find(7, "DIDIDI"), Creator.getList(2,1,7,3,6,4,5));
        Verifier.verifyEquals(fp.find(7, "DDIDII"), Creator.getList(3,2,1,7,4,5,6));
        Verifier.verifyEquals(fp.find(7, "IIDDII"), Creator.getList(5,6,7,2,1,3,4));
    }

}
