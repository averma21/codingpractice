package stack.round2;

import util.Creator;
import util.Verifier;

import java.util.List;
import java.util.Stack;

public class RainWaterTrapped {

    /**
     * Basic idea behind stack based solution is to be able to identify when two walls will be able to hold water.
     * We find for next greater or equal element for every entry. While doing so, the entries which get popped out of
     * stack will not contribute as walls because they will be submerged in the water between the entry which caused
     * them to pop and the last entry (last remaining element - meaning stack size 1) in the stack.
     * So, we need to calculate area when we are going to pop the last entry from stack and subtract the area occupied
     * by walls in between the last entry of stack and the entry which is going to pop this last entry.
     *
     * In the end, the stack will contain elements in non-increasing order and water could be trapped in between every two
     * adjacent entries present in stack. So we calculate and add area between every two adjacent entries and keep on
     * subtracting area occupied by submerged walls.
     *
     * An easier solution without using stack is given in {@link #calcEasy(List)}
     */
    public int calc(List<Integer> A) {
        if (A == null || A.size() <= 1) {
            return 0;
        }
        Stack<Integer> indexStack = new Stack<Integer>();
        int n = A.size();
        int [] sumArray = new int[n]; // can be used to
        sumArray[0] = A.get(0);
        indexStack.push(0);
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int ai = A.get(i);
            sumArray[i] = sumArray[i-1] + ai;
            while (!indexStack.isEmpty() && A.get(indexStack.peek()) < ai) {
                int index = indexStack.pop();
                if (indexStack.isEmpty()) {
                    ans += A.get(index) * (i - index - 1);
                    int inBetweenOccupiedArea = sumArray[i-1] - sumArray[index];
                    ans -= inBetweenOccupiedArea;
                }
            }
            indexStack.push(i);
        }
        while (!indexStack.isEmpty()) {
            int rightIndex = indexStack.pop();
            if (indexStack.isEmpty()) {
                break;
            }
            int leftIndex = indexStack.peek();
            ans += Math.min(A.get(rightIndex), A.get(leftIndex)) * (rightIndex - leftIndex - 1);
            int inBetweenOccupiedArea = sumArray[rightIndex-1] - sumArray[leftIndex];
            ans -= inBetweenOccupiedArea;
        }
        return ans;
    }

    public int calcEasy(List<Integer> A) {
        int l=0,r=A.size()-1;
        int ans=0,lm=0,rm=0;
        while(l < r){
            int al = A.get(l), ar = A.get(r);
            if(al < ar){
                lm = Math.max(lm, al);
                ans += lm - al;
                l++;
            }
            else{
                rm= Math.max(rm, ar);
                ans += rm- ar;
                r--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        RainWaterTrapped rwt = new RainWaterTrapped();
        Verifier.verifyEquals(rwt.calc(Creator.getList(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)), 6);
        Verifier.verifyEquals(rwt.calc(Creator.getList(1,2)), 0);
        Verifier.verifyEquals(rwt.calc(Creator.getList(4,2,1,3,3,0,0,1,2,0,3,0,4,0,0,6,0,6)), 43);
        Verifier.verifyEquals(rwt.calc(Creator.getList(7,4,2,1,3,3,0,0,1,2,0,3,0,4,0,0,6,0,6)), 73);


        Verifier.verifyEquals(rwt.calcEasy(Creator.getList(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)), 6);
        Verifier.verifyEquals(rwt.calcEasy(Creator.getList(1,2)), 0);
        Verifier.verifyEquals(rwt.calcEasy(Creator.getList(4,2,1,3,3,0,0,1,2,0,3,0,4,0,0,6,0,6)), 43);
        Verifier.verifyEquals(rwt.calcEasy(Creator.getList(7,4,2,1,3,3,0,0,1,2,0,3,0,4,0,0,6,0,6)), 73);
    }

}
