package checkpoint.round2;

import java.util.ArrayList;
import java.util.Collections;

public class PrettyPrint {

    public static ArrayList<ArrayList<Integer>> prettyPrint(int A) {

        if (A <= 0) {
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int size = 2*A - 1;
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> l1 = new ArrayList<>(size);
            for (int j = 0; j < size; j++) {
                l1.add(1);
            }
            ans.add(l1);
        }
        int start = A;
        int top = 0, left = 0, right = size-1, bottom = size-1;
        while (top <= bottom && left <= right) {
            for (int i = left; i < right; i++) {
                ans.get(top).set(i, start);
            }
            for (int j = top; j < bottom; j++) {
                ans.get(j).set(right, start);
            }
            for (int i = right; i > left; i--) {
                ans.get(bottom).set(i, start);
            }
            for (int i = bottom; i > top; i--) {
                ans.get(i).set(left, start);
            }
            top++;right--;bottom--;left++;
            start--;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(prettyPrint(0));
        System.out.println("======================");
        System.out.println(prettyPrint(1));
        System.out.println("======================");
        System.out.println(prettyPrint(2));
        System.out.println("======================");
        System.out.println(prettyPrint(3));
        System.out.println("======================");
        System.out.println(prettyPrint(4));
        System.out.println("======================");
        System.out.println(prettyPrint(5));
        System.out.println("======================");
    }

}
