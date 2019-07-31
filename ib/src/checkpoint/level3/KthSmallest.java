package checkpoint.level3;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KthSmallest {

    static int kthSmallest(List<Integer> A, int B) {
        int min = Collections.min(A);
        int max = Collections.max(A);
        while (min <= max) {
            int mid = min + (max - min) / 2;
            int lessThan = 0, equal = 0;
            for (Integer ai : A) {
                if (ai < mid)
                    lessThan++;
                else if (ai == mid)
                    equal++;
                if (lessThan >= B)
                    break;
            }
            if (lessThan < B && lessThan + equal >= B)
                return mid;
            if (lessThan >= B)
                max = mid - 1;
            else
                min = mid + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(kthSmallest(new ArrayList<Integer>(){{add(3);}}, 1), 3);
        Verifier.verifyEquals(kthSmallest(new ArrayList<Integer>(){{add(3);}}, 2), -1);
        Verifier.verifyEquals(kthSmallest(new ArrayList<Integer>(){{add(3);}}, 0), -1);
        Verifier.verifyEquals(kthSmallest(Creator.getList(4,1,2,3), 2), 2);
        Verifier.verifyEquals(kthSmallest(Creator.getList(4,1,2,3,3,4), 4), 3);
        Verifier.verifyEquals(kthSmallest(Creator.getList(4,1,2,3,3,4), 5), 4);
    }

}
