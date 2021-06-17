package checkpoint.level3;

import util.Creator;
import util.Verifier;

import java.util.List;

public class Numrange {

    private int countInternal(List<Integer> A, int V) {
        int i = 0, j = 0, sum = 0, count = 0;
        while (j < A.size()) {
            sum += A.get(j);
            while (i <= j && sum > V) {
                sum -= A.get(i);
                i++;
            }
            count += (j - i + 1);
            j++;
        }
        return count;
    }

    int count(List<Integer> A, int B, int  C) {
        if (A == null || A.size() == 0) {
            return 0;
        }

        return countInternal(A, C) - countInternal(A, B-1);
    }

    int countBF(List<Integer> A, int B, int  C) {
        //System.out.println("BRUTE FORCE====================");
        int n = A.size(), count = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += A.get(j);
                if (sum > C) {
                    break;
                }
                if (sum >= B && sum <= C) {
                    //System.out.printf("%d to %d = %d\n", i, j, sum);
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Numrange numrange = new Numrange();
        Verifier.verifyEquals(numrange.count(Creator.getList(10), 4, 5), 0);
        Verifier.verifyEquals(numrange.count(Creator.getList(10), 4, 50), 1);
        Verifier.verifyEquals(numrange.count(Creator.getList(10), 40, 50), 0);
        Verifier.verifyEquals(numrange.count(Creator.getList(10, 5, 1, 0, 2), 6, 8), 3);
        Verifier.verifyEquals(numrange.count(Creator.getList(5, 10, 1, 0, 2), 6, 8), 0);
        Verifier.verifyEquals(numrange.count(Creator.getList(5, 5, 5), 5, 5), 3);
        Verifier.verifyEquals(numrange.count(Creator.getList(5, 5, 5), 0, 0), 0);
        Verifier.verifyEquals(numrange.count(Creator.getList(0, 0, 0), 0, 0), 6);
        Verifier.verifyEquals(numrange.count(Creator.getList(5, 10, 1, 0, 2), 0, 0), 1);
        List<Integer> bigList = Creator.getList(80, 97, 78, 45, 23, 38, 38, 93, 83, 16,
                91, 69, 18, 82, 60, 50, 61, 70, 15, 6, 52, 90);
        Verifier.verifyEquals(numrange.count(bigList, 99, 269), numrange.countBF(bigList, 99, 269));
    }

}
