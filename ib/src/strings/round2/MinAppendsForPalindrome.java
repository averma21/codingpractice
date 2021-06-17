package strings.round2;

import util.Verifier;

import static strings.round2.MinCharPalindrome.getFailureArray;

public class MinAppendsForPalindrome {

    int find(String A) {
        int [] arr = getFailureArray(new StringBuilder(A).reverse().toString() + "$" + A);
        return A.length() - arr[arr.length - 1];
    }

    public static void main(String[] args) {
        MinAppendsForPalindrome mafp = new MinAppendsForPalindrome();
        Verifier.verifyEquals(mafp.find("abede"), 2);
        Verifier.verifyEquals(mafp.find("aabb"), 2);
    }

}
