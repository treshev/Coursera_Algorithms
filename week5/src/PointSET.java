import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET
{
    private final RedBlackBST<Integer, Point2D> pointBST = new RedBlackBST<>();

    public PointSET()                               // construct an empty set of points
    {
    }

    public boolean isEmpty()                      // is the set empty?
    {
        return pointBST.isEmpty();
    }

    public int size()                         // number of points in the set
    {
        return pointBST.size();
    }

    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if (p == null) throw new IllegalArgumentException();
        pointBST.put(p.hashCode(), p);
    }

    public boolean contains(Point2D p)            // does the set contain point p?
    {
        if (p == null) throw new IllegalArgumentException();
        return pointBST.contains(p.hashCode());
    }

    public void draw()                         // draw all points to standard draw
    {
        for (int key : pointBST.keys())
        {
            Point2D point = pointBST.get(key);
            StdDraw.point(point.x(), point.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    {
        if (rect == null) throw new IllegalArgumentException();

        SET<Point2D> pointsInRectangle = new SET<>();
        for (int key : pointBST.keys())
        {
            Point2D point2D = pointBST.get(key);
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
        double minimumDistance = 10;
        double distance;

        for (int key : pointBST.keys())
        {
            Point2D point2D = pointBST.get(key);
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