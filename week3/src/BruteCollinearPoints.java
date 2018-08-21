import java.util.Arrays;

public class BruteCollinearPoints
{

    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] originalPoints)    // finds all line segments containing 4 points
    {
        if (originalPoints == null) throw new java.lang.IllegalArgumentException();

        Point[] points = Arrays.copyOf(originalPoints, originalPoints.length);

        for (Point point : points)
        {
            if (point == null) throw new java.lang.IllegalArgumentException();
        }

        Arrays.sort(points);

        for (int i = 0; i < points.length - 1; i++)
        {
            if (points[i].compareTo(points[i + 1]) == 0) throw new java.lang.IllegalArgumentException();
        }

        int index = 0;
        LineSegment[] tmpSegments = new LineSegment[points.length];

        for (int i = 0; i < points.length; i++)
        {
            for (int j = i + 1; j < points.length; j++)
            {
                for (int k = j + 1; k < points.length; k++)
                {
                    for (int m = k + 1; m < points.length; m++)
                    {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[m];

                        if (Double.compare(p.slopeTo(q), p.slopeTo(r)) == 0
                                && Double.compare(p.slopeTo(r), p.slopeTo(s)) == 0)
                        {
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
        return Arrays.copyOf(segments, segments.length);
    }
}