import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] segments = null;

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null) throw new java.lang.IllegalArgumentException();

        for (Point point : points) {
            if (point == null) throw new java.lang.IllegalArgumentException();
        }

        Arrays.sort(points);

        int index = 0;
        LineSegment[] tmpSegments = new LineSegment[points.length];

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];

                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
                            tmpSegments[index] = new LineSegment(p, s);
                            index++;
                        }

                    }
                }
            }
        }
        segments = new LineSegment[index];
        System.arraycopy(tmpSegments, 0, segments, 0, index);
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
