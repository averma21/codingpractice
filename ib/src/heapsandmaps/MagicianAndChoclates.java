package heapsandmaps;

import util.Creator;
import util.Verifier;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given N bags, each bag contains Ai chocolates. There is a kid and a magician. In one unit of time, kid chooses a random bag i, eats Ai chocolates, then the magician fills the ith bag with floor(Ai/2) chocolates.
 * Given Ai for 1 <= i <= N, find the maximum number of chocolates kid can eat in K units of time.
 * For example,
 * K = 3
 * N = 2
 * A = 6 5
 * Return: 14
 * At t = 1 kid eats 6 chocolates from bag 0, and the bag gets filled by 3 chocolates
 * At t = 2 kid eats 5 chocolates from bag 1, and the bag gets filled by 2 chocolates
 * At t = 3 kid eats 3 chocolates from bag 0, and the bag gets filled by 1 chocolate
 * so, total number of chocolates eaten: 6 + 5 + 3 = 14
 * Note: Return your answer modulo 10^9+7
 * https://www.interviewbit.com/problems/magician-and-chocolates/
 */
public class MagicianAndChoclates {

    static int nchoc(int A, List<Integer> B) {
        if (A <= 0 || B == null || B.size() == 0)
            return 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(B.size(), Collections.reverseOrder());
        priorityQueue.addAll(B);
        long total = 0;
        while (A > 0 && !priorityQueue.isEmpty()) {
            int max = priorityQueue.poll();
            total += max;
            total %= 1000000007;
            if (max > 1)
                priorityQueue.add(max/2);
            A--;
        }
        return (int)total;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(nchoc(3, Creator.getList(6,5)), 14);
    }

}
