import java.util.Arrays;
import java.util.Objects;

public class FastCollinearPoints
{

    private LineSegment[] segments = new LineSegment[2];
    private PointMap[] map = new PointMap[2];
    private int mapIndex = 0;

    private class PointMap
    {
        final Point point;
        final double tang;

        PointMap(Point point, double tang)
        {
            this.point = point;
            this.tang = tang;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            PointMap pointMap = (PointMap) obj;
            return Double.compare(pointMap.tang, tang) == 0 &&
                    Objects.equals(point, pointMap.point);
        }

        @Override
        public int hashCode()
        {
            return point.hashCode();
        }
    }


    public FastCollinearPoints(Point[] originalPoints)    // finds all line segments containing 4 points
    {
        if (originalPoints == null)
        {
            throw new java.lang.IllegalArgumentException();
        }

        Point[] points = Arrays.copyOf(originalPoints, originalPoints.length);

        for (int i = 0; i < points.length; i++)
        {
            if (points[i] == null) throw new java.lang.IllegalArgumentException();
        }

        Arrays.sort(points);

        for (int i = 0; i < points.length - 1; i++)
        {
            if (points[i].compareTo(points[i + 1]) == 0) throw new java.lang.IllegalArgumentException();
        }

        for (int i = 0; i < points.length - 3; i++)
        {
            int pointCount = 1;
            Point current = points[i];

            Point[] currentArray = new Point[points.length - i - 1];
            System.arraycopy(points, i + 1, currentArray, 0, currentArray.length);
            Arrays.sort(currentArray, current.slopeOrder());

            for (int j = 0; j < currentArray.length - 1; j++)
            {
                if (Double.compare(current.slopeTo(currentArray[j]), current.slopeTo(currentArray[j + 1])) == 0)
                {
                    pointCount++;
                }
                else if (pointCount >= 3)
                {
                    addSegment(current, currentArray[j]);
                    pointCount = 1;
                }
                else
                {
                    pointCount = 1;
                }
            }
            if (pointCount >= 3)
            {
                addSegment(current, currentArray[currentArray.length - 1]);
            }
        }
        int index = 0;
        for (LineSegment segment : segments)
        {
            if (segment != null)
            {
                index++;
            }
        }
        LineSegment[] tmp = new LineSegment[index];
        System.arraycopy(segments, 0, tmp, 0, index);
        segments = tmp;
    }

    private void addSegment(Point p1, Point p2)
    {
        double tang = p1.slopeTo(p2);
        PointMap pointMap = new PointMap(p2, tang);

        for (PointMap pm : map)
        {
            if (pm != null && pm.equals(pointMap)) return;
        }

        if (map.length == mapIndex)
        {
            map = resizeMap(map, map.length * 2);
        }
        map[mapIndex++] = pointMap;

        LineSegment lineSegment = new LineSegment(p1, p2);
        segments = addSegment(segments, lineSegment);
    }

    private LineSegment[] addSegment(LineSegment[] array, LineSegment item)
    {
        int tail = 0;

        for (LineSegment lineSegment : array)
        {
            if (lineSegment != null)
            {
                tail++;
            }
        }

        if (tail == array.length)
        {
            array = resize(array, 2 * array.length);
        }
        array[tail] = item;
        return array;
    }

    private LineSegment[] resize(LineSegment[] array, int newSize)
    {
        LineSegment[] newDataSet = new LineSegment[newSize];
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

    private PointMap[] resizeMap(PointMap[] array, int newSize)
    {
        PointMap[] newDataSet = new PointMap[newSize];
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

    public int numberOfSegments()
    {
        return (segments != null) ? segments.length : 0;
    }

    public LineSegment[] segments()
    {
        return Arrays.copyOf(segments, segments.length);
    }
}
