package others.leetc.graphs;

import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/course-schedule/
public class CourseSchedule {

    Set<Integer> whiteSet;
    Set<Integer> greySet;
    Set<Integer> blackSet;
    Map<Integer, List<Integer>> prereqs;

    private boolean containsCycle(int v) {
        whiteSet.remove(v);
        greySet.add(v);
        for (int n : prereqs.getOrDefault(v, Collections.emptyList())) {
            if (greySet.contains(n)) {
                return true;
            } else {
                if (blackSet.contains(n))
                    continue;
                if (containsCycle(n))
                    return true;
            }
        }
        greySet.remove(v);
        blackSet.add(v);
        return false;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        prereqs = new HashMap<>();
        for (int [] pre : prerequisites) {
            prereqs.putIfAbsent(pre[0], new ArrayList<>());
            prereqs.computeIfPresent(pre[0], (k,v) -> {v.add(pre[1]); return v;});
        }

        whiteSet = new HashSet<>();
        greySet = new HashSet<>();
        blackSet = new HashSet<>();

        for (int i = 0; i < numCourses; i++) {
            whiteSet.add(i);
        }

        while(!whiteSet.isEmpty()) {
            boolean cycleFound = containsCycle(whiteSet.iterator().next());
            if (cycleFound) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();
        Verifier.verifyEquals(cs.canFinish(2, new int[][]{}), true);
        Verifier.verifyEquals(cs.canFinish(0, new int[][]{}), true);
        Verifier.verifyEquals(cs.canFinish(2, new int[][]{{1,0}}), true);
        Verifier.verifyEquals(cs.canFinish(2, new int[][]{{1,0}, {0,1}}), false);
        Verifier.verifyEquals(cs.canFinish(6, new int[][]{{0,1}, {0,2}, {1,2}, {3,4}, {4,5}, {5,4}}), false);
        Verifier.verifyEquals(cs.canFinish(6, new int[][]{{0,1}, {0,2}, {1,2}, {3,4}, {4,5}}), true);
    }

}
