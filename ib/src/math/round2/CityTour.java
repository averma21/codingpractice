package math.round2;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//incorrect solution
public class CityTour {

    int findWays(int A, List<Integer> B) {
        if (A <= 0) {
            return 0;
        }
        Set<Integer> visited = B != null ? new HashSet<>(B) : new HashSet<>();
        List<Integer> toExplore = new ArrayList<>(visited);
        long ways = 1;
        int mod = (int)1e9 + 7;
        while (visited.size() < A) {
            Set<Integer> nextLevelSet = new HashSet<>();
            for (int v : toExplore) {
                int v1 = Math.max(v-1, 1);
                int v2 = Math.min(v+1, A);
                if (!visited.contains(v1)) {
                    nextLevelSet.add(v1);
                }
                if (!visited.contains(v2)) {
                    nextLevelSet.add(v2);
                }
            }
            ways *= nextLevelSet.size();
            ways %= mod;
            visited.addAll(nextLevelSet);
            toExplore.clear();
            toExplore.addAll(nextLevelSet);
        }
        return (int)(ways%mod);
    }

    public static void main(String[] args) {
        CityTour ct = new CityTour();
        Verifier.verifyEquals(ct.findWays(5, Creator.getList(2,5)), 6);
    }

}
