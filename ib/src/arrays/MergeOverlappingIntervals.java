package arrays;

import java.util.ArrayList;
import java.util.Comparator;

import static arrays.MergeIntervals.Interval;
import static arrays.MergeIntervals.createList;

public class MergeOverlappingIntervals {

    public Interval getNewRunningInterval(Interval running, Interval interval) {
        if (running.end >= interval.end) {
            return running;
        }
        if (interval.start <= running.end) {
            return new Interval(running.start, interval.end);
        }
        return interval;
    }

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return intervals;
        }

        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval interval, Interval t1) {
                return Integer.compare(interval.start, t1.start);
            }
        });
        ArrayList<Interval> ans = new ArrayList<>();
        Interval runningInterval = new Interval(intervals.get(0).start, intervals.get(0).end);
        for (int i = 1; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            Interval newRunning = getNewRunningInterval(runningInterval, interval);
            if (newRunning.equals(interval)) {
                ans.add(runningInterval);
            }
            runningInterval = newRunning;
        }
        ans.add(runningInterval);
        return ans;
    }

    public static void main(String[] args) {
        ArrayList<Interval> intervals = createList(4, 100, 48, 94, 16, 21, 58, 71, 36, 53, 49, 68, 18, 42, 37, 90, 68, 75, 6, 57, 25, 78, 58, 79, 18,
                29, 69, 94, 5, 31, 10, 87, 21, 35, 1, 32, 7, 24, 17, 85, 30, 95, 5, 63, 1, 17, 67, 100, 53, 55, 30, 63, 7, 76, 33, 51, 62, 68, 78, 83, 12,
                24, 31, 73, 64, 74, 33, 40, 51, 63, 17, 31, 14, 29, 9, 15, 39, 70, 13, 67, 27, 100, 10, 71, 18, 47, 48, 79, 70, 73, 44, 59, 68, 78, 24, 67,
                32, 70, 29, 94, 45, 90, 10, 76, 12, 28, 31, 78, 9, 44, 29, 83, 21, 35, 46, 93, 66, 83, 21, 72, 29, 37, 6, 11, 56, 87, 10, 26, 11, 12, 15, 88,
                3, 13, 56, 70, 40, 73, 25, 62, 63, 73, 47, 74, 8, 36);
        MergeOverlappingIntervals moi = new MergeOverlappingIntervals();
        moi.merge(intervals);
    }

}
