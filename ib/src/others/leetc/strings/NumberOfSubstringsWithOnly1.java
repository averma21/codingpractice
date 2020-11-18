package others.leetc.strings;

import java.util.LinkedList;
import java.util.List;

public class NumberOfSubstringsWithOnly1 {

    public int numSub(String s) {
        List<Integer> all1StringLengths = new LinkedList<>();
        int lastOneIndex = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '0') {
                if (lastOneIndex != -1) {
                    all1StringLengths.add(i - lastOneIndex);
                    lastOneIndex = -1;
                }
            } else {
                if (lastOneIndex == -1) {
                    lastOneIndex = i;
                }
            }
        }
        if (lastOneIndex != -1) {
            all1StringLengths.add(s.length() - lastOneIndex);
        }
        int mod = (int)1.0e9+7;
        long sum = 0;
        for (int length : all1StringLengths) {
            sum += ((long)length*(length+1))/2;
            sum %= mod;
        }
        return (int)sum;
    }

}
