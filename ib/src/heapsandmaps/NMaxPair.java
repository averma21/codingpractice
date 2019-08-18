package heapsandmaps;

import backtracking.NQueen;
import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class NMaxPair {

    class Node implements Comparable<Node> {
        final int sum;
        final int aIndex;
        final int bIndex;

        public Node(int sum, int aIndex, int bIndex) {
            this.sum = sum;
            this.aIndex = aIndex;
            this.bIndex = bIndex;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(sum, node.sum);
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Node && ((Node) o).aIndex == aIndex && ((Node) o).bIndex == bIndex;
        }

        @Override
        public int hashCode() {
            return aIndex + bIndex;
        }
    }

    public List<Integer> solve(List<Integer> A, List<Integer> B) {
        if (A == null || A.size() == 0)
            return B;
        if (B == null || B.size() == 0)
            return A;
        Collections.sort(A, Collections.reverseOrder());
        Collections.sort(B, Collections.reverseOrder());
        PriorityQueue<Node> pq = new PriorityQueue<>(Collections.reverseOrder(Node::compareTo));
        pq.add(new Node(A.get(0) + B.get(0), 0, 0));
        int count = 0;
        ArrayList<Integer> ans = new ArrayList<>(A.size());
        Set<Node> set = new HashSet<>(A.size());
        while (count < A.size() && !pq.isEmpty()) {
            Node polled = pq.poll();
            ans.add(polled.sum);
            if (polled.aIndex < A.size() - 1) {
                Node next1 = new Node(A.get(polled.aIndex + 1) + B.get(polled.bIndex), polled.aIndex + 1, polled.bIndex);
                if (!set.contains(next1)) {
                    set.add(next1);
                    pq.add(next1);
                }
            }
            if (polled.bIndex < A.size() -1) {
                Node next2 = new Node(A.get(polled.aIndex) + B.get(polled.bIndex + 1), polled.aIndex, polled.bIndex + 1);
                if (!set.contains(next2)) {
                    set.add(next2);
                    pq.add(next2);
                }
            }
            count++;
        }
        return ans;
    }

    public static void main(String[] args) {
        NMaxPair nmp = new NMaxPair();
        Verifier.verifyEquals(nmp.solve(Creator.getList(3,2), Creator.getList(1,4)), Creator.getList(7,6));
        Verifier.verifyEquals(nmp.solve(Creator.getList(1), Creator.getList(2)), Creator.getList(3));
    }

}
