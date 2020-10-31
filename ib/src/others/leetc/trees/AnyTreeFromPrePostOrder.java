package others.leetc.trees;

//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
public class AnyTreeFromPrePostOrder {

    private void printArr(int [] arr, int startPos, int endPos) {
        for (int i = startPos; i <= endPos; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println("");
    }

    private int findPos(int [] arr, int ele, int startPos, int endPos) {
        for (int i = startPos; i <= endPos; i++) {
            if (arr[i] == ele)
                return i;
        }
        return -1;
    }

    private TreeNode construct(int[] pre, int[] post, int preLeftIndex, int preRightIndex, int postLeftIndex,
                               int postRightIndex) {
        if (preLeftIndex > preRightIndex || postLeftIndex > postRightIndex || postRightIndex - postLeftIndex != preRightIndex - preLeftIndex) {
            return null;
        }
//        System.out.println("Constructing from - ");
//        System.out.print("Pre - ");printArr(pre, preLeftIndex, preRightIndex);
//        System.out.print("Post - ");printArr(post, postLeftIndex, postRightIndex);
        if (preLeftIndex == preRightIndex) {
            return new TreeNode(pre[preLeftIndex]);
        }
        int leftRootPosPre = preLeftIndex + 1;
        int leftRootPosPost = findPos(post, pre[leftRootPosPre], postLeftIndex, postRightIndex-1);
        int leftTreeMaxPosPre = leftRootPosPre;
        for (int i = leftRootPosPre + 1; i <= preRightIndex; i++) {
            int idx = findPos(post, pre[i], postLeftIndex, postRightIndex-1);
            leftTreeMaxPosPre = i;
            if (idx > leftRootPosPost) {
                leftTreeMaxPosPre = i - 1;
                break;
            }
        }
        TreeNode left = construct(pre, post, leftRootPosPre, leftTreeMaxPosPre, postLeftIndex, leftRootPosPost);
        TreeNode right = construct(pre, post, leftTreeMaxPosPre + 1, preRightIndex, leftRootPosPost+1, postRightIndex-1);
        return new TreeNode(pre[preLeftIndex], left, right);
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return construct(pre, post, 0, pre.length - 1, 0, post.length - 1);
    }

    public static void main(String[] args) {
        AnyTreeFromPrePostOrder tfpp = new AnyTreeFromPrePostOrder();
        TreeNode tn;
        tn = tfpp.constructFromPrePost(new int[] {1,2}, new int[] {2,1});
        System.out.println(tn);
        tn = tfpp.constructFromPrePost(new int[] {1,2,3}, new int[] {3,2,1});
        System.out.println(tn);
        tn = tfpp.constructFromPrePost(new int[] {1,2,4,5,3,6,7}, new int[] {4,5,2,6,7,3,1});
        System.out.println(tn);
        tn = tfpp.constructFromPrePost(new int[] {1,2,3,4,5,6}, new int[] {3,2,6,5,4,1});
        System.out.println(tn);
    }

}
