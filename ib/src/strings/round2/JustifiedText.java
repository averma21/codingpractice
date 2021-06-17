package strings.round2;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.List;

public class JustifiedText {

    List<String> justify(List<String> A, int B) {
        ArrayList<String> ans = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();
        int startIndex = 0;
        if (A == null) {
            return ans;
        }
        while (startIndex < A.size()) {
            int curLength = 0;
            int endIndex = startIndex;
            for (int i = startIndex; i < A.size(); i++) {
                String word = A.get(i);
                curLength += word.length();
                int numWords = i - startIndex + 1;
                endIndex = i;
                if (curLength + numWords -1 > B) {
                    curLength -= word.length();
                    endIndex = i-1;
                    break;
                }
            }
            int numWords = endIndex - startIndex + 1;
            int spacesToInsert = B - curLength;
            int spacesPerWord = numWords > 1 ? spacesToInsert / (numWords-1) : spacesToInsert;
            int leftOverSpaces = numWords > 1 ? spacesToInsert % (numWords-1) : 0;
            StringBuilder spacesB = new StringBuilder();
            for (int s = 0; s < spacesPerWord; s++) {
                spacesB.append(" ");
            }
            String spaces = spacesB.toString();
            boolean isLastLine = endIndex == A.size() - 1;
            for (int i = startIndex; i <= endIndex; i++) {
                currentLine.append(A.get(i));
                if (!isLastLine) {
                    if (spacesToInsert == 0) {
                        break;
                    }
                    currentLine.append(spaces);
                    spacesToInsert -= spacesPerWord;
                    if (leftOverSpaces > 0) {
                        currentLine.append(" ");
                        spacesToInsert--;
                        leftOverSpaces--;
                    }
                } else {
                    if (i <= endIndex - 1) {
                        currentLine.append(" ");
                        spacesToInsert--;
                    } else {
                        StringBuilder spacesBi = new StringBuilder();
                        for (int s = 0; s < spacesToInsert; s++) {
                            spacesBi.append(" ");
                        }
                        currentLine.append(spacesBi);
                    }
                }
            }
            ans.add(currentLine.toString());
            currentLine.setLength(0);
            startIndex = endIndex + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        JustifiedText jt = new JustifiedText();
        Verifier.verifyEquals(jt.justify(Creator.getList("This", "is", "an", "example", "of", "text",
                "justification."), 16), Creator.getList("This    is    an",
                "example  of text",
                "justification.  "));
        Verifier.verifyEquals(jt.justify(Creator.getList("This_this_this", "is", "an", "example", "of", "text",
                "justification."), 16), Creator.getList(
                        "This_this_this  ",
                                  "is an example of",
                                  "text            ",
                                  "justification.  "));
        Verifier.verifyEquals(jt.justify(Creator.getList("This", "this"), 4), Creator.getList(
                "This",
                "this"));
        Verifier.verifyEquals(jt.justify(Creator.getList( "What", "must", "be", "shall", "be."), 12), Creator.getList(
                "What must be",
                          "shall be.   "));
    }

}
