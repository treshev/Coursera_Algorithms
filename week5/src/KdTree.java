import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree
{
    private Node root;
    private int size;
    private Node champion;

    private static class Node
    {
        Point2D p;      // the point
        RectHV rect;    // the axis-aligned rectangle corresponding to this node
        Node lb;        // the left/bottom subtree
        Node rt;        // the right/top subtree
        boolean isVertical = false;
        char index = 'A';

        Node(Point2D p)
        {
            this.p = p;
            this.lb = null;
            this.rt = null;
        }
    }

    public KdTree()                               // construct an empty set of points
    {
        size = 0;
    }

    public boolean isEmpty()                      // is the set empty?
    {
        return root == null;
    }

    public int size()                         // number of points in the set
    {
        return this.size;
    }

    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if (p == null)
        {
            throw new IllegalArgumentException();
        }
        if (contains(p)) return;

        Node newNode = new Node(p);
        int charValue = (int) newNode.index;
        charValue += this.size;
        newNode.index = (char) charValue;
        this.size++;

        if (root == null)
        {
            newNode.rect = new RectHV(newNode.p.x(), 0, newNode.p.x(), 1);
            root = newNode;
            root.isVertical = true;
        }
        else
        {
            Node current = root;
            while (true)
            {
                if (current.isVertical)
                {
                    if (p.x() < current.p.x())
                    {
                        if (current.lb == null)
                        {
                            newNode.rect = new RectHV(0, newNode.p.y(), current.p.x(), newNode.p.y());
                            current.lb = newNode;
                            break;
                        }
                        current = current.lb;
                    }
                    else
                    {
                        if (current.rt == null)
                        {
                            newNode.rect = new RectHV(current.p.x(), newNode.p.y(), 1, newNode.p.y());
                            current.rt = newNode;
                            break;
                        }
                        current = current.rt;

                    }
                }
                else
                {
                    if (p.y() < current.p.y())
                    {
                        if (current.lb == null)
                        {
                            newNode.rect = new RectHV(newNode.p.x(), 0, newNode.p.x(), current.p.y());
                            current.lb = newNode;
                            newNode.isVertical = true;
                            break;
                        }
                        current = current.lb;
                    }
                    else
                    {
                        if (current.rt == null)
                        {
                            newNode.rect = new RectHV(newNode.p.x(), current.p.y(), newNode.p.x(), 1);
                            current.rt = newNode;
                            newNode.isVertical = true;
                            break;
                        }
                        current = current.rt;
                    }
                }
            }
        }
    }

    public boolean contains(Point2D point)            // does the set contain point p?
    {
        if (point == null)
        {
            throw new IllegalArgumentException();
        }

        Node current = root;
        while (current != null)
        {
            if (current.p.equals(point)) return true;

            if (current.isVertical)
            {
                if (point.x() < current.p.x())
                {
                    current = current.lb;
                }
                else
                {
                    current = current.rt;
                }
            }
            else
            {
                if (point.y() < current.p.y())
                {
                    current = current.lb;
                }
                else
                {
                    current = current.rt;
                }
            }
        }
        return false;
    }

    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    {
        if (rect == null)
        {
            throw new IllegalArgumentException();
        }

        ArrayList<Point2D> points = new ArrayList<>();
        findInRent(rect, points, root);

        return points;
    }

    private void findInRent(RectHV rect, ArrayList<Point2D> points, Node current)
    {
        if (current == null) return;

        if (rect.contains(current.p))
        {
            points.add(current.p);
        }

        if (current.isVertical)
        {
            if (rect.xmax() < current.p.x() || rect.xmin() < current.p.x())
            {
                findInRent(rect, points, current.lb);
            }

            if (rect.xmax() >= current.p.x() || rect.xmin() >= current.p.x())
            {
                findInRent(rect, points, current.rt);
            }
        }
        else
        {
            if (rect.ymax() < current.p.y() || rect.ymin() < current.p.y())
            {
                findInRent(rect, points, current.lb);
            }

            if (rect.ymax() >= current.p.y() || rect.ymin() >= current.p.y())
            {
                findInRent(rect, points, current.rt);
            }
        }
    }

    public Point2D nearest(Point2D point)             // a nearest neighbor in the set to point p; null if the set is empty
    {
        if (point == null)
        {
            throw new IllegalArgumentException();
        }
        champion = root;
        findNearest(point, root);

        if (champion != null)
        {
            return champion.p;
        }
        return null;
    }

    public Point2D nearest2(Point2D point)             // a nearest neighbor in the set to point p; null if the set is empty
    {
        if (point == null)
        {
            throw new IllegalArgumentException();
        }
        champion = root;
        findNearest2(point, root);

        if (champion != null)
        {
            return champion.p;
        }
        return null;
    }

    public ArrayList visitedNodes = new ArrayList();

    private void findNearest(Point2D point, Node current)
    {
        if (current == null) return;
        visitedNodes.add(current.index);
        if (point.distanceTo(current.p) < point.distanceTo(champion.p)) champion = current;

        if (current.isVertical)
        {
            if (point.x() < current.p.x())
            {
                findNearest(point, current.lb);
                if (point.distanceTo(champion.p) > point.distanceTo(new Point2D(current.p.x(), point.y())))
                {
                    findNearest(point, current.rt);
                }
            }
            else
            {
                findNearest(point, current.rt);

                if (point.distanceTo(champion.p) > point.distanceTo(new Point2D(current.p.x(), point.y())))
                {
                    findNearest(point, current.lb);
                }
            }
        }
        else //horisont
        {
            if (point.y() < current.p.y())
            {
                findNearest(point, current.lb);

                if (point.distanceTo(champion.p) > point.distanceTo(new Point2D(point.x(), current.p.y())))
                {
                    findNearest(point, current.rt);
                }
            }
            else
            {
                findNearest(point, current.rt);

                if (point.distanceTo(champion.p) > point.distanceTo(new Point2D(point.x(), current.p.y())))
                {
                    findNearest(point, current.lb);
                }
            }
        }
    }


    private void findNearest2(Point2D point, Node current)
    {
        if (current == null) return;
        visitedNodes.add(current.index);
        if (point.distanceSquaredTo(current.p) < point.distanceSquaredTo(champion.p)) champion = current;

        if (current.isVertical)
        {
            if (point.x() < current.p.x())
            {
                findNearest2(point, current.lb);

                if (point.distanceSquaredTo(champion.p) > current.rect.distanceSquaredTo(point))
                {
                    findNearest2(point, current.rt);
                }
            }
            else
            {
                findNearest2(point, current.rt);

                if (point.distanceSquaredTo(champion.p) > current.rect.distanceSquaredTo(point))
                {
                    findNearest2(point, current.lb);
                }
            }
        }
        else
        {
            if (point.y() < current.p.y())
            {
                findNearest(point, current.lb);

                if (point.distanceSquaredTo(champion.p) > current.rect.distanceSquaredTo(point))
                {
                    findNearest2(point, current.rt);
                }
            }
            else
            {
                findNearest(point, current.rt);

                if (point.distanceSquaredTo(champion.p) > current.rect.distanceSquaredTo(point))
                {
                    findNearest2(point, current.lb);
                }
            }
        }
    }

    public void draw()                         // draw all points to standard draw
    {
        if (!isEmpty())
        {
            drawNode(root);
        }
    }

    private void drawNode(Node node)
    {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.02);
        StdDraw.point(node.p.x(), node.p.y());
        StdDraw.text(node.p.x(), node.p.y(), node.index + ":" + String.valueOf(node.p.x()).substring(0, 3) + ", " + String.valueOf(node.p.y()).substring(0, 3));
        if (node.isVertical)
        {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.005);
            StdDraw.line(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
        }
        else
        {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius(0.005);
            StdDraw.line(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
        }

        if (node.lb != null)
        {
            drawNode(node.lb);
        }
        if (node.rt != null)
        {
            drawNode(node.rt);
        }
    }

    public static void main(String[] args)                  // unit testing of the methods (optional)
    {
        System.out.println("Hi");
    }
}
