package backtracking;

import math.Palindrome;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    private boolean isPalindrome(String s) {
        if (s == null || s.length() == 1)
            return true;
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    List<List<String>> partition(String A) {
        List<List<String>> ans = new ArrayList<>();
        if (A == null || A.length() == 0)
            return ans;
        String prefix = "";
        for (int i = 0; i < A.length(); i++) {
            prefix += A.charAt(i);
            if (isPalindrome(prefix)) {
                if (i < A.length() - 1) {
                    List<List<String>> futResult = partition(A.substring(i + 1));
                    for (List<String> l : futResult) {
                        l.add(0, prefix);
                        ans.add(l);
                    }
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(prefix);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        PalindromePartitioning pp = new PalindromePartitioning();
        System.out.println(pp.partition("aab"));
        System.out.println(pp.partition("aabb"));
        System.out.println(pp.partition("aabc"));
        System.out.println(pp.partition("abcd"));
        System.out.println(pp.partition("abcba"));
        System.out.println(pp.partition("abcbab"));
    }

}
