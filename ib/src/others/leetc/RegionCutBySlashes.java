package others.leetc;

import util.Verifier;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/regions-cut-by-slashes/

/**
 * Too complicated solution. See {@link RegionCutBySlashesDFS} for a time efficient solution based on DFS.
 *
 * Split each square into two parts and assigning regions to both of them. Merging regions when possible.
 * Assigning same regions to adjacent parts depending on geometry.
 *
 * While working on a square we look the the regions of the parts of the top and left square. Merge (or use same) regions when possible.
 *
 */
public class RegionCutBySlashes {

    int regionCount;
    /**
     * Contains the region assigned to each small square. Each small square is split into two parts (even if it is emptu we assume two parts and assign same region to both).
     * Third dimension would have size 2. 0 indicates top(or left) part in case of / (or \) and 1 indicates bottom (or right) part.
     */
    int[][][] mappedRegions;
    List<Set<Integer>> regions;

    private void createNewRegion(int i, int j, int a) {
        regionCount++;
        mappedRegions[i][j][a] = regionCount;
        Set<Integer> newSet = new HashSet<>();
        newSet.add(regionCount);
        regions.add(newSet);
    }

    private void mergeRegions(int r1, int r2) {
        Set<Integer> r1Set = new HashSet<>(), r2Set = Collections.emptySet();
        int index = 0, r2Index = -1;
        for (Set<Integer> set : regions) {
            if (set.contains(r1) && set.contains(r2)) {
                return;
            }
            if (set.contains(r1)) {
                r1Set = set;
            }
            if (set.contains(r2)) {
                r2Set = set;
                r2Index = index;
            }
            index++;
        }
        r1Set.addAll(r2Set);
        regions.remove(r2Index);
    }

    public int regionsBySlashes(String[] grid) {
        regionCount = 0;
        int m = grid.length;
        mappedRegions = new int[m][m][2];
        regions = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            String currentGrid = grid[i];
            for (int j = 0; j < m; j++) {
                char c = currentGrid.charAt(j);
                if (i == 0 && j == 0) {
                    createNewRegion(0, 0, 0);
                    if (c != ' ') {
                        createNewRegion(0, 0, 1);
                    } else {
                        mappedRegions[0][0][1] = regionCount; //=1 at this point
                    }
                    continue;
                }
                if (c == '/') {
                    int leftRegion = -1, topRegion = -1;
                    if (i > 0) {
                        char atTop = grid[i-1].charAt(j);
                        topRegion = mappedRegions[i-1][j][atTop == '/' ? 1 : 0];
                    }
                    if (j > 0) {
                        leftRegion = mappedRegions[i][j-1][1];
                    }
                    mappedRegions[i][j][0] = leftRegion > 0 ? leftRegion : topRegion;
                    createNewRegion(i, j, 1);
                    if (i > 0 && j > 0) {
                        mergeRegions(leftRegion, topRegion);
                    }
                } else if (c == '\\') {
                    int leftRegion = -1, topRegion = -1;
                    if (i > 0) {
                        char atTop = grid[i-1].charAt(j);
                        topRegion = mappedRegions[i-1][j][atTop == '/' ? 1 : 0];
                        mappedRegions[i][j][1] = topRegion;
                        if (j == 0)
                        createNewRegion(i, j, 0);
                    }
                    if (j > 0) {
                        leftRegion = mappedRegions[i][j-1][1];
                        mappedRegions[i][j][0] = leftRegion;
                        if (i == 0)
                        createNewRegion(i, j, 1);
                    }
                } else {
                    int leftRegion = -1, topRegion = -1;
                    if (i > 0) {
                        char atTop = grid[i-1].charAt(j);
                        topRegion = mappedRegions[i-1][j][atTop == '/' ? 1 : 0];
                    }
                    if (j > 0) {
                        leftRegion = mappedRegions[i][j-1][1];
                    }
                    mappedRegions[i][j][0] = leftRegion > 0 ? leftRegion : topRegion;
                    mappedRegions[i][j][1] = mappedRegions[i][j][0];
                    if (i > 0 && j > 0) {
                        mergeRegions(leftRegion, topRegion);
                    }
                }
            }
        }
        return regions.size();
    }

    public static void main(String[] args) {
        RegionCutBySlashes rcbs = new RegionCutBySlashes();
        Verifier.verifyEquals(rcbs.regionsBySlashes(new String[] {" /","/ "}), 2);
        Verifier.verifyEquals(rcbs.regionsBySlashes(new String[] {" /",
                "  "}), 1);
        Verifier.verifyEquals(rcbs.regionsBySlashes(new String[] {"\\/",
                "/\\"}), 4);
        Verifier.verifyEquals(rcbs.regionsBySlashes(new String[] {"/\\",
                "\\/"}), 5);
        Verifier.verifyEquals(rcbs.regionsBySlashes(new String[] {"//",
                "/ "}), 3);
        Verifier.verifyEquals(rcbs.regionsBySlashes(new String[] {" /\\"," \\/","\\  "}), 4);
    }

}
