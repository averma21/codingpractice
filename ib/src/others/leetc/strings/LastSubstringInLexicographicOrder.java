package others.leetc.strings;

import util.Verifier;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//TLE - see LastSubstringInLexicographicOrderLOL
public class LastSubstringInLexicographicOrder {

    private static class Helper {
        int startPos;
        int endPos;

        Helper(int pos) {
            this.startPos = pos;
            this.endPos = pos;
        }
    }

    List<Helper> biggestCharPositions;
    char biggestChar;

    private void findBiggestCharPositions(String s) {
        biggestCharPositions = new ArrayList<>();
        biggestChar = 'A';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > biggestChar) {
                biggestChar = c;
                biggestCharPositions.clear();
                biggestCharPositions.add(new Helper(i));
            } else if (c == biggestChar) {
                biggestCharPositions.add(new Helper(i));
            }
        }
        //merge any adjacent char positions
        int prevPos = -1;
        Helper startPos = null;
        List<Helper> newCharPos = new LinkedList<>();
        for (Helper hpos : biggestCharPositions) {
            if (prevPos == -1) {
                prevPos = hpos.startPos;
                startPos = hpos;
                continue;
            }
            if (hpos.startPos == prevPos + 1) {
                prevPos = hpos.startPos;
                continue;
            }
            newCharPos.add(startPos);
            startPos = hpos;
            prevPos = hpos.startPos;

        }
        newCharPos.add(startPos);
        biggestCharPositions = newCharPos;
        for (int i = 0; i < biggestCharPositions.size() - 1; i++) {
            biggestCharPositions.get(i).endPos = biggestCharPositions.get(i+1).startPos - 1;
        }
        biggestCharPositions.get(biggestCharPositions.size() - 1).endPos = s.length() - 1;
    }



    public String lastSubstring(String s) {
        findBiggestCharPositions(s);
        if (biggestCharPositions.size() == 1) {
            return s.substring(biggestCharPositions.get(0).startPos);
        }
        List<Helper> workingSet = new ArrayList<>(biggestCharPositions);
        int offset = 0;
        while (workingSet.size() > 0) {
            Helper biggest = workingSet.get(0);
            List<Helper> newWorkingSet = new ArrayList<>();
            newWorkingSet.add(biggest);
            char bigC = s.charAt(biggest.startPos + offset);
            for (int i = 1; i < workingSet.size(); i++) {
                Helper wh = workingSet.get(i);
                if (wh.startPos + offset > wh.endPos) {
                    continue;
                }
                char whc = s.charAt(wh.startPos + offset);
                if (whc > bigC) {
                    bigC = whc;
                    newWorkingSet.clear();
                    newWorkingSet.add(wh);
                } else if (whc == bigC) {
                    newWorkingSet.add(wh);
                }
            }
            workingSet = newWorkingSet;
            if (newWorkingSet.size() == 1) {
                return s.substring(newWorkingSet.get(0).startPos);
            }
            offset++;
        }
        return "";
    }

    public static void main(String[] args) {
        LastSubstringInLexicographicOrder lsi = new LastSubstringInLexicographicOrder();
        Verifier.verifyEquals(lsi.lastSubstring("aab"), "b");
        Verifier.verifyEquals(lsi.lastSubstring("abab"), "bab");
        Verifier.verifyEquals(lsi.lastSubstring("leetcode"), "tcode");
        Verifier.verifyEquals(lsi.lastSubstring("tttt"), "tttt");
        Verifier.verifyEquals(lsi.lastSubstring("acttctstts"), "tts");
        Verifier.verifyEquals(lsi.lastSubstring("tactbdtbc"), "tbdtbc");
        Verifier.verifyEquals(lsi.lastSubstring("tdctbdtbc"), "tdctbdtbc");
    }

}
