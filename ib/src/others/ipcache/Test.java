package others.ipcache;

import util.Verifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//Some test data can be obtained from - https://lite.ip2location.com/database-ip-country
public class Test {

    private static long convertIPStringToLong(String ipString) {
        String [] parts = ipString.split("\\.");
        StringBuilder s = new StringBuilder();
        for (String part : parts) {
            Integer partI = new Integer(part);
            String converted = Integer.toBinaryString(partI);
            int zerosToPrepend = IPCacheIndex.IP_BLOCK_LENGTH - converted.length();
            while (zerosToPrepend > 0) {
                s.append("0");
                zerosToPrepend--;
            }
            s.append(converted);
        }
        return Long.parseLong(s.toString(), 2);
    }

    private static void verifyFromDatafile(int limit) throws IOException {
        File file = new File("/home/amrit/Downloads/IP2LOCATION-LITE-DB1.CSV/copy.csv");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int count = 0;
        IPCacheIndex index = new IPCacheIndex(32);
        while ((st = br.readLine()) != null) {
            String [] parts = st.split(",");
            if (parts[3].equals("-")) {
                continue;
            }
            index.addIPRange(parts[3].substring(1, parts[3].length() - 1),
                    Long.parseLong(parts[0].substring(1, parts[0].length() - 1)),
                    Long.parseLong(parts[1].substring(1, parts[1].length() - 1)));
            count++;
            if (count > limit) {
                break;
            }
        }
        Verifier.verifyEquals(index.findPlaceByIP(16909055), "China");
        Verifier.verifyEquals(index.findPlaceByIP(17039360), "Australia");
        Verifier.verifyEquals(index.findPlaceByIP(34606592), "United Kingdom of Great Britain and Northern Ireland");
        Verifier.verifyEquals(index.findPlaceByIP(34606600), "United Kingdom of Great Britain and Northern Ireland");
    }

    private static void verifySelfTestCases() {
        IPCacheIndex index = new IPCacheIndex(24);
        String [][] data = new String[][] {
                {"A", "50.50.30", "50.150.20"},
                {"B", "50.200.30", "150.100.50"},
                {"C", "150.150.80", "150.200.70"}
        };
        String [][] testResults = new String[][] {
                {"50.50.30", "A"}, {"150.100.50", "B"}, {"50.40.100", ""},
                {"50.50.40", "A"}, {"50.100.20", "A"}, {"200.100.100", ""},
                {"100.60.40", "B"}, {"150.120.30", ""}, {"40.100.100", ""},
                {"50.50.20", ""}, {"50.150.30", ""}
        };
        for (String [] entry : data) {
            index.addIPRange(entry[0], convertIPStringToLong(entry[1]), convertIPStringToLong(entry[2]));
        }
        for (String [] test : testResults) {
            String ans = index.findPlaceByIP(convertIPStringToLong(test[0]));
            try {
                Verifier.verifyEquals(ans, test[1]);
            } catch (Exception e) {
                System.out.printf("ERROR - Failing case {%s}={%s} got answer={%s}\n", test[0], test[1], ans);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        verifyFromDatafile(Integer.MAX_VALUE);
        verifySelfTestCases();
    }

}
