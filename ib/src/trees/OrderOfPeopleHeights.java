package trees;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given the following :
 *
 * A positive number N
 * Heights : A list of heights of N persons standing in a queue
 * Infronts : A list of numbers corresponding to each person (P) that gives the number of persons who are taller than P and standing in front of P
 * You need to return list of actual order of persons’s height
 *
 * Consider that heights will be unique
 *
 * Example
 *
 * Input :
 * Heights: 5 3 2 6 1 4
 * InFronts: 0 1 2 0 3 2
 * Output :
 * actual order is: 5 3 2 1 6 4
 * So, you can see that for the person with height 5, there is no one taller than him who is in front of him, and hence Infronts has 0 for him.
 *
 * For person with height 3, there is 1 person ( Height : 5 ) in front of him who is taller than him.
 *
 * You can do similar inference for other people in the list.
 *
 * https://www.interviewbit.com/problems/order-of-people-heights/
 *
 * Solution Analysis:
 *
 * Note that the position of  the smallest element in heights array will remain same as corresponding InFronts value.
 * Once position of smallest element is fixed , the position of next smallest can be fixed in correspondence to the inFronts  value.
 *
 * We can start arranging peoples from smaller to higher heights. As we can see people with smallest height(index i) will come at
 * index InFront[i] ( considering 0 based indexing) because all the people infront of it will have larger heights .
 * Same we can do for all.
 */
public class OrderOfPeopleHeights {

    private static class Info implements  Comparable<Info> {
        final int height, infront;

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
        List<Info> infos = new ArrayList<>(heights.size());
        List<Integer> ans = new ArrayList<>(heights.size());
        for (int i = 0; i < heights.size(); i++) {
            infos.add(new Info(heights.get(i), infronts.get(i)));
            ans.add(-1);
        }
        Collections.sort(infos);
        for (Info info : infos) {
            int freeSpaceBefore = 0;
            boolean found = false;
            for (int i = 0; i < ans.size(); i++) {
                if (ans.get(i) == -1 || ans.get(i) == info.height) {
                    if (freeSpaceBefore == info.infront) {
                        ans.set(i, info.height);
                        found = true;
                        break;
                    }
                    freeSpaceBefore++;
                }
            }
            if (!found)
                return null;
        }
        return ans;
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
