package others.leetc.contests.biweekly.thirtyseven;

import util.Creator;
import util.Verifier;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Fancy {

    private static final int MOD = 1000000007;
    private final List<Integer> seq;
    private final List<BigInteger> add;
    private final List<BigInteger> mult;

    public Fancy() {
        add = new ArrayList<>();
        seq = new ArrayList<>();
        mult = new ArrayList<>();
        add.add(BigInteger.ZERO);
        mult.add(BigInteger.ONE);
    }

    public void append(int val) {
        seq.add(val);
        add.add(add.get(add.size() - 1));
        mult.add(mult.get(mult.size() - 1));
    }

    public void addAll(int inc) {
        int lastIndex = add.size() - 1;
        add.set(lastIndex, add.get(lastIndex).add(new BigInteger("" + inc)));
    }

    public void multAll(int m) {
        int lastIndex = add.size() - 1;
        BigInteger bigM = new BigInteger(""+m);
        add.set(lastIndex, add.get(lastIndex).multiply(bigM));
        mult.set(lastIndex, mult.get(lastIndex).multiply(bigM));
    }

    public int getIndex(int idx) {
        int count = mult.size();
        if (idx >= count - 1) { return -1; }
        BigInteger m = mult.get(count - 1).divide(mult.get(idx));
        BigInteger inc = add.get(count - 1).subtract(m.multiply(add.get(idx)));
        return m.multiply(new BigInteger("" + seq.get(idx))).add(inc).mod(new BigInteger("" + MOD)).intValue();
    }

    private static List<Integer> prepareTest(String [] command, int [] num) {
        Fancy fancy = new Fancy();
        List<Integer> ans = new ArrayList<>();
        ans.add(null);
        for (int i = 1; i < command.length; i++) {
            String com = command[i];
            int ni = num[i];
            switch (com) {
                case "append":
                    fancy.append(ni);
                    ans.add(null);
                    break;
                case "addAll":
                    fancy.addAll(ni);
                    ans.add(null);
                    break;
                case "multAll":
                    fancy.multAll(ni);
                    ans.add(null);
                    break;
                default:
                    ans.add(fancy.getIndex(ni));
                    break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(prepareTest(new String[]{"Fancy","append","addAll","append","multAll","getIndex","addAll","append","multAll","getIndex","getIndex","getIndex"},
                new int[] {0,2,3,7,2,0,3,10,2,0,1,2}), Creator.getList(null,null,null,null,null,10,null,null,null,26,34,20));
        Verifier.verifyEquals(prepareTest(new String[] {"Fancy","append","getIndex","getIndex","getIndex","addAll",
                        "getIndex","getIndex","multAll","append","append","append","addAll","append","addAll","getIndex"},
                new int[] {0,6,0,4,0,2,0,0,10,5,6,7,8,3,4,1}), Creator.getList(null,null,6,-1,6,null,8,8,null,null,null,null,null,null,null,17));
        Verifier.verifyEquals(prepareTest(new String[] {"Fancy","append","append","getIndex","append","getIndex","addAll","append","getIndex","getIndex","append","append","getIndex","append","getIndex","append","getIndex", "append","getIndex","multAll","addAll","getIndex","append","addAll","getIndex","multAll","getIndex","multAll","addAll","addAll","append","multAll","append","append",
                        "append","multAll","getIndex","multAll","multAll","multAll","getIndex","addAll","append","multAll","addAll","addAll","multAll","addAll",
                        "addAll","append","append","getIndex"},
                new int[] {0,12,8,1,12,0,12,8,2,2,4,13,4,12,6,11,1,10,2,3,1,6,14,5,6,12,3,12,15,6,7,8,13,15,15,10,9,12,12,9,9,9,9,4,8,11,15,9,1,4,10,9}),
                Creator.getList(null,null,null,8,null,12,null,null,24,24,null,null,4,null,12,null,20,null,24,null,null,37,null,null,42,null,360,null,null,
                        null,null,null,null,null,null,null,220560,null,null,null,285845760,null,null,null,null,null,null,null,null,null,null,150746316));
    }

}
