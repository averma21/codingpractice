package TwoPointers;

import java.util.List;

public class DiffK {

    int diffPossible(List<Integer> A, int B) {
        int i = 0 , j = 0, n = A.size();
        while (j<n) {
            int ai = A.get(i), aj = A.get(j);
            if (i == j)
                j++;
            else if (aj - ai == B)
                return 1;
            else if (aj - ai < B)
                j++;
            else
                i++;
        }
        return 0;
    }

    public static void main(String[] args) {

    }

}
