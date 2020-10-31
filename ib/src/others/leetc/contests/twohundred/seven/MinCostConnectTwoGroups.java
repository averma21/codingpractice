package others.leetc.contests.twohundred.seven;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Move row wise. Choose all possible combinations of the column entries. Mark the selected entries into bitmap.
 * When all bitMap entries are 1 (meaning every column is selected), choose minimum value from all the remaining rows.
 *
 * Gives correct solution (checked locally) but TLE.
 */
public class MinCostConnectTwoGroups {

    List<List<Integer>> cost;
    BitMap fullBitMap;
    int[] colWiseMin;
    int rowCount, colCount;
    Map<Integer, Map<BitMap, Integer>> costCache;

    private static class BitMap {
        final int[] arr;

        public BitMap(int size) {
            this.arr = new int[size];
        }

        public void setBit(int i) {
            arr[i]++;
        }

        public void setBits(List<Integer> list) {
            for (int l : list) {
                arr[l]++;
            }
        }

        public void eraseBit(int i) {
            arr[i]--;
        }

        public void eraseBits(List<Integer> list) {
            for (int l : list) {
                arr[l]--;
            }
        }

        public boolean isSet(int i) {
            return arr[i]>0;
        }

        public void setAllOne() {
            for (int i = 0; i < arr.length; i++) {
                setBit(i);
            }
        }

