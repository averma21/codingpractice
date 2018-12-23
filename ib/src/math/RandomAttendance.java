package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomAttendance {

    private ArrayList<Integer> solve(int A, List<Integer> B) {
        int numDigits = (int)Math.log10(A) + 1;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int b : B) {
            int remainingCount = b ;
            for (int i = 1; i <= 9; i++) {
                int prefixDigits = digitsWithPrefix("" + i, numDigits, A);
                if (prefixDigits >= remainingCount) {
                    remainingCount--;
                    ans.add(Integer.parseInt(generatePrefix("" + i, remainingCount, numDigits, A)));
                    break;
                }
                remainingCount -= prefixDigits;
            }
        }
        return ans;
    }

    private int digitsWithPrefix(String prefix, int maxDigits, int maxNumber) {
        int digitsInPrefix = prefix.length();
        final int remainingDigits = maxDigits - digitsInPrefix;
        if (remainingDigits < 0) {
            return 0;
        }
        if (remainingDigits == 0) {
            return 1;
        }
        int count = 1;
        for (int i = digitsInPrefix + 1; i < maxDigits; i++ ) {
            count += Math.pow(10, i - digitsInPrefix);
        }
        int msb = maxNumber;
        while (msb >= 10) {
            msb/=10;
        }
        int prefixMsb = Integer.parseInt(prefix.substring(0, 1));
        String suffix = "";
        for (int i = 0; i < remainingDigits; i++)
            suffix = suffix.concat("0");
        int projectedNumber = Integer.MAX_VALUE;
        try {
            projectedNumber = Integer.parseInt(prefix + suffix);
        } catch (NumberFormatException e) {
            // ignore
        }
        if (("" + maxNumber).startsWith(prefix) && projectedNumber <= maxNumber) {
            count += (maxNumber - projectedNumber + 1);
        }
        else if (projectedNumber <= maxNumber) {
            count += Math.pow(10, maxDigits - digitsInPrefix);
        }
        return count;
    }

    private String generatePrefix(String prefix, int remainingCount, int maxDigits, int maxNumber) {
        if (remainingCount == 0)
            return prefix;
        for (int i = 0; i <= 9; i++) {
            String extendedPrefix = prefix + i;
            int digitsWithExtendedPrefix = digitsWithPrefix(extendedPrefix, maxDigits, maxNumber);
            if (digitsWithExtendedPrefix >= remainingCount) {
                remainingCount--;
                return generatePrefix(extendedPrefix, remainingCount, maxDigits, maxNumber);
            }
            remainingCount -= digitsWithExtendedPrefix;
        }
        throw new IllegalArgumentException("Not possible");
    }

    private static void verifyEquals(int a, int b) {
        if (a != b)
            throw new RuntimeException("Unequal" + a + " " + b);
    }

    private static void verifyEquals(List<Integer> A, List<Integer> B) {
        if (A.size() != B.size())
            throw new RuntimeException("Unequal" + A + " " + B);
        for (int i = 0; i < A.size(); i++) {
            verifyEquals(A.get(i), B.get(i));
        }
    }

    public static void main(String[] args) {
        RandomAttendance randomAttendance = new RandomAttendance();
//        verifyEquals(randomAttendance.digitsWithPrefix("1", 3, 105), 17);
//        verifyEquals(randomAttendance.digitsWithPrefix("1", 3, 205), 111);
//        verifyEquals(randomAttendance.digitsWithPrefix("101", 4, 1000), 1);
//        verifyEquals(randomAttendance.digitsWithPrefix("101", 5, 10000), 11);
//        verifyEquals(randomAttendance.digitsWithPrefix("80", 9, 804289385), 5400497);
//        System.out.println(randomAttendance.solve(804289385, Arrays.asList(new Integer [] {106058146})));
//        verifyEquals(randomAttendance.solve(3, Arrays.asList(new Integer[] {1, 2, 3})),
//                Arrays.asList(new Integer[]{1, 2, 3}));
//        verifyEquals(randomAttendance.solve(1000, Arrays.asList(new Integer[] {1, 2, 10, 112, 113})),
//                Arrays.asList(new Integer[] {1, 10, 106, 199, 2}));
//        verifyEquals(randomAttendance.solve(804289385, Arrays.asList(new Integer[] {
//                73114008, 106058146, 349169024, 424238336, 719885387, 41181723, 596516650, 385352037, 220912978,
//                546200643, 783368691, 298230675, 436318994, 358935157, 560891156, 736094042, 304089173, 499166352,
//                35005212, 521595369, 294702568, 118377660, 336465783, 56732146, 278722863, 233665124, 536595298,
//                468703136, 297224545, 193401033, 511344638, 635723059, 564843685, 321608783, 255672009, 480439687,
//                628175012, 47899273, 326886845, 44798604, 55195037, 305966150, 608413785, 756898538})),
//                Arrays.asList(new Integer[]{
//                165802602, 195452327, 414252116, 481814499, 747896846, 137063546, 636864982, 44681683, 298821677,
//                591580575, 81171370, 368407603, 492687091, 423041636, 604802035, 762484634, 373680251, 549249713,
//                131504686, 569435829, 365232306, 206539890, 40281920, 151058927, 350850572, 310298607, 582935765,
//                521832818, 367502086, 274060925, 560210169, 672150749, 608359312, 389447901, 330104802, 532395714,
//                665357507, 143109340, 394198157, 140318739, 14967553, 375369531, 647572402, 781208680
//        }));
//        System.out.println(randomAttendance.solve( 734575200, Arrays.asList(new Integer[] {
//        149798316, 569513971, 394991214, 184803527, 412776092, 689693781, 442609557, 14666674, 137806863, 42999171,
//                248331797, 135497282, 511702306, 615270526, 468326685, 358185928, 572660337, 424551306, 71175647,
//                163471330, 366086114 })));
//
//        System.out.println(randomAttendance.solve( 734575200, Arrays.asList(new Integer[] {
//                689693781 })));
            verifyEquals(randomAttendance.solve(1000000000,
                    Arrays.asList(new Integer [] { 389040744, 733053145, 433102830, 887658391, 402961683,
                            672655341, 900553542, 400000570, 337453827, 81174233, 780172262, 450956043, 941690361,
                            410409118, 847228024, 516266762, 866000082, 175526310, 586903191, 2495426, 500618997,
                            989806368, 184214678, 4504235, 61730691, 186631627, 16764525, 717226058, 748349615,
                            276673169, 411328206, 137390359, 9726313, 696947387, 877565101, 265204347, 369602727,
                            630634995, 665204917, 707056553, 564325579, 297893530, 10528947, 358532291, 708302648,
                            857756971, 874799052, 426819081, 885799632, 314218594, 281830858, 386418628, 156541313,
                            318561887, 243439215, 70788356, 505193513, 112720091, 788014413, 106059480, 241909611,
                            51858970, 95966190, 104152275, 748806356, 826047642, 369356621, 970925434, 309198988,
                            887077889, 530498339, 873524567} )), Arrays.asList(new Integer[] {
                    450136664, 759747827, 489792543, 898892549, 46266551, 705389802, 910498183, 460000506, 403708439,
                    173056804, 80215503, 505860433, 94752132, 469368201, 862505216, 564640080, 879400069, 257973675,
                    628212867, 102245878, 550557092, 990825727, 265793205, 104053805, 155557617, 267968460, 115088067,
                    745503447, 773514649, 349005847, 470195380, 223651317, 108753677, 727252643, 889808588, 338683908,
                    432642449, 667571491, 698684421, 736350893, 607893016, 368104171, 109476047, 422679057, 737472379,
                    87198127, 887319142, 484137168, 897219665, 382796730, 353647768, 447776761, 240887177, 386705694,
                    319095289, 163709515, 554674157, 201448076, 809212967, 195453527, 317718645, 146673068, 186369567,
                    193737042, 773925716, 843442873, 432420953, 973832887, 378279085, 898370096, 577448500, 886172105
            }));

    }

}
