import edu.princeton.cs.algs4.StdDraw;
import org.w3c.dom.ls.LSException;

import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] segments = null;

    public FastCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null) throw new java.lang.IllegalArgumentException();

        for (Point point : points) {
            if (point == null) throw new java.lang.IllegalArgumentException();
        }

        LineSegment[] tmpSegments = new LineSegment[points.length];
        int segmentsIndex = 0;
        Arrays.sort(points);

        for (int i = 0; i < points.length; i++) {
            Point current = points[i];
            Point[] currentArray = Arrays.copyOf(points, points.length);
            Arrays.sort(currentArray, current.slopeOrder());
            int flag = 1;

            for (int j = 0; j < currentArray.length - 1; j++) {
                if (current.slopeTo(currentArray[j]) == current.slopeTo(currentArray[j + 1])) {
                    flag++;
                } else if (flag > 2) {
                    LineSegment newSegment = new LineSegment(current, currentArray[j]);
                    if (notUsed(current, currentArray[j], tmpSegments, segmentsIndex)) {
                        tmpSegments[segmentsIndex] = newSegment;
                        segmentsIndex++;
                    }
                    flag = 1;

                } else {
                    flag = 1;
                }
            }
            if (flag > 2) {
                if (notUsed(current, currentArray[currentArray.length - 1], tmpSegments, segmentsIndex)) {
                    tmpSegments[segmentsIndex] = new LineSegment(current, currentArray[currentArray.length - 1]);
                    segmentsIndex++;
                }
            }
        }
        segments = new LineSegment[segmentsIndex];

        for (int i = 0; i < segmentsIndex; i++) {
            segments[i] = tmpSegments[i];
        }
    }

    private boolean notUsed(Point start, Point end, LineSegment[] tmpSegments, int segmentsIndex) {
        for (int i = 0; i < segmentsIndex; i++) {
            if (tmpSegments[i].isPointsInSegment(start, end))
                return false;
        }
        return true;
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
