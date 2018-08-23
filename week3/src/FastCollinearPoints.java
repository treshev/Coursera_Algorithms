import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints
{

    private final ArrayList<LineSegment> segments = new ArrayList<>();

    public FastCollinearPoints(Point[] originalPoints)    // finds all line segments containing 4 points
    {
        if (originalPoints == null)
        {
            throw new IllegalArgumentException();
        }

        Point[] points = originalPoints.clone();

        for (Point point : points)
        {
            if (point == null) throw new IllegalArgumentException();
        }

        Arrays.sort(points);

        for (int i = 0; i < points.length - 1; i++)
        {
            if (points[i].compareTo(points[i + 1]) == 0) throw new IllegalArgumentException();
        }

        ArrayList<Point[]> lines = new ArrayList<>();
        for (int i = 0; i < points.length - 3; i++)
        {
            int pointCount = 1;
            Point current = points[i];

            Point[] currentArray = new Point[points.length - i - 1];
            System.arraycopy(points, i + 1, currentArray, 0, currentArray.length);
            Arrays.sort(currentArray, current.slopeOrder());

            double slope = current.slopeTo(currentArray[0]);
            for (int j = 0; j < currentArray.length - 1; j++)
            {
                double slopeNext = current.slopeTo(currentArray[j + 1]);

                if (Double.compare(slope, slopeNext) == 0)
                {
                    pointCount++;
                }
                else if (pointCount >= 3)
                {
                    lines.add(new Point[]{current, currentArray[j]});
                    pointCount = 1;
                }
                else
                {
                    pointCount = 1;
                }
                slope = slopeNext;
            }
            if (pointCount >= 3)
            {
                lines.add(new Point[]{current, currentArray[currentArray.length - 1]});
            }
        }

        lines.sort((o1, o2) -> (Double.compare(o1[0].slopeTo(o1[1]), o2[0].slopeTo(o2[1])) != 0)
                ? Double.compare(o1[0].slopeTo(o1[1]), o2[0].slopeTo(o2[1])) : o1[1].compareTo(o2[1]));

        boolean isFirst = true;
        for (int i = 0; i < lines.size() - 1; i++)
        {
            if (lines.get(i)[1] == lines.get(i + 1)[1] && Double.compare(lines.get(i)[0].slopeTo(lines.get(i)[1]), lines.get(i + 1)[0].slopeTo(lines.get(i + 1)[1])) == 0)
            {
                if (isFirst)
                {
                    segments.add(new LineSegment(lines.get(i)[0], lines.get(i)[1]));
                    isFirst = false;
                }
            }
            else
            {
                if (isFirst)
                {
                    segments.add(new LineSegment(lines.get(i)[0], lines.get(i)[1]));
                }
                else
                {
                    isFirst = true;
                }
            }
        }
        if (isFirst && !lines.isEmpty()) segments.add(new LineSegment(lines.get(lines.size() - 1)[0],
                lines.get(lines.size() - 1)[1]));


    }

    public int numberOfSegments()
    {
        return segments.size();
    }

    public LineSegment[] segments()
    {
        return segments.toArray(new LineSegment[0]);
    }
}
