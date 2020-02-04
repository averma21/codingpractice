package others.leetc.arrays;

import util.Verifier;

//https://leetcode.com/problems/jump-game/
public class JumpGame {

    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length == 1) {
            return true;
        }
        int maxJumpIndex = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (i > maxJumpIndex) {
                return false;
            }
            if (maxJumpIndex >= nums.length - 1) {
                return true;
            }
            if (i + nums[i] > maxJumpIndex) {
                maxJumpIndex = i + nums[i];
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(canJump(new int[]{2,3,1,1,4}), true);
        Verifier.verifyEquals(canJump(new int[]{0,2,3}), false);
    }

}
