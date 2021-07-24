package heapsandmaps.round2;

import java.util.*;

public class MaximumSumCombinations {

    private static class Tuple implements Comparable<Tuple> {
        int sum;
        Pair p;

        public Tuple(int sum, Pair p) {
            this.sum = sum;
            this.p = p;
        }

        @Override
        public int compareTo(Tuple o) {
            return Integer.compare(sum, o.sum);
        }
    }

    private static final class Pair {
        int i,j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return i == pair.i &&
                    j == pair.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        Collections.sort(A, Collections.reverseOrder());
        Collections.sort(B, Collections.reverseOrder());
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Tuple> pq = new PriorityQueue<>(Collections.reverseOrder());
        Set<Pair> done = new HashSet<>();
        Pair p = new Pair(0,0);
        done.add(p);
        pq.add(new Tuple(A.get(0) + B.get(0), p));
        while (ans.size() < C) {
            Tuple polled = pq.poll();
            if (polled == null) {
                throw new IllegalArgumentException("Queue can't be empty");
            }
            ans.add(polled.sum);
            Pair p1 = new Pair(polled.p.i + 1, polled.p.j);
            Pair p2 = new Pair(polled.p.i, polled.p.j + 1);
            if (!done.contains(p1)) {
                done.add(p1);
                pq.add(new Tuple(A.get(p1.i) + B.get(p1.j), p1));
            }
            if (!done.contains(p2)) {
                done.add(p2);
                pq.add(new Tuple(A.get(p2.i) + B.get(p2.j), p2));
            }
        }

        return ans;
    }

}
