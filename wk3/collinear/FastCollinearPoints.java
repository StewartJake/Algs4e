import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints
{
    private LineSegment[] lineSegs;
    private int count;
    // private static final Comparator<Point> BY_SLOPE = new slopeOrder();
    
    public FastCollinearPoints(Point[] points)
    {
        if (points == null)
            throw new IllegalArgumentException ("The array is null.");
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++)
            if (points[i] == null || points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException("Illegal point in array");
        lineSegs = new LineSegment[1];
        double[] slopes = new double[points.length - 1];
        int maxIter = points.length;
        count = 0;

        for (int p = 0; p < maxIter; p++)
        {
            for (int q = p + 1; q < maxIter; q++)
                slopes[p] = points[p].slopeTo(points[q]);
            Arrays.sort(slopes);
            Arrays.sort(points, points[p].slopeOrder());
            int slopesCounter = 0;
            double slope = slopes[0];
            for (int r = 1; r < slopes.length; r++)
            {
                if (slopes[r] == slope)
                    slopesCounter++;
                else
                {
                    if (slopesCounter >= 3)
                    {
                        if (lineSegs.length == count)   resize(count * 2);
                        lineSegs[count++] = new LineSegment(points[p], points[p + slopesCounter]);
                        slopesCounter = 0;
                        slope = slopes[r];
                    }
                }
            }
        }
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
