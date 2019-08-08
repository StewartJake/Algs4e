import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints
{
    private LineSegment[] lineSegs;
    private Point[] sortedPoints;
    private Point[] startPoints;
    private int startPtCounter;
    private int count;
   

    public FastCollinearPoints(Point[] points)
    {
        if (points == null)
            throw new IllegalArgumentException ("The array is null.");
        
        int maxIter = points.length;
        lineSegs = new LineSegment[1];
        startPoints = new Point[1];
        sortedPoints = new Point[maxIter];
        Arrays.sort(points);
        for (Point pt : points)
           System.out.println(pt); 
        for (int i = 0; i < maxIter; i++)
            sortedPoints[i] = points[i];

        for (int j = 0; j < maxIter - j; j++)
            if (points[j] == null 
                || sortedPoints[j].compareTo(sortedPoints[j + 1]) == 0)
                throw new IllegalArgumentException("Illegal point in array");

        startPtCounter = 0;
        count = 0;

        for (int p = 0; p < maxIter; p++)
        {
            Arrays.sort(sortedPoints, points[p].slopeOrder());
            int slopesCounter = 1;
            double slope = sortedPoints[0].slopeTo(sortedPoints[1]);

            // sortedPoints[0] == points[p]
            for (int q = 2; q < maxIter; q++)
            {
                if (sortedPoints[0].slopeTo(sortedPoints[q]) == slope)
                    slopesCounter++;
                else
                {
                    if (slopesCounter >= 3 && canAdd(sortedPoints[0]))
                    {
                        if (lineSegs.length == count)   resize(count * 2);
                        lineSegs[count++] = new LineSegment(
                                        sortedPoints[0], sortedPoints[q - 1]);
                        if (startPoints.length == startPtCounter)
                            resizePt(startPoints, startPtCounter * 2);
                        startPoints[startPtCounter++] = sortedPoints[0];
                    }
                    slope = sortedPoints[0].slopeTo(sortedPoints[q]);
                    slopesCounter = 1;
                }
            }
        }
    }
  
    private boolean canAdd(Point pt)
    {
        for (Point point : startPoints)
        {
           if (point == pt)
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
    private void resizePt(Point[] a, int capacity)
    {
        Point[] copy = new Point[capacity];
        for (int i = 0; i < a.length; i++)
            copy[i] = a[i];
        a = copy;
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
