package trees;

import util.Creator;

import java.util.ArrayList;
import java.util.List;

public class ShortestUniquePrefix {

    List<String> prefix(List<String> A) {
        List<String> res = new ArrayList<>();
        if (A == null || A.isEmpty())
            return res;
        Trie trie = new Trie();
        for (String s : A)
            trie.insert(s);
        for (String s : A)
            res.add(trie.findPath(s));
        return res;
    }

    public static void main(String[] args) {
        ShortestUniquePrefix sup = new ShortestUniquePrefix();
        System.out.println(sup.prefix(Creator.getList("zebra", "dog", "duck", "dove")));
    }

}
