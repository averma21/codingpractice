package others.leetc.thirtydaychallenge;

import util.Verifier;

//https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/529/week-2/3297/
public class LastStoneWeight {

    private static class MaxHeap {
        int [] heap;
        int nextHeapPos;
        final int maxSize;

        public MaxHeap(int[] arr) {
            this.heap = new int[arr.length];
            nextHeapPos = 0;
            maxSize = arr.length;
            for (int a : arr) {
                insert(a);
            }
        }

        private void insert(int num) {
            if (nextHeapPos == maxSize) {
                throw new IllegalStateException("Heap full");
            }
            heap[nextHeapPos++] = num;
            heapify(nextHeapPos-1);
        }

        private void heapify(int pos) {
            int parent = (pos - 1)/2;
            if (pos > 0 && heap[parent] < heap[pos]) {
                swap(pos, parent);
                heapify(parent);
            }
        }

        private void swap(int pos, int pos2) {
            int n = heap[pos];
            heap[pos] = heap[pos2];
            heap[pos2] = n;
        }

        private void trickleDown(int pos) {
            if (pos >= nextHeapPos) {
                return;
            }
            int leftIndex = 2*pos + 1;
            int rightIndex = 2*pos + 2;
            int maxPos = leftIndex < nextHeapPos && heap[leftIndex] > heap[pos] ? leftIndex : pos;
            maxPos = rightIndex < nextHeapPos && heap[rightIndex] > heap[maxPos] ? rightIndex : maxPos;
            if (maxPos != pos) {
                swap(maxPos, pos);
                trickleDown(maxPos);
            }
        }

        private int removeMax() {
            if (nextHeapPos == 0)
                throw new IllegalStateException("Heap empty");
            int popped = heap[0];
            heap[0] = heap[nextHeapPos - 1];
            nextHeapPos--;
            trickleDown(0);
            return popped;
        }

        private boolean isEmpty() {
            return nextHeapPos == 0;
        }
    }

    public int lastStoneWeight(int[] stones) {
        MaxHeap heap = new MaxHeap(stones);
        while (!heap.isEmpty()) {
            int max1 = heap.removeMax();
            if (heap.isEmpty()) {
                return max1;
            }
            int max2 = heap.removeMax();
            if (max1 != max2) {
                heap.insert(max1 - max2);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        LastStoneWeight lsw = new LastStoneWeight();
        Verifier.verifyEquals(lsw.lastStoneWeight(new int[]{2,7,4,1,8,1}), 1);
        Verifier.verifyEquals(lsw.lastStoneWeight(new int[]{2,2}), 0);
        Verifier.verifyEquals(lsw.lastStoneWeight(new int[]{10,3,4,3}), 0);
        Verifier.verifyEquals(lsw.lastStoneWeight(new int[]{10,3,4,5}), 2);
    }

}
