package others.leetc.strings;

import util.Verifier;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/minimum-number-of-frogs-croaking/
public class MinNumberOfFrogsCroaking {

//    public int minNumberOfFrogs(String croakOfFrogs) {
//        Queue<Integer> cQueue = new LinkedList<>();
//        Queue<Integer> rQueue = new LinkedList<>();
//        Queue<Integer> oQueue = new LinkedList<>();
//        Queue<Integer> aQueue = new LinkedList<>();
//        Queue<Integer> kQueue = new LinkedList<>();
//        Queue<Queue<Integer>> queues = new LinkedList<>();
//        queues.add(cQueue);
//        queues.add(rQueue);
//        queues.add(oQueue);
//        queues.add(aQueue);
//        queues.add(kQueue);
//        for (int i = 0; i < croakOfFrogs.length(); i++) {
//            char c = croakOfFrogs.charAt(i);
//            switch (c) {
//                case 'c' : cQueue.add(i);break;
//                case 'r' : rQueue.add(i);break;
//                case 'o' : oQueue.add(i);break;
//                case 'a' : aQueue.add(i);break;
//                case 'k' : kQueue.add(i);break;
//            }
//        }
//        int count = 0;
//        int lastIndex = -1;
//        while (!queues.isEmpty()) {
//            Queue<Integer> queue = queues.remove();
//            if (queue.isEmpty()) {
//                break;
//            }
//            int pos = queue.remove();
//            if (pos < lastIndex) {
//                break;
//            }
//            lastIndex = pos;
//            if (croakOfFrogs.charAt(pos) == 'k') {
//                count++;
//            }
//            queues.add(queue);
//        }
//        return count;
//    }

    public int minNumberOfFrogs(String croakOfFrogs) {
        int frogCount = 0;
        List<Queue<Integer>> frogRequirements = new ArrayList<>();
        frogRequirements.add(new LinkedList<>()); // for frogs requiring 'c' - who have completed one croak atleast
        frogRequirements.add(new LinkedList<>()); // for frogs requiring 'r' - who have last spelled out 'c'
        frogRequirements.add(new LinkedList<>()); // for frogs requiring 'o' - who have last spelled out 'r'
        frogRequirements.add(new LinkedList<>()); // for frogs requiring 'a' - who have last spelled out 'o'
        frogRequirements.add(new LinkedList<>()); // for frogs requiring 'k' - who have last spelled out 'a'
        for (char c : croakOfFrogs.toCharArray()) {
            if (c == 'c') {
                // check if there is an existing frog who has completed one croak. If yes, assign this 'c' to it, otherwise create a new frog
                Queue<Integer> frogsRequiringC = frogRequirements.get(0);
                if(!frogsRequiringC.isEmpty()) {
                    frogRequirements.get(1).add(frogsRequiringC.remove());
                } else {
                    frogRequirements.get(1).add(++frogCount); //add new frog
                }
            } else if (c == 'r') {
                Queue<Integer> frogsRequiringR = frogRequirements.get(1);
                if(!frogsRequiringR.isEmpty()) {
                    frogRequirements.get(2).add(frogsRequiringR.remove());
                } else {
                    return -1;
                }
            } else if (c == 'o') {
                Queue<Integer> frogsRequiringO = frogRequirements.get(2);
                if(!frogsRequiringO.isEmpty()) {
                    frogRequirements.get(3).add(frogsRequiringO.remove());
                } else {
                    return -1;
                }
            } else if (c == 'a') {
                Queue<Integer> frogsRequiringA = frogRequirements.get(3);
                if(!frogsRequiringA.isEmpty()) {
                    frogRequirements.get(4).add(frogsRequiringA.remove());
                } else {
                    return -1;
                }
            } else if (c == 'k') {
                Queue<Integer> frogsRequiringK = frogRequirements.get(4);
                if(!frogsRequiringK.isEmpty()) {
                    frogRequirements.get(0).add(frogsRequiringK.remove());
                } else {
                    return -1;
                }
            }
        }
        for (int i = 1; i <= 4; i++) {
            // no frogs should be in the middle of speaking croak (i.e. still expecting a letter other than 'c')
            if (frogRequirements.get(i).size() > 0) {
                return -1;
            }
        }
        return frogCount;
    }

    public static void main(String[] args) {
        MinNumberOfFrogsCroaking mnofc = new MinNumberOfFrogsCroaking();
        Verifier.verifyEquals(mnofc.minNumberOfFrogs("croakcroak"), 1);
        Verifier.verifyEquals(mnofc.minNumberOfFrogs("crcoakroak"), 2);
        Verifier.verifyEquals(mnofc.minNumberOfFrogs("croakcrook"), -1);
        Verifier.verifyEquals(mnofc.minNumberOfFrogs("croakcroa"), -1);
        Verifier.verifyEquals(mnofc.minNumberOfFrogs("crcroakcroakoak"), 2);
        Verifier.verifyEquals(mnofc.minNumberOfFrogs("oakrc"), -1);
    }

}
