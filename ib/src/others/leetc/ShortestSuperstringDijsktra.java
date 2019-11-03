package others.leetc;

import util.Verifier;

import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

//https://leetcode.com/problems/find-the-shortest-superstring/discuss/382211/Easy-Understand-Java-code-using-Dijsktra
public class ShortestSuperstringDijsktra {

    class State {
        String word;
        int mask;

        public State(String word, int mask) {
            this.word = word;
            this.mask = mask;
        }

    }

    public String shortestSuperstring(String[] A) {
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> a.word.length() - b.word.length());
        Set<Integer> visited = new HashSet<>();

        int endMask = 0;
        for (int i = 0; i < A.length; i++) {
            endMask |= (1 << i);
        }
        System.out.println("endmask=" + Integer.toBinaryString(endMask));

        pq.add(new State("", 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            System.out.println("Polled=" + cur.word + ":" + Integer.toBinaryString(cur.mask));
            if (cur.mask == endMask) {
                return cur.word;
            }

            if (visited.contains(cur.mask)) {
                System.out.println("==================Found again : " + cur.word);
                continue;
            }

            visited.add(cur.mask);

            for (int i = 0; i < A.length; i++) {
                if ((cur.mask & (1 << i)) == 0) {
                    String nextStr1 = merge(cur.word, A[i]);
                    String nextStr2 = merge(A[i], cur.word);
                    int nextMask = cur.mask | (1 << i); //mask indicates all the words which have been used to form a string
                    System.out.println("Taking i = " + i + " string = " + A[i] + " ns1 = " + nextStr1 + " ns2 = "
                            + nextStr2 + " nmask = " + Integer.toBinaryString(nextMask));
                    pq.add(new State(nextStr1, nextMask));
                    pq.add(new State(nextStr2, nextMask));
                }
            }
            Iterator<State> itr =  pq.iterator();
            while (itr.hasNext()) {
                State nxt = itr.next();
                System.out.print(nxt.word + ", ");
            }
            System.out.println("");
        }

        return null;
    }

    private String merge(String head, String tail) {
        int i = 0, j = 0;
        while (i < head.length()) {
            if (j < tail.length() && head.charAt(i) == tail.charAt(j)) {
                i++;
                j++;
            } else if (j >= tail.length() && j != 0) {
                j = 0;
            } else if (j != 0) {
                j = 0;
            } else {
                i++;
            }
        }

        return head + tail.substring(j);
    }

    public static void main(String[] args) {
        ShortestSuperstringDijsktra ssd = new ShortestSuperstringDijsktra();
        Verifier.verifyEquals(ssd.shortestSuperstring(new String[]{"catg","ctaagt","gcta","ttca","atgcatc"}), "gctaagttcatgcatc");
    }

}
