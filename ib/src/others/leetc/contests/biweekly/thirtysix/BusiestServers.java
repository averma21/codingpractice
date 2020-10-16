package others.leetc.contests.biweekly.thirtysix;

import util.Creator;
import util.Verifier;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;


//https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/
public class BusiestServers {
      // didn't get accepted....TLE
//    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
//        Map<Integer, Integer> assignment = new HashMap<>();
//        Map<Integer, Integer> reqCount = new HashMap<>();
//        List<Integer> answer = new LinkedList<>();
//        int maxRequests = 0;
//        for (int i = 0; i < arrival.length; i++) {
//            int preferredServer = i % k;
//            for (int j = 0, s = preferredServer; j < k; s = (s+1)%k, j++) {
//                int val = assignment.getOrDefault(s, -1);
//                if (val == -1 || val <= arrival[i]) {
//                    assignment.put(s, arrival[i] + load[i]);
//                    int req = reqCount.getOrDefault(s, 0) + 1;
//                    reqCount.put(s, req);
//                    if (req > maxRequests) {
//                        maxRequests = req;
//                        answer.clear();
//                        answer.add(s);
//                    } else if (req == maxRequests) {
//                        answer.add(s);
//                    }
//                    break;
//                }
//            }
//        }
//        return answer;
//    }

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        PriorityQueue<int []> busyServers = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        TreeSet<Integer> freeServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            freeServers.add(i);
        }
        List<Integer> answer = new LinkedList<>();
        Map<Integer, Integer> reqCounts = new HashMap<>();
        int maxRequests = 0;
        for (int i = 0; i < arrival.length; i++) {
            int curTime = arrival[i];
            while (!busyServers.isEmpty() && busyServers.element()[1] <= curTime) {
                int [] entry = busyServers.remove();
                freeServers.add(entry[0]);
            }
            if (freeServers.isEmpty()) {
                continue;
            }
            Integer free = freeServers.ceiling(i%k);
            if (free == null) {
                free = freeServers.first();
            }
            freeServers.remove(free);
            busyServers.add(new int[] {free, arrival[i] + load[i]});
            int req = reqCounts.getOrDefault(free, 0) + 1;
            reqCounts.put(free, req);
            if (req > maxRequests) {
                maxRequests = req;
                answer.clear();
                answer.add(free);
            } else if (req == maxRequests) {
                answer.add(free);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        BusiestServers bs = new BusiestServers();
        Verifier.verifyEquals(bs.busiestServers(3, new int[] {1,2,3,4,5}, new int[] {5,2,3,3,3}), Creator.getList(1));
        Verifier.verifyEquals(bs.busiestServers(3, new int[] {1,2,3,4}, new int[] {1,2,1,2}), Creator.getList(0));
        Verifier.verifyEquals(bs.busiestServers(3, new int[] {1,2,3}, new int[] {10,12,11}), Creator.getList(0,1,2));
        Verifier.verifyEquals(bs.busiestServers(3, new int[] {1,2,3,4,8,9,10}, new int[] {5,2,10,3,1,2,2}), Creator.getList(1));
        Verifier.verifyEquals(bs.busiestServers(1, new int[] {1}, new int[] {1}), Creator.getList(0));
    }

}
