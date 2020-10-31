package others.leetc.contests.biweekly.thirtyseven;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.List;

class FancyTLE {

    private enum OPCODE {
        ADD,
        MUL
    }

    private static class Operation {
        OPCODE opcode;
        int index;
        int magnitude;

        public Operation(OPCODE opcode, int index, int magnitude) {
            this.opcode = opcode;
            this.index = index;
            this.magnitude = magnitude;
        }
    }

    List<Integer> numbers;
    List<Operation> operations;

    public FancyTLE() {
        numbers = new ArrayList<>();
        operations = new ArrayList<>();
    }

    public void append(int val) {
        numbers.add(val);
    }

    public void addAll(int inc) {
        operations.add(new Operation(OPCODE.ADD, numbers.size(), inc));
    }

    public void multAll(int m) {
        operations.add(new Operation(OPCODE.MUL, numbers.size(), m));
    }

    public int getIndex(int idx) {
        if (idx < 0 || idx >= numbers.size()) {
            return -1;
        }
        int start = 0;
        int end = operations.size() - 1;
        int foundIndex = -1;
        while (start <= end) {
            int mid = (start+end)/2;
            Operation op = operations.get(mid);
            if (op.index <= idx) {
                start = mid+1;
            } else {
                if (mid == 0 || operations.get(mid - 1).index <= idx) {
                    foundIndex = mid;
                    break;
                }
                end = mid-1;
            }
        }
        long ans = numbers.get(idx);
        if (foundIndex != -1) {
            for (int i = foundIndex; i < operations.size(); i++) {
                Operation op = operations.get(i);
                if (op.opcode == OPCODE.ADD) {
                    ans += op.magnitude;
                } else {
                    ans *= op.magnitude;
                }
                ans = ans%(int)(1.0E9 + 7);
            }
        }
        return (int)ans;
    }

    private static List<Integer> prepareTest(String [] command, int [] num) {
        FancyTLE fancyTLE = new FancyTLE();
        List<Integer> ans = new ArrayList<>();
        ans.add(null);
        for (int i = 1; i < command.length; i++) {
            String com = command[i];
            int ni = num[i];
            switch (com) {
                case "append":
                    fancyTLE.append(ni);
                    ans.add(null);
                    break;
                case "addAll":
                    fancyTLE.addAll(ni);
                    ans.add(null);
                    break;
                case "multAll":
                    fancyTLE.multAll(ni);
                    ans.add(null);
                    break;
                default:
                    ans.add(fancyTLE.getIndex(ni));
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
    }
}
