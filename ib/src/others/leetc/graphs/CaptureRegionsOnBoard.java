package others.leetc.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CaptureRegionsOnBoard {

    Map<Integer, Set<Integer>> connectedSet;

    private void capture(List<List<Character>> board) {
        if (board == null || board.size() == 0 || board.get(0).size() == 0)
            return;
        connectedSet = new HashMap<>();
        char zeroChar = 'O';
        int m = board.size(), n = board.get(0).size();
        for (int rowIndex = 0; rowIndex < m; rowIndex++) {
            List<Character> row = board.get(rowIndex);
            for (int colIndex = 0; colIndex < n; colIndex++) {
                if (row.get(colIndex) == zeroChar) {
                    boolean added = false;
                    Set<Integer> upset = null;
                    Set<Integer> leftSet = null;
                    int upKey = -1, leftKey = -1;
                    int newKey = rowIndex * n + colIndex;
                    if (rowIndex > 0 && board.get(rowIndex - 1).get(colIndex) == zeroChar) {
                        upKey = (rowIndex - 1)*n + colIndex;
                        upset = connectedSet.get(upKey);
                    }
                    if (colIndex > 0 && row.get(colIndex - 1) == zeroChar) {
                        leftKey = rowIndex*n + colIndex - 1;
                        leftSet = connectedSet.get(leftKey);
                    }
                    if (upset != null && leftSet != null) {
                        for (int x : leftSet) {
                            connectedSet.put(x, upset);
                            upset.add(x);
                        }
                        upset.add(newKey);
                        connectedSet.put(newKey, upset);
                    } else if (upset != null) {
                        upset.add(newKey);
                        connectedSet.put(newKey, upset);
                    } else if (leftSet != null) {
                        leftSet.add(newKey);
                        connectedSet.put(newKey, leftSet);
                    } else {
                        Set<Integer> set = new HashSet<>();
                        set.add(newKey);
                        connectedSet.put(newKey, set);
                    }
                }
            }
        }
        for (int col = 0; col < n; col++) {
            int key = col;
            connectedSet.computeIfPresent(key, (k,v) -> {v.clear(); return v;});
            key = (m-1)*n + col;
            connectedSet.computeIfPresent(key, (k,v) -> {v.clear(); return v;   });
        }

        for (int row = 1; row < m - 1; row++) {
            int key = row*n;
            connectedSet.computeIfPresent(key, (k,v) -> {v.clear(); return v;});
            key = row*n + n - 1;
            connectedSet.computeIfPresent(key, (k,v) -> {v.clear(); return v;});
        }

        for (Map.Entry<Integer, Set<Integer>> entry : connectedSet.entrySet()) {
            if (entry.getValue().size() > 0) {
                int key = entry.getKey();
                int row = key/n;
                int col = key%n;
                board.get(row).set(col, 'X');
            }
        }
    }

    private static List<List<Character>> getList(String [] arr) {
        List<List<Character>> lists = new ArrayList<>(arr.length);
        for (String s : arr) {
            List<Character> list = new ArrayList<>(s.length());
            for (int i = 0; i < s.length(); i++)
                list.add(s.charAt(i));
            lists.add(list);
        }
        return lists;
    }

    public static void main(String[] args) {
        CaptureRegionsOnBoard crob = new CaptureRegionsOnBoard();
        List<List<Character>> list = getList(new String[] {"XXXX",
                                                           "XOOX",
                                                           "XXOX",
                                                           "XOXX"});
        crob.capture(list);
        System.out.println(list);
        list = getList(new String[] {
           "XXXX",
           "OOOX",
           "XXOX",
           "XOXX"
        });
        crob.capture(list);
        System.out.println(list);
        list = getList(new String[]{
           "XXXX",
           "XXXX"
        });
        crob.capture(list);
        System.out.println(list);
        list = getList(new String[]{
                "OOOXXXO",
                "XXXOOOO",
                "XXOXOXO",
                "OXOXOXO",
                "XXOXOXX",
                "XOOOXXO"
        });
        crob.capture(list);
        System.out.println(list);
        list = getList(new String[]{
            "OOOXXXO",
            "XXXOOOO",
            "XXOXOXO",
            "OXOXOXO",
            "XXOXOXX",
            "XOOOXXO",
            "OXXOXOO",
            "OXOOXOX"
        });
        crob.capture(list);
        System.out.println(list);
    }

}
