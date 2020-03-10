package others.leetc.contests.oneseventyeight;

import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankTeamsByVotes {

    public static String rankTeams(String[] votes) {
        if (votes == null)
            return null;
        if (votes.length == 1)
            return votes[0];
        Map<Character, List<Integer>> counts = new HashMap<>();
        for (String vote : votes) {
            for (int i = 0; i < vote.length(); i++) {
                char team = vote.charAt(i);
                if (!counts.containsKey(team)) {
                    List<Integer> l = new ArrayList<>(vote.length());
                    for (int j = 0; j < vote.length(); j++) {
                        l.add(0);
                    }
                    counts.put(team, l);
                }
                final int index = i;
                counts.computeIfPresent(vote.charAt(i), (k, v) -> {v.set(index, v.get(index) + 1); return v;});
            }
        }
        List<Character> list = new ArrayList<>(counts.keySet());
        list.sort(new Comparator<Character>() {
            @Override
            public int compare(Character t, Character t1) {
                List<Integer> c1 = counts.get(t);
                List<Integer> c2 = counts.get(t1);
                for (int i = 0; i < c1.size(); i++) {
                    //descending sort
                    if (c1.get(i) > c2.get(i)) {
                        return -1;
                    }
                    if (c1.get(i) < c2.get(i)) {
                        return 1;
                    }
                }
                return t.compareTo(t1);
            }
        });
        StringBuilder ans = new StringBuilder();
        list.forEach(ans::append);
        return ans.toString();
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(rankTeams(new String[]{"ABC","ACB","ABC","ACB","ACB"}), "ACB");
        Verifier.verifyEquals(rankTeams(new String[]{"WXYZ","XYZW"}), "XWYZ");
        Verifier.verifyEquals(rankTeams(new String[]{"ZABCEDGTR"}), "ZABCEDGTR");
        Verifier.verifyEquals(rankTeams(new String[]{"BCA","CAB","CBA","ABC","ACB","BAC"}), "ABC");
        Verifier.verifyEquals(rankTeams(new String[]{"M","M","M","M"}), "M");
    }

}
