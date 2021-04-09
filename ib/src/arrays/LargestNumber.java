package arrays;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LargestNumber {

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public String largestNumber(List<Integer> A) {
        List<Integer> B = new ArrayList<>(A);
        B.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                String s1 = t1.toString();
                String s2 = t2.toString();
                String s1First = s1 + s2;
                String s2First = s2 + s1;
                return s2First.compareTo(s1First);
            }
        });
        StringBuilder stringBuilder = new StringBuilder();
        boolean nonZeroFound = false;
        for (int b : B) {
            if (b != 0) {
                nonZeroFound = true;
            }
            if (nonZeroFound) {
                stringBuilder.append(b);
            }
        }
       // System.out.println(B);
        if (!nonZeroFound && A.size() > 0) {
            stringBuilder.append(0);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LargestNumber largestNumber = new LargestNumber();
        Verifier.verifyEquals(largestNumber.largestNumber(Creator.getList(3, 30, 34, 5, 9)), "9534330");
        Verifier.verifyEquals(largestNumber.largestNumber(Creator.getList(3, 341, 34112, 34152, 5, 9)), "9534152341341123");
        Verifier.verifyEquals(largestNumber.largestNumber(Creator.getList(3, 341, 341341, 5, 9)), "953413413413");
        Verifier.verifyEquals(largestNumber.largestNumber(Creator.getList(931, 94, 209, 448, 716, 903, 124, 372, 462, 196, 715, 802, 103, 740,
                389, 872, 615, 638, 771, 829, 899, 999, 29, 163, 342, 902, 922, 312, 326, 817, 288, 75, 37, 286, 708, 589, 975, 747, 743, 699, 743,
                954, 523, 989, 114, 402, 236, 855, 323, 79, 949, 176, 663, 587, 322)), "99998997595494994931922903902899872855829817802797717574774374374" +
                "07167157086996636386155895875234624484023893737234232632332231229288286236209196176163124114103");
        Verifier.verifyEquals(largestNumber.largestNumber(Creator.getList(3, 3, 3)), "333");
        Verifier.verifyEquals(largestNumber.largestNumber(Creator.getList(3, 3, 3, 9, 9, 9)), "999333");
        Verifier.verifyEquals(largestNumber.largestNumber(Creator.getList(1, 2, 3)), "321");
        Verifier.verifyEquals(largestNumber.largestNumber(Creator.getList(0, 0, 2, 3)), "3200");
        Verifier.verifyEquals(largestNumber.largestNumber(Creator.getList(0, 0, 0)), "0");
        Verifier.verifyEquals(largestNumber.largestNumber(Creator.getList(3)), "3");
        Verifier.verifyEquals(largestNumber.largestNumber(Creator.getList()), "");
    }

}
