import java.util.Arrays;

public class FastCollinearPoints
{

    private LineSegment[] segments = null;

    public FastCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null) throw new java.lang.IllegalArgumentException();

        for (Point point : points)
        {
            if (point == null) throw new java.lang.IllegalArgumentException();
        }

        LineSegment[] tmpSegments = new LineSegment[points.length];
        int segmentsIndex = 0;
        Arrays.sort(points);
        Point[] visitedPoints = new Point[points.length];
        int visitedIndex = 0;

        for (int i = 0; i < points.length; i++)
        {
            Point current = points[i];

            Point[] currentArray = new Point[points.length - i];
            System.arraycopy(points, i, currentArray, 0, currentArray.length);
            Arrays.sort(currentArray, current.slopeOrder());

            int pointCount = 1;

            for (int j = 0; j < currentArray.length - 1; j++)
            {

                if (current.slopeTo(currentArray[j]) == current.slopeTo(currentArray[j + 1]))
                {
                    if (pointCount >= 3)
                    {
                        visitedPoints[visitedIndex++] = currentArray[j];
                    }
                    pointCount++;
                } else if (pointCount >= 3)
                {
                    LineSegment newSegment = new LineSegment(current, currentArray[j]);
                    if (notUsed(current, currentArray[j], visitedPoints))
                    {
                        tmpSegments[segmentsIndex] = newSegment;
                        segmentsIndex++;
                        visitedPoints[visitedIndex++] = current;
                        visitedPoints[visitedIndex++] = currentArray[j];
                    }
                    pointCount = 1;

                } else
                {
                    pointCount = 1;
                }
            }
            if (pointCount >= 3)
            {
                if (notUsed(current, currentArray[currentArray.length - 1], visitedPoints))
                {
                    tmpSegments[segmentsIndex] = new LineSegment(current, currentArray[currentArray.length - 1]);
                    segmentsIndex++;
                    visitedPoints[visitedIndex++] = current;
                    visitedPoints[visitedIndex++] = currentArray[currentArray.length - 1];
                }

            }
        }
        for (Point visitedPoint : visitedPoints)
        {
            if (visitedPoint != null)
                System.out.println(visitedPoint);
        }
        segments = new LineSegment[segmentsIndex];
        System.arraycopy(tmpSegments, 0, segments, 0, segmentsIndex);
    }

    private boolean notUsed(Point start, Point end, Point[] visitedPoints)
    {
        int flag = 0;
        for (Point visitedPoint : visitedPoints)
        {
            if (visitedPoint == start || visitedPoint == end)
            {
                flag++;
                if (flag == 2) return false;
            }
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
