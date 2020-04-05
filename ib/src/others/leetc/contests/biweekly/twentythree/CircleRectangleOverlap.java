package others.leetc.contests.biweekly.twentythree;

import util.Verifier;

public class CircleRectangleOverlap {

    private static double getDist(int x1, int y1, int x2, int y2) {
        int xDist = Math.abs(x1-x2);
        int yDist = Math.abs(y1-y2);
        return Math.sqrt(xDist * xDist + yDist * yDist);
    }

    public static boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        int xDist = Math.min(Math.abs(x1 - x_center), Math.abs(x2 - x_center));
        int yDist = Math.min(Math.abs(y1 - y_center), Math.abs(y2 - y_center));
        boolean inBetweenY = y1 <= y_center && y_center <= y2;
        boolean inBetweenX = x1 <= x_center && x_center <= x2;
        boolean isCircleInside = inBetweenX && inBetweenY;
        boolean isRectInside = x_center - radius <= x1 && x2 <= x_center + radius && y_center - radius <= y1 && y2 <= y_center + radius;
        boolean isCornerInside = getDist(x_center, y_center, x1, y1) < radius
                || getDist(x_center, y_center, x1, y2) < radius
                || getDist(x_center, y_center, x2, y1) < radius
                || getDist(x_center, y_center, x2, y2) < radius;
        return xDist <= radius && inBetweenY || yDist <= radius && inBetweenX || isCircleInside || isRectInside || isCornerInside;
    }

    public static void main(String[] args) {
        Verifier.verifyEquals(checkOverlap(24, 13, 1, 0, 15, 5, 18), true);
        Verifier.verifyEquals(checkOverlap(1415,
                807,
                -784,
                -733,
                623,
                -533,
                1005), false);
        Verifier.verifyEquals(checkOverlap(27,
                0,
                19,
                22,
                8,
                28,
                18), true);
    }
}
