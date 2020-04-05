package others.leetc.contests.oneeighty.three;

//https://leetcode.com/contest/weekly-contest-183/problems/longest-happy-string/
public class LongestHappyString {

    private String repeat(String s, int t) {
        String ans = "";
        for (int i = 0; i < t; i++)
            ans += s;
        return ans;
    }

    /*
     Tip: to simplify the logic, preprocess to assume a >= b >= c.
     Intuition: (assuming the proprocessing above) always try to add 'aa'. If, after this, a >= b, add b. Repeat recursivelly for the remaining counts.
     https://leetcode.com/problems/longest-happy-string/discuss/564277/C%2B%2BJava-a-greater-b-greater-c
     */
    private String generate(int a, int b, int c, String A, String B, String C) {
        if (a < b)
            return generate(b, a, c, B, A, C);
        if (b < c)
            return generate(a, c, b, A, C, B);
        if (b == 0)
            return repeat(A, Math.min(2, a));
        int use_a = Math.min(2, a), use_b = a - use_a >= b ? 1 : 0;
        return repeat(A, use_a) + repeat(B, use_b) +
                generate(a - use_a, b - use_b, c, A, B, C);
    }

    public String longestDiverseString(int a, int b, int c) {
        return generate(a, b, c, "a", "b", "c");
    }

}
