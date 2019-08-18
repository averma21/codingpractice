package trees;

import util.Verifier;

public class Trie {

    TrieNode root;

    public Trie() {
        this.root = new TrieNode(null);
    }

    public Trie(String s) {
        this();
        this.root.addNode(s.charAt(0), s);
    }

    public void insert(String s) {
        TrieNode node = this.root;
        int i = 0;
        while (node.getNode(s.charAt(i)) != null) {
            node = node.getNode(s.charAt(i));
            i++;
        }
        String presentElementVal = node.getElementVal();
        if (presentElementVal != null) {
            node.setElementVal(null);
            for (; i < s.length() && i < presentElementVal.length() && s.charAt(i) == presentElementVal.charAt(i); i++) {
                node = node.addNode(s.charAt(i), null);
            }
            if (i == s.length() && i < presentElementVal.length()) {
                node.setElementVal(s);
                node.addNode(presentElementVal.charAt(i), presentElementVal);
            } else if (i == presentElementVal.length() && i < s.length()) {
                node.setElementVal(presentElementVal);
                node.addNode(s.charAt(i), s);
            } else {
                node.addNode(s.charAt(i), s);
                node.addNode(presentElementVal.charAt(i), presentElementVal);
            }
        } else {
            node.addNode(s.charAt(i), s);
        }
    }

    public String findPath(String s) {
        String path = "";
        TrieNode node = this.root;
        int i = 0;
        while (i != s.length() && node.getNode(s.charAt(i)) != null) {
            path += s.charAt(i);
            node = node.getNode(s.charAt(i));
            i++;
        }
        if (s.equals(node.getElementVal()))
            return path;
        return null;
    }

    public static void main(String[] args) {
        Trie trie = new Trie("dear");
        trie.insert("to");
        trie.insert("tom");
        trie.insert("tommy");
        trie.insert("tea");
        trie.insert("ted");
        trie.insert("ear");
        trie.insert("dent");
        trie.insert("dog");
        Verifier.verifyEquals(trie.findPath("x"), null);
        Verifier.verifyEquals(trie.findPath("to"), "to");
        Verifier.verifyEquals(trie.findPath("tox"), null);
        Verifier.verifyEquals(trie.findPath("tom"), "tom");
        Verifier.verifyEquals(trie.findPath("tommy"), "tomm");
        Verifier.verifyEquals(trie.findPath("ear"), "e");
        Verifier.verifyEquals(trie.findPath("dear"), "dea");
        Verifier.verifyEquals(trie.findPath("dear"), "dea");
        Verifier.verifyEquals(trie.findPath("den"), null);
        Verifier.verifyEquals(trie.findPath("dent"), "den");
        Verifier.verifyEquals(trie.findPath("dog"), "do");
    }
}
