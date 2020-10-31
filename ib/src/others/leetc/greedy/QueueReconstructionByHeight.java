package others.leetc.greedy;

import util.Verifier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Among those people who have k=0, we need find the smallest height person and put that in the answer. Then we decrement
 * the k value of every remaining person whose height is smaller or equal to the person we just put in the answer. Repeat the
 * above steps until we get all answers.
 */

//https://leetcode.com/problems/queue-reconstruction-by-height/
public class QueueReconstructionByHeight {

    private static class Entry {
        int number;
        int index;

        public Entry(int number, int index) {
            this.number = number;
            this.index = index;
        }
    }

    public int[][] reconstructQueue(int[][] people) {
        Comparator<Integer> cmpr = new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return Integer.compare(people[integer][0], people[t1][0]);
            }
        };
        PriorityQueue<Integer> zeroElePriorityQueue = new PriorityQueue<Integer>(cmpr);
        TreeMap<Integer, List<Entry>> dataSet = new TreeMap<>(cmpr);
        int [][] ans = new int[people.length][2];
        for (int i = 0; i < people.length; i++) {
            int [] p = people[i];
            if (p[1] == 0) {
                zeroElePriorityQueue.add(i);
            } else {
                dataSet.putIfAbsent(i, new ArrayList<>());
                int i1 = i;
                dataSet.computeIfPresent(i , (k,v) -> {v.add(new Entry(p[1], i1)); return v;});
            }
        }
        int ansIndex = 0;
        while (!zeroElePriorityQueue.isEmpty()) {
            int idx = zeroElePriorityQueue.remove();
            ans[ansIndex][0] = people[idx][0];
            ans[ansIndex][1] = people[idx][1];
            ansIndex++;
            dataSet.headMap(idx, true).forEach((i1, le1) ->  {
                le1.forEach(e1 -> {
                    e1.number = e1.number-1;
                    if (e1.number == 0) {
                        zeroElePriorityQueue.add(e1.index);
                    }
                });

            });
        }
        return ans;
    }

    public static void main(String[] args) {
        QueueReconstructionByHeight qrbh = new QueueReconstructionByHeight();
        Verifier.verifyArrayEquals(qrbh.reconstructQueue(
             new int[][] {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}}
        ), new int[][] {{5,0},{7,0},{5,2},{6,1},{4,4},{7,1}});
        Verifier.verifyArrayEquals(qrbh.reconstructQueue(
                new int[][] {{9,0},{7,0},{1,9},{3,0},{2,7},{5,3},{6,0},{3,4},{6,2},{5,2}}
        ), new int[][] {{3,0},{6,0},{7,0},{5,2},{3,4},{5,3},{6,2},{2,7},{9,0},{1,9}});
    }

}
