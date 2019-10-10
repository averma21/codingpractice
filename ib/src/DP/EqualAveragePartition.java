package DP;

import util.Creator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


//TLE Solution
public class EqualAveragePartition {

    void findSubset(List<Integer> A, List<List<Integer>> answers, List<Integer> subset, List<Integer> otherSet,
                    int remSum, int startIndex, int expectedSize) {
        if (!answers.isEmpty())
            return;
        if (remSum == 0 && subset.size() == expectedSize) {
            answers.add(new ArrayList<>(subset));
            while (startIndex < A.size())
                otherSet.add(startIndex++);
            answers.add(new ArrayList<>(otherSet));
            return;
        }
        if (startIndex >= A.size()) {
            return;
        }
        int curEle = A.get(startIndex);
        if (remSum >= curEle) {
            subset.add(startIndex);
            findSubset(A, answers, subset, otherSet,remSum - curEle, startIndex+1, expectedSize);
            subset.remove(subset.size() - 1);
        }
        otherSet.add(startIndex);
        findSubset(A, answers, subset, otherSet, remSum, startIndex+1, expectedSize);
        if (answers.isEmpty()) {
            otherSet.remove(otherSet.size() - 1);
        }
    }

    List<List<Integer>> partition(List<Integer> A) {
        Collections.sort(A);
        int sum = 0;
        int n = A.size();
        for (int a : A) {
            sum += a;
        }
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 1; i <= n - 1; i++) {
            List<List<Integer>> answers = new ArrayList<>();
            int div = sum*i/n;
            if (div*n == sum*i) {
                findSubset(A, answers, new ArrayList<>(), new ArrayList<>(), div, 0, i);
                if (!answers.isEmpty()) {
                    for (List<Integer> indexList : answers) {
                        List<Integer> values = new ArrayList<>();
                        for (int index : indexList) {
                            values.add(A.get(index));
                        }
                        results.add(values);
                    }
                    break;
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        EqualAveragePartition eap = new EqualAveragePartition();
        List<List<Integer>> answers = new ArrayList<>();
//        eap.findSubset(Creator.getList(4,5,6,7), answers, new ArrayList<>(), new ArrayList<>(), 11, 0, 2);
        System.out.println(eap.partition(Creator.getList(1,2,3,4,5,6,7,8)));
        System.out.println(eap.partition(Creator.getList(1,7,15,29,11,9)));
        System.out.println(eap.partition(Creator.getList(19, 5, 38, 22, 44, 12, 17, 35)));
        System.out.println(eap.partition(Creator.getList(47, 14, 30, 19, 30, 4, 32, 32, 15, 2, 6, 24)));
        System.out.println(eap.partition(Creator.getList(19, 5, 38, 22, 44, 12, 17, 35 )));
    }
}
