package TwoPointers;

import util.Verifier;

import java.util.Arrays;
import java.util.List;

public class RemoveDuplicatesSortedArrayII {

    public static int removeDuplicates(List<Integer> a) {
        if (a == null) {
            return 0;
        }
        if (a.size() <= 2)
            return a.size();
        int i = 0, j = 1, count = 1;
        while (j < a.size()) {
            if (a.get(i).equals(a.get(j))) {
                j++;
                count++;
            } else {
                if (count >= 2) {
                    a.set(i+1, a.get(i));
                    i++;
                }
                a.set(i+1, a.get(j));
                i++;j++;
                count = 1;
            }

        }
        if (count >= 2) {
            a.set(i+1, a.get(i));
        } else if (i < a.size() - 1){
            a.set(i+1, a.get(j-1));
        }
        i = count >= 2 ? i + 2:i+1;
        return i;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(new Integer[]{1,2,2,3,3,3,4,5,5,5});
        Verifier.verifyEquals(removeDuplicates(list), 8);
        Verifier.verifyEquals(list, Arrays.asList(new Integer[]{1,2,2,3,3,4,5,5,5,5}));
        list = Arrays.asList(new Integer[]{1,2,2,2,3,4});
        Verifier.verifyEquals(removeDuplicates(list), 5);
        Verifier.verifyEquals(list, Arrays.asList(new Integer[]{1,2,2,3,4,4}));
        list = Arrays.asList(new Integer[]{0,0,1,2});
        Verifier.verifyEquals(removeDuplicates(list), 4);
        Verifier.verifyEquals(list, Arrays.asList(new Integer[]{0,0,1,2}));
    }
}
