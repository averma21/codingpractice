package others.leetc.dp;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/different-ways-to-add-parentheses/

/**
 * First extract numbers and operators from the given string.<br>
 * Eg given string 2*3-4*5<br>
 * Numbers [] = {2, 3, 4, 5}<br>
 * Operators [] = {'*', '-', '*'}<br>
 * N = Numbers.length<br>
 * Maintain a DP array of three dimensions dp[N][N][variable]<br>
 * dp[i][j] will denote the answer from index i to j of <b>Numbers arr</b>.<br>
 * so dp[i][j] would be an array.<br>
 * The final answer would be dp[0][N-1]<br>
 * if (j < i) dp[i][j] doesn't make sense so it is set to [0].<br>
 * if (j == i) dp[i][j] = [Numbers[i]]<br>
 * if (j == i+1) dp[i][j] = [Numbers[i] operated with Numbers[j]] (since this is of the form "A operator B" where A and B are numbers.
 * Operation is done based on operator[i]) Eg dp[2][3] = [20] (just 4*5)<br>
 * if (j > i + 1) then answer is obtained from other values in dp array Eg.<br>
 *    if i = 0 and j = 3, then theoretically we could place parenthesis just enclosing:<br>
 *      <ol>
 *          <li>(i) and (i+1, i+2, j) i.e. (2) * (3,4,5) </li>
 *          <li>(i, i+1) and (i+2, j) i.e. (2,3) - (4,5) </li>
 *          <li>(i, i+1, i+2) and (j) i.e. (2,3,4) * (5) </li>
 *      </ol>
 *     In the above cases, the left and right part lists(arrays) are picked from earlier calculated dp values. The operator
 *     is picked based on the index where we divide left and right. In each case, each element of the left list has to be
 *     operated with each element of the right list.<br>
 *
 *  Eg
 *
 *    <code>dp[1][3] = [dp[1][1] - dp[2][3] , dp[1][2] * dp[3][3]] = [3 - 20, -1 * 5] = [-17, -5]</code><br>
 *    <code>dp[0,2] = [dp[0][0] * dp[1][2], dp[0][1] - dp[2][2]] = [2*-1, 6-4] = [-2, 2]</code><br>
 *    <code>dp[0,3] = [dp[0][0] * dp[1][3], dp[0][1] - dp[2][3], dp[0][2] * dp[3][3]] = [2*[-17, -5], 6 - 20, [-2,2]*5] = [-34, -10, -14, -10, 10] => Answer</code>
 *
 */
public class DifferentWaysToAddParentheses {

    List<Integer> numbers;
    List<Character> operators;

    int operate(int left, int right, char c) {
        int result;
        switch (c) {
            case '+': result = left + right;break;
            case '-': result = left - right;break;
            default:  result = left * right;break;
        }
        return result;
    }

    void segregateNumbersAndOperators(String input) {
        StringBuilder curNum = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                if (curNum.length() > 0) {
                    numbers.add(Integer.parseInt(curNum.toString()));
                    curNum.setLength(0);
                    operators.add(c);
                }
            } else {
                curNum.append(c);
            }
        }
        if (curNum.length() > 0) {
            numbers.add(Integer.parseInt(curNum.toString()));
        }
    }

    public List<Integer> diffWaysToCompute(String input) {
        numbers = new ArrayList<>();
        operators = new ArrayList<>();
        segregateNumbersAndOperators(input);
        int N = numbers.size();
        int [][][] dp = new int[N][N][];
        List<Integer> values = new ArrayList<>();
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                if (j < i) {
                    dp[i][j] = new int[] {0};
                } else if (j == i) {
                    dp[i][j] = new int[] {numbers.get(i)};
                } else if (j == i + 1){
                    dp[i][j] = new int[] {operate(numbers.get(i), numbers.get(j), operators.get(i))};
                } else {
                    for (int k = i; k < j; k++) {
                        int [] leftV = dp[i][k];
                        int [] rightV = dp[k+1][j];
                        for (int lv : leftV) {
                            for (int rv : rightV) {
                                values.add(operate(lv, rv, operators.get(k)));
                            }
                        }
                    }
                    int [] vArr = new int[values.size()];
                    int index = 0;
                    for (int v : values) {
                        vArr[index++] = v;
                    }
                    values.clear();
                    dp[i][j] = vArr;
                }
            }
        }
        for (int v : dp[0][N-1]) {
            values.add(v);
        }
        return values;
    }

    public static void main(String[] args) {
        DifferentWaysToAddParentheses dwt = new DifferentWaysToAddParentheses();
        Verifier.verifyEqualsUnordered(dwt.diffWaysToCompute("2-1-1"), Creator.getList(2,0));
        Verifier.verifyEqualsUnordered(dwt.diffWaysToCompute("2*3-4*5"), Creator.getList(-34,-14,-10,-10,10));
        Verifier.verifyEqualsUnordered(dwt.diffWaysToCompute("2*31-4*51*6"), Creator.getList(-2386,-2386,-2076,-2076,-1162,
                -1162,-852,16524,16524,16524,16524,16524,17748,17748));
    }
}
