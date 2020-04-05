package others.leetc.contests.biweekly.twentythree;

import util.Verifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/contest/biweekly-contest-23/problems/reducing-dishes/
public class ReducingDishes {
    /**
     * Intuition
     * If we cook some dishes,
     * they must be the most satisfied among all choices.
     *
     * Another important observation is that,
     * we will cook the dish with small satisfication,
     * and leave the most satisfied dish in the end.
     *
     * Explanation
     * We choose dishes from most satisfied.
     * Everytime we add a new dish to the menu list,
     * all dishes on the menu list will be cooked one time unit later,
     * so the result += total satisfaction on the list.
     * We'll keep doing this as long as A[i] + total > 0.
     *
     *
     * Complexity
     * Time O(NlogN)
     * Space O(1)
     *
     */
    private static int copiedSolution(int[] satisfaction) {
         Arrays.sort(satisfaction);
         int res = 0, total = 0, n = satisfaction.length;
         for (int i = n - 1; i >= 0 && satisfaction[i] > -total; --i) {
             total += satisfaction[i];
             res += total;
         }
         return res;
    }

    public static int maxSatisfaction(int[] satisfaction) {

        List<Integer> list = new ArrayList<>(satisfaction.length);
        int positiveSum = 0, totalSum = 0;
        for (int s : satisfaction) {
            list.add(s);
            if (s>0) {
                positiveSum += s;
            }
            totalSum += s;
        }
        Collections.sort(list);
        int maxSum = 0;
        if (positiveSum == 0)
            return 0;

        for (int i = 0; i < list.size(); i++) {
            maxSum += (i+1)* list.get(i);
        }

        for (int ele : list) {
            if (ele < 0) {
                int sum = maxSum - totalSum;
                totalSum -= ele;
                if (sum > maxSum) {
                    maxSum = sum;
                } else {
                    break;
                }
            }
        }
        return (int)maxSum;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(maxSatisfaction(new int[]{-1,-8,0,5,-9}), 14);
        Verifier.verifyEquals(maxSatisfaction(new int[]{4,3,2}), 20);
        Verifier.verifyEquals(maxSatisfaction(new int[]{-1,-4,-5}), 0);
        Verifier.verifyEquals(maxSatisfaction(new int[]{-2,5,-1,0,3,-3}), 35);
    }
}
