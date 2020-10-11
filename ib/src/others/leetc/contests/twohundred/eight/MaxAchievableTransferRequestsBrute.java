package others.leetc.contests.twohundred.eight;

import util.Verifier;

import java.util.Arrays;

public class MaxAchievableTransferRequestsBrute {

    private int [] entropy;
    private boolean [] choices;
    private int maxRequests;
    private int[][] requests;

    private boolean isAcceptable() {
        return Arrays.stream(entropy).allMatch(x -> x==0);
    }

    private void acceptRequest(int requestNo) {
        entropy[requests[requestNo][0]]--;
        entropy[requests[requestNo][1]]++;
        choices[requestNo] = true;
        //System.out.print("Accepting " + requestNo + "->");
        //printEntropy();
    }

    private void unAcceptRequest(int requestNo) {
        entropy[requests[requestNo][0]]++;
        entropy[requests[requestNo][1]]--;
        choices[requestNo] = false;
//        System.out.print("Rejecting " + requestNo + "->");
//        printEntropy();
    }

    private void printEntropy() {
        for (int e : entropy) {
            System.out.print(e + ", ");
        }
        System.out.println("");
    }

    private void calc(int requestNo) {
        if (requestNo == requests.length - 1) {
            acceptRequest(requestNo);
            if (isAcceptable()) {
                maxRequests = Math.max(maxRequests, getCurrentSatisfiedRequests());
            }
            unAcceptRequest(requestNo);
            if (isAcceptable()) {
                maxRequests = Math.max(maxRequests, getCurrentSatisfiedRequests());
            }
        } else {
            acceptRequest(requestNo);
            calc(requestNo+1);
            unAcceptRequest(requestNo);
            calc(requestNo+1);
        }
    }

    private int getCurrentSatisfiedRequests() {
        int c = 0;
        for (boolean choice : choices) {
            if (choice) c++;
           // System.out.print(choice + ", ");
        }
        //System.out.println("   count = " + c);
        return c;
    }

    public int maximumRequests(int n, int[][] requests) {
        this.entropy = new int[n];
        this.maxRequests = 0;
        this.requests = requests;
        this.choices = new boolean[requests.length];
        calc(0);
        return maxRequests;
    }

    public static void main(String[] args) {
        MaxAchievableTransferRequestsBrute matrb = new MaxAchievableTransferRequestsBrute();
        Verifier.verifyEquals(matrb.maximumRequests(5, new int[][]{{0,1},{1,0},{0,1},{1,2},{2,0},{3,4}}), 5);
        Verifier.verifyEquals(matrb.maximumRequests(3, new int[][]{{0,0},{1,2},{2,1}}), 3);
        Verifier.verifyEquals(matrb.maximumRequests(4, new int[][]{{0,3},{3,1},{1,2},{2,0}}), 4);
        Verifier.verifyEquals(matrb.maximumRequests(4, new int[][]{{1,2},{1,2},{2,2},{0,2},{2,1},{1,1},{1,2}}), 4);
        Verifier.verifyEquals(matrb.maximumRequests(3, new int[][]{{1,2},{0,0},{0,2},{0,1},{0,0},{0,2},{1,0},{0,1},{2,0}}), 7);
    }

}
