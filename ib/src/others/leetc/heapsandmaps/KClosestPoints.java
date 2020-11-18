package others.leetc.heapsandmaps;

import util.Verifier;

import java.util.PriorityQueue;

public class KClosestPoints {

    private static class Point {
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double distanceFromOrigin() {
            return Math.sqrt(x*x + y*y);
        }
    }

    PriorityQueue<Point> queue;

    public int[][] kClosest(int[][] points, int K) {
        queue = new PriorityQueue<Point>((p1,p2) ->
             Double.compare(p2.distanceFromOrigin(), p1.distanceFromOrigin()) //need reverse order so comparing in opposite way
        );
        for (int i = 0; i < K; i++) {
            queue.add(new Point(points[i][0], points[i][1]));
        }
        for (int i = K; i < points.length; i++) {
            Point farthestPointTillNow = queue.peek();
            int [] p = points[i];
            if (Double.compare(Math.sqrt(p[0]*p[0] + p[1]*p[1]), farthestPointTillNow.distanceFromOrigin()) >= 0) {
                continue;
            }
            queue.remove();
            queue.add(new Point(p[0], p[1]));
        }
        int [][] ans = new int[K][2];
        int i = 0;
        for (Point p : queue) {
            ans[i][0] = p.x;
            ans[i][1] = p.y;
            i++;
        }
        return ans;
    }

    public static void main(String[] args) {
        KClosestPoints kcp = new KClosestPoints();
        Verifier.verifyArrayEqualsUnordered(kcp.kClosest(new int[][] {
                {1,3}, {-2,2}
        } , 1), new int[][] {{-2,2}});
        Verifier.verifyArrayEqualsUnordered(kcp.kClosest(new int[][] {
                {3,3}, {5,-1}, {-2,4}
        } , 2), new int[][] {
                {3,3}, {-2,4}
        });
        Verifier.verifyArrayEqualsUnordered(kcp.kClosest(new int[][] {
                {3,3}, {5,-1}, {-2,4}
        } , 3), new int[][] {
                {3,3}, {5,-1}, {-2,4}
        });
        Verifier.verifyArrayEqualsUnordered(kcp.kClosest(new int[][] {
                {3,3}, {3,3}, {3,3}
        } , 2), new int[][] {
                {3,3}, {3,3}
        });
        Verifier.verifyArrayEqualsUnordered(kcp.kClosest(new int[][] {
                {3,3}, {3,3}, {1,2}
        } , 2), new int[][] {
                {3,3}, {1,2}
        });
    }
}
