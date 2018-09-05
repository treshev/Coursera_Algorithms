import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.HashSet;

public class PointSET
{
    private final HashSet<Point2D> pointSet = new HashSet<>();

    public PointSET()                               // construct an empty set of points
    {
    }

    public boolean isEmpty()                      // is the set empty?
    {
        return pointSet.size() == 0;
    }

    public int size()                         // number of points in the set
    {
        return pointSet.size();
    }

    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if (p == null) throw new IllegalArgumentException();
        pointSet.add(p);
    }

    public boolean contains(Point2D p)            // does the set contain point p?
    {
        if (p == null) throw new IllegalArgumentException();
        return pointSet.contains(p);
    }

    public void draw()                         // draw all points to standard draw
    {
        for (Point2D point2D : pointSet)
        {
            StdDraw.point(point2D.x(), point2D.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    {
        if (rect == null) throw new IllegalArgumentException();

        HashSet<Point2D> pointsInRectangle = new HashSet<>();
        for (Point2D point2D : pointSet)
        {
            if (rect.contains(point2D))
            {
                pointsInRectangle.add(point2D);
            }
        }
        return pointsInRectangle;
    }

    public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
    {
        if (p == null) throw new IllegalArgumentException();
        Point2D neighbor = p;
        double minimumDistance = 100000;

        for (Point2D point2D : pointSet)
        {
            double distance = p.distanceTo(point2D);
            if (distance < minimumDistance)
            {
                minimumDistance = distance;
                neighbor = point2D;
            }
        }
        return neighbor;
    }

    public static void main(String[] args)                  // unit testing of the methods (optional)
    {
        //test
    }
}