package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//https://www.interviewbit.com/problems/find-duplicate-in-array/
public class FindDuplicatesInArray {

    int find(ArrayList<Integer> A) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int a : A) {
            if (countMap.get(a) == null) {
                countMap.put(a, 1);
            } else {
                return a;
            }
        }
        return -1;
    }

}
