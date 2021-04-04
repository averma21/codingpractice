package arrays;

import java.util.ArrayList;
import java.util.List;

public class Palindrome {

    public ArrayList<ArrayList<Integer>> solve(int A) {
        if (A < 1) {
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> l1 = new ArrayList<>();
        l1.add(1);
        ans.add(l1);
        if (A == 1) {
            return ans;
        }
        List<Integer> prev = l1;
        for (int i = 1; i < A; i++) {
            ArrayList<Integer> cur = new ArrayList<>();
            cur.add(1);
            for (int j = 1; j < i; j++) {
                cur.add(prev.get(j) + prev.get(j-1));
            }
            cur.add(1);
            ans.add(cur);
            prev = cur;
        }
        return ans;
    }


    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.solve(0));
        System.out.println(palindrome.solve(1));
        System.out.println(palindrome.solve(2));
        System.out.println(palindrome.solve(3));
        System.out.println(palindrome.solve(4));
        System.out.println(palindrome.solve(5));
    }

}
