package util;

import java.util.List;

public class Printer {

    public static void printArr(int [] a) {
        for (int e : a) {
            System.out.print(e + ", ");
        }
        System.out.println("");
    }

    public static void printList(List<String> a) {
        for (String e : a) {
            System.out.print(e + ", ");
        }
        System.out.println("");
    }

    public static void printLists(List<List<String>> a) {
        for (List<String> e : a) {
            printList(e);
        }
        System.out.println("=================");
    }

}
