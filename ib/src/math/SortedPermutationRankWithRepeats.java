package math;

import util.Verifier;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SortedPermutationRankWithRepeats {

    //Map<Character, Boolean> used;
    List<Character> charList;

    public int findRank(String A) {
        //used = new HashMap<>(26);
        Map<Character, Integer> repeatCount = new HashMap<>(26);
        Set<Character> characters = new HashSet<>();
        for (int i = 0; i < A.length(); i++) {
            characters.add(A.charAt(i));
            int count = repeatCount.getOrDefault(A.charAt(i), 0);
            repeatCount.put(A.charAt(i), count + 1);
        }
        charList = new ArrayList<>(characters);
        Collections.sort(charList);
        return getCount(A, 0, repeatCount);
    }

    // BIG INteger is not required since (abmodm)=((amodm)(bmodm))modm
    // so just loop from 1 to n and multiply and take mods
    private BigInteger factorial(BigInteger x) {
        if (x.equals(BigInteger.ONE) || x.equals(BigInteger.ZERO))
            return BigInteger.ONE;
        return x.multiply(factorial(x.subtract(BigInteger.ONE)));
    }

    private int calculatePerm(int remainingPlaces, Map<Character, Integer> repeatCount) {
        BigInteger factB = factorial(new BigInteger("" + remainingPlaces));
        for (Map.Entry<Character, Integer> entry : repeatCount.entrySet()) {
            if (entry.getValue() > 1) {
                factB = factB.divide(factorial(new BigInteger("" + entry.getValue())));
            }
        }
        BigInteger mod = factB.mod(new BigInteger("" + 1000003));
        return Integer.parseInt(mod.toString());
    }

    private int getCount(String s, int index, Map<Character, Integer> repeatCount) {
        int size = s.length();
        char currentChar = s.charAt(index);
        long count = 0;
        int remainingPlaces = size - index - 1;
        for (char c : charList) {
            if (c == currentChar) {
                break;
            }
            int cFreq = repeatCount.get(c);
            if (cFreq == 0) {
                continue;
            }
            repeatCount.put(c, cFreq - 1);
            int fact = calculatePerm(remainingPlaces, repeatCount);
            count = (fact + count)%1000003;
            repeatCount.put(c, cFreq);
        }
        if (remainingPlaces == 0) {
            return (int)((count + 1)%1000003);
        }
        int currentFreq = repeatCount.get(currentChar);
        repeatCount.put(currentChar, currentFreq - 1);
        return (int)((count + getCount(s, index + 1, repeatCount))%1000003);
    }

    public static void main(String[] args) {
        SortedPermutationRankWithRepeats permutationRankWithRepeats = new SortedPermutationRankWithRepeats();
        Verifier.verifyEquals(permutationRankWithRepeats.findRank("cbad"), 15);
        Verifier.verifyEquals(permutationRankWithRepeats.findRank("acb"), 2);
        Verifier.verifyEquals(permutationRankWithRepeats.findRank("VIEW"), 15);
        Verifier.verifyEquals(permutationRankWithRepeats.findRank("AaBbCc"), 51);
        Verifier.verifyEquals(permutationRankWithRepeats.findRank("ZCSFLVHXRYJQKWABGT"), 318057);
        Verifier.verifyEquals(permutationRankWithRepeats.findRank("aba"), 2);
        Verifier.verifyEquals(permutationRankWithRepeats.findRank("baca"), 8);
        Verifier.verifyEquals(permutationRankWithRepeats.findRank("aacb"), 2);
        Verifier.verifyEquals(permutationRankWithRepeats.findRank("baac"), 7);
        Verifier.verifyEquals(permutationRankWithRepeats.findRank("aaaa"), 1);
        Verifier.verifyEquals(permutationRankWithRepeats.findRank("ababab"), 6);
    }

}
