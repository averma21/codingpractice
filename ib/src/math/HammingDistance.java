package math;

import util.Creator;

import java.util.ArrayList;
import java.util.List;

public class HammingDistance {

    private long diff(final int [] a) {
        int oneCount = 0, zeroCount = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0)
                zeroCount++;
            else oneCount++;
        }
        return 2L*oneCount*zeroCount;
    }

    public int hammingDistance(final List<Integer> A) {
        long sum = 0;
        List<Integer> B = new ArrayList<>(A);
        for (int bit = 0; bit < 32; bit++) {
            int a[] = new int[B.size()];
            for (int i = 0; i < B.size(); i++) {
                int b = B.get(i);
                a[i] = b & 1;
                B.set(i, b >> 1);
            }
            sum = (sum + diff(a))% 1000000007 ;
        }
        return (int)(sum) ;
    }

//    public int hammingDistance(final int[] A) {
//        int sum = 0;
//        for (int i = 0; i < A.length; i++) {
//            int a = A[i];
//            for (int j = i + 1; j < A.length; j++) {
//                int b = A[j];
//                int n = a^b;
//                int count = 0;
//                while (n != 0) {
//                    n = n & (n - 1);
//                    count++;
//                }
//                sum = sum + count;
//            }
//        }
//        return (sum * 2) % 1000000007;
//    }

    public static void main(String[] args) {
        HammingDistance hammingDistance = new HammingDistance();
        System.out.println(hammingDistance.hammingDistance(Creator.getList(2, 4, 6)));
        System.out.println(hammingDistance.hammingDistance(Creator.getList(Integer.MAX_VALUE, Integer.MAX_VALUE - 1)));
    }

}
