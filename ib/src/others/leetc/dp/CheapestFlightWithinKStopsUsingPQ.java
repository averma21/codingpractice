package others.leetc.dp;

import util.Verifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Based on Dijsktra's
 */
public class CheapestFlightWithinKStopsUsingPQ {

    //faster than 50% solutions
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<Integer[]>> graph = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return Integer.compare(ints[0], t1[0]);
            }
        });
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int [] flight : flights) {
            graph.computeIfPresent(flight[0], (k,list) -> {
               list.add(new Integer[] {flight[1], flight[2]});
               return list;
            });
        }
        pq.add(new int[]{0, src, -1});
        while (!pq.isEmpty()) {
            int [] entry = pq.remove();
            int price = entry[0];
            int city = entry[1];
            int stops = entry[2];
            if (city == dst && stops <= K) {
                return price;
            }
            if (stops >= K) {
                // no need to look for this node's neighbours as steps would be more than K
                continue;
            }
            List<Integer[]> list = graph.get(city);
            for (Integer[] integers : list){
                pq.add(new int[] {price + integers[1], integers[0], stops+1});
            }
        }
        return -1;
    }

    private static class Heap {
        int [][] keyValue;
        int insertPos = 0;
        int [] keyPositionMap;

        public Heap(int size, int keyRange) {
            this.keyValue = new int[size][3];
            this.keyPositionMap = new int[keyRange + 1];
        }

        public void insert(int key, int value, int steps) {
            if (insertPos == keyValue.length) {
                throw new IllegalStateException("Heap full");
            }
            keyValue[insertPos][0] = key;
            keyValue[insertPos][1] = value;
            keyValue[insertPos][2] = steps;
            keyPositionMap[key] = insertPos;
            heapify(insertPos);
            insertPos++;
        }

        int findCurCost(int city) {
            int pos = keyPositionMap[city];
            if (keyValue[pos][0] == city) {
                return keyValue[pos][1];
            }
            return Integer.MAX_VALUE;
        }

        private void swap(int pos1, int pos2) {
            int [] temp = new int[3];
            temp[0] = keyValue[pos1][0];
            temp[1] = keyValue[pos1][1];
            temp[2] = keyValue[pos1][2];
            keyValue[pos1][0] = keyValue[pos2][0];
            keyValue[pos1][1] = keyValue[pos2][1];
            keyValue[pos1][2] = keyValue[pos2][2];
            keyValue[pos2][0] = temp[0];
            keyValue[pos2][1] = temp[1];
            keyValue[pos2][2] = temp[2];
            keyPositionMap[keyValue[pos2][0]] = pos2;
            keyPositionMap[keyValue[pos1][0]] = pos1;
        }

        private void heapify(int pos) {
            if (pos == 0) {
                return;
            }
            int parentPos = (pos-1)/2;
            if (keyValue[parentPos][1] > keyValue[pos][1]) {
                swap(pos, parentPos);
                heapify(parentPos);
            }
        }

        private void trickleDown(int pos) {
            int left = pos*2 + 1;
            int right = left + 1;
            int minPos = pos;
            if (left >= insertPos) {
                return;
            }
            if (keyValue[left][1] < keyValue[minPos][1]) {
                minPos = left;
            }
            if (right < insertPos) {
                if (keyValue[right][1] < keyValue[minPos][1]) {
                    minPos = right;
                }
            }
            if (pos != minPos) {
                swap(pos, minPos);
            }
        }

        int [] extractMin() {
            int [] ans = Arrays.copyOf(keyValue[0], keyValue[0].length);
            keyValue[0][0] = keyValue[insertPos-1][0];
            keyValue[0][1] = keyValue[insertPos-1][1];
            keyValue[0][2] = keyValue[insertPos-1][2];
            keyPositionMap[keyValue[0][0]] = 0;
            insertPos--;
            trickleDown(0);
            return ans;
        }

        boolean isEmpty() {
            return insertPos == 0;
        }
    }

    //Wrong Ans
    public int findCheapestPriceWrongAns(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<Integer[]>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int [] flight : flights) {
            graph.computeIfPresent(flight[0], (k,list) -> {
               list.add(new Integer[] {flight[1], flight[2]});
               return list;
            });
        }
        Heap heap = new Heap(n, n);
        heap.insert(src, 0, -1);
        while (!heap.isEmpty()) {
            int [] details = heap.extractMin();
            int city = details[0];
            int price = details[1];
            int stops = details[2];
            if (city == dst) {
                return price;
            }
            if (stops >= K) {
                // no need to look for this node's neighbours as steps would be more than K
                continue;
            }
            List<Integer[]> list = graph.get(city);
            for (Integer[] integers : list) {
                if (heap.findCurCost(integers[0]) > price + integers[1]) {
                    heap.insert(integers[0], price + integers[1], stops + 1);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        CheapestFlightWithinKStopsUsingPQ cfw = new CheapestFlightWithinKStopsUsingPQ();
        long t1 = System.currentTimeMillis();
        Verifier.verifyEquals(cfw.findCheapestPrice(5,
                new int [][]{{0,1,5},{1,2,5},{0,3,2},{3,1,2},{1,4,1},{4,2,1}},
                0,
                2,
                2), 7);
        Verifier.verifyEquals(cfw.findCheapestPrice(3,
                new int [][]{{0,1,100},{1,2,100},{0,2,500}},
        0,
        2,
        1), 200);
        Verifier.verifyEquals(cfw.findCheapestPrice(3,
                new int [][]{{0,1,100},{1,2,100},{0,2,500}},
                0,
                2,
                0), 500);
        Verifier.verifyEquals(cfw.findCheapestPrice(4,
                new int [][]{{0,1,100},{1,2,100},{0,3,500}},
                0,
                2,
                1), 200);
        Verifier.verifyEquals(cfw.findCheapestPrice(4,
                new int [][]{{0,1,100},{1,2,100},{0,3,500}},
                0,
                2,
                0), -1);
        Verifier.verifyEquals(cfw.findCheapestPrice(4,
                new int [][]{{0,1,2},{1,2,1},{2,0,10}},
                1,
                2,
                1), 1);
        Verifier.verifyEquals(cfw.findCheapestPrice(4,
                new int [][]{{0,1,1},{0,2,5},{1,2,1},{2,3,1}},
                0,
                3,
                1), 6);
        System.out.println("Time =" + (System.currentTimeMillis() - t1));
    }

}
