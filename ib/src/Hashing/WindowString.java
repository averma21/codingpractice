package Hashing;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class WindowString {

    static boolean contains(Map<Character, Integer> a, Map<Character, Integer> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> entry : a.entrySet()) {
            if (entry.getValue() < b.get(entry.getKey()))
                return false;
        }
        return true;
    }

    static String minWindow(String A, String B) {
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < B.length(); i++) {
            tMap.computeIfPresent(B.charAt(i), (k,v) -> {return v+1;});
            tMap.putIfAbsent(B.charAt(i), 1);
        }
        int start = -1;
        int minLength = Integer.MAX_VALUE;
        int ansStart = -1;
        for (int i = 0; i < A.length(); i++) {
            char si = A.charAt(i);
            if (tMap.containsKey(si)) {
                if (start == -1)
                    start = i;
                sMap.computeIfPresent(si, (k,v) -> {return v+1;});
                sMap.putIfAbsent(si, 1);
                char toRemove = A.charAt(start);
                if (sMap.get(toRemove) > tMap.get(toRemove)) {
                    do {
                        start++;
                        sMap.computeIfPresent(toRemove, (k, v) -> {
                            return v - 1;
                        });
                        sMap.remove(toRemove, 0);
                        toRemove = A.charAt(start);
                    } while (start <=i && (!tMap.containsKey(toRemove) || sMap.get(toRemove) > tMap.get(toRemove)));
                }
                if (contains(sMap, tMap)) {
                    int length = i - start + 1;
                    if (length < minLength) {
                        minLength = length;
                        ansStart = start;
                    }
                }
            }
        }
        if (ansStart != -1)
            return A.substring(ansStart, ansStart + minLength);
        return "";
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(minWindow("ADOBECODEBANC", "ABC"), "BANC");
        Verifier.verifyEquals(minWindow("ADOBECODEBANC", "ABCD"), "ADOBEC");
        Verifier.verifyEquals(minWindow("ADOBECODEBANC", "ABCDXZ"), "");
        Verifier.verifyEquals(minWindow("xiEjBOGeHIMIlslpQIZ6jERaAVoHUc9Hrjlv7pQpUSY8oHqXoQYWWll8Pumov89wXDe0Qx6bEjsNJQAQ0A6K21Z0BrmM96FWEdRG69M9CYtdBOrDjzVGPf83UdP3kc4gK0uHVKcPN4HPdccm9Qd2VfmmOwYCYeva6BSG6NGqTt1aQw9BbkNsgAjvYzkVJPOYCnz7U4hBeGpcJkrnlTgNIGnluj6L6zPqKo5Ui75tC0jMojhEAlyFqDs7WMCG3dmSyVoan5tXI5uq1IxhAYZvRQVHtuHae0xxwCbRh6S7fCLKfXeSFITfKHnLdT65K36vGC7qOEyyT0Sm3Gwl2iXYSN2ELIoITfGW888GXaUNebAr3fnkuR6VwjcsPTldQSiohMkkps0MH1cBedtaKNoFm5HbH15kKok6aYEVsb6wOH2w096OwEyvtDBTQwoLN87JriLwgCBBavbOAiLwkGGySk8nO8dLHuUhk9q7f0rIjXCsQeAZ1dfFHvVLupPYekXzxtWHd84dARvv4Z5L1Z6j8ur2IXWWbum8lCi7aErEcq41WTo8dRlRykyLRSQxVH70rUTz81oJS3OuZwpI1ifBAmNXoTfznG2MXkLtFu4SMYC0bPHNctW7g5kZRwjSBKnGihTY6BQYItRwLUINApd1qZ8W4yVG9tnjx4WyKcDhK7Ieih7yNl68Qb4nXoQl079Vza3SZoKeWphKef1PedfQ6Hw2rv3DpfmtSkulxpSkd9ee8uTyTvyFlh9G1Xh8tNF8viKgsiuCZgLKva32fNfkvW7TJC654Wmz7tPMIske3RXgBdpPJd5BPpMpPGymdfIw53hnYBNg8NdWAImY3otYHjbl1rqiNQSHVPMbDDvDpwy01sKpEkcZ7R4SLCazPClvrx5oDyYolubdYKcvqtlcyks3UWm2z7kh4SHeiCPKerh83bX0m5xevbTqM2cXC9WxJLrS8q7XF1nh",
                "dO4BRDaT1wd0YBhH88Afu7CI4fwAyXM8pGoGNsO1n8MFMRB7qugS9EPhCauVzj7h"), "8oHqXoQYWWll8Pumov89wXDe0Qx6bEjsNJQAQ0A6K21Z0BrmM96FWEdRG69M9CYtdBOrDjzVGPf83UdP3kc4gK0uHVKcPN4HPdccm9Qd2VfmmOwYCYeva6BSG6NGqTt1aQw9BbkNsgAjvYzkVJPOYCnz7U4hBeGpcJkrnlTgNIGnluj6L6zPqKo5Ui75tC0jMojhEAlyFqDs7WMCG3dmSyVoan5tXI5uq1IxhAYZvR");
    }

}
