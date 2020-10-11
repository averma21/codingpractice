package others.leetc.contests.oneseventy.oneseventynine;

import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeNeededToInform {

    Map<Integer, List<Integer>> tree = new HashMap<>();

    int calcTime(int parent, int [] informTime) {
        int selfTime = informTime[parent];
        int maxTime = 0;
        for (int child : tree.getOrDefault(parent, Collections.emptyList())) {
            maxTime = Math.max(maxTime, calcTime(child, informTime));
        }
        return selfTime + maxTime;
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        tree = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            int parent = manager[i];
            int child = i;
            tree.putIfAbsent(parent, new ArrayList<>());
            tree.computeIfPresent(parent, (p, l) -> {l.add(child); return l;});
        }
        return calcTime(headID, informTime);
    }

    public static void main(String[] args) {
        TimeNeededToInform tni = new TimeNeededToInform();
        Verifier.verifyEquals(tni.numOfMinutes(1, 0, new int[]{-1}, new int[]{0}), 0);
        Verifier.verifyEquals(tni.numOfMinutes(7, 6, new int[]{1,2,3,4,5,6,-1}, new int[]{0,6,5,4,3,2,1}), 21);
        Verifier.verifyEquals(tni.numOfMinutes(15, 0, new int[]{-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6}, new int[]{1,1,1,1,1,1,1,0,0,0,0,0,0,0,0}), 3);
        Verifier.verifyEquals(tni.numOfMinutes(4, 2, new int[]{3,3,-1,2}, new int[]{0,0,162,914}), 1076);
    }

}
