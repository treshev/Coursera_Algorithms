import javax.sound.sampled.Line;
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

        Object[] visitedPoints = new Point[1];
        int visitedIndex = 0;

        Arrays.sort(points);

        Object[] tmpSegments = new LineSegment[2];
        int segmentsIndex = 0;

        for (int i = 0; i < points.length; i++)
        {
            Point current = points[i];

            Point[] currentArray = new Point[points.length - i];
            System.arraycopy(points, i, currentArray, 0, currentArray.length);
            Arrays.sort(currentArray, current.slopeOrder());

            int pointCount = 1;

            Object[] localVisitedPoints = new Point[2];
            int localVisitedIndex = 0;

            for (int j = 0; j < currentArray.length - 1; j++)
            {
                if (current.slopeTo(currentArray[j]) == current.slopeTo(currentArray[j + 1]))
                {
//                    localVisitedPoints[localVisitedIndex++] = currentArray[j];
                    localVisitedPoints = addItem(localVisitedPoints, localVisitedIndex++, currentArray[j]);
                    pointCount++;
                }
                else if (pointCount >= 3)
                {
                    LineSegment newSegment = new LineSegment(current, currentArray[j]);
                    if (notUsed(current, currentArray[j], visitedPoints))
                    {
//                        tmpSegments[segmentsIndex] = newSegment;
                        tmpSegments = addItem(tmpSegments, segmentsIndex, newSegment);
                        segmentsIndex++;

                        visitedPoints = addItem(visitedPoints, visitedIndex++, current);
                        visitedPoints = addItem(visitedPoints, visitedIndex++, currentArray[j]);
//                        visitedPoints[visitedIndex++] = current;
//                        visitedPoints[visitedIndex++] = currentArray[j];
                        for (Object localVisitedPoint : localVisitedPoints)
                        {
                            if (localVisitedPoint != null)
                            {
                                visitedPoints = addItem(visitedPoints, visitedIndex++, localVisitedPoint);
//                                visitedPoints[visitedIndex++] = localVisitedPoint;
                            }
                        }
                    }
                    pointCount = 1;

                }
                else
                {
                    localVisitedPoints = new Point[2];
                    localVisitedIndex = 0;

                    pointCount = 1;
                }
            }
            if (pointCount >= 3)
            {
                if (notUsed(current, currentArray[currentArray.length - 1], visitedPoints))
                {
                    LineSegment lineSegment = new LineSegment(current, currentArray[currentArray.length - 1]);
                    tmpSegments = addItem(tmpSegments, segmentsIndex, lineSegment);
//                    tmpSegments[segmentsIndex] = lineSegment;
                    segmentsIndex++;

//                    visitedPoints[visitedIndex++] = current;
//                    visitedPoints[visitedIndex++] = currentArray[currentArray.length - 1];

                    visitedPoints = addItem(visitedPoints, visitedIndex++, current);
                    visitedPoints = addItem(visitedPoints, visitedIndex++, currentArray[currentArray.length - 1]);

                    for (Object localVisitedPoint : localVisitedPoints)
                    {
                        if (localVisitedPoint != null)
                        {
                            visitedPoints = addItem(visitedPoints, visitedIndex++, localVisitedPoint);
//                            visitedPoints[visitedIndex++] = localVisitedPoint;
                        }

                    }
                }

            }
        }
        for (Object visitedPoint : visitedPoints)
        {
            if (visitedPoint != null)
                System.out.println("Point = " + visitedPoint);
        }
        segments = new LineSegment[segmentsIndex];
        System.arraycopy(tmpSegments, 0, segments, 0, segmentsIndex);
    }

    private <Item> Item[] addItem(Item[] array, int index, Item item)
    {
        if (index == array.length)
        {
            array = resize(array, 2 * array.length);
            array = (Item[]) array;
        }
        array[index] = item;
        return (Item[]) array;
    }

    private <Item> void removeItem(Item[] array, int index, Item item)
    {
        array[index] = null;

        if (index > 2 && index == array.length / 4)
        {
            resize(array, array.length / 2);
        }
    }

    private <Item> Item[] resize(Item[] array, int newSize)
    {
        Item[] newDataSet = (Item[]) new Object[newSize];
        int i = 0;
        int j = 0;
        while (i < newSize && j < array.length)
        {
            if (array[j] != null)
            {
                newDataSet[i] = (Item) array[j];
                i++;
            }
            j++;
        }
        return (Item[]) newDataSet;
    }

    private boolean notUsed(Point start, Point end, Object[] visitedPoints)
    {
        int flag = 0;
        for (Object visitedPoint : visitedPoints)
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
