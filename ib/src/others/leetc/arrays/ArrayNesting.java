package others.leetc.arrays;

import util.Verifier;

//https://leetcode.com/problems/array-nesting/
public class ArrayNesting {
    public int arrayNesting(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MAX_VALUE) {
                int start = nums[i], count = 0;
                while (nums[start] != Integer.MAX_VALUE) {
                    System.out.println("Considering " + start);
                    int temp = start;
                    start = nums[start];
                    count++;
                    nums[temp] = Integer.MAX_VALUE;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayNesting an = new ArrayNesting();
        Verifier.verifyEquals(an.arrayNesting(new int[]{5,4,0,3,1,6,2}), 4);
    }
}
