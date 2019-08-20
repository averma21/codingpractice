package trees;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HotelReviews {

    private static class Score implements Comparable<Score> {
        int index;
        int score;

        public Score(int index, int score) {
            this.index = index;
            this.score = score;
        }

        @Override
        public int compareTo(Score score) {
            return Integer.compare(this.score, score.score);
        }
    }

    public static List<Integer> solve(String A, List<String> B) {
        Trie trie = new Trie();
        String [] goodWords = A.split("_");
        for (String goodWord : goodWords) {
            trie.insert(goodWord);
        }
        List<Score> scores = new ArrayList<>(B.size());
        for (int i = 0; i < B.size(); i++) {
            String review = B.get(i);
            String [] words = review.split("_");
            int count = 0;
            for (String word : words) {
                if (trie.findPath(word) != null) {
                    count++;
                }
            }
            scores.add(new Score(i, count));
        }
        Collections.sort(scores, Collections.reverseOrder());
        ArrayList<Integer> ans = new ArrayList<>();
        for (Score score : scores) {
            ans.add(score.index);
        }
        return ans;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(solve("cool_ice_wifi", Creator.getList("water_is_cool", "cold_ice_drink",
                "cool_wifi_speed")), Creator.getList(2,0,1));
        Verifier.verifyEquals(solve("cool_ice_wifi", Creator.getList("water_is_cool", "cold_ice_drink",
                "cool_speed_ice", "cool_wifi_speed")), Creator.getList(2,3,0,1));
    }

}
