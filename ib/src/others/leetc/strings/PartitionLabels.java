package others.leetc.strings;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        int [] lastMap = new int[26];
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            lastMap[c - 'a'] = i;
        }
        List<Integer> answer = new ArrayList<>();
        int prev = 0;
        for (int i = 0; i < S.length();) {
            char c = S.charAt(i);
            int max = lastMap[c-'a'];
            int j = i+1;
            for (; j<max; j++) {
                char c1 = S.charAt(j);
                int lastIndex = lastMap[c1-'a'];
                if (lastIndex > max) {
                    max = lastIndex;
                }
            }
            answer.add(max-prev+1);
            i = max+1;
            prev = i;
        }
        return answer;
    }

    public static void main(String[] args) {
        PartitionLabels pl = new PartitionLabels();
        Verifier.verifyEquals(pl.partitionLabels("ababcbacadefegdehijhklij"), Creator.getList(9,7,8));
        Verifier.verifyEquals(pl.partitionLabels("caedbdedda"), Creator.getList(1,9));
        Verifier.verifyEquals(pl.partitionLabels("cat"), Creator.getList(1,1,1));
        Verifier.verifyEquals(pl.partitionLabels("ccat"), Creator.getList(2,1,1));
        Verifier.verifyEquals(pl.partitionLabels("d"), Creator.getList(1));
    }

}
