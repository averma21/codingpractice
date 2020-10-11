package others.leetc.thirtydaychallenge;

import util.Verifier;

public class BackspaceStringCompare {

    private static class Info {
        private int index;
        private int bCount;

        public Info(int index, int bCount) {
            this.index = index;
            this.bCount = bCount;
        }
    }

    private static Character getChar(String S, Info info) {
        if (info.index < 0 || info.index >= S.length())
            return null;
        while (info.index >= 0) {
            char s = S.charAt(info.index);
            if (s == '#') {
                info.index--;
                info.bCount++;
            } else if(info.bCount > 0) {
                info.index--;
                info.bCount--;
            } else {
                info.index--;
                return s;
            }
        }
        return null;
    }

    public static boolean backspaceCompare(String S, String T) {
        Info sInfo = new Info(S.length() - 1, 0);
        Info tInfo = new Info(T.length() - 1, 0);
        do {
            Character s = getChar(S, sInfo);
            Character t = getChar(T, tInfo);
            if (s == null && t == null)
                return true;
            if (s == null || !s.equals(t))
                return false;
        } while (true);
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(backspaceCompare("ab#c", "ad#c"), true);
        Verifier.verifyEquals(backspaceCompare("ab##", "c#d#"), true);
        Verifier.verifyEquals(backspaceCompare("a##c", "#a#c"), true);
        Verifier.verifyEquals(backspaceCompare("a#c", "b"), false);
        Verifier.verifyEquals(backspaceCompare("abcd#e##f", "abde##f"), true);
        Verifier.verifyEquals(backspaceCompare("abcd#e##f", "acde##f"), false);
    }

}
