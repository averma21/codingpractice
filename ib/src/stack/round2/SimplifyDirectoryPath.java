package stack.round2;

import util.Verifier;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyDirectoryPath {

    private void takeAction(String curFolder, Deque<String> stack) {
        if (curFolder.length() > 0) {
            if ("..".equals(curFolder)) {
                stack.pollLast();
            } else if (!".".equals(curFolder)){
                stack.offerLast(curFolder);
            }
        }
    }

    String simplify(String A) {
        Deque<String> stack = new ArrayDeque<>();
        String curFolder = "";
        if (A == null || "/".equals(A)) {
            return A;
        }
        for (int i = 0; i < A.length(); i++) {
            char ci = A.charAt(i);
            if (ci == '/') {
                if (curFolder.length() > 0) {
                    takeAction(curFolder, stack);
                }
                curFolder = "";
            } else {
                curFolder += ci;
            }
        }
        takeAction(curFolder, stack);
        StringBuilder path = new StringBuilder("/");
        while (!stack.isEmpty()) {
            path.append(stack.removeFirst()).append("/");
        }
        return path.length() > 1 ? path.deleteCharAt(path.length() - 1).toString() : path.toString();
    }

    public static void main(String[] args) {
        SimplifyDirectoryPath sdp = new SimplifyDirectoryPath();
        Verifier.verifyEquals(sdp.simplify("/"), "/");
        Verifier.verifyEquals(sdp.simplify("/a"), "/a");
        Verifier.verifyEquals(sdp.simplify("/abc"), "/abc");
        Verifier.verifyEquals(sdp.simplify("/a/"), "/a");
        Verifier.verifyEquals(sdp.simplify("/ab/c/./d/"), "/ab/c/d");
        Verifier.verifyEquals(sdp.simplify("/ab/cd/ef/../.././e"), "/ab/e");
        Verifier.verifyEquals(sdp.simplify("/a/./b/../../../c/"), "/c");
        Verifier.verifyEquals(sdp.simplify("/../"), "/");
    }

}
