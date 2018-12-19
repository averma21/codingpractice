package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomAttendance {

    int counted = 0;

    private ArrayList<Integer> solve(int A, List<Integer> B) {
        int numDigits = (int)Math.log10(A) + 1;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int b : B) {
            int remainingCount = b -1 ;
            for (int i = 1; i <= 9; i++) {
                int prefixDigits = digitsWithPrefix("" + i, numDigits);
                if (prefixDigits >= remainingCount) {
                    ans.add(Integer.parseInt(generatePrefix("" + i, remainingCount, numDigits)));
                    break;
                }
                remainingCount -= prefixDigits;
            }
        }
        return ans;
    }

    private int digitsWithPrefix(String prefix, int maxDigits) {
        int digitsInPrefix = prefix.length();
        int remainingDigits = maxDigits - digitsInPrefix;
        if (remainingDigits < 0) {
            return 0;
        }
        if (remainingDigits == 0) {
            return 1;
        }
        int count = 1, c = 1;
        while (c <= remainingDigits) {
            count = count * 10 + 1;
            c++;
        }
        return count;
    }

    private String generatePrefix(String prefix, int remainingCount, int maxDigits) {
        if (remainingCount <= 1)
            return prefix;
        for (int i = 0; i < 9; i++) {
            String extendedPrefix = prefix + i;
            int digitsWithExtendedPrefix = digitsWithPrefix(extendedPrefix, maxDigits);
            if (digitsWithExtendedPrefix >= remainingCount) {
                remainingCount--;
                return generatePrefix(extendedPrefix, remainingCount, maxDigits);
            }
            remainingCount -= digitsWithExtendedPrefix;
        }
        throw new IllegalArgumentException("Not possible");
    }


    private ArrayList<Integer> solve1(int A, List<Integer> B) {

        List<String> strings = new ArrayList<>(A);
        for (int i = 1; i <= A; i++) {
            strings.add("" + i);
        }
        Collections.sort(strings);
        System.out.println(strings);
        ArrayList<Integer> ans = new ArrayList<>(B.size());
        for (int i : B) {
            ans.add(Integer.parseInt(strings.get(i - 1)));
        }
        return ans;
    }

    public static void main(String[] args) {
        RandomAttendance randomAttendance = new RandomAttendance();
        System.out.println(randomAttendance.solve(804289385, Arrays.asList(new Integer [] {2, 5, 10, 106058146})));
    }

}
