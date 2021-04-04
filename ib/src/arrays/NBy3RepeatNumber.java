package arrays;

import java.util.List;

//https://www.geeksforgeeks.org/n3-repeated-number-array-o1-space/
public class NBy3RepeatNumber {

    public int repeatedNumber(final List<Integer> a) {
        if (a == null || a.size() == 0) {
            return -1;
        }
        int count1 = 0, count2 = 0, n = a.size();
        if (n < 3) {
            return a.get(0);
        }
        int first=Integer.MAX_VALUE, second=Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int ai = a.get(i);
            if (first == ai) {
                count1++;
            } else if (second == ai) {
                count2++;
            } else if (count1 == 0) {
                count1++;
                first = ai;
            } else if (count2 == 0) {
                count2++;
                second = ai;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        // Again traverse the array and find the
        // actual counts.
        for (int i = 0; i < n; i++) {
            int ai = a.get(i);
            if (ai == first) {
                count1++;
            } else if (ai == second) {
                count2++;
            }
        }

        if (count1 > n / 3)
            return first;

        if (count2 > n / 3)
            return second;

        return -1;
    }

}
