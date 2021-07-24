package Hashing.round2;

import util.Verifier;

import java.util.HashMap;
import java.util.Map;

public class WindowString {

    private boolean isSurplus(Map<Character, Integer> countMap, Map<Character, Integer> patternMap) {
        if (countMap.size() != patternMap.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> entry : patternMap.entrySet()) {
            if (countMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public String minWindow(String A, String B) {
        Map<Character, Integer> patternMap = new HashMap<>();
        if (A == null || A.isEmpty() || B == null || B.isEmpty()) {
            return "";
        }
        for (int i = 0; i < B.length(); i++) {
            char c = B.charAt(i);
            patternMap.compute(c, (k,v) -> v == null ? 1 : v+1);
        }
        int ws = 0, we = 0;
        int minLength = Integer.MAX_VALUE;
        Map<Character, Integer> countMap = new HashMap<>();
        int i1 = -1, j1 = -1;
        while (we < A.length()) {
            char c = A.charAt(we);
            if (patternMap.containsKey(c)) {
                countMap.compute(c, (k,v) -> v == null ? 1 : v+1);
                if (isSurplus(countMap, patternMap)) {
                    while (ws <= we && (!patternMap.containsKey(A.charAt(ws)) || (countMap.get(A.charAt(ws)) - 1 >= patternMap.get(A.charAt(ws))))) {
                        countMap.computeIfPresent(A.charAt(ws), (k,v) -> v-1);
                        ws++;
                    }
                    if (we - ws + 1 < minLength) {
                        i1 = ws;
                        j1 = we;
                        minLength = we - ws + 1;
                    }
                }
            }
            we++;
        }
        return i1 == -1 ? "" : A.substring(i1, j1+1);
    }

    public static void main(String[] args) {
        WindowString ws = new WindowString();
        Verifier.verifyEquals(ws.minWindow("ADOBECODEBANC", "ABC"), "BANC");
        Verifier.verifyEquals(ws.minWindow("ADOBECODEBANC", "ABCD"), "ADOBEC");
        Verifier.verifyEquals(ws.minWindow("ADOBECODEBANC", "ABCDXZ"), "");
        Verifier.verifyEquals(ws.minWindow("xiEjBOGeHIMIlslpQIZ6jERaAVoHUc9Hrjlv7pQpUSY8oHqXoQYWWll8Pumov89wXDe0Qx6bEjsNJQAQ0A6K21Z0BrmM96FWEdRG69M9CYtdBOrDjzVGPf83UdP3kc4gK0uHVKcPN4HPdccm9Qd2VfmmOwYCYeva6BSG6NGqTt1aQw9BbkNsgAjvYzkVJPOYCnz7U4hBeGpcJkrnlTgNIGnluj6L6zPqKo5Ui75tC0jMojhEAlyFqDs7WMCG3dmSyVoan5tXI5uq1IxhAYZvRQVHtuHae0xxwCbRh6S7fCLKfXeSFITfKHnLdT65K36vGC7qOEyyT0Sm3Gwl2iXYSN2ELIoITfGW888GXaUNebAr3fnkuR6VwjcsPTldQSiohMkkps0MH1cBedtaKNoFm5HbH15kKok6aYEVsb6wOH2w096OwEyvtDBTQwoLN87JriLwgCBBavbOAiLwkGGySk8nO8dLHuUhk9q7f0rIjXCsQeAZ1dfFHvVLupPYekXzxtWHd84dARvv4Z5L1Z6j8ur2IXWWbum8lCi7aErEcq41WTo8dRlRykyLRSQxVH70rUTz81oJS3OuZwpI1ifBAmNXoTfznG2MXkLtFu4SMYC0bPHNctW7g5kZRwjSBKnGihTY6BQYItRwLUINApd1qZ8W4yVG9tnjx4WyKcDhK7Ieih7yNl68Qb4nXoQl079Vza3SZoKeWphKef1PedfQ6Hw2rv3DpfmtSkulxpSkd9ee8uTyTvyFlh9G1Xh8tNF8viKgsiuCZgLKva32fNfkvW7TJC654Wmz7tPMIske3RXgBdpPJd5BPpMpPGymdfIw53hnYBNg8NdWAImY3otYHjbl1rqiNQSHVPMbDDvDpwy01sKpEkcZ7R4SLCazPClvrx5oDyYolubdYKcvqtlcyks3UWm2z7kh4SHeiCPKerh83bX0m5xevbTqM2cXC9WxJLrS8q7XF1nh",
                "dO4BRDaT1wd0YBhH88Afu7CI4fwAyXM8pGoGNsO1n8MFMRB7qugS9EPhCauVzj7h"), "8oHqXoQYWWll8Pumov89wXDe0Qx6bEjsNJQAQ0A6K21Z0BrmM96FWEdRG69M9CYtdBOrDjzVGPf83UdP3kc4gK0uHVKcPN4HPdccm9Qd2VfmmOwYCYeva6BSG6NGqTt1aQw9BbkNsgAjvYzkVJPOYCnz7U4hBeGpcJkrnlTgNIGnluj6L6zPqKo5Ui75tC0jMojhEAlyFqDs7WMCG3dmSyVoan5tXI5uq1IxhAYZvR");

    }

}
