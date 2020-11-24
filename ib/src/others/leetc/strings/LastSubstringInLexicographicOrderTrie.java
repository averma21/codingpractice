package others.leetc.strings;

import util.Verifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.TreeMap;

// TLE - see LastSubstringInLexicographicOrderLOL
public class LastSubstringInLexicographicOrderTrie {

    String input;

    class Range implements Comparable<Range> {
        int start;
        int end;

        Range(int s, int e) {
            this.start = s;
            this.end = e;
        }

        int length() {
            return end - start + 1;
        }

        char charAt(int pos) {
            return input.charAt(start + pos);
        }

        String getString() {
            return input.substring(start, end + 1);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Range range = (Range) o;
            return start == range.start &&
                    end == range.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }

        @Override
        public int compareTo(Range o) {
            return -1 * getString().compareTo(o.getString());
        }
    }

    class Node {

        boolean isData;
        TreeMap<Range, Node> children;

        Node() {
            this.children = new TreeMap<>();
            this.isData = false;
        }

        void addChild(String input, int pos) {
            if (input == null || pos >= input.length()) {
                return;
            }
            char c = input.charAt(pos);
            Range matchedPart = null;
            boolean wholeMatch = false;
            Range matchedRange = null;
            for (Range range : children.keySet()) {
                int i = 0, j = pos;
                while (i < range.length() && j < input.length()) {
                    if (range.charAt(i) == input.charAt(j)) {
                        i++;
                        j++;
                    } else {
                        break;
                    }
                }
                if (i > 0) {
                    matchedRange = range;
                    if (i == range.length()) {
                        wholeMatch = true;
                    }
                    matchedPart = new Range(range.start, range.start + i - 1);
                    break;
                }
            }
            if (wholeMatch) {
                children.get(matchedRange).addChild(input, pos + matchedPart.length());
            } else if (matchedRange != null){
                Node child = children.remove(matchedRange);
                Node newNode = new Node();
                addNode(matchedPart, newNode);
                newNode.addNode(new Range(matchedRange.start + matchedPart.length(), matchedRange.end), child);
                if (input.length() > pos + matchedPart.length()) {
                    newNode.addChild(input, pos + matchedPart.length());
                } else {
                    newNode.isData = true;
                }
            } else {
                addNode(new Range(pos, input.length() - 1), new Node());
            }
        }

        void addNode(Range range, Node node) {
            this.children.put(range, node);
        }

    }

    class SuffixTree {

        Node root;
        String input;

        SuffixTree(String input) {
            this.input = input;
            this.root = new Node();
            for (int i = input.length() - 1; i >= 0; i--) {
                root.addChild(input, i);
            }
        }

        String getAnswer() {
            StringBuilder sb = new StringBuilder();
            traverse(root, sb);
            return sb.toString();
        }

        void traverse(Node node, StringBuilder sb) {
            if (node != null && node.children.size() > 0) {
                sb.append(node.children.firstKey().getString());
                traverse(node.children.get(node.children.firstKey()), sb);
            }
        }

    }

    SuffixTree suffixTree;

    public String lastSubstring(String s) {
        this.input = s;
        this.suffixTree = new SuffixTree(s);
        return suffixTree.getAnswer();
    }

    public static void main(String[] args) throws IOException {
        LastSubstringInLexicographicOrderTrie lsi = new LastSubstringInLexicographicOrderTrie();
        Verifier.verifyEquals(lsi.lastSubstring("aab"), "b");
        Verifier.verifyEquals(lsi.lastSubstring("abab"), "bab");
        Verifier.verifyEquals(lsi.lastSubstring("leetcode"), "tcode");
        Verifier.verifyEquals(lsi.lastSubstring("tttt"), "tttt");
        Verifier.verifyEquals(lsi.lastSubstring("acttctstts"), "tts");
        Verifier.verifyEquals(lsi.lastSubstring("tactbdtbc"), "tbdtbc");
        Verifier.verifyEquals(lsi.lastSubstring("tdctbdtbc"), "tdctbdtbc");

//        String test = lsi.getTest1();
//        Verifier.verifyEquals(lsi.lastSubstring(test), "a");
    }

//    String getTest1()  throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader("resources/testFile"));
//        String currentLine = reader.readLine();
//        reader.close();
//        return currentLine;
//    }

}
