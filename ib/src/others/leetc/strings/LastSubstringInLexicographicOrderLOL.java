package others.leetc.strings;

import util.Verifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//TLE
public class LastSubstringInLexicographicOrderLOL {


    public String lastSubstring(String s) {
        int len = s.length();
        if (len==0) return "";

        //initial indexes
        int low=0;
        int fast=1;
        int k=0;

        //find substr
        while (fast+k<len) {

            if (s.charAt(low+k) == s.charAt(fast+k)) {
                k++;
                continue;
            }

            if (s.charAt(low+k) < s.charAt(fast+k)) low = fast;
            fast++;
            k=0;
        }

        return s.substring(low);
    }

    public static void main(String[] args)  throws IOException  {
        LastSubstringInLexicographicOrderLOL lsi = new LastSubstringInLexicographicOrderLOL();
        Verifier.verifyEquals(lsi.lastSubstring("aab"), "b");
        Verifier.verifyEquals(lsi.lastSubstring("abab"), "bab");
        Verifier.verifyEquals(lsi.lastSubstring("leetcode"), "tcode");
        Verifier.verifyEquals(lsi.lastSubstring("tttt"), "tttt");
        Verifier.verifyEquals(lsi.lastSubstring("acttctstts"), "tts");
        Verifier.verifyEquals(lsi.lastSubstring("tactbdtbc"), "tbdtbc");
        Verifier.verifyEquals(lsi.lastSubstring("tdctbdtbc"), "tdctbdtbc");
        String test = lsi.getTest1();
        System.out.print(lsi.lastSubstring(test));
    }

    String getTest1()  throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("resources/testFile"));
        String currentLine = reader.readLine();
        reader.close();
        return currentLine;
    }
}
