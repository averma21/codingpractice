package others.ipcache;

public class IPCacheIndex {

    final IndexNode root;
    final int ipLength;
    static final int IP_BLOCK_LENGTH = 8;

    public IPCacheIndex(int ipLength) {
        this.root = new IndexNode((short) -1, false);
        this.ipLength = ipLength;
    }

    private IndexNode insertIP(String ip24MSB) {
        int startIndex = 0;
        IndexNode cur = root;
        while (startIndex < ip24MSB.length()) {
            String section = ip24MSB.substring(startIndex, startIndex + IP_BLOCK_LENGTH);
            short ipSec = Short.parseShort(section, 2);
            IndexNode child = cur.getChild(ipSec);
            if (child == null) {
                child = new IndexNode(ipSec, startIndex + IP_BLOCK_LENGTH >= ip24MSB.length());
                cur.insertChild(child);
            }
            cur = child;
            startIndex += IP_BLOCK_LENGTH;
        }
        return cur;
    }

    private static String getIPString(long ip, int ipLength) {
        String ipString = Long.toBinaryString(ip);
        int zerosToAppend = ipLength - ipString.length();
        StringBuilder stringBuilder = new StringBuilder("");
        while (zerosToAppend > 0) {
            stringBuilder.append("0");
            zerosToAppend--;
        }
        stringBuilder.append(ipString);
        return stringBuilder.toString();
    }

    public void addIPRange(String country, long startIP, long endIP) {
        String startIPString = getIPString(startIP, ipLength);
        String endIPString = getIPString(endIP, ipLength);
        int firstPartLength = ipLength - IP_BLOCK_LENGTH;
        IndexNode startIPThirdLevelNode = insertIP(startIPString.substring(0, firstPartLength));
        IndexNode endIPThirdLevelNode = insertIP(endIPString.substring(0, firstPartLength));
        startIPThirdLevelNode.addPlaceStartOrEnd(country, Short.parseShort(startIPString.substring(firstPartLength), 2), true);
        endIPThirdLevelNode.addPlaceStartOrEnd(country, Short.parseShort(endIPString.substring(firstPartLength), 2), false);
    }

    public String findPlaceByIP(long ip) {
        String ipString = getIPString(ip, ipLength);
        int startIndex = 0, sectionSize = 8;
        IndexNode cur = root;
        int firstPartLength = ipLength - IP_BLOCK_LENGTH;
        String ip24MSB = ipString.substring(0, firstPartLength);
        boolean findRightMost = false;
        while (startIndex < ip24MSB.length()) {
            String section = ip24MSB.substring(startIndex, startIndex + sectionSize);
            IndexNode child;
            if (!findRightMost) {
                short ipSec = Short.parseShort(section, 2);
                child = cur.getChild(ipSec);
                if (child == null) {
                    child = cur.getChildFloored(ipSec);
                    if (child == null) {
                        return "";
                    }
                    findRightMost = true;
                }
            } else {
                child = cur.getLastChild();
            }
            cur = child;
            startIndex += sectionSize;
        }
        short lastSection = Short.parseShort(ipString.substring(firstPartLength), 2);
        String place = !findRightMost ? cur.getPlaceFloored(lastSection) : cur.getLastPlace();
        if (place.isEmpty()) {
            return "";
        }
        if (IndexNode.isStart(place)) {
            return IndexNode.extractPlaceName(place);
        }
        if (place.equals(cur.getPlace(lastSection))) {
            return IndexNode.extractPlaceName(place);
        }
        return "";
    }
}

