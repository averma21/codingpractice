package others.leetc.strings;

import util.Verifier;

public class KthLexicographicalString {

    public String getHappyString(int n, int k) {
        char forbiddenChar = '0';
        int remainingCount = k;
        StringBuilder ans = new StringBuilder();
        for (int position = 0; position < n; position++) {
            int possibilities = (int)(Math.pow(2, n-position-1));
            if (possibilities >= remainingCount) {
                for (char c = 'a'; c <= 'c'; c++) {
                    if (c != forbiddenChar) {
                        ans.append(c);
                        forbiddenChar = c;
                        break;
                    }
                }
            } else {
                int charsToSkip =  remainingCount/possibilities - (remainingCount % possibilities == 0 ? 1:0);
                if (charsToSkip >= 3) {
                    return "";
                }
                char c = 'a';
                int copy = charsToSkip;
                for (; c <= 'c' && (copy > 0 || c == forbiddenChar); c++) {
                    if (c == forbiddenChar) {
                        continue;
                    }
                    copy--;
                }
                forbiddenChar = c;
                ans.append(c);
                remainingCount = remainingCount - charsToSkip*possibilities;
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        KthLexicographicalString kls = new KthLexicographicalString();
        Verifier.verifyEquals(kls.getHappyString(1,3), "c");
        Verifier.verifyEquals(kls.getHappyString(1,4), "");
        Verifier.verifyEquals(kls.getHappyString(3,9), "cab");
        Verifier.verifyEquals(kls.getHappyString(2,7), "");
        Verifier.verifyEquals(kls.getHappyString(10,100), "abacbabacb");
        Verifier.verifyEquals(kls.getHappyString(10,98), "abacbababc");
    }

}
