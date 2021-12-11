package others.leetc.contests.biweekly.sixty.six;

import util.Verifier;

public class MinBuckets {

    public int minimumBuckets(String street) {
        char [] chars = street.toCharArray();

        int ans = 0;
        int n = chars.length;
        if (chars.length < 1) {
            return -1;
        }
        if (chars.length == 1) {
            return chars[0] == '.' ? 0 : -1;
        }
        for (int i = 1; i < n-1; i++) {
            char c = chars[i];
            if (c != '.') {
                continue;
            }
            char c1 = chars[i-1];
            char c2 = chars[i+1];
            if (c1 == 'H' && c2 == 'H') {
                ans++;
                chars[i] = 'X';
                chars[i-1] = 'X';
                chars[i+1] = 'X';
            }
        }
        for (int i = 0; i < n; i++) {
            if (chars[i] == 'H') {
                if (i > 0 && chars[i-1] == '.' || i < n - 1 && chars[i+1] == '.') {
                    ans++;
                } else {
                    return -1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MinBuckets mb = new MinBuckets();
        Verifier.verifyEquals(mb.minimumBuckets("H..H"), 2);
        Verifier.verifyEquals(mb.minimumBuckets(".H.H"), 1);
        Verifier.verifyEquals(mb.minimumBuckets(".HHH."), -1);
        Verifier.verifyEquals(mb.minimumBuckets("H"), -1);
        Verifier.verifyEquals(mb.minimumBuckets("."), 0);
        Verifier.verifyEquals(mb.minimumBuckets(".H.H.H"), 2);
        Verifier.verifyEquals(mb.minimumBuckets(".H.H.H.H"), 2);
    }

}
