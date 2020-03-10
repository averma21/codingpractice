package others.leetc.strings;

import util.Verifier;

import java.math.BigInteger;

public class ClosestPalindrome {

    String left;
    String mid;

    String reversed(String s) {
        StringBuilder s1 = new StringBuilder(s);
        return s1.reverse().toString();
    }

    private void setVars(String s) {
        int length = s.length();
        int half = length/2;
        left = s.substring(0, half);
        mid = length % 2 == 0 ? "" : s.substring(half, half + 1);
    }

    BigInteger getMirrorPalindrome() {
        String p1 = left + mid + reversed(left);
        return new BigInteger(p1);
    }

    BigInteger getSmallerPalindrome() {
        String l1s = left + mid;
        long l1 = Long.parseLong(l1s);
        l1--;
        String reduced = "" + l1;
        if (reduced.length() == l1s.length())
            return mid.length() == 0 ? new BigInteger(reduced + reversed(reduced)) :
                    new BigInteger(reduced + reversed(reduced.substring(0, reduced.length() - 1)));
        //reduced already contains the mid part so we remove it before reversing and appending
        //eg in case of 12345, reduced would have been 123 - 1 = 122. So we return 12221
        return mid.length() == 0 ? new BigInteger(reduced + "9" + reversed(reduced)) : new BigInteger(reduced + reversed(reduced));
    }

    BigInteger getBiggerPalindrome() {
        String l1s = left + mid;
        long l1 = Long.parseLong(l1s);
        l1++;
        String increased = "" + l1;
        if (increased.length() == l1s.length()) {
            return mid.length() == 0 ? new BigInteger(increased + reversed(increased)) :
                    new BigInteger(increased + reversed(increased.substring(0, increased.length() - 1)));
        }
        return mid.length() == 0 ? new BigInteger(increased + reversed(increased.substring(0, increased.length() - 1))) :
                new BigInteger(increased + reversed(increased.substring(0, increased.length() - 2)));
    }

    public String nearestPalindromic(String n) {
        int length = n.length();
        if (length == 0)
            return n;
        if (length == 1)
            return "" + (Integer.parseInt(n) - 1);
        if (length == 2) {
            int d = Integer.parseInt(n);
            if (d == 10 || d == 11)
                return "9";
        }
        setVars(n);
        BigInteger orig = new BigInteger(n);
        BigInteger mirrored = getMirrorPalindrome();
        BigInteger smaller = getSmallerPalindrome();
        BigInteger bigger = getBiggerPalindrome();
        BigInteger origMirrDist = orig.subtract(mirrored).abs();
        BigInteger bigOrigDist = bigger.subtract(orig);
        BigInteger smallOrigDist = orig.subtract(smaller);
        if (orig.compareTo(mirrored) > 0) {
            if (bigOrigDist.compareTo(origMirrDist) < 0) {
                return bigger.toString();
            }
            return mirrored.toString();
        } else if (orig.compareTo(mirrored) < 0) {
            if (smallOrigDist.compareTo(origMirrDist) <= 0) {
                return smaller.toString();
            }
            return mirrored.toString();
        } else {
            if (bigOrigDist.compareTo(smallOrigDist) < 0)
                return bigger.toString();
            return smaller.toString();
        }
    }

    public static void main(String[] args) {
        ClosestPalindrome cp = new ClosestPalindrome();
        Verifier.verifyEquals(cp.nearestPalindromic(""), "");
        Verifier.verifyEquals(cp.nearestPalindromic("1"), "0");
        Verifier.verifyEquals(cp.nearestPalindromic("10"), "9");
        Verifier.verifyEquals(cp.nearestPalindromic("11"), "9");
        Verifier.verifyEquals(cp.nearestPalindromic("12"), "11");
        Verifier.verifyEquals(cp.nearestPalindromic("19"), "22");
        Verifier.verifyEquals(cp.nearestPalindromic("18"), "22");
        Verifier.verifyEquals(cp.nearestPalindromic("20"), "22");
        Verifier.verifyEquals(cp.nearestPalindromic("100"), "99");
        Verifier.verifyEquals(cp.nearestPalindromic("111"), "101");
        Verifier.verifyEquals(cp.nearestPalindromic("101"), "99");
        Verifier.verifyEquals(cp.nearestPalindromic("121"), "111");
        Verifier.verifyEquals(cp.nearestPalindromic("122"), "121");
        Verifier.verifyEquals(cp.nearestPalindromic("129"), "131");
        Verifier.verifyEquals(cp.nearestPalindromic("99"), "101");
        Verifier.verifyEquals(cp.nearestPalindromic("999"), "1001");
        Verifier.verifyEquals(cp.nearestPalindromic("1000"), "999");
        Verifier.verifyEquals(cp.nearestPalindromic("1001"), "999");
    }

}
