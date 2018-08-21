import java.util.Arrays;

public class FastCollinearPoints
{

    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null)
        {
            throw new java.lang.IllegalArgumentException();
        }

        for (Point point : points)
        {
            if (point == null) throw new java.lang.IllegalArgumentException();
        }

        Arrays.sort(points);

        for (int i = 0; i < points.length - 1; i++)
        {
            if (points[i].compareTo(points[i + 1]) == 0) throw new java.lang.IllegalArgumentException();
        }

        Object[] visitedPoints = new Object[1];

        Object[] tmpSegments = new Object[2];
        int segmentsIndex = 0;

        for (int i = 0; i < points.length - 3; i++)
        {
            int pointCount = 1;
            Point current = points[i];

            Point[] currentArray = new Point[points.length - i - 1];
            System.arraycopy(points, i + 1, currentArray, 0, currentArray.length);
            Arrays.sort(currentArray, current.slopeOrder());

            Object[] localVisitedPoints = new Object[2];
            int localVisitedIndex = 0;

            for (int j = 0; j < currentArray.length - 1; j++)
            {
                if (new Double(current.slopeTo(currentArray[j])).equals(current.slopeTo(currentArray[j + 1])))
                {
                    localVisitedPoints = addItem(localVisitedPoints, localVisitedIndex++, currentArray[j]);
                    pointCount++;
                }
                else if (pointCount >= 3)
                {
                    localVisitedPoints = addItem(localVisitedPoints, localVisitedIndex++, current);
                    localVisitedPoints = addItem(localVisitedPoints, localVisitedIndex, currentArray[j]);

                    LineSegment newSegment = new LineSegment(current, currentArray[j]);
                    if (notUsed(localVisitedPoints, visitedPoints))
                    {
                        tmpSegments = addItem(tmpSegments, segmentsIndex, newSegment);
                        segmentsIndex++;

                        for (Object localVisitedPoint : localVisitedPoints)
                        {
                            if (localVisitedPoint != null)
                            {
                                visitedPoints = addVisitedPoint(visitedPoints, localVisitedPoint);
                            }
                        }
                    }
                    localVisitedPoints = new Object[2];
                    localVisitedIndex = 0;
                    pointCount = 1;
                }
                else
                {
                    localVisitedPoints = new Object[2];
                    localVisitedIndex = 0;
                    pointCount = 1;
                }
            }
            if (pointCount >= 3)
            {
                localVisitedPoints = addItem(localVisitedPoints, localVisitedIndex++, current);
                localVisitedPoints = addItem(localVisitedPoints, localVisitedIndex, currentArray[currentArray.length - 1]);
                LineSegment lineSegment = new LineSegment(current, currentArray[currentArray.length - 1]);

                if (notUsed(localVisitedPoints, visitedPoints))
                {
                    tmpSegments = addItem(tmpSegments, segmentsIndex, lineSegment);
                    segmentsIndex++;

                    for (Object localVisitedPoint : localVisitedPoints)
                    {
                        if (localVisitedPoint != null)
                        {
                            visitedPoints = addVisitedPoint(visitedPoints, localVisitedPoint);
                        }

                    }
                }
            }
        }
        segments = new LineSegment[segmentsIndex];
        System.arraycopy(tmpSegments, 0, segments, 0, segmentsIndex);
    }

    private Object[] addVisitedPoint(Object[] array, Object item)
    {
        int nail = 0;
        for (Object obj : array)
        {
            if (obj != null)
            {
                Point point = (Point) obj;
                if (point.compareTo((Point) item) == 0)
                    return array;
                else
                {
                    nail++;
                }
            }
        }
        return addItem(array, nail, item);
    }

    private Object[] addItem(Object[] array, int index, Object item)
    {
        if (index == array.length)
        {
            array = resize(array, 2 * array.length);
        }
        array[index] = item;
        return array;
    }

    private Object[] resize(Object[] array, int newSize)
    {
        Object[] newDataSet = new Object[newSize];
        int i = 0;
        int j = 0;
        while (i < newSize && j < array.length)
        {
            if (array[j] != null)
            {
                newDataSet[i] = array[j];
                i++;
            }
            j++;
        }
        return newDataSet;
    }

    private boolean notUsed(Object[] localVisited, Object[] globalVisited)
    {
        for (Object local : localVisited)
        {
            if (localVisited.length > globalVisited.length) return true;

            if (local != null)
            {
                boolean localoPointIsExists = false;
                Point localPoint = (Point) local;

                for (Object global : globalVisited)
                {
                    if (global != null)
                    {
                        Point globalPoint = (Point) global;
                        if (localPoint.compareTo(globalPoint) == 0)
                        {
                            localoPointIsExists = true;
                        }
                    }
                }
                if (!localoPointIsExists)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public int numberOfSegments()
    {
        return (segments != null) ? segments.length : 0;
    }

    public LineSegment[] segments()
    {
        return Arrays.copyOf(segments, segments.length);
    }
}
