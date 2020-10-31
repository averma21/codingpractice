package others.leetc.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class IntervalListIntersections {

    List<Integer> getIntersection(int [] a, int [] b) {
        if (a[1] < b[0] || b[1] < a[0]) {
            return Collections.emptyList();
        }
        int [] arr = new int[4];
        arr[0] = a[0];
        arr[1] = a[1];
        arr[2] = b[0];
        arr[3] = b[1];
        Arrays.sort(arr);
        return new ArrayList<Integer>() {{
            add(arr[1]);
            add(arr[2]);
        }};
    }

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0, j = 0; i < A.length && j < B.length; ) {
            int[] a = A[i], b = B[j];
            List<Integer> in = getIntersection(a, b);
            if (in.size() > 0) {
                list.add(in);
            }
            if (a[1]> b[1]) {
                j++;
            } else {
                i++;
            }
        }
        int [][] ans = new int[list.size()][2];
        int i = 0;
        for (List<Integer> l : list) {
            ans[i++] = new int[] {l.get(0), l.get(1)};
        }
        return ans;
    }

    public static void main(String[] args) {

    }

}
