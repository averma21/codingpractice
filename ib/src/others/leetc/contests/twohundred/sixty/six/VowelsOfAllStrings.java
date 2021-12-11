package others.leetc.contests.twohundred.sixty.six;

import util.Verifier;

/**
 *0123456789 10 11 12
 * noosabasbo o  s  a
 *
 * n -> 0
 * no -> 1
 * noo -> 2
 * noos -> 2
 * noosa -> 3
 * noosab -> 3
 * noosaba -> 4
 * noosabas -> 4
 * noosabasb -> 4
 * noosabasbo -> 5
 * noosabasboo -> 6
 * noosabasboos -> 6
 * noosabasboosa -> 7
 *
 *
 * 0 -> 47
 * 1- > 47 (0th position was not vowel, so all substrings starting at position 1 would have same count of vowels as 0th position)
 * 2 -> (47 - (12 - 2 + 1) - 1) = 35
 * 3 -> 35 - (12 - 3 + 1) - 1 = 24
 * 4 -> 24
 * 5- > 24 - (12 - 5 + 1) -1 = 15
 * 6 -> 15
 * 7 -> 15 - (12 - 7 +1 ) -1 = 8
 * 8 -> 8
 * 9 -> 8
 * 10 -> 8 - (12 - 10 + 1) - 1= 4
 * 11 -> 4 - (12 - 11 +1 ) -1 = 1
 * 12 -> 1
 *
 * 47 + 47+ 35 + 24 + 24 + 15 + 15 + 8 + 8 + 8 + 4 + 1 + 1
 */
public class VowelsOfAllStrings {

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' ||  c == 'o' || c == 'u';
    }

    public long countVowels(String word) {
        long sumOfVowelsInSubstringBeginningAt0 = 0;
        int n = word.length();
        int prevVowelCount = 0;
        for (int i = 0; i < n; i++) {
            int vowelsInSubsEndingAtI = prevVowelCount + (isVowel(word.charAt(i)) ? 1 : 0);
            sumOfVowelsInSubstringBeginningAt0 += vowelsInSubsEndingAtI;
            prevVowelCount = vowelsInSubsEndingAtI;
        }
        long sumOfVowelsInSubstringBeginningAtPrevPos = sumOfVowelsInSubstringBeginningAt0;
        long ans = sumOfVowelsInSubstringBeginningAtPrevPos;
        for (int i = 1; i < n; i++) {
            boolean wasPreviousCharVowel = isVowel(word.charAt(i-1));
            long sumOfVowelsInSubstringBeginningAtCurPos = wasPreviousCharVowel ? sumOfVowelsInSubstringBeginningAtPrevPos - (n-1 - i + 1) - 1 : sumOfVowelsInSubstringBeginningAtPrevPos;
            ans += sumOfVowelsInSubstringBeginningAtCurPos;
            sumOfVowelsInSubstringBeginningAtPrevPos = sumOfVowelsInSubstringBeginningAtCurPos;
        }
        return ans;
    }

    public static void main(String[] args) {
        VowelsOfAllStrings voas = new VowelsOfAllStrings();
        Verifier.verifyEquals(voas.countVowels("aba"), 6);
        Verifier.verifyEquals(voas.countVowels("abc"), 3);
        Verifier.verifyEquals(voas.countVowels("ltcd"), 0);
        Verifier.verifyEquals(voas.countVowels("noosabasboosa"), 237);
    }

}
