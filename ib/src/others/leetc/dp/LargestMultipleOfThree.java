package others.leetc.dp;

import util.Verifier;

//https://leetcode.com/problems/largest-multiple-of-three/
//https://leetcode.com/problems/largest-multiple-of-three/discuss/517628/Python-Basic-Math

/**
 * Basic Math
 * 999....999 % 3 == 0
 * 1000....000 % 3 == 1
 * a000....000 % 3 == a % 3
 * abcdefghijk % 3 == (a+b+c+..+i+j+k) % 3
 *
 *
 * Explanation
 * Calculate the sum of digits total = sum(A)
 * If total % 3 == 0, we got it directly
 * If total % 3 == 1 and we have one of 1,4,7 in A:
 * we try to remove one digit of 1,4,7
 * If total % 3 == 2 and we have one of 2,5,8 in A:
 * we try to remove one digit of 2,5,8
 * If total % 3 == 2:
 * we try to remove two digits of 1,4,7
 * If total % 3 == 1:
 * we try to remove two digits of 2,5,8
 * Submit
 *
 * Complexity
 * Time O(nlogn), where I use quick sort.
 * We can also apply counting sort, so it will be O(n)
 * Space O(sort)
 */
public class LargestMultipleOfThree {

    public String largestMultipleOfThree(int[] digits) {
        int sum = 0;
        int [] count = new int [10];
        for (int d : digits) {
            sum+=d;
            count[d]++;
        }
        int mod = sum%3;
        if (sum == 0) {
            return "0";
        }
        while(mod!=0) {
            int reduction = 0;
            if (mod == 1) {
                boolean done = false;
                if (count[1] > 0) {
                    count[1]--;reduction = 1;
                    done = true;
                } else if (count[4] > 0) {
                    count[4]--;reduction = 4;
                    done = true;
                } else if (count[7] > 0) {
                    count[7]--;reduction = 7;
                    done = true;
                }
                if (!done) {
                    int reduce2By = count[2] >=2 ? 2 : count[2];
                    int reduce5By = count[5] > 0 ? (reduce2By == 2 ? 0 : (count[5] >=2 ? 2 : count[5]) - reduce2By) : 0;
                    int totalRed = reduce2By + reduce5By;
                    int reduce8By =  count[8] > 0 ? (totalRed == 2 ? 0 : (count[8] >=2 ? 2 : count[8]) - totalRed) : 0;
                    count[2] -= reduce2By;
                    count[5] -= reduce5By;
                    count[8] -= reduce8By;
                    reduction = reduce2By*2 + reduce5By*5 +reduce8By*8;
                }
            }
            if (mod == 2) {
                boolean done = false;
                if (count[2] > 0) {
                    count[2]--;reduction = 2;
                    done = true;
                } else if (count[5] > 0) {
                    count[5]--;reduction = 5;
                    done = true;
                } else if (count[8] > 0) {
                    count[8]--;reduction = 8;
                    done = true;
                }
                if (!done) {
                    int reduce1By = count[1] >=2 ? 2 : count[1];
                    int reduce4By = count[4] > 0 ? (reduce1By == 2 ? 0 : (count[4] >=2 ? 2 : count[4]) - reduce1By) : 0;
                    int totalRed = reduce1By + reduce4By;
                    int reduce7By =  count[7] > 0 ? (totalRed == 2 ? 0 : (count[7] >=2 ? 2 : count[7]) - totalRed) : 0;
                    count[1] -= reduce1By;
                    count[4] -= reduce4By;
                    count[7] -= reduce7By;
                    reduction = reduce1By + reduce4By*4 + reduce7By*7;
                }
            }
            sum -= reduction;
            mod = sum%3;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >=0; i--) {
            while(count[i] > 0) {
                sb.append(i);
                count[i]--;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LargestMultipleOfThree lm3 = new LargestMultipleOfThree();
        Verifier.verifyEquals(lm3.largestMultipleOfThree(new int[] {8,6,7,1,0}), "8760");
    }

}