        @Override
        public int hashCode() {
            StringBuilder sb = new StringBuilder();
            for (int b : arr) {
                sb.append(b > 0 ? "1" : "0");
            }
            return Integer.parseInt(sb.toString(), 2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BitMap bitMap = (BitMap) o;
            if (arr.length != bitMap.arr.length) {
                return false;
            }
            for (int i = 0; i < this.arr.length; i++) {
                if (isSet(i) && !bitMap.isSet(i) || !isSet(i) && bitMap.isSet(i)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public String toString() {
            return "BitMap{" +
                    "arr=" + Arrays.toString(arr) +
                    '}';
        }
    }

    int getMin(List<Integer> l) {
        int min = Integer.MAX_VALUE;
        for (int i : l) {
            min = Math.min(i, min);
        }
        return min;
    }

    private int getMin(int row, BitMap bitMap) {
        Map<BitMap, Integer> rowMap = costCache.getOrDefault(row, new HashMap<>());
        if (rowMap.containsKey(bitMap)) {
            //System.out.println("Returning from cache");
            return rowMap.get(bitMap);
        }
        boolean isLastRow = row == cost.size() - 1;
        if (fullBitMap.equals(bitMap)) {
            if (isLastRow) {
                return getMin(cost.get(row));
            }
            return getMin(cost.get(row)) + getMin(row + 1, bitMap);
        }
        if (isLastRow) {
            int minPos = -1, min = Integer.MAX_VALUE;
            for (int i = 0; i < colCount; i++) {
                int cij = cost.get(row).get(i);
                if (!bitMap.isSet(i) && cij < min) {
                    minPos = i;
                    min = cij;
                }
            }
            bitMap.setBit(minPos);
            for (int i = 0; i < colCount; i++) {
                if (!bitMap.isSet(i)) {
                    min += colWiseMin[i];
                }
            }
            bitMap.eraseBit(minPos);
            rowMap.put(bitMap, min);
            costCache.put(row, rowMap);
            return min;
        }

        int min = Integer.MAX_VALUE;
        List<Integer> crow = cost.get(row);
        //List<Integer> unsetIndices = bitMap.getUnsetIndices();
        int totalPossibilities = (int) Math.pow(2, colCount);
        for (int i = 1; i < totalPossibilities; i++) {
            String s = Integer.toBinaryString(i);
            List<Integer> indicesToSet = new ArrayList<>();
            for (int j = s.length() - 1; j >= 0; j--) {
                char c = s.charAt(j);
                if (c == '1') {
                    indicesToSet.add(colCount - (s.length() - j));
                }
            }
            bitMap.setBits(indicesToSet);
            int c = 0;
            for (int b : indicesToSet) {
                c += crow.get(b);
            }
            c += getMin(row + 1, bitMap);
            min = Math.min(min, c);
            bitMap.eraseBits(indicesToSet);
        }

        rowMap.put(bitMap, min);
        costCache.put(row, rowMap);
        return min;
    }

    public int connectTwoGroups(List<List<Integer>> cost) {
        this.cost = cost;
        rowCount = cost.size();
        colCount = cost.get(0).size();
        fullBitMap = new BitMap(colCount);
        fullBitMap.setAllOne();
        colWiseMin = new int[colCount];
        costCache = new HashMap<>();
        for (int j = 0; j < colCount; j++) {
            colWiseMin[j] = cost.get(0).get(j);
            for (int i = 1; i < rowCount; i++) {
                colWiseMin[j] = Math.min(colWiseMin[j], cost.get(i).get(j));
            }
        }
        int min = getMin(0, new BitMap(colCount));
        //System.out.println(costCache);
        return min;
    }

    public static void main(String[] args) {
        MinCostConnectTwoGroups mcct = new MinCostConnectTwoGroups();
        long start = System.currentTimeMillis();
        Verifier.verifyEquals(mcct.connectTwoGroups(Creator.getList(
                Creator.getList(15, 96),
                Creator.getList(36, 2)
        )), 17);
        Verifier.verifyEquals(mcct.connectTwoGroups(Creator.getList(
                Creator.getList(1, 3, 5),
                Creator.getList(4, 1, 1),
                Creator.getList(1, 5, 3)
        )), 4);
        Verifier.verifyEquals(mcct.connectTwoGroups(Creator.getList(
                Creator.getList(2, 5, 1),
                Creator.getList(3, 4, 7),
                Creator.getList(8, 1, 2),
                Creator.getList(6, 2, 4),
                Creator.getList(3, 8, 8)
        )), 10);
        Verifier.verifyEquals(mcct.connectTwoGroups(Creator.getList(
                Creator.getList(34, 91),
                Creator.getList(52, 92),
                Creator.getList(13, 60),
                Creator.getList(43, 65),
                Creator.getList(96, 25)
        )), 167);
        Verifier.verifyEquals(mcct.connectTwoGroups(Creator.getList(
                Creator.getList(69,53,71,24,12,18,12,65,70),
                Creator.getList(64,89,34,95,71,8,76,15,31),
                Creator.getList(96,92,50,90,71,78,77,43,81),
                Creator.getList(48,9,34,51,25,99,51,24,10),
                Creator.getList(84,32,75,59,86,58,0,36,64),
                Creator.getList(48,22,87,46,4,82,36,27,23),
                Creator.getList(25,20,87,49,53,31,37,56,1),
                Creator.getList(13,76,9,81,8,40,59,91,61),
                Creator.getList(72,91,26,45,17,50,50,32,92))), 128);
        Verifier.verifyEquals(mcct.connectTwoGroups(Creator.getList(
                Creator.getList(98,48,4,71,20,18,14,9,87,84),
                Creator.getList(80,10,45,25,58,98,53,73,60,99),
                Creator.getList(68,61,98,95,2,76,61,82,54,46),
                Creator.getList(10,44,87,19,15,58,33,1,83,61),
                Creator.getList(10,41,96,6,34,11,99,71,63,6),
                Creator.getList(41,73,0,23,4,9,25,55,80,78),
                Creator.getList(61,54,87,82,35,40,63,67,99,21),
                Creator.getList(31,11,82,2,79,23,62,17,59,17),
                Creator.getList(86,17,81,0,80,47,51,64,67,63),
                Creator.getList(58,6,87,98,26,95,33,97,84,70),
                Creator.getList(98,66,51,48,47,25,54,64,11,75),
                Creator.getList(82,32,2,88,26,33,8,23,62,10))), 84);
        Verifier.verifyEquals(mcct.connectTwoGroups(Creator.getList(
                Creator.getList(66,78,9,28,3,70,92,79,18,63,80),
                Creator.getList(56,7,12,6,1,90,1,34,21,36,62),
                Creator.getList(4,68,28,93,17,38,72,96,26,89,63),
                Creator.getList(11,94,47,60,46,36,0,69,52,47,35),
                Creator.getList(18,76,87,51,37,9,38,60,45,98,9),
                Creator.getList(64,78,41,95,59,23,90,9,31,10,23),
                Creator.getList(25,52,84,48,44,69,3,9,50,13,15),
                Creator.getList(33,90,38,6,65,9,11,99,67,64,61),
                Creator.getList(29,30,79,46,78,74,89,80,98,92,89),
                Creator.getList(41,18,58,65,25,18,91,21,11,17,8),
                Creator.getList(41,39,38,47,100,55,96,29,62,38,94))), 120);
        System.out.println("Completed in " + (System.currentTimeMillis() - start));
    }

}
