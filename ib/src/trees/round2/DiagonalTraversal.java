package trees.round2;

import trees.TreeNode;
import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagonalTraversal {

    Map<Integer, List<Integer>> diagonalElemMap;

    private void traverse(TreeNode node, int diagonalNum) {
        if (node == null) {
            return;
        }
        diagonalElemMap.putIfAbsent(diagonalNum, new ArrayList<>());
        diagonalElemMap.computeIfPresent(diagonalNum, (k,l)-> {l.add(node.val); return l;});
        traverse(node.left, diagonalNum+1);
        traverse(node.right, diagonalNum);
    }

    public List<Integer> solve(TreeNode A) {
        diagonalElemMap = new HashMap<>();
        traverse(A, 0);
        List<Integer> ans = new ArrayList<>();
        int diag = 0;
        while (diagonalElemMap.containsKey(diag)) {
            ans.addAll(diagonalElemMap.get(diag));
            diag++;
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode [] l = Creator.createTreeNodes(9);
        l[1].left = l[4];
        l[4].left = l[8]; l[4].right = l[5];
        l[5].left = l[9]; l[5].right = l[7];
        l[1].right = l[2];
        l[2].right = l[3];
        l[3].left = l[6];
        DiagonalTraversal dt = new DiagonalTraversal();
        Verifier.verifyEquals(dt.solve(l[1]), Creator.getList(1, 2, 3, 4, 5, 7, 6, 8, 9));
        TreeNode n11 = new TreeNode(11);
        TreeNode n12 = new TreeNode(12);
        TreeNode n13 = new TreeNode(13);
        TreeNode n16 = new TreeNode(16);
        TreeNode n20 = new TreeNode(20);
        TreeNode n15 = new TreeNode(15);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n17 = new TreeNode(17);
        TreeNode n22 = new TreeNode(22);
        TreeNode n34 = new TreeNode(34);
        n11.left=n20;n11.right=n12;
        n20.left=n1;n20.right=n15;
        n15.left=n2;n15.right=n17;
        n2.right=n22;n17.left=n34;
        n12.right=n13;n13.left=n16;
        Verifier.verifyEquals(dt.solve(n11), Creator.getList(11, 12, 13, 20, 15, 17, 16, 1, 2, 22, 34));

    }

}
