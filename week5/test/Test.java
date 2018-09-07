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

        points.add(new Point2D(0.5, 0.875));
        points.add(new Point2D(0.375, 0.375));
        points.add(new Point2D(0.625, 0.625));
        points.add(new Point2D(0.375, 0.625));
        points.add(new Point2D(0.625, 0.375));
        points.add(new Point2D(0.375, 0.75));
        points.add(new Point2D(1.0, 0.625));
        points.add(new Point2D(0.5, 0.0));
        points.add(new Point2D(0.25, 0.75));
        points.add(new Point2D(0.875, 0.5));
        points.add(new Point2D(0.875, 0.25));
        points.add(new Point2D(0.75, 0.625));
        points.add(new Point2D(0.5, 0.375));
        points.add(new Point2D(0.625, 0.125));
        points.add(new Point2D(0.0, 0.75));
        points.add(new Point2D(0.125, 0.875));
        points.add(new Point2D(0.0, 0.375));
        points.add(new Point2D(1.0, 0.375));
        points.add(new Point2D(0.875, 0.875));
        points.add(new Point2D(0.125, 1.0));


        for (Point2D point : points)
        {
            brute.insert(point);
            kdTree.insert(point);
        }

        System.out.println();
        System.out.println();

        RectHV rect = new RectHV(0.25, 0.0, 0.625, 0.875);
        System.out.println("Rect = " + rect);

        long start = new Date().getTime();
        System.out.println("Brute range = " + brute.range(rect));
        System.out.println("Brute range time: " + (new Date().getTime() - start));

        start = new Date().getTime();
        System.out.println("Brute nearest point: " + brute.nearest(new Point2D(0.5, 0.5)));
        System.out.println("Brute nearest time: " + (new Date().getTime() - start));

        System.out.println();
        System.out.println();

        start = new Date().getTime();
        System.out.println("kdTree ranage = " + kdTree.range(rect));
        System.out.println("kdTree range time: " + (new Date().getTime() - start));

        start = new Date().getTime();
        System.out.println("kdTree nearest point: " + kdTree.nearest(new Point2D(0.5, 0.5)));
        System.out.println("kdTree nearest time: " + (new Date().getTime() - start));

//        // draw the points
        StdDraw.clear();
        kdTree.draw();
        brute.draw();

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.01);

        StdDraw.rectangle(rect.xmin(), rect.ymin(), rect.xmax(), rect.ymax());
        StdDraw.show();
    }
}
