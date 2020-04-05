package others.leetc.contests.biweekly.twentythree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountLargestGroup {

    public int countLargestGroup(int n) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int sum = 0, num = i;
            while (num > 0) {
                sum += num%10;
                num/=10;
            }
            map.putIfAbsent(sum, new HashSet<>());
            int num1 = i;
            map.computeIfPresent(sum, (k,v) -> {v.add(num1); return v;});
        }
        int maxSize = 0, maxCount = 0;
        for (Set<Integer> set : map.values()) {
            if (set.size() > maxSize) {
                maxSize = set.size();
                maxCount = 1;
            } else if (set.size() == maxSize) {
                maxCount++;
            }
        }
        return maxCount;
    }

}
