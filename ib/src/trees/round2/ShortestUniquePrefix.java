package trees.round2;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestUniquePrefix {

    private static class Node {
        String val;
        Map<Character, Node> children;

        public Node() {
            this("");
        }

        public Node(String val) {
            this.val = val;
            children = new HashMap<>();
        }

        boolean isEmpty() {
            return "".equals(val);
        }

        public Node getChild(char c) {
            return children.get(c);
        }

        public void addChild(char c, Node node) {
            children.put(c, node);
        }
    }

    private static class Trie {

        Node root = new Node();

        Map<String, String> codes;

        private void addInternal(Node node, String s, int index) {
            Node existingChild = index < s.length() ? node.getChild(s.charAt(index)) : null;
            if (existingChild != null) {
                addInternal(existingChild, s, index+1);
            } else {
                Node commonPathNode = node;
                while (index < node.val.length() && index < s.length()
                        && node.val.charAt(index) == s.charAt(index)) {
                    Node child = new Node();
                    commonPathNode.addChild(s.charAt(index), child);
                    commonPathNode = child;
                    index++;
                }
                Node child = new Node(s);
                commonPathNode.addChild(s.charAt(index), child);
                if (!node.isEmpty()) {
                    commonPathNode.addChild(node.val.charAt(index), new Node(node.val));
                    node.val = "";
                }
            }
        }

        private void add(String s) {
            addInternal(root, s, 0);
        }

        private void traverse(Node node, String path) {
            if (!node.isEmpty()) {
                codes.put(node.val, path);
            } else {
                for (Character c : node.children.keySet()) {
                    traverse(node.getChild(c), path + c);
                }
            }
        }

        private void createCodes() {
            codes = new HashMap<>();
            traverse(root, "");
        }

    }

    public List<String> prefix(List<String> A) {
        Trie trie = new Trie();
        for (String a : A) {
            trie.add(a);
        }
        trie.createCodes();
        List<String> ans = new ArrayList<>();
        for (String a : A) {
            ans.add(trie.codes.get(a));
        }
        return ans;
    }

    public static void main(String[] args) {
        ShortestUniquePrefix sup = new ShortestUniquePrefix();
        Verifier.verifyEquals(sup.prefix(Creator.getList("zebra", "dog", "duck", "dove")),
                Creator.getList("z", "dog", "du", "dov"));
        Verifier.verifyEquals(sup.prefix(Creator.getList( "abcdefgv", "abcdefgrr", "abcdefglj",
                "abcdefgtnsnfwzqfj", "abcdefafadr", "abcdefgwsofsbcnuv", "abcdefghffbsaq", "abcdefgwp",
                "abcdefgcb", "abcdefgcehch")),
                Creator.getList("abcdefgv", "abcdefgr", "abcdefgl", "abcdefgt", "abcdefa", "abcdefgws", "abcdefgh",
                        "abcdefgwp", "abcdefgcb", "abcdefgce" ));
    }

}
