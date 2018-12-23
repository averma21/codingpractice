package math;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortedPermutationRank {

    Map<Character, Boolean> used;
    List<Character> charList;

    public int findRank(String A) {
        used = new HashMap<>(26);
        charList = new ArrayList<>(A.length());
        for (int i = 0; i < A.length(); i++) {
            charList.add(A.charAt(i));
        }
        Collections.sort(charList);
        return getCount(A, 0);
    }

    // BIG INteger is not required since (abmodm)=((amodm)(bmodm))modm
    // so just loop from 1 to n and multiply and take mods
    private BigInteger factorial(BigInteger x) {
        if (x.equals(BigInteger.ONE) || x.equals(BigInteger.ZERO))
            return BigInteger.ONE;
        return x.multiply(factorial(x.subtract(BigInteger.ONE)));
    }

    private int getCount(String s, int index) {
        int size = s.length();
        char currentChar = s.charAt(index);
        long count = 0;
        int remainingPlaces = size - index - 1;
        BigInteger factB = factorial(new BigInteger("" + remainingPlaces));
        BigInteger mod = factB.mod(new BigInteger("" + 1000003));
        int fact = Integer.parseInt(mod.toString());
        for (char c : charList) {
            if (c == currentChar) {
                break;
            }
            if (used.get(c) != null) {
                continue;
            }
            count = (fact + count)%1000003;
        }
        if (remainingPlaces == 0) {
            return (int)((count + 1)%1000003);
        }
        used.put(currentChar, true);
        return (int)((count + getCount(s, index + 1))%1000003);
    }

    public static void main(String[] args) {
        SortedPermutationRank sortedPermutationRank = new SortedPermutationRank();
        System.out.println(sortedPermutationRank.findRank("cbad"));
        System.out.println(sortedPermutationRank.findRank("acb"));
        System.out.println(sortedPermutationRank.findRank("VIEW"));
        System.out.println(sortedPermutationRank.findRank("AaBbCc"));
        System.out.println(sortedPermutationRank.findRank("ZCSFLVHXRYJQKWABGT"));
    }

}
