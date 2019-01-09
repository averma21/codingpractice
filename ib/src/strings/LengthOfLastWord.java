package strings;

import util.Verifier;

public class LengthOfLastWord {

    public int lengthOfLastWord(final String A) {
        if (A == null || A.length() == 0) {
            return 0;
        }
        int n = A.length();
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = A.charAt(i);
            if (c == ' ') {
                if (count > 0) {
                    break;
                }
            } else {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LengthOfLastWord lastWord = new LengthOfLastWord();
        Verifier.verifyEquals(lastWord.lengthOfLastWord(null), 0);
        Verifier.verifyEquals(lastWord.lengthOfLastWord(""), 0);
        Verifier.verifyEquals(lastWord.lengthOfLastWord("  "), 0);
        Verifier.verifyEquals(lastWord.lengthOfLastWord("a"), 1);
        Verifier.verifyEquals(lastWord.lengthOfLastWord("a bc"), 2);
        Verifier.verifyEquals(lastWord.lengthOfLastWord("a bc "), 2);
        Verifier.verifyEquals(lastWord.lengthOfLastWord("a bc d"), 1);
    }

}
