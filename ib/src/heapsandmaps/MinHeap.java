package heapsandmaps;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<K extends Comparable<? super K>> {

    private final int size;
    private final List<K> list;
    private int insertPos = 0;

    public MinHeap(int size) {
        this.size = size;
        this.list = new ArrayList<>(size);
        for (int i = 0; i < size; i++)
            list.add(i, null);
    }

    MinHeap insert(K k) {
        if (insertPos == size)
            throw new IllegalStateException("Heap is full");
        list.set(insertPos, k);
        bubbleUp(insertPos);
        insertPos++;
        return this;
    }

    K peak() {
        return list.get(0);
    }

    K delete() {
        K returnVal = list.get(0);
        list.set(0, list.get(insertPos -1));
        trickleDown(0);
        insertPos--;
        return returnVal;
    }

    boolean isFull() {
        return insertPos == size;
    }

    boolean isEmpty() {
        return insertPos == 0;
    }

    private void bubbleUp(int pos) {
        if (pos == 0)
            return;
        int parentPos = pos%2 == 0 ? pos/2 - 1 : pos/2;
        if (list.get(parentPos).compareTo(list.get(pos)) > 0) {
            K k = list.get(pos);
            list.set(pos, list.get(parentPos));
            list.set(parentPos, k);
            bubbleUp(parentPos);
        }
    }

    private void trickleDown(int pos) {
        int leftPos = pos*2 + 1;
        int rightPos = leftPos + 1;
        int minPos = pos;
        if (rightPos < insertPos) {
            minPos = list.get(pos).compareTo(list.get(rightPos)) > 0 ? rightPos : pos;
            minPos = list.get(minPos).compareTo(list.get(leftPos)) > 0 ? leftPos : minPos;
        } else if (leftPos < insertPos) {
            minPos = list.get(minPos).compareTo(list.get(leftPos)) > 0 ? leftPos : minPos;
        }

        if (minPos != pos) {
            K val = list.get(pos);
            list.set(pos, list.get(minPos));
            list.set(minPos, val);
            trickleDown(minPos);
        }
    }

}
