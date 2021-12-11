package others.leetc.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseCompletion {

    public int solve(int A, List<Integer> B, List<Integer> C) {
        Map<Integer, List<Integer>> isPrereqOfMap = new HashMap<>();
        Queue<Integer> noRemainingPrereqs = new LinkedList<>();
        Map<Integer, Integer> prereqCount = new HashMap<>();
        for (int i = 0; i < B.size(); i++) {
            int bi = B.get(i), ci = C.get(i);
            isPrereqOfMap.putIfAbsent(bi, new ArrayList<>());
            isPrereqOfMap.computeIfPresent(bi, (k,v) -> {v.add(ci); return v;});
            prereqCount.putIfAbsent(ci, 0);
            prereqCount.computeIfPresent(ci, (k,v) -> v+1);
        }
        for (int i = 1; i <= A; i++) {
            if (!prereqCount.containsKey(i)) {
                noRemainingPrereqs.add(i);
            }
        }
        int coursesFinished = 0;
        while (!noRemainingPrereqs.isEmpty()) {
            coursesFinished++;
            int course = noRemainingPrereqs.remove();
            for (int c : isPrereqOfMap.getOrDefault(course, new ArrayList<>())) {
                int count = prereqCount.get(c);
                count--;
                if (count == 0) {
                    prereqCount.remove(c);
                    noRemainingPrereqs.add(c);
                } else {
                    prereqCount.put(c, count);
                }
            }
        }
        return coursesFinished == A ? 1 : 0;
    }

}
