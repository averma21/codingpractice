package DP;

import util.Verifier;

import java.util.Stack;

public class LongestValidParenthesis {

    /**
     * O(n) time O(n) space. See {@link #lvp(String)} for O(1) space
     */
    public static int longestValidParentheses(String A) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    // O(1) space O(n) time
    public static int lvp(String A) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = A.length() - 1; i >= 0; i--) {
            if (A.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(longestValidParentheses("(())"), 4);
    }
}
