package others.leetc.contests.oneeighty.two;

import util.Verifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountNumberOfTeams {

    public int numTeams(int[] rating) {
        Map<Integer, List<Integer>> greater = new HashMap<>();
        Map<Integer, List<Integer>> smaller = new HashMap<>();

        for (int i = 0; i < rating.length; i++) {
            greater.put(i, new ArrayList<>());
            smaller.put(i, new ArrayList<>());
            for (int j = i + 1; j < rating.length; j++) {
                int temp = j;
                if (rating[j] > rating[i]) {
                    greater.computeIfPresent(i, (k,v) -> {v.add(temp); return v;});
                } else if (rating[j] < rating[i]) {
                    smaller.computeIfPresent(i, (k,v) -> {v.add(temp); return v;});
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < rating.length; i++) {
            List<Integer> gList = greater.get(i);
            List<Integer> sList = smaller.get(i);
            int gCount = 0;
            for (int g : gList) {
                gCount += greater.get(g).size();
            }
            int sCount = 0;
            for (int s : sList) {
                sCount += smaller.get(s).size();
            }
            answer += gCount + sCount;
        }
        return answer;
    }

    public static void main(String[] args) {
        CountNumberOfTeams cnot = new CountNumberOfTeams();
        Verifier.verifyEquals(cnot.numTeams(new int[]{2,5,3,4,1}), 3);
        Verifier.verifyEquals(cnot.numTeams(new int[]{2,1,3}), 0);
        Verifier.verifyEquals(cnot.numTeams(new int[]{2,1,2,3}), 1);
        Verifier.verifyEquals(cnot.numTeams(new int[]{1,2,3,4}), 4);
    }

}
