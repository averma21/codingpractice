package strings;

import java.util.ArrayList;
import java.util.Arrays;

public class PrettyJSON {

    private static String getNewLine(int indent) {
        StringBuilder s = new StringBuilder("");
        for (int ic = 0; ic < indent; ic++) {
            s.append("\t");
        }
        return s.toString();
    }

    private static boolean isNewLine(char current, char prev) {
        if (current == '{' || current == '}' || current == '[' || current == ']' ||
                prev == ',' || ((prev == '{' || prev == '}' || prev == '[' || prev == ']') && current != ','))
            return true;
        return false;
    }

    public static ArrayList<String> prettyJSON(String A) {
        ArrayList<String> list = new ArrayList<>();
        int indentCountForCurrentLine = 0;
        String s = "";
        boolean insideQuote = false;
        for (int i = 0; i < A.length(); i++) {
            char currentChar = A.charAt(i);
            char prevChar = i > 0 ? A.charAt(i - 1) : '.';
            if (prevChar == '"') {
                insideQuote = !insideQuote;
            }
            boolean marksNewLine = isNewLine(currentChar, prevChar);
            if (!insideQuote && marksNewLine) {
                if (!s.trim().isEmpty()) {
                    list.add(s);
                }
                if (currentChar == '}' || currentChar == ']') {
                    indentCountForCurrentLine--;
                }
                s = getNewLine(indentCountForCurrentLine);
                if (currentChar == '{' || currentChar == '[') {
                    indentCountForCurrentLine++;
                }
            }
            if (currentChar == ' ' && !insideQuote)
                continue;
            s += currentChar;
        }
        list.add(s);
        return list;
    }

    public static void main(String[] args) {
        ArrayList<String> ans = prettyJSON("{A:\"B{B\", C:{D:\"E\",F:{G:\"H\",I:\"J\"}}}");
        for (String s : ans) {
            System.out.println(s);
        }
        ArrayList<String> ans2 = prettyJSON("[\"foo\", {\"bar\":[\"baz\",null,1.0,2]}]");
        for (String s : ans2) {
            System.out.println(s);
        }
    }

}
