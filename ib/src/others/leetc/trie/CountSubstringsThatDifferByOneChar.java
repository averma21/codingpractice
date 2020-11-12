package others.leetc.trie;

import util.Verifier;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/count-substrings-that-differ-by-one-character/
public class CountSubstringsThatDifferByOneChar {

    private static class Node {
        char branchName;
        Node child;

        public void addChild(Node node, char branchName) {
            this.child = node;
            this.branchName = branchName;
        }

        public static Node constructBranch(char [] chars, int pos) {
            if (pos == chars.length) {
                return new Node();
            }
            Node node = new Node();
            node.addChild(constructBranch(chars, pos+1), chars[pos]);
            return node;
        }
    }

    private static class SuffixTrie {
        final List<Node> nodes;
        final List<Character> branchNames;

        public SuffixTrie(String s) {
            nodes = new ArrayList<>();
            branchNames = new ArrayList<>();
            Node prevBranch = Node.constructBranch(s.toCharArray(), 1);
            nodes.add(prevBranch);
            branchNames.add(s.charAt(0));
            for (int i = 1; i < s.length(); i++) {
                nodes.add(prevBranch.child);
                branchNames.add(s.charAt(i));
                prevBranch = prevBranch.child;
            }
        }
    }

    SuffixTrie trie1, trie2;
    int ans;
    int diffTillNow;

    private void iterate(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        if (diffTillNow == 1) {
            ans++;
        }
        if (node1.branchName != node2.branchName) {
            diffTillNow++;
        }
        if (diffTillNow >= 2) {
            return;
        }
        iterate(node1.child, node2.child);
    }

    public int countSubstrings(String s, String t) {
        trie1 = new SuffixTrie(s);
        trie2 = new SuffixTrie(t);
        ans = 0;
        int i = 0;
        for (Node node1 : trie1.nodes) {
            int j = 0;
            for (Node node2 : trie2.nodes) {
                if (trie1.branchNames.get(i) == trie2.branchNames.get(j)) {
                    diffTillNow = 0;
                } else {
                    diffTillNow = 1;
                }
                iterate(node1, node2);
                j++;
            }
            i++;
        }
        return ans;
    }

    public static void main(String[] args) {
        CountSubstringsThatDifferByOneChar cs = new CountSubstringsThatDifferByOneChar();
        Verifier.verifyEquals(cs.countSubstrings("aba", "baba"), 6);
        Verifier.verifyEquals(cs.countSubstrings("ab", "bb"), 3);
        Verifier.verifyEquals(cs.countSubstrings("a", "a"), 0);
        Verifier.verifyEquals(cs.countSubstrings("abe", "bbc"), 10);
        Verifier.verifyEquals(cs.countSubstrings("computer", "computation"), 90);
    }

}
