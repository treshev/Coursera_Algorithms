import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class BruteCollinearPoints {

    private LineSegment[] segments = null;

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null) throw new java.lang.IllegalArgumentException();

        for (Point point : points) {
            if (point == null) throw new java.lang.IllegalArgumentException();
        }

        Arrays.sort(points);

        Map maps = new HashMap();

        for (int i = 0; i < points.length; i++) {
            Point current = points[0];
            for (int j = i; j < points.length; j++) {

            }
        }


//        onlyFourPoints(points);
    }

    private void onlyFourPoints(Point[] points) {
        Point p = points[0];
        Point q = points[1];
        Point r = points[2];
        Point s = points[3];

        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
            segments = new LineSegment[]{new LineSegment(p, s)};
        }

        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) != p.slopeTo(s)) {
            segments = new LineSegment[]{new LineSegment(p, r), new LineSegment(r, s)};
        } else if (p.slopeTo(q) != p.slopeTo(r) && q.slopeTo(r) == q.slopeTo(s)) {
            segments = new LineSegment[]{new LineSegment(p, q), new LineSegment(p, s)};
        } else if (p.slopeTo(q) != p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
            segments = new LineSegment[]{new LineSegment(p, s), new LineSegment(p, q)};
        } else {
            segments = new LineSegment[]{new LineSegment(p, q), new LineSegment(q, r), new LineSegment(r, s)};
        }
    }

    public int numberOfSegments()        // the number of line segments
    {
        return (segments != null) ? segments.length : 0;
    }

    public LineSegment[] segments()                // the line segments
    {
        return segments;
    }
}
