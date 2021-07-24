package heapsandmaps.round2;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class KLargestElements {

    public ArrayList<Integer> solve(ArrayList<Integer> A, int B) {

        if (A == null || A.size() == 0 || A.size() < B) {
            return A;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < B; i++) {
            pq.add(A.get(i));
        }

        for (int i = B; i < A.size(); i++) {
            int ai = A.get(i);
            if (ai > pq.peek()) {
                pq.poll();
                pq.add(ai);
            }
        }
        return new ArrayList<>(pq);
    }

}
