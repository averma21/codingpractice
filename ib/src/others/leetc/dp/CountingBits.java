package others.leetc.dp;

public class CountingBits {

    public int[] countBits(int num) {
        int []  ans = new int[num + 1];
        if (num >= 0)
            ans[0] = 0;
        if (num >= 1)
            ans[1] = 1;
        if (num >= 2)
            ans[2] = 1;
        if (num >= 3)
            ans[3] = 2;
        int start = 0, xorFlag = 8-1;
        for (int i = 4; i <= num; i++, start++) {
            ans[i] = ans[start] + 1;
            if ((i^xorFlag) == 0) {
                start = -1;
                xorFlag = (xorFlag+1)*2 - 1;
            }
        }
        return ans;
    }

}
