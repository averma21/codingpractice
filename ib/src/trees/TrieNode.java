package trees;

import sun.text.normalizer.Trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    private Map<Character, TrieNode> branches;
    private String elementVal;

    public TrieNode(String elementVal) {
        this.elementVal = elementVal;
        branches = new HashMap<>();
    }

    public String getElementVal() {
        return elementVal;
    }

    public void setElementVal(String s) {
        elementVal = s;
    }

    public TrieNode getNode(char c) {
        return branches.get(c);
    }

    public TrieNode addNode(char c, String s) {
        TrieNode n = new TrieNode(s);
        branches.put(c, n);
        return n;
    }
}
