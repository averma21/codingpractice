package DP;

import util.Creator;
import util.Verifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreak {


    static int[] dp = null;

    public static int process(String a, List<String> b, int index){

        System.out.println("Process called for " + a + " " + index);

        if(index==a.length()){
            return 1;
        }
        if(index>a.length()){
            return 0;
        }

        if(dp[index] != -1){
            return dp[index];
        }

        for(String str : b){

            if(index+str.length() <= a.length()){

                 String sub = a.substring(index,index+str.length());

                if(sub.equals(str)){
                   if(process(a,b,index + str.length())==1){
                       dp[index] = 1;
                       return 1;
                   }
                }

            }

        }
        dp[index] = 0;
        return 0;
    }

    public int wordBreak(String A, List<String> B) {
        dp = new int[A.length()+1];

        if(B.isEmpty() || A.length()==0){
            return 0;
        }
        Arrays.fill(dp,-1);

        int res = process(A, B,0);

        return res;
    }



//    private int [] dp;
//
//    private int checkInternal(String A, Set<String> B, int startIndex) {
//        System.out.println("Called for " + A + " " + startIndex);
//        if (startIndex == A.length())
//            return 1;
//        if (dp[startIndex] != -1)
//            return dp[startIndex];
//        StringBuilder prefix = new StringBuilder();
//        int answer = 0;
//        for (int i = startIndex; i < A.length(); i++) {
//            prefix.append(A.charAt(i));
//            if (B.contains(prefix.toString())) {
//                int further = checkInternal(A, B, i+1);
//                if (further == 1) {
//                    answer = 1;
//                    break;
//                }
//            }
//        }
//        dp[startIndex]=  answer;
//        return answer;
//    }
//
//    public int wordBreak(String A, List<String> B) {
//        dp = new int[A.length() + 1];
//        Arrays.fill(dp, -1);
//        return checkInternal(A, new HashSet<>(B), 0);
//    }

    public static void main(String[] args) {
        WordBreak breaker = new WordBreak();
        Verifier.verifyEquals(breaker.wordBreak("catsanddog", Creator.getList("cat", "cats", "and", "sand", "dog")), 1);
        Verifier.verifyEquals(breaker.wordBreak("pineapplepenapple", Creator.getList("apple", "pen", "applepen", "pine", "pineapple")), 1);
        Verifier.verifyEquals(breaker.wordBreak("pineapplepenzs", Creator.getList("apple", "pen", "applepen", "pine", "pineapple")), 0);
        Verifier.verifyEquals(breaker.wordBreak("a", Creator.getList("a")), 1);
        Verifier.verifyEquals(breaker.wordBreak("aa", Creator.getList("a", "aa")), 1);
        Verifier.verifyEquals(breaker.wordBreak("aba", Creator.getList("a", "aa")),0);
        Verifier.verifyEquals(breaker.wordBreak("a", Creator.getList("aa")),0);
    }
}
