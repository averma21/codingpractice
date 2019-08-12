package heapsandmaps;

import util.Verifier;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Max Heap is a special kind of complete binary tree in which for every node the value present in that node is greater than the value present in it’s children nodes. If you want to know more about Heaps, please visit this link
 *
 * So now the problem statement for this question is:
 *
 * How many distinct Max Heap can be made from n distinct integers
 *
 * In short, you have to ensure the following properties for the max heap :
 *
 * Heap has to be a complete binary tree ( A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible. )
 * Every node is greater than all its children
 * Let us take an example of 4 distinct integers. Without loss of generality let us take 1 2 3 4 as our 4 distinct integers
 *
 * Following are the possible max heaps from these 4 numbers :
 *
 *          4
 *        /  \
 *       3   2
 *      /
 *     1
 *          4
 *        /  \
 *       2   3
 *      /
 *     1
 *         4
 *        / \
 *       3  1
 *      /
 *     2
 * These are the only possible 3 distinct max heaps possible for 4 distinct elements.
 *
 * Implement the below function to return the number of distinct Max Heaps that is possible from n distinct elements.
 *
 * As the final answer can be very large output your answer modulo 1000000007
 *
 * Input Constraints : n <= 100
 */

public class WaysToFormMaxHeap {

    private static int findMaxLevel(int n) {
        if (n <= 1)
            return 0;
        int level = 0;
        for (int i = 2; i <= n; i*=2) {
            level++;
        }
        return level;
    }

    private static int combination(int n, int r) {
        int D1 = r > n-r ? r : n-r;
        BigInteger D2 = new BigInteger("" + (n - D1));
        BigInteger num = new BigInteger("1");
        BigInteger bign = new BigInteger("" + n);
        while (n >= D1 + 1) {
            num = num.multiply(bign);
            bign = bign.subtract(BigInteger.ONE);
            n--;
        }
        while (D2.compareTo(BigInteger.ONE) > 0) {
            num = num.divide(D2);
            D2 = D2.subtract(BigInteger.ONE);
        }
        return num.intValue();
    }

    private static Map<Integer, Integer> solution;

    private static int internalSolve(int A) {
        if (A <= 2)
            return 1;
        if (solution.containsKey(A))
            return solution.get(A);
        int maxLevel = findMaxLevel(A);
        int filledLevel = maxLevel;
        if (A != Math.pow(2, maxLevel+1) - 1)
            filledLevel--;
        int rightNodeCount, leftNodeCount;
        if (filledLevel == maxLevel) {
            rightNodeCount = (int)Math.pow(2, maxLevel) -1;
        } else {
            int nodesInLastLevel = A - (int)(Math.pow(2, maxLevel) - 1);
            int maxPossibleNodesInLastLevel = (int)Math.pow(2, maxLevel);
            int nodesInLastLevelInRightTree = nodesInLastLevel - maxPossibleNodesInLastLevel/2;
            if (nodesInLastLevelInRightTree > 0) {
                rightNodeCount = (int)Math.pow(2, maxLevel-1) -1 + nodesInLastLevelInRightTree;
            } else {
                rightNodeCount = (int)Math.pow(2, maxLevel-1) -1;
            }
        }
        leftNodeCount = A - 1 - rightNodeCount;
        int waysToChooseRightNodes = combination(A-1, rightNodeCount);
        int ans = waysToChooseRightNodes * internalSolve(rightNodeCount) * internalSolve(leftNodeCount);
        solution.put(A, ans);
        return ans;
    }

    public static int solve(int A) {
        solution = new HashMap<>();
        return internalSolve(A);
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(findMaxLevel(0), 0);
        Verifier.verifyEquals(findMaxLevel(1), 0);
        Verifier.verifyEquals(findMaxLevel(2), 1);
        Verifier.verifyEquals(findMaxLevel(3), 1);
        Verifier.verifyEquals(findMaxLevel(4), 2);
        Verifier.verifyEquals(findMaxLevel(6), 2);
        Verifier.verifyEquals(findMaxLevel(15), 3);
        Verifier.verifyEquals(combination(1,1), 1);
        Verifier.verifyEquals(combination(2,1), 2);
        Verifier.verifyEquals(combination(3,2), 3);
        Verifier.verifyEquals(combination(3,0), 1);
        Verifier.verifyEquals(combination(10,3), 120);
        Verifier.verifyEquals(solve(3), 2);
        Verifier.verifyEquals(solve(4), 3);
        Verifier.verifyEquals(solve(5), 8);
        Verifier.verifyEquals(solve(6), 20);
        Verifier.verifyEquals(solve(20), 258365767);
    }

}
