package others.leetc.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/all-possible-full-binary-trees/
public class AllPossibleFullBinaryTress {



    // It is only possible to construct this tree with odd number of nodes.
    // Construct all subtrees of left and right and then for each pair of subtrees from left and right list,
    // construct a new tree with selected subtress as left and right child respectively.
    private List<TreeNode> helper(int N) {
        if (N == 1) {
            return Collections.singletonList(new TreeNode(0));
        }
        List<TreeNode> ans = new ArrayList<>();
        for (int leftCount = 1; leftCount <= N - 2; leftCount+=2) {
            int rightCount = N - leftCount - 1; // leftCount selected for left subtree and 1 node selected for root.
            List<TreeNode> leftList = helper(leftCount);
            List<TreeNode> rightList = helper(rightCount);
            for (TreeNode lNode : leftList) {
                for (TreeNode rNode : rightList) {
                    ans.add(new TreeNode(0, lNode, rNode));
                }
            }
        }
        return ans;
    }

    public List<TreeNode> allPossibleFBT(int N) {
        return helper(N);
    }

}
