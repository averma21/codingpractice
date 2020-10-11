package others.leetc.contests.twohundred.eight;

import util.Verifier;

public class CentennialWheel {

    private static final int MAX_CUSTOMERS_PER_GANDOLA = 4;

    public static int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int profit = 0, maxProfit = 0, rotations = -1, waitingCustomers = 0, totalCustomersBoarded = 0, round = 1;
        for (int newCustomers : customers) {
            int totalCustomers = waitingCustomers + newCustomers;
            int customersToBoard = Math.min(totalCustomers, MAX_CUSTOMERS_PER_GANDOLA);
            waitingCustomers = totalCustomers - customersToBoard;
            totalCustomersBoarded += customersToBoard;
            profit = boardingCost * totalCustomersBoarded - runningCost * round;
            if (profit > maxProfit) {
                maxProfit = profit;
                rotations = round;
            }
            //System.out.println("Round = " + round + " , profit = " + profit);
            round++;
        }
        while (waitingCustomers > 0) {
            int customersToBoard = Math.min(waitingCustomers, MAX_CUSTOMERS_PER_GANDOLA);
            waitingCustomers = waitingCustomers - customersToBoard;
            totalCustomersBoarded += customersToBoard;
            profit = boardingCost * totalCustomersBoarded - runningCost * round;
            if (profit > maxProfit) {
                maxProfit = profit;
                rotations = round;
            }
            //System.out.println("Round = " + round + " , profit = " + profit);
            round++;
        }
        return rotations;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(minOperationsMaxProfit(new int[] {8,3}, 5, 6), 3);
        Verifier.verifyEquals(minOperationsMaxProfit(new int[] {10,9,6}, 6, 4), 7);
        Verifier.verifyEquals(minOperationsMaxProfit(new int[] {3,4,0,5,1}, 1, 92), -1);
        Verifier.verifyEquals(minOperationsMaxProfit(new int[] {10,10,6,4,7}, 3, 8), 9);
    }

}
