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
        if (points == null)
            throw new IllegalArgumentException ("The array is null.");
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++)
            if (points[i] == null || points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException("Illegal point in array");
   
        lineSegs = new LineSegment[1];
        count = 0;
        int maxIter = points.length;
        
        for (int p = 0; p < maxIter; p++)
            for (int q = p + 1; q < maxIter; q++)
                for (int r = q + 1; r < maxIter; r++)
                    for (int s = r + 1; s < maxIter; s++)
                        if (points[p].slopeTo(points[q]) == points[q].slopeTo(points[r])
                            && points[q].slopeTo(points[r]) == points[r].slopeTo(points[s]))
                        {
                            if (count == lineSegs.length)   resize(lineSegs.length*2);
                                lineSegs[count++] = new LineSegment(points[p], points[s]);
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
        for (int i = 0; i < count; i ++)
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
