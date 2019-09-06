package DP;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class SCS2 {

    private static boolean getNextOrder(List<Integer> l, int maxNo) {
        if (l.size() <= 1)
            return false;
        int breakPoint = l.size() - 1;
        Set<Integer> rem = new LinkedHashSet<>();
        for (; breakPoint > 0; breakPoint--) {
            rem.add(l.get(breakPoint));
            if (l.get(breakPoint - 1) < l.get(breakPoint))
                break;
        }
        if (breakPoint > 0) {
            int indexToChange = breakPoint - 1;
            int toChange = l.get(indexToChange);
            Set<Integer> used = new HashSet<>(l.subList(0, indexToChange));
            int changed = toChange;
            while (changed < maxNo) {
                changed++;
                if (!used.contains(changed)) {
                    break;
                }
            }
            if (changed <= maxNo) {
                used.add(changed);
                rem.add(toChange);
                rem.remove(changed);
                l.set(indexToChange, changed);
                List<Integer> x= new ArrayList<>(rem);
                Collections.sort(x);
                for (Integer i : x) {
                    l.set(++indexToChange, i);
                }
                return true;
            }
        }
        return false;
    }

    private static void getAllPerm(List<String> list, List<List<String>> perms, Set<String> visited, List<String> perm) {
        if (visited.size() == list.size()) {
            perms.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (visited.contains(list.get(i)))
                continue;
            String li = list.get(i);
            visited.add(li);
            perm.add(li);
            getAllPerm(list, perms, visited, perm);
            visited.remove(li);
            perm.remove(li);
        }
    }

    private static String join(String s1, String s2) {
        int m = Math.min(s1.length(), s2.length());
        for (int i = m; i >=0; i--) {
            if (s1.endsWith(s2.substring(0, i))) {
                return s1 + s2.substring(i);
            }
        }
        return s1 + s2;
    }

    private static int getSCS2(List<String> A) {
        Map<Integer, String> map = new HashMap<>();
        int num = 1;
        List<Integer> numList = new ArrayList<>(A.size());
        for (String s : A) {
            numList.add(num);
            map.put(num++, s);
        }
        boolean change;
        int min = Integer.MAX_VALUE;
        do {
            String joined = "";
            for (int index : numList) {
                joined = join(joined, A.get(index-1));
            }
            if (joined.length() < min)
                min = joined.length();
            change = getNextOrder(numList, A.size());
        } while (change);
        return min;
    }

    private static int getSCS(List<String> A) {
        List<List<String>> perms = new ArrayList<>();
        List<String> perm = new ArrayList<>();
        getAllPerm(A, perms, new HashSet<>(), perm);
        int min = Integer.MAX_VALUE;
        for (List<String> perm1 : perms) {
            String joined = "";
            for (String s : perm1) {
                joined = join(joined, s);
            }
            if (joined.length() < min)
                min = joined.length();
        }
        return min;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(join("a", "b"), "ab");
        Verifier.verifyEquals(join("a", "ab"), "ab");
        Verifier.verifyEquals(join("aa", "ab"), "aab");
        Verifier.verifyEquals(join("aa", "b"), "aab");
        Verifier.verifyEquals(getSCS(Creator.getList("alex","loves","leetcode")), "alexlovesleetcode".length());
        Verifier.verifyEquals(getSCS(Creator.getList("abb","bbc","bbb")), "abbbc".length());
        Verifier.verifyEquals(getSCS(Creator.getList("catg","ctaagt","gcta","ttca","atgcatc")), "gctaagttcatgcatc".length());

        Verifier.verifyEquals(getSCS2(Creator.getList("alex","loves","leetcode")), "alexlovesleetcode".length());
        Verifier.verifyEquals(getSCS2(Creator.getList("abb","bbc","bbb")), "abbbc".length());
        Verifier.verifyEquals(getSCS2(Creator.getList("catg","ctaagt","gcta","ttca","atgcatc")), "gctaagttcatgcatc".length());
        Verifier.verifyEquals(getSCS2(Creator.getList("nejqokaplfbrqe", "cjqpuidbwnbaml", "naiwqahtpubspt", "jvidmdlrhjhkjt", "pjvyhpbqlsm", "lcgkneuqsydk", "mruvnqlapmjhp", "sioft", "nehtaxnb", "xlpsgn")), 108);

//        List<Integer>  l = Creator.getList(1,2,3,4,5);
//        List<Integer> prev = new ArrayList<>(l);
//        boolean change;
//        int count = 0;
//        do {
//            System.out.println(l);
//            count++;
//            change = getNextOrder(l, 5);
//        } while (change);
//        System.out.println("Total perm count :" + count);
    }

}
