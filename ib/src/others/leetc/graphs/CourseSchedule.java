package others.leetc.graphs;

import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/problems/course-schedule/
public class CourseSchedule {
/*
    //Approach detecting cycles -- slower - well, time complexity is same
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
 */

    /*
     Faster solution. Removes prerequisites one by one, and keeps adding courses to resulting order.
     Idea from https://leetcode.com/problems/course-schedule/discuss/162743/JavaC%2B%2BPython-BFS-Topological-Sorting-O(N-%2B-E)
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] degree = new int[numCourses];
        Queue<Integer> bfs = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] e : prerequisites) {
            graph.computeIfPresent(e[1], (k,v) -> {
                v.add(e[0]);
                return v;
            });
            degree[e[0]]++;
        }
        for (int i = 0; i < numCourses; ++i) if (degree[i] == 0) bfs.add(i);
        int addedEntryCount = 0;
        while (!bfs.isEmpty()) {
            int start = bfs.remove();
            addedEntryCount++;
            for (int j: graph.getOrDefault(start, Collections.emptyList()))
                if (--degree[j] == 0) {
                    bfs.add(j);
                }
        }

        return addedEntryCount == numCourses;
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
