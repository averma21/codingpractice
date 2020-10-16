package others.leetc.contests.twohundred.ten;

import util.Verifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MaximalNetworkRank {

    private static class VertexPair {
        int v1; int v2;

        public VertexPair(int v1, int v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        @Override
        public int hashCode() {
            return v1 + v2;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof VertexPair && ((((VertexPair) o).v1 == v1 && ((VertexPair)o).v2 == v2 ) ||
                    (((VertexPair) o).v2 == v1 && ((VertexPair)o).v1 == v2));
        }
    }

    public int maximalNetworkRank(int n, int[][] roads) {
        Map<Integer, List<Integer>> networkMap = new HashMap<>();
        Map<VertexPair, Integer> pairMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            networkMap.put(i, new ArrayList<>());
        }
        for (int i = 0 ; i < roads.length; i++) {
            int [] road = roads[i];
            int roadID = i;
            VertexPair vp = new VertexPair(road[0], road[1]);
            networkMap.computeIfPresent(road[0], (k,l) -> {l.add(roadID); return l;});
            networkMap.computeIfPresent(road[1], (k,l) -> {l.add(roadID); return l;});
            pairMap.putIfAbsent(vp, 0);
            pairMap.computeIfPresent(vp, (k,v)->v+1);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int rank = networkMap.get(i).size() + networkMap.get(j).size() - pairMap.getOrDefault(new VertexPair(i,j), 0);
                max = Math.max(max, rank);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaximalNetworkRank mnr  = new MaximalNetworkRank();


    }

}
