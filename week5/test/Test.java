import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.Date;

public class Test
{
    public static void main(String[] args)                  // unit testing of the methods (optional)
    {
        KdTree kdTree = new KdTree();
        PointSET brute = new PointSET();

        System.out.println("Size = " + kdTree.size());
        System.out.println("Is empty = " + kdTree.isEmpty());

        int n = 5_000_000;

        if (args.length>0)
        {
            n = Integer.parseInt(args[0]);
        }

//        for (int i = 0; i < n; i++)
//        {
//            double x = StdRandom.uniform(0.0, 1.0);
//            double y = StdRandom.uniform(0.0, 1.0);
//
//            brute.insert(new Point2D(x, y));
//            kdTree.insert(new Point2D(x, y));
//        }

        System.out.println("Filling done with N = " + n);

        ArrayList<Point2D> points = new ArrayList<>();
//        points.add(new Point2D(0.7, 0.2));
//        points.add(new Point2D(0.5, 0.4));
//        points.add(new Point2D(0.2, 0.3));
//        points.add(new Point2D(0.4, 0.7));
//        points.add(new Point2D(0.9, 0.6));

//        points.add(new Point2D(0.5, 0.875));
//        points.add(new Point2D(0.375, 0.375));
//        points.add(new Point2D(0.625, 0.625));
//        points.add(new Point2D(0.375, 0.625));
//        points.add(new Point2D(0.625, 0.375));
//        points.add(new Point2D(0.375, 0.75));
//        points.add(new Point2D(1.0, 0.625));
//        points.add(new Point2D(0.5, 0.0));
//        points.add(new Point2D(0.25, 0.75));
//        points.add(new Point2D(0.875, 0.5));
//        points.add(new Point2D(0.875, 0.25));
//        points.add(new Point2D(0.75, 0.625));
//        points.add(new Point2D(0.5, 0.375));
//        points.add(new Point2D(0.625, 0.125));
//        points.add(new Point2D(0.0, 0.75));
//        points.add(new Point2D(0.125, 0.875));
//        points.add(new Point2D(0.0, 0.375));
//        points.add(new Point2D(1.0, 0.375));
//        points.add(new Point2D(0.875, 0.875));
//        points.add(new Point2D(0.125, 1.0));


        points.add(new Point2D(0.1875, 0.21875));
        points.add(new Point2D(0.21875, 0.6875));
        points.add(new Point2D(0.875, 0.46875));
        points.add(new Point2D(0.53125, 1.0));
        points.add(new Point2D(0.28125, 0.75));
        points.add(new Point2D(0.71875, 0.1875));
        points.add(new Point2D(0.78125, 0.9375));
        points.add(new Point2D(0.5625, 0.4375));
        points.add(new Point2D(0.5, 0.0625));
        points.add(new Point2D(0.8125, 0.03125));
        points.add(new Point2D(0.25, 0.3125));
        points.add(new Point2D(0.6875, 0.84375));
        points.add(new Point2D(1.0, 0.875));
        points.add(new Point2D(0.09375, 0.0));
        points.add(new Point2D(0.3125, 0.15625));
        points.add(new Point2D(0.4375, 0.90625));
        points.add(new Point2D(0.9375, 0.09375));
        points.add(new Point2D(0.96875, 0.71875));
        points.add(new Point2D(0.40625, 0.34375));
        points.add(new Point2D(0.375, 0.40625));

        for (Point2D point : points)
        {
            brute.insert(point);
            kdTree.insert(point);
        }

        System.out.println();
        System.out.println();

        RectHV rect = new RectHV(0.25, 0.0, 0.625, 0.875);
        Point2D testPoint = new Point2D(0.0625, 0.375);
        System.out.println("Rect = " + rect);

        System.out.println("Brute isEmpty = " + brute.isEmpty());
        System.out.println("Brute size = " + brute.size());
        System.out.println("Brute contains = " + brute.contains(testPoint));

        long start = new Date().getTime();
        System.out.println("Brute range = " + brute.range(rect));
        System.out.println("Brute range time: " + (new Date().getTime() - start));

        start = new Date().getTime();
        System.out.println("Brute nearest point: " + brute.nearest(testPoint));
        System.out.println("Brute nearest time: " + (new Date().getTime() - start));

        System.out.println();
        System.out.println();

        System.out.println("kdTree isEmpty = " + kdTree.isEmpty());
        System.out.println("kdTree size = " + kdTree.size());
        System.out.println("kdTree contains = " + kdTree.contains(testPoint));

        start = new Date().getTime();
        System.out.println("kdTree ranage = " + kdTree.range(rect));
        System.out.println("kdTree range time: " + (new Date().getTime() - start));

        start = new Date().getTime();
        System.out.println("kdTree nearest point: " + kdTree.nearest(testPoint));
        System.out.println("VISITED NODES: " + kdTree.visitedNodes);
        System.out.println();
        kdTree.visitedNodes = new ArrayList();
        System.out.println("kdTree nearest point: " + kdTree.nearest2(testPoint));
        System.out.println("VISITED NODES: " + kdTree.visitedNodes);

        System.out.println("kdTree nearest time: " + (new Date().getTime() - start));

//        // draw the points
        StdDraw.clear();
        kdTree.draw();
//        brute.draw();

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(testPoint.x(), testPoint.y());
//        StdDraw.rectangle(rect.xmin(), rect.ymin(), rect.xmax(), rect.ymax());

        StdDraw.show();
    }
}
