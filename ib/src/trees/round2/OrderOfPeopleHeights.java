package trees.round2;

import util.Creator;
import util.Verifier;

import java.util.*;

public class OrderOfPeopleHeights {

    private static class Node {
        Info info;
        Node prev;
        Node next;

        public Node(Info info) {
            this.info = info;
        }
    }

    private static class DoublyLL {
        private Node head;
        private Node tail;
        Map<Integer, Node> infoMap;

        public DoublyLL() {
            infoMap = new HashMap<>();
        }

        private void print(String message) {
            Node node = head;
            System.out.println(message);
            while (node != null) {
                System.out.print(node.info.height + "->");
                node = node.next;
            }
            System.out.println();
        }

        private void add(Info a) {
            Node node = new Node(a);
            infoMap.put(a.height, node);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            //print("Added " + a.height);
        }

        private Node find(int height) {
            return infoMap.get(height);
        }

        private void delete(Node node) {
            //System.out.println("Deleting " + node.info.height);
            if (node == head) {
                head = head.next;
                if (head == null) { //single node case
                    tail = null;
                } else {
                    head.prev = null;
                }
            } else if (node == tail) {
                //single node case can't come here - it should go in previous if
                tail = tail.prev;
                tail.next = null;
            } else {
                Node next = node.next;
                Node prev = node.prev;
                prev.next = next;
                next.prev = prev;
            }
            //print("Deleted " + node.info.height);
        }
    }

    private static class Info implements  Comparable<Info> {
        int height, infront;

        Info(int height, int infront) {
            this.height = height;
            this.infront = infront;
        }

        @Override
        public int compareTo(Info info) {
            return Integer.compare(height, info.height);
        }
    }

    private static List<Integer> order(List<Integer> heights, List<Integer> infronts) {
        List<Info> infos = new ArrayList<>();
        PriorityQueue<Info> zeroInfrontQueue = new PriorityQueue<>();
        DoublyLL ll = new DoublyLL();
        for (int i = 0; i < heights.size(); i++) {
            Info info = new Info(heights.get(i), infronts.get(i));
            if (infronts.get(i) == 0) {
                zeroInfrontQueue.add(info);
            }
            infos.add(info);
        }
        Collections.sort(infos);
        for (Info info : infos) {
            ll.add(info);
        }
        List<Integer> ans = new ArrayList<>();
        while (!zeroInfrontQueue.isEmpty()) {
            Info info = zeroInfrontQueue.poll();
            ans.add(info.height);
            Node node = ll.find(info.height);
            List<Node> toRemove = new ArrayList<>();
            while (node != null) {
                if (node.info.infront == 0) {
                    toRemove.add(node);
                } else {
                    node.info.infront--;
                    if (node.info.infront == 0) {
                        zeroInfrontQueue.add(node.info);
                    }
                }
                node = node.prev;
            }
            for (Node node1 : toRemove) {
                ll.delete(node1);
            }
        }
        return ans.size() != 0 ? ans : null;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(order(Creator.getList(5, 3, 2, 6, 1, 4), Creator.getList(0, 1, 2, 0, 3, 2)),
                Creator.getList(5, 3, 2, 1, 6, 4));
        Verifier.verifyEquals(order(Creator.getList(3,2,1), Creator.getList(0,1,1)),
                Creator.getList(3,1,2));
        Verifier.verifyEquals(order(Creator.getList(7,4,7,5,6,5), Creator.getList(0,4,1,0,1,2)),
                Creator.getList(5,7,5,6,4,7));
        Verifier.verifyEquals(order(Creator.getList(1,2,3), Creator.getList(3,3,3)), null);
    }

}
