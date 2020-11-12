package others.leetc.trie;

import util.Verifier;

public class WordDictionary {

    private static class TrieNode {
        TrieNode [] children = new TrieNode[26];
        boolean isWord;
    }

    TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            TrieNode node = cur.children[c - 'a'];
            if (node == null) {
                node = new TrieNode();
                cur.children[c - 'a'] = node;
            }
            cur = node;
        }
        cur.isWord=true;
    }

    private static boolean traverse(TrieNode node, char [] chars, int position) {
        if (node == null) {
            return false;
        }
        if (position == chars.length) {
            return node.isWord;
        }
        char c = chars[position];
        if (c == '.') {
            for (TrieNode child : node.children) {
                if (child != null) {
                    boolean found = traverse(child, chars, position + 1);
                    if (found) {
                        return true;
                    }
                }
            }
            return false;
        }
        return traverse(node.children[chars[position] - 'a'], chars, position+1);
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return traverse(root, word.toCharArray(), 0);
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("cat");
        wordDictionary.addWord("mat");
        wordDictionary.addWord("cats");
        Verifier.verifyEquals(wordDictionary.search("cat"), true);
        Verifier.verifyEquals(wordDictionary.search("cats"), true);
        Verifier.verifyEquals(wordDictionary.search("ca."), true);
        Verifier.verifyEquals(wordDictionary.search("ca.."), true);
        Verifier.verifyEquals(wordDictionary.search("ca.y"), false);
        wordDictionary.addWord("hi");
        wordDictionary.addWord("hit");
        wordDictionary.addWord("dad");
        Verifier.verifyEquals(wordDictionary.search("da.d"), false);
        wordDictionary.addWord("dad");
        Verifier.verifyEquals(wordDictionary.search("dad."), false);
        Verifier.verifyEquals(wordDictionary.search("dad"), true);
    }
}
