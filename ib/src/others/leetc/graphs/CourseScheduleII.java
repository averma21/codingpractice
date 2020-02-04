package others.leetc.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

//https://leetcode.com/problems/course-schedule-ii/
public class CourseScheduleII {

    Map<Integer, List<Integer>> prereqs;
    private LinkedHashSet<Integer> order;
    private Set<Integer> visited;

    private boolean visitAndCheckCycle(int n) {
        if (visited.contains(n)) {
            return !order.contains(n);
        }
        visited.add(n);
        for (int v : prereqs.getOrDefault(n, Collections.emptyList())) {
            if (visitAndCheckCycle(v)) {
                return true;
            }
        }
        order.add(n);
        return false;
    }

    public List<Integer> findOrder(int numCourses, int[][] prerequisites) {
        prereqs = new HashMap<>();
        for (int [] pre : prerequisites) {
            prereqs.putIfAbsent(pre[0], new ArrayList<>());
            prereqs.computeIfPresent(pre[0], (k,v) -> {v.add(pre[1]); return v;});
        }
        order = new LinkedHashSet<>();
        visited = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            if (visitAndCheckCycle(i)) {
                return Collections.emptyList();
            }
        }
        return new ArrayList<>(order);
    }

    public static void main(String[] args) {
        CourseScheduleII cs2 = new CourseScheduleII();
        System.out.println(cs2.findOrder(2, new int[][]{{1,0}}));
        System.out.println(cs2.findOrder(6, new int[][]{{0,1}, {0,2}, {1,2}, {3,4}, {4,5}}));
        System.out.println(cs2.findOrder(6, new int[][]{{0,1}, {0,2}, {1,2}, {3,4}, {4,5}, {5,3}}));
    }

}
