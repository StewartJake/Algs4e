import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints
{
    private LineSegment[] lineSegs;
    private Point[] sortedPoints;
    private int count;
   

    public FastCollinearPoints(Point[] points)
    {
        if (points == null)
            throw new IllegalArgumentException ("The array is null.");

        int maxIter = points.length;
        lineSegs = new LineSegment[1];
        sortedPoints = new Point[maxIter];

        for (int i = 0; i < maxIter; i++)
            sortedPoints[i] = points[i];

        for (int j = 0; j < maxIter - j; j++)
            if (points[j] == null 
                || sortedPoints[j].compareTo(sortedPoints[j + 1]) == 0)
                throw new IllegalArgumentException("Illegal point in array");

        //double[] slopes = new double[points.length - 1];
        count = 0;

        for (int p = 0; p < maxIter - 2; p++)
        {
            double[] slopes = new double[points.length - 1];
            for (int q = p + 1; q < maxIter; q++)
                slopes[p] = points[p].slopeTo(points[q]);
            Arrays.sort(slopes);
            Arrays.sort(sortedPoints, sortedPoints[p].slopeOrder());
            int slopesCounter = 0;
            double slope = slopes[0];
            for (int r = 0; r < slopes.length; r++)
            {
                if (slopes[r] == slope)
                    slopesCounter++;
                else
                {
                    if (slopesCounter >= 3)
                    {
                        if (lineSegs.length == count)   resize(count * 2);
                        int endpoint = p + slopesCounter >= maxIter ?
                                         maxIter - 1 : p + slopesCounter;
                        lineSegs[count++] = new LineSegment(
                                        sortedPoints[p], sortedPoints[r]);
                    }
                    r = r + slopesCounter >= slopes.length ?
                                 slopes.length - 1 : r + slopesCounter;
                    slope = slopes[r];
                    slopesCounter = 0;
                }
            }
        }
        // remove duplicates

     //   resize(count);
    }
  
    
    private void resize(int capacity)
    {
        LineSegment[] copy = new LineSegment[capacity];
        for (int i = 0; i < count; i++)
            copy[i] = lineSegs[i];
        lineSegs = copy;
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
