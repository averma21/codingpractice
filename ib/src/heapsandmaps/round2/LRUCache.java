package heapsandmaps.round2;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private static class ListNode {
        ListNode prev;
        ListNode next;
        final int key;
        int value;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class KeyList {
        ListNode head;
        ListNode tail;

        void insert(ListNode node) {
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }
        }

        void bringToHead(ListNode node) {
            if (node == head) {
                return;
            }
            ListNode prev = node.prev;
            ListNode next = node.next;
            prev.next = next;
            if (next != null) {
                next.prev = prev;
            }
            if (node == tail) {
                tail = prev;
            }
            insert(node);
        }

        ListNode deleteNode() {
            ListNode toDelete = tail;
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            toDelete.prev = null;
            return toDelete;
        }
    }

    private final int capacity;
    private final KeyList keyList;
    private final Map<Integer, ListNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.keyList = new KeyList();
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            keyList.bringToHead(node);
            return node.value;
        }
        return -1;
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            keyList.bringToHead(node);
            node.value = value;
            return;
        }
        if (map.size() == capacity) {
            ListNode listNode = keyList.deleteNode();
            map.remove(listNode.key);
        }
        ListNode listNode = new ListNode(key, value);
        keyList.insert(listNode);
        map.put(key, listNode);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.set(1,10);
        cache.set(5,12);
        Verifier.verifyEquals(cache.get(5), 12);
        Verifier.verifyEquals(cache.get(1), 10);
        Verifier.verifyEquals(cache.get(10), -1);
        cache.set(6,14);
        Verifier.verifyEquals(cache.get(5), -1);
        cache.set(6,12);
        Verifier.verifyEquals(cache.get(6), 12);
        cache.set(7,18);
        Verifier.verifyEquals(cache.get(1), -1);
        cache.set(8,20);
        Verifier.verifyEquals(cache.get(6), -1);


        cache = new LRUCache(2);
        Verifier.verifyEquals(cache.get(2), -1);
        cache.set(2, 6);
        Verifier.verifyEquals(cache.get(1), -1);
        cache.set(1, 5);
        cache.set(1, 2);
        Verifier.verifyEquals(cache.get(1), 2);
        Verifier.verifyEquals(cache.get(2), 6);
    }

}
