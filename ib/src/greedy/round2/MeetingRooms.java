package greedy.round2;

import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingRooms {

    private static class Info implements Comparable<Info> {
        int time;
        char type;

        public Info(int time, char type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Info info) {
            int c = Integer.compare(time, info.time);
            if (c != 0) {
                return c;
            }
            if (type == 'e') {
                return -1;
            }
            return 1;
        }
    }

    public int solve(int[][] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        List<Info> list = new ArrayList<>(A.length);
        for (int[] ints : A) {
            list.add(new Info(ints[0], 's'));
            list.add(new Info(ints[1], 'e'));
        }
        Collections.sort(list);
        int maxConcurrentMeetings = 0;
        int curOpen = 0;
        for (Info info : list) {
            if (info.type == 's') {
                curOpen++;
            } else {
                curOpen--;
            }
            maxConcurrentMeetings = Math.max(maxConcurrentMeetings, curOpen);
        }
        return maxConcurrentMeetings;
    }

    public static void main(String[] args) {
        MeetingRooms mr = new MeetingRooms();
        Verifier.verifyEquals(mr.solve(new int[][] {
                {0,30},
                {5,10},
                {15,20}
        }), 2);
        Verifier.verifyEquals(mr.solve(new int[][] {
                {1,18},
                {18,23},
                {15,29},
                {4,15},
                {2,11},
                {5,13}
        }), 4);
        Verifier.verifyEquals(mr.solve(new int[][] {
                {7,10},
                {4,19},
                {19,26},
                {14,16},
                {13,18},
                {16,21}
        }), 3);
    }

}
