package arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//https://www.interviewbit.com/problems/set-matrix-zeros/
public class SetMatrixToZeros {

    void set(ArrayList<ArrayList<Integer>> a) {
        if (a == null || a.size() == 0 || a.get(0).size() == 0) {
            return;
        }
        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroCols = new HashSet<>();
        int m = a.size(), n = a.get(0).size();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a.get(i).get(j) == 0) {
                    zeroCols.add(j);
                    zeroRows.add(i);
                }
            }
        }
        zeroRows.forEach(r -> a.get(r).replaceAll(x -> 0));
        zeroCols.forEach(c -> {
            for (ArrayList<Integer> integers : a) {
                integers.set(c, 0);
            }
        });
    }

}
