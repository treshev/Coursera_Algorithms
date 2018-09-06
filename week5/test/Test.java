import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdRandom;

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

        for (int i = 0; i < n; i++)
        {
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);
//            StdOut.printf("%8.2f %8.2f\n", x, y);

            brute.insert(new Point2D(x, y));
            kdTree.insert(new Point2D(x, y));
        }

        System.out.println("Filling done with N = " + n);

//        ArrayList<Point2D> points = new ArrayList<>();
//        points.add(new Point2D(0.7, 0.2));
//        points.add(new Point2D(0.5, 0.4));
//        points.add(new Point2D(0.2, 0.3));
//        points.add(new Point2D(0.4, 0.7));
//        points.add(new Point2D(0.9, 0.6));


//        for (Point2D point : points)
//        {
//            kdTree.insert(point);
//        }

        System.out.println();
        System.out.println();

        long start = new Date().getTime();
        brute.range(new RectHV(0, 0, 1, 1));
        System.out.println("Brute range time: " + (new Date().getTime() - start));

        start = new Date().getTime();
        System.out.println("Brute nearest point: " + brute.nearest(new Point2D(0.5, 0.5)));
        System.out.println("Brute nearest time: " + (new Date().getTime() - start));

        System.out.println();
        System.out.println();

        start = new Date().getTime();
        kdTree.range(new RectHV(0.2, 0.2, 0.3, 0.3));
        System.out.println("kdTree range time: " + (new Date().getTime() - start));

        start = new Date().getTime();
        System.out.println("kdTree nearest point: " + kdTree.nearest(new Point2D(0.5, 0.5)));
        System.out.println("kdTree nearest time: " + (new Date().getTime() - start));

//        // draw the points
//        StdDraw.clear();
//        kdTree.draw();
//        StdDraw.show();
    }
}
