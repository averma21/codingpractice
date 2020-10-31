package others.leetc.contests.biweekly.thirtyseven;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class CoordinateWithMaxNetworkQuality {

    private Map<Integer, Integer> effectFromPrevious;

    double distance (int [] t1, int [] t2) {
        double x2 = Math.pow(t1[0] - t2[0], 2);
        double y2 = Math.pow(t1[1] - t2[1], 2);
        return Math.sqrt(x2 + y2);
    }

    boolean isLexSmaller(int [] p1, int [] p2) {
        return p1[0] < p2[0] || (p1[0] == p2[0] && p1[1] < p2[1]);
    }

    public int[] bestCoordinate(int[][] towers, int radius) {
        int maxQuality = -1;
        int [] answer = new int[] {-1,-1};
        effectFromPrevious = new HashMap<>();
        for (int i = 0; i < towers.length; i++) {
            int [] t1 = towers[i];
            int networkQuality = t1[2] + effectFromPrevious.getOrDefault(i, 0);
            for (int j = i+1; j < towers.length; j++) {
                int [] t2 = towers[j];
                double d = distance(t1, t2);
                if (d <= radius) {
                    networkQuality += Math.floor(t2[2]/(1+d));
                    int thisTowersEffect = (int)Math.floor(t1[2]/(1+d));
                    int curVal = effectFromPrevious.getOrDefault(j, 0);
                    effectFromPrevious.put(j, curVal + thisTowersEffect);
                }
            }
            if (networkQuality > maxQuality || (networkQuality == maxQuality && isLexSmaller(t1, answer))) {
                maxQuality = networkQuality;
                answer[0] = t1[0];
                answer[1] = t1[1];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        CoordinateWithMaxNetworkQuality cwm = new CoordinateWithMaxNetworkQuality();
        Verifier.verifyEquals(cwm.bestCoordinate(new int[][]{{1,2,5},{2,1,7},{3,1,9}}, 2), new int[] {2,1});
        Verifier.verifyEquals(cwm.bestCoordinate(new int[][]{{23,11,21}}, 9), new int[] {23,11});
        Verifier.verifyEquals(cwm.bestCoordinate(new int[][]{{1,2,13},{2,1,7},{0,1,9}}, 2), new int[] {1,2});
        Verifier.verifyEquals(cwm.bestCoordinate(new int[][]{{2,1,9},{0,1,9}}, 2), new int[] {0,1});
    }

}
