package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// WRONG SOLUTION

public class CityTour {

//    public int solve(int A, List<Integer> B) {
//        Map<Integer, Boolean> visited = new HashMap<>(A);
//        Set<Integer> nextSteps = new HashSet<>(A);
//        for (Integer x : B) {
//            visited.put(x, true);
//        }
//        for (int i = 1; i <= A; i++) {
//            visited.putIfAbsent(i, false);
//        }
//        for (int i = 1; i <= A; i++) {
//            if (visited.get(i)) {
//                if (i > 1 && !visited.get(i - 1)) {
//                    nextSteps.add(i - 1);
//                }
//                if (i < A && !visited.get(i + 1)) {
//                    nextSteps.add(i + 1);
//                }
//            }
//        }
//        return internalSolve(visited, nextSteps);
//    }

    private void print(Map<Integer, Boolean> visited) {
        for (Map.Entry<Integer, Boolean> entry : visited.entrySet()) {
            if (entry.getValue())System.out.print(entry.getKey() + ", ");
        }
    }

//    private int internalSolve(Map<Integer, Boolean> visited, Set<Integer> nextSteps) {
//        long mod = 1000000007;
//        long count = 0;
////        System.out.print("Visited : " );print(visited);
////        System.out.print("\nNextSteps:");
////        for (Integer integer : nextSteps) {
////            System.out.print(integer + ", ");
////        }
//        if (nextSteps.isEmpty()) {
////            System.out.print("Path complete.............\n");
//            return 1;
//        }
////        System.out.print("\n");
//        for (int step : nextSteps) {
////            System.out.println("Choosing step............." + step);
//            Set<Integer> next = new HashSet<>();
//            for (int x : nextSteps) {
//                if (x != step) {
//                    next.add(x);
//                }
//            }
//            Boolean ahead = visited.get(step + 1);
//            Boolean before = visited.get(step - 1);
//            if (ahead != null) {
//                if (!ahead) {
//                    next.add(step + 1);
//                }
//            }
//            if (before != null) {
//                if (!before) {
//                    next.add(step - 1);
//                }
//            }
//            visited.put(step, true);
//            count += (internalSolve(visited, next))%mod;
//            visited.put(step, false);
//            count = count % mod;
//        }
//        return (int) count;
//    }

    public static void main(String[] args) {
        CityTour tour = new CityTour();
//        System.out.println(tour.solve(5, Arrays.asList(new Integer[]{2, 5})));
//        System.out.println(tour.solve(4, Arrays.asList(new Integer[]{3, 1})));
//        System.out.println(tour.solve(10, Arrays.asList(new Integer[] {7, 5, 9, 3, 10, 2, 4, 8, 6 })));
//        System.out.println(tour.solve(5, Arrays.asList(new Integer[] {3})));
//        System.out.println(tour.solve(10, Arrays.asList(new Integer[] {6})));
//        System.out.println(tour.solve(48, Arrays.asList(new Integer[] {5, 12, 48, 34, 21, 29, 25, 11, 37, 26, 14, 15, 35, 41, 24, 39, 10, 17, 23, 16, 8, 44, 13, 31})));
    }

}
