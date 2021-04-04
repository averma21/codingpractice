package arrays;

import util.Verifier;

import java.util.ArrayList;
import java.util.Objects;

public class MergeIntervals {

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Interval interval = (Interval) o;
            return start == interval.start && end == interval.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }

    static class Position {
        int pos;
        boolean isContained;

        Position(int pos, boolean contained) {
            this.pos = pos;
            this.isContained = contained;
        }

        void print() {
            System.out.println("pos = " + pos + ", contained = " + isContained);
        }
    }

    private Position findPos(ArrayList<Interval> intervals, int num) {
        for (int i = 0; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (interval.start <= num && interval.end >= num) {
                return new Position(i, true);
            } else if (interval.start > num) {
                return new Position(i - 1, false);
            }
        }
        return new Position(intervals.size(), false);
    }


    public void copyList(ArrayList<Interval> to, ArrayList<Interval> from, int startPos, int endPos) {
        for (int i = startPos; i < endPos && i < from.size(); i++) {
            to.add(from.get(i));
        }
    }


    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> ans = new ArrayList<>();
        if (newInterval == null) {
            return intervals;
        }
        if (newInterval.start > newInterval.end) {
            newInterval = new Interval(newInterval.end, newInterval.start);
        }
        if (intervals == null || intervals.size() == 0) {
            ans.add(newInterval);
            return ans;
        }
        Position pos1 = findPos(intervals, newInterval.start);
        Position pos2 = findPos(intervals, newInterval.end);
        //pos1.print();
        //pos2.print();
        if (pos1.isContained) {
            copyList(ans, intervals, 0, pos1.pos);
        } else {
            copyList(ans, intervals, 0, pos1.pos + 1);
        }
        if (pos1.pos == pos2.pos) {
            if (pos1.isContained) {
                if (pos2.isContained) {
                    return intervals;
                }
                ans.add(new Interval(intervals.get(pos1.pos).start, newInterval.end));
                copyList(ans, intervals, pos1.pos + 1, intervals.size());
            } else {
                if (pos2.isContained) {
                    ans.add(new Interval(newInterval.start, intervals.get(pos2.pos).end));
                    copyList(ans, intervals, pos1.pos + 1, intervals.size());
                } else {
                    ans.add(newInterval);
                    copyList(ans, intervals, pos1.isContained ? pos1.pos : pos1.pos + 1, intervals.size());
                }
            }
            return ans;
        }
        if (pos1.isContained && pos2.isContained) {
            ans.add(new Interval(intervals.get(pos1.pos).start, intervals.get(pos2.pos).end));
            copyList(ans, intervals, pos2.pos + 1, intervals.size());
        } else if (pos1.isContained) {
            ans.add(new Interval(intervals.get(pos1.pos).start, newInterval.end));
            copyList(ans, intervals, pos2.pos + 1, intervals.size());
        } else if (pos2.isContained) {
            ans.add(new Interval(newInterval.start, intervals.get(pos2.pos).end));
            copyList(ans, intervals, pos2.pos + 1, intervals.size());
        } else {
            ans.add(newInterval);
            copyList(ans, intervals, pos2.pos + 1, intervals.size());
        }
        return ans;
    }

    public static ArrayList<Interval> createList(int... arr) {
        if (arr.length % 2 != 0) {
            throw new IllegalArgumentException("Array length is not even");
        }
        ArrayList<Interval> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i+=2) {
            list.add(new Interval(arr[i], arr[i+1]));
        }
        return list;
    }

    public static void main(String[] args) {
        MergeIntervals mi = new MergeIntervals();
        ArrayList<Interval> input = mi.createList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
        Verifier.verifyEquals(mi.insert(input, new Interval(10, 60)), mi.createList(10, 60, 70, 80, 90, 100));
        Verifier.verifyEquals(mi.insert(input, new Interval(10, 50)), mi.createList(10, 60, 70, 80, 90, 100));
        Verifier.verifyEquals(mi.insert(input, new Interval(2, 3)), mi.createList(2, 3, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100));
        Verifier.verifyEquals(mi.insert(input, new Interval(101, 105)), mi.createList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 101, 105));
        Verifier.verifyEquals(mi.insert(input, new Interval(2, 65)), mi.createList(2, 65, 70, 80, 90, 100));
        Verifier.verifyEquals(mi.insert(input, new Interval(2, 105)), mi.createList(2, 105));
        Verifier.verifyEquals(mi.insert(input, new Interval(22, 25)), mi.createList(10, 20, 22, 25, 30, 40, 50, 60, 70, 80, 90, 100));
        Verifier.verifyEquals(mi.insert(input, new Interval(22, 35)), mi.createList(10, 20, 22, 40, 50, 60, 70, 80, 90, 100));
    }

}
