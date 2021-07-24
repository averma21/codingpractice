package heapsandmaps.round2;

import java.util.*;

public class NMaxPairCombinations {
    ArrayList<Integer> A;
    ArrayList<Integer> B;

    class Pair implements Comparable<Pair> {
        int i,j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        private int getSum() {
            return A.get(i) + B.get(j);
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

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(getSum(), o.getSum());
        }
    }

    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        if (A == null || A.size() == 0) {
            return B;
        }
        if (B == null || B.size() == 0) {
            return A;
        }
        this.A = A;
        this.B = B;
        Collections.sort(this.A, Collections.reverseOrder());
        Collections.sort(this.B, Collections.reverseOrder());
        int N = A.size();
        PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());
        Pair p = new Pair(0,0);
        pq.add(p);
        Set<Pair> completed = new HashSet<>();
        completed.add(p);
        ArrayList<Integer> ans = new ArrayList<>();
        while (ans.size() < N) {
            Pair next = pq.poll();
            if (next == null) {
                break;
            }
            ans.add(next.getSum());
            if (next.j < N - 1) {
                Pair p1 = new Pair(next.i, next.j+1);
                if (!completed.contains(p1)) {
                    completed.add(p1);
                    pq.add(p1);
                }
            }
            if (next.i < N - 1) {
                Pair p2 = new Pair(next.i + 1, next.j);
                if (!completed.contains(p2)) {
                    completed.add(p2);
                    pq.add(p2);
                }
            }
        }
        return ans;
    }

}
