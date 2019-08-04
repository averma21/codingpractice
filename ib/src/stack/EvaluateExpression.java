package stack;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EvaluateExpression {

    public static int evalRPN(List<String> A) {
        Stack<String> stack = new Stack<>();
        for (String a : A) {
            int op1, op2;
            if (a.equals("+")) {
                op1 = Integer.parseInt(stack.pop());
                op2 = Integer.parseInt(stack.pop());
                stack.push((op2 + op1) + "");
            } else if (a.equals("-")) {
                op1 = Integer.parseInt(stack.pop());
                op2 = Integer.parseInt(stack.pop());
                stack.push((op2 - op1) + "");
            } else if (a.equals("*")) {
                op1 = Integer.parseInt(stack.pop());
                op2 = Integer.parseInt(stack.pop());
                stack.push((op2 * op1) + "");
            } else if (a.equals("/")) {
                op1 = Integer.parseInt(stack.pop());
                op2 = Integer.parseInt(stack.pop());
                stack.push((op1 == 0 ? 0 : op2/op1) + "");
            } else {
                stack.push(a);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(evalRPN(Creator.getList("2", "1", "+", "3", "*")), 9);
        Verifier.verifyEquals(evalRPN(Creator.getList("4", "13", "5", "/", "+")), 6);
        Verifier.verifyEquals(evalRPN(Creator.getList("2", "2", "/")), 1);
    }

}
