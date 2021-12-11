package others.leetc.contests.biweekly.sixty.five;

import util.Verifier;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class MostBeautifulItem {

    public int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, Comparator.comparing(it -> it[0]));
        int [] dp = new int[items.length];
        int [] ans = new int[queries.length];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int maxBeautyTillNow = items[0][1];
        dp[0] = maxBeautyTillNow;
        treeMap.put(items[0][0], maxBeautyTillNow);
        for (int i = 1; i < items.length; i++) {
            dp[i] = Math.max(dp[i-1], items[i][1]);
            int ind=i;
            treeMap.compute(items[i][0], (k,v) -> v == null ? dp[ind] : Math.max(dp[ind], v));
        }
        for (int i = 0; i < queries.length; i++) {
            Integer key = treeMap.floorKey(queries[i]);
            ans[i] = key != null ? treeMap.get(key) : 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        MostBeautifulItem mbi = new MostBeautifulItem();
        Verifier.verifyEquals(mbi.maximumBeauty(new int[][]{{1,2},{3,2},{2,4},{5,6},{3,5}}, new int[] {1,2,3,4,5,6}), new int[] {2,4,5,5,6,6});
        Verifier.verifyEquals(mbi.maximumBeauty(new int[][]{{1,2},{1,2},{1,3},{1,4}}, new int[] {1}), new int[] {4});
        Verifier.verifyEquals(mbi.maximumBeauty(new int[][]{{10,1000}}, new int[] {5}), new int[] {0});
    }

}
