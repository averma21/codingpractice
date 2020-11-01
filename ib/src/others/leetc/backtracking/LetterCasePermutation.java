package others.leetc.backtracking;

import util.Creator;
import util.Verifier;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/letter-case-permutation/
public class LetterCasePermutation {

    String s;
    List<String> ans;

    private String generateString(int bitValue) {
        StringBuilder sb = new StringBuilder();
        String bitst = Integer.toBinaryString(bitValue);
        int zerosToAppend = s.length() - bitst.length();
        StringBuilder bits = new StringBuilder();
        while (zerosToAppend > 0) {
            bits.append("0");
            zerosToAppend--;
        }
        bits.append(bitst);
        for (int i = 0; i < bits.length(); i++) {
            char c = s.charAt(i);
            char b = bits.charAt(i);
            if (Character.isDigit(c)) {
                sb.append(c);
            } else {
                sb.append(b == '0' ? Character.toLowerCase(c) : Character.toUpperCase(c));
            }
        }
        return sb.toString();
    }

    private void traverse(int pos, int bitValue) {
        if (pos >= s.length()) {
            return;
        }
        if (pos == s.length() - 1) {
            ans.add(generateString(bitValue));
            return;
        }
        char c = s.charAt(pos+1);
        if (Character.isAlphabetic(c)) {
            traverse(pos+1, bitValue*2);
        }
        traverse(pos+1, bitValue*2+1);
    }

    public List<String> letterCasePermutation(String S) {
        s = S;
        ans = new LinkedList<>();
        if (Character.isAlphabetic(s.charAt(0))) {
            traverse(0, 0);
        }
        traverse(0, 1);
        return ans;
    }

    public static void main(String[] args) {
        LetterCasePermutation lcp = new LetterCasePermutation();
        Verifier.verifyEquals(lcp.letterCasePermutation("a1b2"), Creator.getList("a1b2","a1B2","A1b2","A1B2"));
        Verifier.verifyEquals(lcp.letterCasePermutation("3z4"), Creator.getList("3z4","3Z4"));
        Verifier.verifyEquals(lcp.letterCasePermutation("0"), Creator.getList("0"));
        Verifier.verifyEquals(lcp.letterCasePermutation("a"), Creator.getList("a", "A"));
        Verifier.verifyEquals(lcp.letterCasePermutation("12345"), Creator.getList("12345"));
    }

}
