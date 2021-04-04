package others;

import util.Verifier;

import java.util.ArrayList;
import java.util.List;

public class RadixSort {

    List<List<Integer>> lists;
    int [] arr;

    boolean distribute(int radix) {
        lists.forEach(List::clear);
        boolean nonZero = false;
        for (int a : arr) {
            int orig = a;
            for (int j = 0; j < radix; j++) {
                a /= 10;
            }
            a%=10;
            if (!nonZero && a != 0) {
                nonZero = true;
            }
            lists.get(a).add(orig);
        }
        return nonZero;
    }

    void collect() {
        int i = 0;
        for (List<Integer> li : lists) {
            for (int ele : li) {
                arr[i++] = ele;
            }
        }
    }

    int [] sort(int [] arr) {
        this.arr = arr;
        lists = new ArrayList<>(10);
        for (int i = 0; i <= 9; i++) {
            lists.add(new ArrayList<>());
        }
        int i = 0;
        while (distribute(i++)) {
            collect();
        }
        return arr;
    }

    public static void main(String[] args) {
        RadixSort rs = new RadixSort();
        Verifier.verifyEquals(rs.sort(new int[] {38, 290, 28, 7, 29, 14}), new int[] {7, 14, 28, 29, 38, 290});
        Verifier.verifyEquals(rs.sort(new int[] {2, 34, 100, 11, 15, 9}), new int[] {2, 9, 11, 15, 34, 100});
    }

}
