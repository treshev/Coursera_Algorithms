import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET
{
    private final SET<Point2D> pointSET = new SET<>();

    public PointSET()                               // construct an empty set of points
    {
    }

    public boolean isEmpty()                      // is the set empty?
    {
        return pointSET.isEmpty();
    }

    public int size()                         // number of points in the set
    {
        return pointSET.size();
    }

    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if (p == null) throw new IllegalArgumentException();
        pointSET.add(p);
    }

    public boolean contains(Point2D p)            // does the set contain point p?
    {
        if (p == null) throw new IllegalArgumentException();
        return pointSET.contains(p);
    }

    public void draw()                         // draw all points to standard draw
    {
        for (Point2D point : pointSET)
        {
            StdDraw.point(point.x(), point.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    {
        if (rect == null) throw new IllegalArgumentException();

        SET<Point2D> pointsInRectangle = new SET<>();
        for (Point2D point2D : pointSET)
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

        Point2D neighbor = null;
        double minimumDistance = 10;
        double distance;

        for (Point2D point2D : pointSET)
        {
            distance = p.distanceSquaredTo(point2D);
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
        System.out.println("Hi");
    }
}