package others.leetc.contests.oneseventy.oneseventyeight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HowManySmaller {

    private static class Help implements Comparable<Help> {
        int num;
        int index;

        public Help(int num, int index) {
            this.num = num;
            this.index = index;
        }

        @Override
        public int compareTo(Help help) {
            return num - help.num;
        }
    }

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        List<Help> list = new ArrayList<>();
        if (nums == null || nums.length == 1)
            return nums;
        int index = 0;
        for (int num : nums) {
            list.add(new Help(num, index++));
        }
        Collections.sort(list);
        int [] ans = new int[nums.length];
        int prevCount = 0;
        index = 0;
        for (Help help : list) {
            int num = help.num;
            if (index > 0 && list.get(index - 1).num == num) {
                prevCount++;
            } else {
                prevCount = 0;
            }
            ans[help.index] = index - prevCount;
            index++;
        }
        return ans;
    }

    static void printArr(int [] a) {
        for (int n : a) {
            System.out.print(n + ",");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        printArr(smallerNumbersThanCurrent(new int[] {8,1,2,2,3}));
        printArr(smallerNumbersThanCurrent(new int[] {6,5,4,8}));
        printArr(smallerNumbersThanCurrent(new int[] {7,7,7,7}));
    }

}
