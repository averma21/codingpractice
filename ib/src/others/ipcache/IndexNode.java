package others.ipcache;

import java.util.Map;
import java.util.TreeMap;

public class IndexNode {

    final short nodeNumber;
    final TreeMap<Short, String> cityMap;
    final TreeMap<Short, IndexNode> children;
    final boolean isLeaf;
    private static final String START_PLACE_PREFIX = "S-";
    private static final String END_PLACE_PREFIX = "E-";

    public IndexNode(short nodeNumber, boolean isLeaf) {
        this.nodeNumber = nodeNumber;
        cityMap = isLeaf ? new TreeMap<>() : null;
        children = isLeaf ? null : new TreeMap<>();
        this.isLeaf = isLeaf;
    }

    public IndexNode getChild(short val) {
        return this.children.get(val);
    }

    public IndexNode getLastChild() {
        return this.children.lastEntry().getValue();
    }

    public IndexNode getChildFloored(short val) {
        Map.Entry<Short, IndexNode> floorEntry = this.children.floorEntry(val);
        return floorEntry != null ? floorEntry.getValue() : null;
    }

    public void insertChild(IndexNode child) {
        this.children.put(child.nodeNumber, child);
    }

    public void addPlaceStartOrEnd(String place, short block, boolean isStart) {
        this.cityMap.put(block, isStart ? START_PLACE_PREFIX + place : END_PLACE_PREFIX + place);
    }

    public String getPlaceFloored(short block) {
        Map.Entry <Short, String> floorEntry = this.cityMap.floorEntry(block);
        return floorEntry != null ? floorEntry.getValue() : "";
    }

    public String getPlace(short block) {
        return this.cityMap.get(block);
    }

    public String getLastPlace() {
        return this.cityMap.lastEntry().getValue();
    }

    public static boolean isStart(String s) {
        return s.startsWith(START_PLACE_PREFIX);
    }

    public static String extractPlaceName(String s) {
        return s.substring(isStart(s) ? START_PLACE_PREFIX.length() : END_PLACE_PREFIX.length());
    }
}
