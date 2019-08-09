import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints
{
    private LineSegment[] lineSegs;
    private Point[] sortedPoints;
    private double[] usedSlopes;
    private int mCounter;
    private int count;
   

    public FastCollinearPoints(Point[] points)
    {
        if (points == null)
            throw new IllegalArgumentException("The array is null.");
        
        Point[] defPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++)
        {
            if (points[i] == null)
                throw new IllegalArgumentException("Null point in array");
            else
                defPoints[i] = points[i];
        }
        int maxIter = defPoints.length;
        lineSegs = new LineSegment[1];
        usedSlopes = new double[1];
        sortedPoints = new Point[maxIter];
        Arrays.sort(defPoints);
        for (int i = 0; i < maxIter; i++)
            sortedPoints[i] = defPoints[i];

        for (int j = 0; j < maxIter - 1; j++)
            if (sortedPoints[j].compareTo(sortedPoints[j + 1]) == 0)
                throw new IllegalArgumentException("Illegal point in array");
        for (Point pt : defPoints)
            System.out.println(pt);

        mCounter = 0;
        count = 0;

        for (int p = 0; p < maxIter; p++)
        {
            Arrays.sort(sortedPoints, defPoints[p].slopeOrder());
            int slopesCounter = 1;
            double slope = sortedPoints[0].slopeTo(sortedPoints[1]);

            // sortedPoints[0] == defPoints[p]
            for (int q = 2; q < maxIter; q++)
            {
                if (sortedPoints[0].slopeTo(sortedPoints[q]) == slope)
                {
                    slopesCounter++;
                    // last spot still correct slope case
                    if (q == maxIter - 1 && slopesCounter >= 3 && canAdd(slope))
                    {
                        if (lineSegs.length == count)   resize(count * 2);
                        lineSegs[count++] = new LineSegment(
                                        sortedPoints[0], sortedPoints[q]);
                    }
                }
                else
                {
                    if (slopesCounter >= 3 && canAdd(slope))
                    {
                        if (lineSegs.length == count)   resize(count * 2);
                        lineSegs[count++] = new LineSegment(
                                        sortedPoints[0], sortedPoints[q - 1]);
                        if (usedSlopes.length == mCounter)
                            resizeDbl(mCounter * 2);
                        usedSlopes[mCounter++] = slope;
                    }
                    slope = sortedPoints[0].slopeTo(sortedPoints[q]);
                    slopesCounter = 1;
                }
            }
        }
    }
  
    private boolean canAdd(double m)
    {
        for (int i = 0; i < mCounter; i++)
        {
           if (usedSlopes[i] == m)
              return false;
        }
        return true;
    } 
    
    private void resize(int capacity)
    {
        LineSegment[] copy = new LineSegment[capacity];
        for (int i = 0; i < count; i++)
            copy[i] = lineSegs[i];
        lineSegs = copy;
    }
    
    // these two should be merged
    private void resizeDbl(int capacity)
    {
        double[] copy = new double[capacity];
        for (int i = 0; i < mCounter; i++)
            copy[i] = usedSlopes[i];
        usedSlopes = copy;
    }
   

    public int numberOfSegments()
   {    return count;   }


    public LineSegment[] segments()
    {
        LineSegment[] copy = new LineSegment[count];
        for (int i = 0; i < count; i++)
            copy[i] = lineSegs[i];
        return copy;
    }

    public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    System.out.println(collinear.numberOfSegments());
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
    }
}           
