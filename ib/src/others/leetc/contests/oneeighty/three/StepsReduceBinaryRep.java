package others.leetc.contests.oneeighty.three;

import util.Verifier;

public class StepsReduceBinaryRep {

    private int lsb;

    private boolean addOne(char [] arr) {
        int carry = 0;
        int i = lsb;
        while (i >= 0 && arr[i] == '1') {
            i--;
        }
        if (i>=0) {
            arr[i] = '1';
            return false;
        }
        return true; // no position found means it's all ones, so just indicate that it is all ones
    }

    public int numSteps(String s) {
        lsb = s.length() - 1;
        int steps = 0;
        char [] arr = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i);
        }
        while (lsb != 0) {
            if (arr[lsb] == '0') {
                lsb--;
                steps++;
            } else {
                boolean allOnes = addOne(arr);
                if (allOnes) {
                    // after adding 1 it would be of form one followed by all zeros... which would result in straightforward lsb + 1 steps
                    return steps + 1 /*for adding 1*/ + lsb + 1 /* for reducing 1000.0 to 1*/;
                }
                steps++;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        StepsReduceBinaryRep sr = new StepsReduceBinaryRep();
        Verifier.verifyEquals(sr.numSteps("1101"), 6);
        Verifier.verifyEquals(sr.numSteps("10"), 1);
        Verifier.verifyEquals(sr.numSteps("1"), 0);
        Verifier.verifyEquals(sr.numSteps("11110"), 6);
        Verifier.verifyEquals(sr.numSteps("1010"), 6);
    }

}
