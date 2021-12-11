package others.leetc.contests.twohundred.sixty.seven;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class TimeNeededToBuyTickets {

    public int timeRequiredToBuy(int[] tickets, int k) {
        Map<Integer, Integer> completingAtRound = new HashMap<>();
        for (int ticket : tickets) {
            completingAtRound.compute(ticket, (t,v) -> v == null ? 1 : v+1);
        }
        int round = 1;
        int time = 0, curRoundTime = tickets.length;
        while (round < tickets[k]) {
            time += curRoundTime;
            curRoundTime -= completingAtRound.getOrDefault(round, 0);
            round++;
        }
        for (int i = 0; i <= k; i++) {
            if (tickets[i] >= tickets[k]) {
                time++;
            }
        }
        return time;
    }

    public static void main(String[] args) {
        TimeNeededToBuyTickets tnt = new TimeNeededToBuyTickets();
        Verifier.verifyEquals(tnt.timeRequiredToBuy(new int[] {2,3,2}, 2), 6);
        Verifier.verifyEquals(tnt.timeRequiredToBuy(new int[] {5,1,1,1}, 0), 8);
        Verifier.verifyEquals(tnt.timeRequiredToBuy(new int[] {5,4,3,4}, 2), 11);
        Verifier.verifyEquals(tnt.timeRequiredToBuy(new int[] {84,49,5,24,70,77,87,8}, 3), 154);
    }

}
