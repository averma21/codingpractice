package DP;

import util.Creator;
import util.Verifier;

import java.util.List;

public class MinJumpsArray {

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

//    private static int minJumps(List<Integer> A) {
//        int aSize = A.size();
//        if (aSize == 0)
//            return 0;
//        int [] steps = new int[aSize];
//        steps[aSize - 1] = 0;
//        for (int i = aSize - 2; i >= 0; i--) {
//            int ai = A.get(i);
//            if (ai == 0)
//                steps[i] = -1;
//            else if (ai >= aSize - 1 - i)
//                steps[i] = 1;
//            else {
//                int min = Integer.MAX_VALUE;
//                for (int j = 1; j <= ai && i + j < aSize; j++) {
//                    if (steps[i+j] != -1 && steps[i+j] < min) {
//                        min = steps[i+j];
//                    }
//                }
//                if (min < Integer.MAX_VALUE)
//                    steps[i] = 1 + min;
//                else
//                    steps[i] = -1;
//            }
//        }
//        return steps[0];
//    }

    public static void main(String[] args) {
        Verifier.verifyEquals(minJumps(Creator.getList(2)), 0);
        Verifier.verifyEquals(minJumps(Creator.getList(2,1)), 1);
        Verifier.verifyEquals(minJumps(Creator.getList(2,1,1)), 1);
        Verifier.verifyEquals(minJumps(Creator.getList(2,0,1)), 1);
        Verifier.verifyEquals(minJumps(Creator.getList(1,0,1)), -1);
        Verifier.verifyEquals(minJumps(Creator.getList(2,3,1,1,4)), 2);
        Verifier.verifyEquals(minJumps(Creator.getList(2,4,1,10,2,1,3,4)), 3);
    }

}
