package segment;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Let us consider the following problem to understand Segment Trees.
 *
 * We have an array arr[0 . . . n-1]. We should be able to
 * 1 Find the sum of elements from index l to r where 0 <= l <= r <= n-1
 *
 * 2 Change value of a specified element of the array to a new value x. We need to do arr[i] = x where 0 <= i <= n-1.
 *
 * https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 */

public class SumOfGivenRange {

    private static class Tree extends IntSegmentTree {

        public Tree(int[] arr) {
            super(arr);
        }

        @Override
        List<Integer> getParentValue(SegmentTreeNode<Integer> leftChild, SegmentTreeNode<Integer> rightChild) {
            return IntStream.range(0, leftChild.getValues().size())
                    .mapToObj(i -> leftChild.getValues().get(i) + rightChild.getValues().get(i))
                    .collect(Collectors.toList());
        }

        int getSum(int left, int right) {
            if (left < 0 || right < 0 || left > right || right >= inputSize) {
                throw new IllegalArgumentException("Invalid left and right " + left + ", " + right);
            }
            return getSum(getRoot(), left, right);
        }

        private int getSum(SegmentTreeNode<Integer> node, int left, int right) {
            if (node.getLeftIndex() > right || node.getRightIndex() < left) {
                return 0;
            }
            if (node.getLeftIndex() == left && node.getRightIndex() == right
                    || node.getLeftIndex() == node.getRightIndex()) {
                return node.getValues().stream().reduce(0 , Integer::sum);
            }
            if (node.getLeftChild().isPresent() && node.getRightChild().isPresent()) {
                return getSum(node.getLeftChild().get(), left, right) + getSum(node.getRightChild().get(), left, right);
            }
            throw new IllegalStateException("Don't have left and right child" +  node.getValues());
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree(new int[] {1, 3, 5, 7, 9, 11});
        System.out.println(tree.getSum(3, 5));
        System.out.println(tree.getSum(2, 3));
        System.out.println(tree.getSum(1, 1));
        //System.out.println(tree.getSum(2, 1));
        //System.out.println(tree.getSum(-2, 1));
        //System.out.println(tree.getSum(1, 6));
    }

}
