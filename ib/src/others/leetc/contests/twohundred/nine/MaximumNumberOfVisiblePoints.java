package others.leetc.contests.twohundred.nine;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/contest/weekly-contest-209/problems/maximum-number-of-visible-points/
// solution using sliding window
//https://leetcode.com/problems/maximum-number-of-visible-points/discuss/887097/Java-Math-%2B-Sliding-Window
public class MaximumNumberOfVisiblePoints {

    public static final double ERROR = 1e-10;

    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> angles = new ArrayList<>();
        int same = 0;
        for (List<Integer> point : points) {
            double dx = point.get(0) - location.get(0);
            double dy = point.get(1) - location.get(1);
            double radian = 0;
            if (dx == 0) {
                if (dy == 0) {
                    same++;
                    continue;
                }
                else if (dy > 0) {
                    radian = Math.PI / 2.0;
                }
                else {
                    radian = 3.0 * Math.PI / 2.0;
                }
            }
            else {
                radian = Math.atan(dy / dx);
                if (dx < 0) { // We need to add 180 degrees for the second and third quadrants.
                    radian += Math.PI;
                }
                else if (dx > 0 && dy < 0) { // We need to add 360 degrees for the fourth quadrants.
                    radian += Math.PI * 2.0;
                }
            }
            angles.add(radian);
        }
        Collections.sort(angles);
        int n = angles.size();
        int res = 0;
        for (int i = 0; i < n; i++) {
            angles.add(angles.get(i) + Math.PI * 2); // For the optimal that may exist in both the first and fourth quadrants.
        }
        int j = 0;
        double view = ((double)angle) * Math.PI / 180.0  + ERROR;
        for (int i = 0; i < angles.size(); i++) {
            while (j < angles.size() && angles.get(j) - angles.get(i) <= view) {
                j++;
            }
            res = Math.max(res, j - i);
        }
        res += same;
        return res;
    }

    private List<List<Integer>> fromArray(int [][] arr) {
        List<List<Integer>> list = new ArrayList<>(arr.length);
        for (int [] p : arr) {
            list.add(new ArrayList<Integer>() {{
                add(p[0]);
                add(p[1]);
            }});
        }
        return list;
    }

    public static void main(String[] args) {
        MaximumNumberOfVisiblePoints mnvp = new MaximumNumberOfVisiblePoints();
        Verifier.verifyEquals(mnvp.visiblePoints(Creator.getList(Creator.getList(2,1),
                Creator.getList(2,2),
                Creator.getList(3,3)), 90, Creator.getList(1,1)), 3);
        Verifier.verifyEquals(mnvp.visiblePoints(Creator.getList(Creator.getList(2,1),
                Creator.getList(2,2),
                Creator.getList(3,4),
                Creator.getList(1,1)), 90, Creator.getList(1,1)), 4);
        Verifier.verifyEquals(mnvp.visiblePoints(Creator.getList(Creator.getList(1,0),
                Creator.getList(2,1)), 13, Creator.getList(1,1)), 1);
        Verifier.verifyEquals(mnvp.visiblePoints(Creator.getList(Creator.getList(1,1),
                Creator.getList(2,2),
                Creator.getList(1,2),
                Creator.getList(2,1)), 0, Creator.getList(1,1)), 2);
        Verifier.verifyEquals(mnvp.visiblePoints(mnvp.fromArray(new int[][]{{673648153,788793266},{812553910,829107321},
                {562844327,521976767},{695280855,29162561},{553807953,876072613},{243000458,647162898},{772234285,290257609},
                {103667421,244622378},{978409303,40172399},{291256097,732953826},{396058829,803288361},{509929097,947931206},
                {630228714,178451455},{511534046,361537974},{403211023,621721535},{347953873,660963267},{429833379,411318713},
                {394331091,999309563},{373115960,658138440},{494978699,566605693},{951582130,869030454},{836619118,57683411},{195853444,993138748},
                {133699350,235334518},{109421553,529705542},{837642897,229897873},{384114697,552096579},{24497401,903872844},{692676763,501763030},
                {340977815,208394801},{94905515,13970927},{845032273,339031792},{409895856,880313851},{725779700,880650516},
                {621492830,222798085}}), 15, Creator.getList(561408099,110226317)), 11);
    }

}
