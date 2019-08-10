import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class BruteCollinearPoints
{
    private LineSegment[] lineSegs;
    private int count;

    public BruteCollinearPoints(Point[] points)
    {
        Point[] defPoints = new Point[points.length];
        if (points == null)
            throw new IllegalArgumentException("The array is null.");
        for (int i = 0; i < points.length; i++)
        {
            if (points[i] == null)
                throw new IllegalArgumentException("Null point in array.");
            else
                defPoints[i] = points[i];
        }
        Arrays.sort(defPoints);
        for (int i = 0; i < defPoints.length - 1; i++)
            if (defPoints[i].compareTo(defPoints[i + 1]) == 0)
                throw new IllegalArgumentException("Illegal point in array");
        lineSegs = new LineSegment[1];
        count = 0;
        int maxIter = defPoints.length;
        
        for (int p = 0; p < maxIter; p++)
            for (int q = p + 1; q < maxIter; q++)
                for (int r = q + 1; r < maxIter; r++)
                    for (int s = r + 1; s < maxIter; s++)
                        if (defPoints[p].slopeTo(defPoints[q]) == defPoints[q].slopeTo(defPoints[r])
                            && defPoints[q].slopeTo(defPoints[r]) == defPoints[r].slopeTo(defPoints[s]))
                        {
                            if (count == lineSegs.length)   resize(lineSegs.length*2);
                                lineSegs[count++] = new LineSegment(defPoints[p], defPoints[s]);
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
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
    }
}           
