package backtracking.round2;

import java.util.*;

public class PalindromePartitioning {

    Map<Integer, ArrayList<ArrayList<String>>> cache;

    ArrayList<ArrayList<String>> internalPartition(String A, int index) {
        if (index >= A.length()) {
            return new ArrayList<>();
        }
        if (index == A.length() - 1) {
            ArrayList<String> l1 =  new ArrayList<>() {{add("" + A.charAt(index));}};
            return new ArrayList<>() {{
                add(l1);
            }};
        }
        if (cache.containsKey(index)) {
            System.out.println("Getting from cache for index " + index);
            return cache.get(index);
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        for (int i = index; i < A.length(); i++) {
            char ai = A.charAt(i);
            sb.append(ai);
            if (isPalindrome(sb)) {
                ArrayList<ArrayList<String>> nextBreakup = internalPartition(A, i+1);
                for (List<String> list : nextBreakup) {
                    ArrayList<String> copy = new ArrayList<>();
                    copy.add(sb.toString());
                    copy.addAll(list);
                    ans.add(copy);
                }
                if (nextBreakup.isEmpty()) {
                    ArrayList<String> copy = new ArrayList<>();
                    copy.add(sb.toString());
                    ans.add(copy);
                }
            }
        }
        cache.put(index, ans);
        return ans;
    }

    private boolean isPalindrome(StringBuilder sb) {
        for (int i = 0, j = sb.length() - 1; i <= j; i++, j--) {
            if (sb.charAt(i) != sb.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    ArrayList<ArrayList<String>> partition(String A) {
        cache = new HashMap<>();
        return internalPartition(A, 0);
    }

    public static void main(String[] args) {
        PalindromePartitioning pp = new PalindromePartitioning();
        System.out.println(pp.partition("aab"));
        System.out.println(pp.partition("aabb"));
        System.out.println(pp.partition("aabc"));
        System.out.println(pp.partition("abcd"));
        System.out.println(pp.partition("abcba"));
        System.out.println(pp.partition("abcbab"));
        System.out.println(pp.partition("aaaaaaaaaaaa"));
    }

}
