package others.leetc.contests.onesixtyone;

import util.Verifier;

import java.util.Stack;

public class MinRemoveValidParenthesis {

    public String minRemoveToMakeValid(String s) {
        boolean [] remove = new boolean[s.length()];
        Stack<Integer> openParenPos = new Stack<>();
        StringBuilder ans = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char si = s.charAt(i);
            if (si == ')') {
                if (openParenPos.isEmpty()) {
                    remove[i] = true;
                } else {
                    openParenPos.pop();
                }
            } else if (si == '(') {
                openParenPos.push(i);
            }
        }
        while (!openParenPos.isEmpty()) {
            remove[openParenPos.pop()] = true;
        }
        for (int i = 0; i < s.length(); i++) {
            char si = s.charAt(i);
            if (!remove[i]) {
                ans.append(si);
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        MinRemoveValidParenthesis mrvp = new MinRemoveValidParenthesis();
        Verifier.verifyEquals(mrvp.minRemoveToMakeValid("lee(t(c)o)de)"), "lee(t(c)o)de");
        Verifier.verifyEquals(mrvp.minRemoveToMakeValid("a)b(c)d"), "ab(c)d");
        Verifier.verifyEquals(mrvp.minRemoveToMakeValid("))(("), "");
        Verifier.verifyEquals(mrvp.minRemoveToMakeValid("(a(b(c)d)"), "a(b(c)d)");
    }

}
