package math;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RearrangeArray {

    private static void arrange(List<Integer> a) {
        int n = a.size();
        for (int i = 0; i < n; i++) {
            int B = a.get(i);
            int C = a.get(B);
            a.set(i, B + (C%n)*n);
        }
        for (int i = 0; i < n; i++) {
            a.set(i, a.get(i) / n);
        }
    }

    private static void printList(List<Integer> list) {
        for (int a: list)
        System.out.print(a + ", ");
        System.out.println("");
    }


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>() {{
            add(2);
            add(1);
            add(0);
        }};
        RearrangeArray.arrange(list);
        printList(list);
        list = new ArrayList<Integer>() {{
            add(1);
            add(0);
        }};
        printList(list);
    }

}
