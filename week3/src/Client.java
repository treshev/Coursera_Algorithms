import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Date;


public class Client {
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.01);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();



        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius();
        // print and draw the line segments
        Date  start = new Date();

        FastCollinearPoints collinear = new FastCollinearPoints(points);
        Date end = new Date();
        System.out.println("Time: " + (end.getTime()-start.getTime())/1000);
        System.out.println("Segments = "+collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
            segment.draw();
        }
//
//        BruteCollinearPoints collinearB = new BruteCollinearPoints(points);
//        System.out.println("Segments = "+collinearB.numberOfSegments());
//        for (LineSegment segment : collinearB.segments()) {
//            StdOut.println(segment);
//            segment.draw();
//        }
//
        StdDraw.show();
    }
}
