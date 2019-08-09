import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints
{
    private LineSegment[] lineSegs;
    private Point[] sortedPoints;
    private Point[] startPoints;
    private Point[] segBuilder;
    private int startPtCounter;
    private int buildCount;
    private int count;
   

    public FastCollinearPoints(Point[] points)
    {
        if (points == null)
            throw new IllegalArgumentException("The array is null.");
       
       // defensive points copy 
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
        //segBuilder = new Point[1];
        startPoints = new Point[1];
        sortedPoints = new Point[maxIter];
        Arrays.sort(defPoints);
        for (int i = 0; i < maxIter; i++)
            sortedPoints[i] = defPoints[i];

        for (int j = 0; j < maxIter - 1; j++)
            if (sortedPoints[j].compareTo(sortedPoints[j + 1]) == 0)
                throw new IllegalArgumentException("Illegal point in array");
        startPtCounter = 0;
        buildCount = 0;
        count = 0;

        for (int p = 0; p < maxIter; p++)
        {
            Arrays.sort(sortedPoints, defPoints[p].slopeOrder());
            int slopesCounter = 1;
            double slope = sortedPoints[0].slopeTo(sortedPoints[1]);
            Point endPoint = sortedPoints[1];
            segBuilder = new Point[1];
            buildCount = 0;
            segBuilder[0] = sortedPoints[0];

            // sortedPoints[0] == defPoints[p]
            for (int q = 2; q < maxIter; q++)
            {
                if (sortedPoints[0].slopeTo(sortedPoints[q]) == slope)
                {
                    slopesCounter++;
                    if (sortedPoints[q].compareTo(endPoint) > 0)
                        endPoint = sortedPoints[q];
                    if (buildCount == segBuilder.length)
                        resizeBd(segBuilder.length * 2);
                    segBuilder[buildCount++] = sortedPoints[q];
                    // last spot still correct slope case
                    if (q == maxIter - 1 && slopesCounter >= 3 && canAdd(endPoint))
                    {
                        if (lineSegs.length == count)   resize(count * 2);
                        lineSegs[count++] = new LineSegment(
                                        sortedPoints[0], endPoint);
                    }
                }
                else
                {
                    Arrays.sort(segBuilder);
                    if (slopesCounter >= 3 && canAdd(segBuilder[0]))
                    {
                        if (lineSegs.length == count)   resize(count * 2);
                        lineSegs[count++] = new LineSegment(
                                        sortedPoints[0], endPoint);
                        if (startPoints.length == startPtCounter)
                            resizePt(startPtCounter * 2);
                        startPoints[startPtCounter++] = sortedPoints[0];
                        if (startPoints.length == startPtCounter)
                            resizePt(startPtCounter * 2);
                        startPoints[startPtCounter++] = endPoint;
                    }
                    slope = sortedPoints[0].slopeTo(sortedPoints[q]);
                    slopesCounter = 1;
                }
            }
        }
    }
  
    private boolean canAdd(Point pt)
    {
        for (int i = 0; i < startPtCounter - 1; i += 2)
        {
           if (pt.compareTo(startPoints[i]) > 0)
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
    private void resizePt(int capacity)
    {
        Point[] copy = new Point[capacity];
        for (int i = 0; i < startPtCounter; i++)
            copy[i] = startPoints[i];
        startPoints = copy;
    }
    
    
    private void resizeBd(int capacity)
    {
        Point[] copy = new Point[capacity];
        for (int i = 0; i < buildCount; i++)
            copy[i] = segBuilder[i];
        segBuilder = copy;
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
