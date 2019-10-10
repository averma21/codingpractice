package DP;

import util.Creator;
import util.Pair;
import util.Verifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubMatricesWithSum0 {

    private static class Boundary implements Comparable<Boundary> {
        int start;
        int end;

        public Boundary(int start, int end) {
            this.start = start;
            this.end = end;
        }

        private int getLength() {
            return end - start + 1;
        }

        @Override
        public int compareTo(Boundary boundary) {
            return Integer.compare(this.getLength(), boundary.getLength());
        }

        @Override
        public boolean equals(Object b) {
            return b instanceof Boundary && ((Boundary) b).start == start && ((Boundary) b).end == end;
        }

        @Override
        public int hashCode() {
            return start + end + start*end;
        }
    }

    private Boundary getMaxSum0Boundary(int [] A) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        int sum = 0;
        if (A.length == 1 && A[0] == 0)
            return new Boundary(0,0);
        Boundary max = new Boundary(1, 0); // 0 length boundary
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            Integer index = sumMap.get(sum);
            if (index != null) {
                if (i - index > max.getLength()) {
                    max.start = sum == 0 ? 0 : index + 1;
                    max.end = i;
                }
            } else {
                sumMap.put(sum, i);
            }
        }
        return max;
    }

    private int getSum0BoundaryCount(int [] A) {
        // create an empty map
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();

        // create an empty vector of pairs to store
        // subarray starting and ending index
        ArrayList<Pair> out = new ArrayList<>();

        // Maintains sum of elements so far
        int sum = 0;

        for (int i = 0; i < A.length; i++)
        {
            // add current element to sum
            sum += A[i];

            // if sum is 0, we found a subarray starting
            // from index 0 and ending at index i
            if (sum == 0)
                out.add(new Pair(0, i));
            ArrayList<Integer> al = new ArrayList<>();

            // If sum already exists in the map there exists
            // at-least one subarray ending at index i with
            // 0 sum
            if (map.containsKey(sum))
            {
                // map[sum] stores starting index of all subarrays
                al = map.get(sum);
                for (int it = 0; it < al.size(); it++)
                {
                    out.add(new Pair(al.get(it) + 1, i));
                }
            }
            al.add(i);
            map.put(sum, al);
        }
        return out.size();
    }

    private int getSubMatrixCountWithSum0(int [][] A) {
        int m = A.length, n = A[0].length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int [] tempSum = new int[m];
            for (int j = i; j < n; j++) {
                for (int row = 0; row < m; row++) {
                    tempSum[row] += A[row][j];
                }
                count += getSum0BoundaryCount(tempSum);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        SubMatricesWithSum0 sum0 = new SubMatricesWithSum0();
        Verifier.verifyEquals(sum0.getMaxSum0Boundary(new int[]{2}), new Boundary(1,0));
        Verifier.verifyEquals(sum0.getMaxSum0Boundary(new int[]{-2}), new Boundary(1,0));
        Verifier.verifyEquals(sum0.getMaxSum0Boundary(new int[]{-2,-2}), new Boundary(1,0));
        Verifier.verifyEquals(sum0.getMaxSum0Boundary(new int[]{0}), new Boundary(0,0));
        Verifier.verifyEquals(sum0.getMaxSum0Boundary(new int[]{0,0}), new Boundary(0,1));
        Verifier.verifyEquals(sum0.getMaxSum0Boundary(new int[]{1,2,3,4,-4,-3,5,6,7,-7,-6,-5}), new Boundary(2,11));
        Verifier.verifyEquals(sum0.getMaxSum0Boundary(new int[]{1,2,3,4,-4,-3,100,5,6,7,-7,-6,-5}), new Boundary(7,12));
        Verifier.verifyEquals(sum0.getSum0BoundaryCount(new int[]{2}), 0);
        Verifier.verifyEquals(sum0.getSum0BoundaryCount(new int[]{-2}), 0);
        Verifier.verifyEquals(sum0.getSum0BoundaryCount(new int[]{-2,-2}), 0);
        Verifier.verifyEquals(sum0.getSum0BoundaryCount(new int[]{0}), 1);
        Verifier.verifyEquals(sum0.getSum0BoundaryCount(new int[]{0,0}), 3);
        Verifier.verifyEquals(sum0.getSum0BoundaryCount(new int[]{0,0,1,0,0}), 6);
        Verifier.verifyEquals(sum0.getSum0BoundaryCount(new int[]{0,0,1,0,0,-1}), 9);
        Verifier.verifyEquals(sum0.getSum0BoundaryCount(new int[]{1,2,3,4,-4,-3,5,6,7,-7,-6,-5}), 6);
        Verifier.verifyEquals(sum0.getSum0BoundaryCount(new int[]{1,2,3,4,-4,-3,100,5,6,7,-7,-6,-5}), 5);
        Verifier.verifyEquals(sum0.getSubMatrixCountWithSum0(new int[][]{{-8,5,7},{3,7,-8},{5,-8,9}}), 2);
    }

}
