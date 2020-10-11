package others.leetc.thirtydaychallenge;

import java.util.Stack;

//https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/530/week-3/3301/
public class ValidParenthesisString {

    //https://leetcode.com/problems/valid-parenthesis-string/discuss/582134/C%2B%2B-2-Pointer-Approach-Explained-O(1)-Space-O(N)-Time
//    public boolean checkValidString(String s) {
//        if (s == null || s.length() == 0)
//            return true;
//        Stack<Character> stack = new Stack<>();
//        int starCount = 0;
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (c == '(') {
//                stack.push('(');
//            } else if (c == ')') {
//                if (!stack.isEmpty()) {
//                    stack.pop();
//                } else if (starCount > 0) {
//                    starCount--;
//                } else {
//                    return false;
//                }
//            } else if (c == '*') {
//                starCount++;
//            }
//        }
//        while (!stack.isEmpty() && starCount > 0) {
//            stack.pop();
//            starCount--;
//        }
//    }

}
