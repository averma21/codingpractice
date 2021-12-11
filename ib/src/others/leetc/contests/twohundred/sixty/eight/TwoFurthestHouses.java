package others.leetc.contests.twohundred.sixty.eight;

public class TwoFurthestHouses {

    public int maxDistance(int[] colors) {
        int max = 0;
        int n = colors.length;
        for (int i = n - 1; i > 0; i--) {
            if (colors[i] != colors[0]) {
                max = i;
                break;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            if (colors[i] != colors[n - 1]) {
                max = Math.max(max, n-1-i);
            }
        }
        return max;
    }

}
