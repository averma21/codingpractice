package others.leetc.contests.oneseventy.oneseventynine;

import util.Verifier;

public class BulbSwitcherIII {

    public int numTimesAllBlue(int[] light) {
        int rightMost = -1, count = 0;
        for (int i = 0; i < light.length; i++) {
            rightMost = Math.max(rightMost, light[i]);
            if (rightMost == i + 1)
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        BulbSwitcherIII bs3 = new BulbSwitcherIII();
        Verifier.verifyEquals(bs3.numTimesAllBlue(new int[]{2,1,3,5,4}), 3);
        Verifier.verifyEquals(bs3.numTimesAllBlue(new int[]{3,2,4,1,5}), 2);
        Verifier.verifyEquals(bs3.numTimesAllBlue(new int[]{4,1,2,3}), 1);
        Verifier.verifyEquals(bs3.numTimesAllBlue(new int[]{2,1,4,3,6,5}), 3);
        Verifier.verifyEquals(bs3.numTimesAllBlue(new int[]{1,2,3,4,5,6}), 6);
        Verifier.verifyEquals(bs3.numTimesAllBlue(new int[]{1}), 1);
    }

}
