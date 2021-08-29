package util;

import trees.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Creator {

    public static <T> List<T> getList(T... elements) {
        List<T> list = new ArrayList<T>(elements.length);
        for (T ele : elements) {
            list.add(ele);
        }
        return list;
    }

    public static ListNode getListNode(int ... elements) {
        ListNode head = null, list = null;
        for (int ele : elements) {
            ListNode node = new ListNode(ele);
            node.next = null;
            if (list == null)
                list = node;
            else {
                list.next = node;
                list = list.next;
            }
            if (head == null)
                head = node;
        }
        return head;
    }

    public static others.leetc.trees.TreeNode createTree(Integer [] arr) {
        others.leetc.trees.TreeNode [] nodes = new others.leetc.trees.TreeNode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = arr[i] != null ? new others.leetc.trees.TreeNode(arr[i]) : null;
            if (i > 0 && arr[i] != null) {
                if (i % 2 == 0) {
                    int parentIndex = (i-2)/2;
                    nodes[parentIndex].right = nodes[i];
                } else {
                    int parentIndex = (i-1)/2;
                    try {
                        nodes[parentIndex].left = nodes[i];
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return nodes[0];
    }

    public static TreeNode[] createTreeNodes(int count) {
        TreeNode [] list = new TreeNode[count + 1];
        for (int i = 1; i <= count; i++){
            list[i] = new TreeNode(i);
        }
        return list;
    }

}
