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
            Arrays.sort(sortedPoints, points[p].slopeOrder());
            for (int q = 0; q < maxIter - 1; q++)
            {
                int slopesCounter = 1;
                double slope = sortedPoints[q].slopeTo(sortedPoints[q + 1]);
                
                for (int r = q + 2; r < maxIter; r++)
                {
                    if (sortedPoints[q].slopeTo(sortedPoints[r]) == slope)
                        slopesCounter++;
                    else
                    {
                        if (slopesCounter >= 3)
                        {
                            if (lineSegs.length == count)   resize(count * 2);
                            lineSegs[count++] = new LineSegment(
                                         sortedPoints[q], sortedPoints[r - 1]);
                        }
                        q = r;
                        if (r != maxIter - 1)
                            slope = sortedPoints[q].slopeTo(sortedPoints[++r]);
                        slopesCounter = 1;
                    }
                }
            }
        }
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
