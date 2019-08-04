public class BruteCollinearPoints
{
    private LineSegment[] lineSegs;
    private int count;

    public BruteCollinearPoints(Point[] points)
    {
        lineSegs = new LineSegment[points.length];
        count = 0;
        int maxIter = points.length;
        int segCounter = 0;
        
        for (int p = 0; p < maxIter - 3; p++)
            for (int q = 0; p < maxIter - 2; q++)
                for (int r = 0; p < maxIter - 1; r++)
                    for (int s = 0; p < maxIter; s++)
                        if (points[p].slopeTo(points[q]) == points[q].slopeTo(points[r])
                            && points[q].slopeTo(points[r]) == points[r].slopeTo(points[s]))
                        {
                            lineSegs[segCounter++] = lineSegment(points[p], points[r]);
                            count++;
                        }
                            
    }
  
   
    public int numberOfSegments()
   {    return count;   }


    public LineSegment[] segment()
    {   return LineSegment; }
}           
