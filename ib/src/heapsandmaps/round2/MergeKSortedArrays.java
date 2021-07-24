package heapsandmaps.round2;

import util.Creator;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedArrays {

    private static class Pair implements Comparable<Pair> {
        int num;
        int listNo;
        int posInList;

        public Pair(int num, int listNo, int posInList) {
            this.num = num;
            this.listNo = listNo;
            this.posInList = posInList;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(num, o.num);
        }
    }

    public List<Integer> solve(List<List<Integer>> A) {
        List<Integer> ans = new ArrayList<>();
        if (A == null || A.isEmpty()) {
            return new ArrayList<>();
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(A.size());
        for (int i = 0; i < A.size(); i++) {
            List<Integer> l = A.get(i);
            if (!l.isEmpty()) pq.add(new Pair(l.get(0), i, 0));
        };
        while (!pq.isEmpty()) {
            Pair polled = pq.poll();
            ans.add(polled.num);
            List<Integer> list = A.get(polled.listNo);
            if (polled.posInList < list.size() - 1)
            pq.add(new Pair(list.get(polled.posInList + 1), polled.listNo, polled.posInList + 1));
        }
        return ans;
    }

    public static void main(String[] args) {
        MergeKSortedArrays mkl = new MergeKSortedArrays();
        System.out.println(mkl.solve(Creator.getList(
                Creator.getList(10,20,30),
                Creator.getList(15,25)
        )));
        System.out.println(mkl.solve(Creator.getList(
                Creator.getList(10,20,30),
                Creator.getList(15,25),
                Creator.getList(1))));
        System.out.println(mkl.solve(Creator.getList(
                Creator.getList(10),
                Creator.getList(10,20),
                Creator.getList(20,30,40,50))));
    }

}
