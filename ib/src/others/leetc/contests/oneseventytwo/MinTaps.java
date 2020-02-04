package others.leetc.contests.oneseventytwo;

import util.Verifier;

import java.util.Arrays;
import java.util.List;

//https://leetcode.com/contest/weekly-contest-172/problems/minimum-number-of-taps-to-open-to-water-a-garden/
public class MinTaps {

    //https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/discuss/484341/Python-Jump-Game-II
    //https://www.interviewbit.com/problems/min-jumps-array/
    private static int minJumps(List<Integer> A) {
        if(A.size() == 1){
            return 0;
        }
        int jumps = 1;
        int lastReach = A.get(0);
        int maxReach = A.get(0);
        for(int i=1; i<A.size(); i++){
            if(maxReach < i){
                return -1;
            }
            if(lastReach < i){
                jumps++;
                lastReach = maxReach;
            }
            maxReach = Math.max(maxReach, i + A.get(i));
        }
        return jumps;
    }

    public int minTaps(int n, int[] ranges) {
        Integer [] maxRange = new Integer[n+1];
        Arrays.fill(maxRange, 0);
        for (int i = 0; i <= n; i++) {
            int left = Math.max(0, i - ranges[i]);
            int right = Math.min(n, i + ranges[i]);
            maxRange[left] = Math.max(maxRange[left], right - left);
        }
        return minJumps(Arrays.asList(maxRange));
    }

    public static void main(String[] args) {
        MinTaps mt = new MinTaps();
        Verifier.verifyEquals(mt.minTaps(5, new int[]{3,4,1,1,0,0}), 1);
        Verifier.verifyEquals(mt.minTaps(3, new int[]{0,0,0,0}), -1);
        Verifier.verifyEquals(mt.minTaps(7, new int[]{1,2,1,0,2,1,0,1}), 3);
        Verifier.verifyEquals(mt.minTaps(8, new int[]{4,0,0,0,0,0,0,0,4}), 2);
        Verifier.verifyEquals(mt.minTaps(8, new int[]{4,0,0,0,4,0,0,0,4}), 1);
        Verifier.verifyEquals(mt.minTaps(35, new int[]{1,0,4,0,4,1,4,3,1,1,1,2,1,4,0,3,0,3,0,3,0,5,3,0,0,1,2,1,2,4,3,0,1,0,5,2}), 6);
        Verifier.verifyEquals(mt.minTaps(19, new int[]{4,1,5,0,5,3,3,3,0,0,3,3,2,2,4,4,2,3,4,2}), 3);
    }

}
