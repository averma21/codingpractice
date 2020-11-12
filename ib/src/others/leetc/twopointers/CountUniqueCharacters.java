package others.leetc.twopointers;

import util.Verifier;

import java.util.Arrays;

//https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/
//https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/discuss/128952/C%2B%2BJavaPython-One-pass-O(N)

/**
 * Intuition
 * Let's think about how a character can be found as a unique character.
 *
 * Think about string "XAXAXXAX" and focus on making the second "A" a unique character.
 * We can take "XA(XAXX)AX" and between "()" is our substring.
 * We can see here, to make the second "A" counted as a uniq character, we need to:
 *
 * insert "(" somewhere between the first and second A
 * insert ")" somewhere between the second and third A
 * For step 1 we have "A(XA" and "AX(A", 2 possibility.
 * For step 2 we have "A)XXA", "AX)XA" and "AXX)A", 3 possibilities.
 *
 * So there are in total 2 * 3 = 6 ways to make the second A a unique character in a substring.
 * In other words, there are only 6 substring, in which this A contribute 1 point as unique string.
 *
 * Instead of counting all unique characters and struggling with all possible substrings,
 * we can count for every char in S, how many ways to be found as a unique char.
 * We count and sum, and it will be out answer.
 *
 *
 * Explanation
 * index[26][2] record last two occurrence index for every upper characters.
 * Initialise all values in index to -1.
 * Loop on string S, for every character c, update its last two occurrence index to index[c].
 * Count when loop. For example, if "A" appears twice at index 3, 6, 9 seperately, we need to count:
 * For the first "A": (6-3) * (3-(-1))"
 * For the second "A": (9-6) * (6-3)"
 * For the third "A": (N-9) * (9-6)"
 *
 * Complexity
 * One pass, time complexity O(N).
 * Space complexity O(1).
 */
public class CountUniqueCharacters {

    public int uniqueLetterString(String s) {
        int [][] positions = new int[26][2];
        for (int i = 0; i < 26; ++i) Arrays.fill(positions[i], -1);
        int L = s.length();
        int mod = (int)1.0E9 + 7;
        long result = 0;
        for (int i = 0; i < L; i++) {
            int c = s.charAt(i) - 'A';
            result += (positions[c][1] - positions[c][0]) * (i - positions[c][1]);
            result %= mod;
            positions[c][0] = positions[c][1];
            positions[c][1] = i;
        }
        for (int c = 0; c < 26; c++) {
            result += (positions[c][1] - positions[c][0]) * (L - positions[c][1]);
            result %= mod;
        }
        return (int)result;
    }

    public static void main(String[] args) {
        CountUniqueCharacters cuc = new CountUniqueCharacters();
        Verifier.verifyEquals(cuc.uniqueLetterString("ABC"), 10);
        Verifier.verifyEquals(cuc.uniqueLetterString("ABA"), 8);
        Verifier.verifyEquals(cuc.uniqueLetterString("LEETCODE"), 92);
    }

}
