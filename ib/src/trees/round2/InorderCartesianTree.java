package trees.round2;

import trees.TreeNode;
import util.Creator;

import java.util.List;


/**
 * Another O(n) solution (not implemented here) from wiki - https://en.wikipedia.org/wiki/Cartesian_tree
 *
 * An alternative linear-time construction algorithm is based on the all nearest smaller values problem.
 * In the input sequence, one may define the left neighbor of a value x to be the value that occurs prior to x,
 * is smaller than x, and is closer in position to x than any other smaller value. The right neighbor is defined
 * symmetrically. The sequence of left neighbors may be found by an algorithm that maintains a stack containing a
 * subsequence of the input. For each new sequence value x, the stack is popped until it is empty or its top element
 * is smaller than x, and then x is pushed onto the stack. The left neighbor of x is the top element at the time x
 * is pushed. The right neighbors may be found by applying the same stack algorithm to the reverse of the sequence.
 * The parent of x in the Cartesian tree is either the left neighbor of x or the right neighbor of x, whichever exists
 * and has a larger value. The left and right neighbors may also be constructed efficiently by parallel algorithms,
 * so this formulation may be used to develop efficient parallel algorithms for Cartesian tree construction.[7]
 */
public class InorderCartesianTree {

    List<Integer> inorder;

    private TreeNode constructTreeBetween(int start, int end) {
        if (start > end) {
            return null;
        }
        int maxIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (inorder.get(i) > inorder.get(maxIndex)) {
                maxIndex = i;
            }
        }
        TreeNode node = new TreeNode(inorder.get(maxIndex));
        if (start == end) {
            return node;
        }
        node.left = constructTreeBetween(start, maxIndex-1);
        node.right = constructTreeBetween(maxIndex+1, end);
        return node;
    }

    public TreeNode buildTree(List<Integer> A) {
        if (A == null || A.size() == 0) {
            return null;
        }
        inorder = A;
        return constructTreeBetween(0, A.size() - 1);
    }

    public static void main(String[] args) {
        InorderCartesianTree iot = new InorderCartesianTree();
        System.out.println(iot.buildTree(Creator.getList(1,2,3)));
        System.out.println(iot.buildTree(Creator.getList(2,1,3)));
        System.out.println(iot.buildTree(Creator.getList(5,10,40,30,28)));
    }

}
