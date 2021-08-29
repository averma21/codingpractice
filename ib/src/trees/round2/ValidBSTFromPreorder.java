package trees.round2;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ValidBSTFromPreorder {

    List<Integer> pre;
    List<Integer> in;
    int indexPre;

    private boolean check(int inStart, int inEnd) {
        if (inEnd < inStart) {
            return true;
        }
        int root = pre.get(indexPre++);
        //System.out.println("Preindex = " + (indexPre-1) +" Checking for root = " + root + " between " + inStart + " and " + inEnd);
        int savedPreIndex = indexPre;
        for (int i = inStart; i <= inEnd; i++) {
            int ini = in.get(i);
            if (ini > root) {
                break;
            }
            if (ini == root) {
                boolean res = check(inStart, i-1) && check(i+1, inEnd);
                if (res) {
                    return res;
                } else {
                    indexPre = savedPreIndex;
                }
            }
        }
        return false;
    }

    public int solve(List<Integer> A) {
        this.pre = A;
        indexPre = 0;
        in = new ArrayList<>(A);
        Collections.sort(in);
//        System.out.println("Preorder " + pre);
//        System.out.println("Inoder " + in);
        return check(0, A.size()-1) ? 1 : 0;
    }

    public static void main(String[] args) {
        ValidBSTFromPreorder vbt = new ValidBSTFromPreorder();
        Verifier.verifyEquals(vbt.solve(Creator.getList(7, 7, 10, 10, 9, 5, 2, 8)), 0);
        Verifier.verifyEquals(vbt.solve(Creator.getList(7, 2, 5, 7, 10, 9, 10)), 1);
        Verifier.verifyEquals(vbt.solve(Creator.getList(7, 7, 5, 2, 10, 10, 9)), 1);
    }

}
